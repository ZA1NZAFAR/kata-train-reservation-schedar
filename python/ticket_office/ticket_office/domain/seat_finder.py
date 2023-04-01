class SeatFinder:
    def __init__(self, train):
        self.train = train

    def find(self, seat_count):
        coach_id = self.find_best_coach(seat_count)
        if not coach_id:
            raise NotEnoughFreeSeats()

        # At this point, we know that occupancy after booking is less that
        # 70%, so it's definitely less than 100%, which means
        # the number of free seats is greater than seat_count
        seats_in_coach = self.train.seats_in_coach(coach_id)
        free_seats = [s for s in seats_in_coach if s.is_free()]
        to_book = free_seats[0:seat_count]
        return [s.id for s in to_book]

    def find_best_coach(self, seat_count):
        for coach_id in self.train.coaches:
            occupancy_after_booking = self.train.occupancy_after_booking(
                coach_id, seat_count
            )
            if occupancy_after_booking < 70:
                return coach_id


class NotEnoughFreeSeats(Exception):
    pass
