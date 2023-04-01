import pytest

from ticket_office.domain.train import NoSuchSeat


def test_booking_seats(test_train):
    test_train.book("abc123", ["1A", "2A", "3A"])
    for seat_id in ["1A", "2A", "3A"]:
        assert test_train._seats[seat_id].is_booked()


def test_raise_no_such_seat_when_seat_id_not_foun(test_train):
    with pytest.raises(NoSuchSeat):
        test_train.book("abc123", "no-such")


def test_get_seats_in_coach(test_train):
    seat_in_coach_a = test_train.seats_in_coach("A")
    assert [s.id for s in seat_in_coach_a] == [
        "1A",
        "2A",
        "3A",
        "4A",
        "5A",
        "6A",
        "7A",
        "8A",
        "9A",
        "10A",
    ]


def test_get_coaches(test_train):
    assert test_train.coaches == ["A", "B", "C", "D", "E"]


def test_get_occupancy_after_booking(test_train):
    test_train.book("abc123", ["1A", "2A", "3A"])
    assert test_train.occupancy_after_booking("A", 2) == 50
