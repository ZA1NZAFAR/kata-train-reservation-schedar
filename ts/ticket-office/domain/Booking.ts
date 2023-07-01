import { SeatId } from "./Seat"

export default class Booking {
  bookingReference: string
  seatIds: SeatId[]
  trainId: string
  constructor(bookingReference: string, seatIds: SeatId[], trainId: string) {
    this.bookingReference = bookingReference
    this.seatIds = seatIds
    this.trainId = trainId
  }
}