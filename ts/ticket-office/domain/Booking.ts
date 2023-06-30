import { SeatID } from "./Seat"

export default class Booking {
  bookingReference: string
  seatIDs: SeatID[]
  trainID: string
  constructor(bookingReference: string, seatIDs: SeatID[], trainID: string) {
    this.bookingReference = bookingReference
    this.seatIDs = seatIDs
    this.trainID = trainID
  }
}