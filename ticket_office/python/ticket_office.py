from httpx import Client
import json


class TicketOffice(object):
    def __init__(self):
        self.client = Client()

    def reserve(self, train_id, seat_count):
        seat_count = int(seat_count)
        train_data = self.client.get(
            f"http://localhost:8081/data_for_train/" + train_id
        ).json()
        available_seats = (
            s for s in train_data["seats"].values() if s["booking_reference"] == ""
        )
        to_reserve = []
        for i in range(seat_count):
            to_reserve.append(next(available_seats))
        booking_reference = self.client.get(
            "http://localhost:8082/booking_reference"
        ).text

        seat_ids = [s["seat_number"] + s["coach"] for s in to_reserve]
        reservation = {
            "train_id": train_id,
            "booking_reference": booking_reference,
            "seats": seat_ids,
        }

        reservation_payload = {
            "train_id": reservation["train_id"],
            "seats": reservation["seats"],
            "booking_reference": reservation["booking_reference"],
        }

        response = self.client.post(
            "http://localhost:8081/reserve",
            json=reservation_payload,
        )
        response = response.json()

        assert response.get("seats")

        return json.dumps(reservation)


if __name__ == "__main__":
    """Deploy this class as a web service using CherryPy"""
    import cherrypy

    TicketOffice.reserve.exposed = True
    cherrypy.config.update({"server.socket_port": 8083})
    cherrypy.quickstart(TicketOffice())
