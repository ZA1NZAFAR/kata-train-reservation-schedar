import { expect, test } from '@jest/globals'
import Seat, { SeatId } from '../domain/Seat'

test('seat cannot be booked twice', () => {
  const id = SeatId.parse("1A")
  const seat = Seat.free(id)
  seat.book("abc123")
})