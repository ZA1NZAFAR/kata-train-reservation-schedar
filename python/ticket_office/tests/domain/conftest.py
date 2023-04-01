import pytest

from ticket_office.domain.client import Client
from ticket_office.domain.seat import Seat
from ticket_office.domain.train import Train


@pytest.fixture
def test_train():
    """
    A Train that contain 5 coaches from A to E,
    and 10 seats by coach
    """
    seats = []
    for number in range(1, 11):
        for coach in "ABCEDE":
            seat = Seat.free(number=number, coach=coach)
            seats.append(seat)
    return Train(seats)


class FakeClient(Client):
    def __init__(self, booking_reference, train):
        self._booking_reference = booking_reference
        self._train = train

    def get_booking_reference(self):
        return self._booking_reference

    def get_train(self, train_id):
        return self._train

    def make_reservation(self, *, train_id, booking_reference, seat_ids):
        self._train.book(booking_reference, seat_ids)
