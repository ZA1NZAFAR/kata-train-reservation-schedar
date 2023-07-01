import Booking from "./Booking"
import Seat, { SeatId } from "./Seat"

export default class Train {

  seats: Map<string, Seat>
  coaches: Set<string>

  constructor(seats: Seat[]) {
    this.seats = new Map()
    this.coaches = new Set()
    for (const seat of seats) {
      this.seats.set(seat.id.value, seat)
      this.coaches.add(seat.coach)
    }
  }

  getCoaches(): string[] {
    return Array.from(this.coaches).sort()
  }

  getSeats(): Seat[] {
    return Array.from(this.seats.values())
  }

  getSeat(id: SeatId) {
    const seat = this.seats.get(id.value)
    if (!seat) {
      throw new Error(`No such seat ${id}`)
    }
    return seat
  }

  applyBooking(booking: Booking) {
    const { seatIds, bookingReference } = booking
    for (const id of seatIds) {
      this.book(id, bookingReference)
    }
  }

  book(id: SeatId, reference: string) {
    const seat = this.getSeat(id)
    seat.book(reference)
  }

  occupancyForCoach(coachId: string): number {
    const seatsInCoach = Array.from(this.seats.values()).filter(s => s.coach === coachId)
    if (seatsInCoach.length === 0) {
      return 0
    }
    const occupiedSeats = seatsInCoach.filter(s => s.isBooked())
    return occupiedSeats.length / seatsInCoach.length
  }

  toString(): string {
    let res = ""
    this.seats.forEach(seat => {
      res += seat.toString() + "\n"
    })
    return res
  }
}