import Seat, { CoachID, SeatID, SeatNumber } from "./domain/Seat"
import Train from "./domain/Train"

export const makeEmptyTrain = (): Train => {
  const seats = []
  const letters = "ABCDE"
  const numbers = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
  for (const letter of letters) {
    for (const number of numbers) {
      const seatNumber = new SeatNumber(number.toString())
      const coachID = new CoachID(letter)
      const seatID = new SeatID(seatNumber, coachID)
      const seat = new Seat(seatID, "")
      seats.push(seat)
    }
  }
  return new Train(seats)
}