import Booking from "./Booking"
import Seat, { SeatId } from "./Seat"

export default class Train {

  seats: Map<SeatId, Seat>

  constructor(seats: Seat[]) {
    this.seats = new Map()
    for (const seat of seats) {
      this.seats.set(seat.id, seat)
    }
  }

  getSeats(): Seat[] {
    return Array.from(this.seats.values())
  }

  applyBooking(booking: Booking) {
    const { seatIds, bookingReference } = booking
    for (const id of seatIds) {
      this.book(id, bookingReference)
    }
  }

  book(id: SeatId, reference: string) {
    const seat = this.seats.get(id)
    if (seat) {
      seat.book(reference)
    }
  }


  toString(): string {
    let res = ""
    this.seats.forEach(seat => {
      res += seat.toString() + "\n"
    })
    return res
  }
}