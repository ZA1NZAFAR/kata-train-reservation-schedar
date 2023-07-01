import { expect, test } from '@jest/globals'
import ReservationController from '../infra/ReservationController'
import TrainRepository from '../domain/TrainRepository'
import Booking from '../domain/Booking'
import Train from '../domain/Train'
import bookingReferenceSource from '../domain/BookingReferenceSource'
import Seat, { CoachID, SeatID, SeatNumber } from '../domain/Seat'
import { makeEmptyTrain } from './helpers'



class InMemoryRepository implements TrainRepository {
  train: Train

  constructor(train: Train) {
    this.train = train
  }

  getTrain(trainID: string): Promise<Train> {
    return Promise.resolve(this.train)
  }

  applyBooking(booking: Booking): Promise<void> {
    this.train.applyBooking(booking)
    return Promise.resolve()
  }
}

class DummyBookingReferenceSource implements bookingReferenceSource {
  counter: number = 0

  constructor() {
    this.counter = 0
  }

  getNewBookingReference(): Promise<string> {
    this.counter++
    return Promise.resolve(this.counter.toString())
  }

}

const makeController = (): ReservationController => {
  const train = makeEmptyTrain()
  const inMemoryRepository = new InMemoryRepository(train)
  const dummyBookingReferenceSource = new DummyBookingReferenceSource()
  return new ReservationController(dummyBookingReferenceSource, inMemoryRepository)
}

test('booking from empty train', async () => {
  const controller = makeController()
  const request = {
    train_id: "dummy",
    count: 4
  }

  const response = await controller.handle(request)

  const { reference, seats } = response
  expect(reference).toBeTruthy()
  expect(seats).toEqual(["0A", "1A", "2A", "3A"])
})