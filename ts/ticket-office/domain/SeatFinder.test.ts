import { expect, test } from '@jest/globals'
import SeatFinder from './SeatFinder'
import { makeEmptyTrain } from '../tests'
import { CoachID, SeatID, SeatNumber } from './Seat'

test('booking four seats from empty train', () => {
  const train = makeEmptyTrain()
  const seatFinder = new SeatFinder(train)
  const actual = seatFinder.findSeats(4).map(s => s.id.toString())
  expect(actual).toEqual(["0A", "1A", "2A", "3A"])
})

test('booking four additional seats', () => {
  const train = makeEmptyTrain()
  for (const id of ["0A", "1A", "2A", "3A"]) {
    const number = id[0]
    const coach = id[1]
    const seatID = new SeatID(new SeatNumber(number), new CoachID(coach))
    train.book(seatID, "old-reference")
  }
  const seatFinder = new SeatFinder(train)
  const actual = seatFinder.findSeats(4).map(s => s.id.toString())
  expect(actual).toEqual(["0A", "1A", "2A", "3A"])
})