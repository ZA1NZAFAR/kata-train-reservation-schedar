from tests.domain.conftest import FakeClient
from ticket_office.domain.booking_service import BookingService


def test_booking_four_seats_from_emyt_train(test_train):
    client = FakeClient("abc123", test_train)

    booking_service = BookingService(client)
    response = booking_service.book("express_2000", 4)

    seat_ids = response["seats"]
    assert seat_ids == ["1A", "2A", "3A", "4A"]
    booking_reference = response["booking_reference"]
    assert booking_reference == "abc123"
