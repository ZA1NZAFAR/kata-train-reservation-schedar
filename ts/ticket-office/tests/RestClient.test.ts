import { expect, test } from '@jest/globals'
import RestClient from '../infra/RestClient'
import Booking from '../domain/Booking'
import { SeatId } from '../domain/Seat'

test('reset train', async () => {
  const restClient = new RestClient()
  await restClient.resetTrain('express_2000')
})

test('book four seats from empty train', async () => {
  const restClient = new RestClient()
  await restClient.resetTrain('express_2000')

  const seatIds = ["1A", "2A", "3A", "4A"].map(s => SeatId.parse(s))
  const booking = new Booking("abc123", seatIds, "express_2000")
  await restClient.applyBooking(booking)
})