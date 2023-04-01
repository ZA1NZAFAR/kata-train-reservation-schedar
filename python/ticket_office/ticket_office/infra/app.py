import json

from flask import Flask, request

from ticket_office.domain.seat_finder import NotEnoughFreeSeats, SeatFinder
from ticket_office.infra.rest_client import RestClient


def do_reserve(request, client):
    payload = request.json
    seat_count = payload["count"]
    train_id = payload["train_id"]

    booking_reference = client.get_booking_reference()
    train = client.get_train(train_id)
    seat_finder = SeatFinder(train)
    try:
        seat_ids = seat_finder.find(seat_count)
    except NotEnoughFreeSeats:
        return 400, "Not enough free seats"

    client.make_reservation(
        train_id=train_id, booking_reference=booking_reference, seat_ids=seat_ids
    )

    return json.dumps({"train_id": train_id, "seats": seat_ids})


def create_app():
    app = Flask("ticket_office")

    client = RestClient()

    @app.post("/reserve")
    def reserve():
        return do_reserve(request, client)

    return app
