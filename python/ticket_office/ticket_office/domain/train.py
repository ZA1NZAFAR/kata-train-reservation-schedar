class Train:
    def __init__(self, seats):
        self._seats = {}
        self._coaches = set()
        for seat in seats:
            self._seats[seat.id] = seat
            self._coaches.add(seat.coach)

    @property
    def coaches(self):
        return sorted(self._coaches)

    def seats_in_coach(self, coach_id):
        return [s for s in self._seats.values() if s.coach == coach_id]

    def book(self, booking_reference, seat_ids):
        for seat_id in seat_ids:
            seat = self._seats.get(seat_id)
            if not seat:
                raise NoSuchSeat(seat_id)
            seat.book(booking_reference)

    def occupancy_after_booking(self, coach_id, seat_count):
        all_seats = self.seats_in_coach(coach_id)
        booked = [s for s in all_seats if s.is_booked()]
        return (len(booked) + seat_count) / len(all_seats) * 100


class NoSuchSeat(Exception):
    def __init__(self, seat_id):
        self.seat_id = seat_id
        super().__init__(seat_id)

    def __str__(self):
        return f"No such seat: {self.seat_id}"
