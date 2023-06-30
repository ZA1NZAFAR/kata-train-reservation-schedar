import Booking from "./Booking"
import Seat from "./Seat"

export default class Train {
  seats: Seat[]

  constructor(seats: Seat[]) {
    this.seats = seats
  }

  getSeats(): Seat[] {
    return this.seats
  }

  appylBooking(booking: Booking) {
  }

  toString(): string {
    let res = ""
    for (const seat of this.seats) {
      res += seat.id + seat.booking_reference + "\n"
    }
    return res
  }
}