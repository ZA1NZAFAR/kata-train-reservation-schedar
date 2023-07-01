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

  get value(): string {
    return this.number.value + this.coach.value
  }

  static parse(s: string): SeatId {
    const number = new SeatNumber(s[0])
    const coach = new CoachId(s[1])
    return new SeatId(number, coach)
  }
}


export default class Seat {
  id: SeatId
  private _bookingReference: string

  private constructor(id: SeatId, bookingReference: string) {
    this.id = id
    this._bookingReference = bookingReference
  }

  static free(id: SeatId): Seat {
    return new Seat(id, "")
  }

  static booked(id: SeatId, reference: string): Seat {
    return new Seat(id, reference)
  }

  get coach(): string {
    return this.id.coach.value
  }

  get number(): string {
    return this.id.number.value
  }

  book(reference: string) {
    this._bookingReference = reference
  }

  isFree(): boolean {
    return this._bookingReference == ""
  }

  toString(): string {
    return this.id + this._bookingReference
  }

}
