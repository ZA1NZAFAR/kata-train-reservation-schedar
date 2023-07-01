export class SeatNumber {
  value: string

  constructor(value: string) {
    this.value = value
  }
}

export class CoachId {
  value: string

  constructor(value: string) {
    this.value = value
  }
}

export class SeatId {
  number: SeatNumber
  coach: CoachId

  constructor(number: SeatNumber, coach: CoachId) {
    this.number = number
    this.coach = coach
  }

  toString(): string {
    return this.number.value + this.coach.value
  }
}


export default class Seat {
  id: SeatId
  booking_reference: string

  constructor(id: SeatId, bookingReference: string) {
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
