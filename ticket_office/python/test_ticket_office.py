import json
import httpx


def test_reserve_seats_from_empty_train():
    train_id = "express_2000"

    client = httpx.Client()
    response = client.post(f"http://127.0.0.1:8081/reset/{train_id}")
    response.raise_for_status()

    reservation = client.post(
        "http://127.0.0.1:8083/reserve", data={"train_id": train_id, "seat_count": 4}
    ).json()

    assert reservation["train_id"] == "express_2000"
    assert len(reservation["seats"]) == 4
    assert reservation["seats"] == ["1A", "1B", "2A", "2B"]


def test_reserve_four_additional_seats():
    train_id = "express_2000"

    client = httpx.Client()
    response = client.post(f"http://127.0.0.1:8081/reset/{train_id}")
    response.raise_for_status()

    reservation = client.post(
        "http://127.0.0.1:8083/reserve", data={"train_id": train_id, "seat_count": 4}
    ).json()

    reservation = client.post(
        "http://127.0.0.1:8083/reserve", data={"train_id": train_id, "seat_count": 4}
    ).json()

    assert reservation["train_id"] == "express_2000"
    assert len(reservation["seats"]) == 4
    assert reservation["seats"] == ["3A", "3B", "4A", "4B"]
