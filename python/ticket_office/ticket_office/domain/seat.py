class Seat:
    def __init__(self, *, number, coach, booking_reference):
        self._number = number
        self._coach = coach
        self._booking_reference = booking_reference

    def is_free(self):
        return self._booking_reference is None

    def is_booked(self):
        return self._booking_reference is not None

    @property
    def number(self):
        return self._number

    @property
    def coach(self):
        return self._coach

    @property
    def id(self):
        return f"{self._number}{self._coach}"

    @property
    def booking_reference(self):
        return self._booking_reference

    @classmethod
    def free(cls, *, number, coach):
        return cls(number=number, coach=coach, booking_reference=None)

    @classmethod
    def booked(cls, *, number, coach, booking_reference):
        return cls(number=number, coach=coach, booking_reference=booking_reference)

    def book(self, booking_reference):
        existing_booking_reference = self._booking_reference
        if (
            existing_booking_reference
            and existing_booking_reference != booking_reference
        ):
            raise AlreadyBooked(
                current_booking_reference=existing_booking_reference,
                new_booking_reference=booking_reference,
            )
        self._booking_reference = booking_reference


class AlreadyBooked(Exception):
    def __init__(self, *, current_booking_reference, new_booking_reference):
        self.current_booking_reference = current_booking_reference
        self.new_booking_reference = new_booking_reference
        super().__init__(current_booking_reference, new_booking_reference)

    def __str__(self):
        res = f"Cannot book with {self.current_booking_reference} - "
        res += f"already bookth with {self.current_booking_reference}"
        return res
