from ticket_office.domain.seat_finder import SeatFinder


class BookingService:
    def __init__(self, client):
        self._client = client

    def book(self, train_id, seat_count):
        booking_reference = self._client.get_booking_reference()
        train = self._client.get_train(train_id)
        seat_finder = SeatFinder(train)
        seat_ids = seat_finder.find(seat_count)
        return {"booking_reference": booking_reference, "seats": seat_ids}
