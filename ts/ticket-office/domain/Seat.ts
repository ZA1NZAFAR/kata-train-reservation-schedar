export class SeatNumber {
  value: string

  constructor(value: string) {
    this.value = value
  }
}

export class CoachID {
  value: string

  constructor(value: string) {
    this.value = value
  }
}

export class SeatID {
  number: SeatNumber
  coach: CoachID

  constructor(number: SeatNumber, coach: CoachID) {
    this.number = number
    this.coach = coach
  }

  toString(): string {
    return this.number.value + this.coach.value
  }
}


export default class Seat {
  id: SeatID
  booking_reference: string

  constructor(id: SeatID, bookingReference: string) {
    this.id = id
    this.booking_reference = bookingReference
  }

  get coach(): string {
    return this.id.coach.value
  }

  get number(): string {
    return this.id.number.value
  }
}
