import pytest

from ticket_office.domain.seat import AlreadyBooked, Seat


def test_can_create_empty_seat():
    seat = Seat.free(number=1, coach="A")
    assert seat.is_free()
    assert seat.number == 1
    assert seat.coach == "A"
    assert seat.booking_reference is None
    assert seat.id == "1A"


def test_seat_attributes_are_read_only():
    seat = Seat.free(number=1, coach="A")

    with pytest.raises(AttributeError):
        seat.number = 2
    with pytest.raises(AttributeError):
        seat.coach = "B"
    with pytest.raises(AttributeError):
        seat.booking_reference = "123abc"


def test_can_book_free_seat():
    seat = Seat.free(number=1, coach="A")
    seat.book("abc123")

    assert seat.booking_reference == "abc123"
    assert not seat.is_free()
    assert seat.is_booked()


def test_can_create_booked_seat():
    seat = Seat.booked(number=1, coach="A", booking_reference="abc123")
    assert seat.is_booked()
    assert seat.booking_reference == "abc123"


def test_cannot_book_seat_twice():
    seat = Seat.booked(number=1, coach="A", booking_reference="abc123")

    with pytest.raises(AlreadyBooked):
        seat.book("def4566")
