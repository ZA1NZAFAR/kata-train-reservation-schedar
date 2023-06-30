export default class Booking {
  bookingReference: string
  seatIDs: string[]
  trainID: string
  constructor(bookingReference: string, seatIDs: string[], trainID: string) {
    this.bookingReference = bookingReference
    this.seatIDs = seatIDs
    this.trainID = trainID
  }
}