from httpx import Client as HttpClient

from ticket_office.domain.client import Client
from ticket_office.domain.seat import Seat
from ticket_office.domain.train import Train


class ReservationFailed(Exception):
    def __init__(self, status_code, text):
        super().__init__(status_code, text)


class RestClient(Client):
    def __init__(self):
        self._http_client = HttpClient()

    def get_booking_reference(self):
        return self._http_client.get("http://localhost:8082/booking_reference").text

    def get_train(self, train_id):
        train_data = self._http_client.get(
            f"http://localhost:8081/data_for_train/" + train_id
        ).json()
        seats = []
        for json_seat in train_data["seats"].values():
            number = json_seat["seat_number"]
            coach = json_seat["coach"]
            booking_reference = json_seat["booking_reference"]
            if booking_reference:
                seat = Seat.booked(
                    number=number, coach=coach, booking_reference=booking_reference
                )
            else:
                seat = Seat.free(number=number, coach=coach)
            seats.append(seat)

        return Train(seats)

    def make_reservation(self, *, train_id, booking_reference, seat_ids):
        reservation_payload = {
            "train_id": train_id,
            "booking_reference": booking_reference,
            "seats": seat_ids,
        }
        response = self._http_client.post(
            "http://localhost:8081/reserve",
            json=reservation_payload,
        )
        if response.status_code != 200:
            raise ReservationFailed(response.status_code, response.text)
