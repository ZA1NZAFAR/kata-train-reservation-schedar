import { expect, test } from '@jest/globals'
import SeatFinder from '../domain/SeatFinder'
import { makeEmptyTrain, makeTrainWithBookedSeats } from './helpers'
import Seat, { CoachId, SeatId, SeatNumber } from '../domain/Seat'
import Train from '../domain/Train'

test('booking four seats from empty train', () => {
  const train = makeEmptyTrain()
  const seatFinder = new SeatFinder(train)

  const found = seatFinder.findSeats(4)

  const foundIds = found.map(s => s.id.toString())
  expect(foundIds).toEqual(["0A", "1A", "2A", "3A"])
  checkSeats(train, found, 4)
})

test('booking four additional seats', () => {
  const train = makeTrainWithBookedSeats(["0A", "1A", "2A", "3A"])
  const seatFinder = new SeatFinder(train)

  const found = seatFinder.findSeats(4)

  const foundIds = found.map(s => s.id.toString())
  expect(foundIds).toEqual(["4A", "5A", "6A", "7A"])
  checkSeats(train, found, 4)
})

test('use second coach if first one is almost full', () => {
  const train = makeTrainWithBookedSeats(["0A", "1A", "2A", "3A"])
  const seatFinder = new SeatFinder(train)

  var found = seatFinder.findSeats(2)

  checkSeats(train, found, 2)
})


const checkSeats = (train: Train, seats: Seat[], expectedCount: number) => {
  expect(seats.length).toBe(expectedCount)

  // Use Train.applyReservation so that we're sure a booking can happen
  for (const seat of seats) {
    train.book(seat.id, "dummy")
  }

  // Check that all seats are in the same coach
  const coaches = new Set(seats.map(s => s.coach))
  expect(coaches.size).toBe(1)


  // Check occupancy of each coach
  train.getCoaches().forEach(coachId => {
    const occupancy = train.occupancyForCoach(coachId)
    expect(occupancy).toBeLessThan(0.7)
  })
}

