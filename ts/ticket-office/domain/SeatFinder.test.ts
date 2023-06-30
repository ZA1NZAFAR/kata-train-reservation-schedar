import { expect, test } from '@jest/globals'
import SeatFinder from './SeatFinder'
import { makeEmptyTrain } from '../tests'

test('booking four seats from empty train', () => {
  const train = makeEmptyTrain()
  const seatFinder = new SeatFinder(train)
  const actual = seatFinder.findSeats(4).map(s => s.id.toString())
  expect(actual).toEqual(["1A"])
})