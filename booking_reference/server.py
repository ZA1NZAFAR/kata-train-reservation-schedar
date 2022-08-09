"""
You can get a unique booking reference using this service. For test purposes, you can start a local service using this code. You can assume the real service will behave the same way, but be available on a different url.

Install [Python 3.3](http://python.org) and [CherryPy](http://www.cherrypy.org/), then start the server by running:

    python booking_reference_service.py

You can use this service to get a unique booking reference. Make a GET request to:

    http://localhost:8082/booking_reference

This will return a string that looks a bit like this:

	75bcd15
"""

import cherrypy
import itertools


class BookingReferenceService(object):
    def __init__(self, starting_point):
        self.counter = starting_point

    def last_booking_reference(self):
        return str(hex(self.counter))[2:]

    def booking_reference(self):
        self.counter += 1
        return str(hex(self.counter))[2:]

    booking_reference.exposed = True
    last_booking_reference.exposed = True


def main():
    starting_point = 123456789
    cherrypy.config.update(
        {"server.socket_port": 8082, "server.socket_host": "0.0.0.0"}
    )

    cherrypy.quickstart(BookingReferenceService(starting_point))


if __name__ == "__main__":
    main()
