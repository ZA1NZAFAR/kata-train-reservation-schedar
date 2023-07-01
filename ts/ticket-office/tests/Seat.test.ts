import { expect, test } from '@jest/globals'
import Seat, { SeatId } from '../domain/Seat'

test('toString', () => {
  const id = SeatId.parse("1A")
  const seat = Seat.free(id)
  expect(seat.toString()).toBe('1A')
})

test('seat cannot be booked twice', () => {
  const id = SeatId.parse("1A")
  const seat = Seat.free(id)
  seat.book("abc123")
  expect(() => seat.book("def")).toThrow()
})