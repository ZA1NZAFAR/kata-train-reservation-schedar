import Booking from "./Booking"
import Seat, { SeatID } from "./Seat"

export default class Train {

  seats: Map<SeatID, Seat>

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
    const { seatIDs, bookingReference } = booking;
    for (const id of seatIDs) {
      this.book(id, bookingReference)
    }
  }

  book(id: SeatID, reference: string) {
    const seat = this.seats.get(id)
    if (seat) {
      seat.booking_reference = reference
    }
  }


  toString(): string {
    let res = ""
    this.seats.forEach(seat => {
      res += seat.id + seat.booking_reference + "\n"
    });
    return res
  }
}