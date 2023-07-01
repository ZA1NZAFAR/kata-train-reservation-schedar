import { expect, test } from '@jest/globals'
import { makeEmptyTrain } from './helpers'
import { SeatId } from '../domain/Seat'


test('booking', () => {
  const train = makeEmptyTrain()
  const seatId = SeatId.parse("0A")
  const seat = train.getSeat(seatId)
  expect(seat.isFree()).toBe(true)

  train.book(seatId, "123")

  expect(seat.isFree()).toBe(false)
  expect(seat.bookingReference).toBe("123")
})