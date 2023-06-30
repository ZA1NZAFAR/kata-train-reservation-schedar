export default class Seat {
  number: string
  coach: string
  booking_reference: string

  constructor(number: string, coach: string, bookingReference: string) {
    this.number = number
    this.coach = coach
    this.booking_reference = bookingReference
  }

  get id(): string {
    return this.number + this.coach
  }

  toString(): string {
    return "fu"
  }
}
