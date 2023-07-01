import { expect, test } from '@jest/globals'
import SeatFinder from '../domain/SeatFinder'
import { makeEmptyTrain } from './helpers'
import { CoachId, SeatId, SeatNumber } from '../domain/Seat'

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
    const seatId = new SeatId(new SeatNumber(number), new CoachId(coach))
    train.book(seatId, "old-reference")
  }
  const seatFinder = new SeatFinder(train)
  const actual = seatFinder.findSeats(4).map(s => s.id.toString())
  expect(actual).toEqual(["0A", "1A", "2A", "3A"])
})