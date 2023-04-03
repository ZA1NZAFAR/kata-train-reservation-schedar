from ticket_office.domain.seat_finder import SeatFinder


class BookingService:
    def __init__(self, client):
        self._client = client

    def book(self, train_id, seat_count):
        booking_reference = self._client.get_booking_reference()

        train = self._client.get_train(train_id)

        seat_finder = SeatFinder(train)
        seat_ids = seat_finder.find(seat_count)

        self._client.make_reservation(
            train_id=train_id,
            booking_reference=booking_reference,
            seat_ids=seat_ids,
        )

        return seat_ids
