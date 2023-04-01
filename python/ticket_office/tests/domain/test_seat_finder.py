import pytest

from ticket_office.domain.seat_finder import NotEnoughFreeSeats, SeatFinder


def test_finding_seats_from_empty_train(test_train):
    seat_finder = SeatFinder(test_train)

    seat_ids = seat_finder.find(4)

    assert seat_ids == ["1A", "2A", "3A", "4A"]


def test_finding_four_additional_seats(test_train):
    test_train.book("abc13", ["1A", "2A"])
    seat_finder = SeatFinder(test_train)

    seat_ids = seat_finder.find(4)

    assert seat_ids == ["3A", "4A", "5A", "6A"]


def test_finding_seats_in_next_best_coach(test_train):
    test_train.book("abc13", ["1A", "2A", "3A", "4A", "5A"])
    seat_finder = SeatFinder(test_train)

    seat_ids = seat_finder.find(2)

    assert seat_ids == ["1B", "2B"]


def test_not_enough_room(test_train):
    # fmt: off
    seat_ids = [
        "1A", "2A", "3A", "4A", "5A", "6A",
        "1B", "2B", "3B", "4B", "5B", "6B",
        "1C", "2C", "3C", "4C", "5C", "6C",
        "1D", "2D", "3D", "4D", "5D", "6D",
        "1E", "2E", "3E", "4E", "5E", "6E",
    ]
    # fmt: on
    test_train.book("abc123", seat_ids)
    seat_finder = SeatFinder(test_train)

    with pytest.raises(NotEnoughFreeSeats):
        seat_finder.find(3)
