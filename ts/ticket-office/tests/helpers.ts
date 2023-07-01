import Seat, { CoachId, SeatId, SeatNumber } from "../domain/Seat"
import Train from "../domain/Train"

export const makeEmptyTrain = (): Train => {
  const seats = []
  const letters = "ABCDE"
  const numbers = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
  for (const letter of letters) {
    for (const number of numbers) {
      const seatNumber = new SeatNumber(number.toString())
      const coachId = new CoachId(letter)
      const seatId = new SeatId(seatNumber, coachId)
      seats.push(Seat.free(seatId))
    }
  }
  return new Train(seats)
}

export const makeTrainWithBookedSeats = (bookedSeats: string[]): Train => {
  const train = makeEmptyTrain()
  const oldReference = "abc123"
  for (const s of bookedSeats) {
    const seatId = SeatId.parse(s)
    train.book(seatId, oldReference)
  }
  return train
}