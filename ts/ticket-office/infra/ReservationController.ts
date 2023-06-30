import Booking from "../domain/Booking"
import BookingReferenceSource from "../domain/BookingReferenceSource"
import SeatFinder from "../domain/SeatFinder"
import TrainRepository from "../domain/TrainRepository"

type ReservationRequest = {
  train_id: string,
  count: number,
}

type ReservationResponse = {
  reference: string,
  seats: string[]
}

export default class ReservationController {
  bookingReferenceSource: BookingReferenceSource
  trainRepository: TrainRepository

  constructor(bookingReferenceSource: BookingReferenceSource, trainRepository: TrainRepository) {
    this.bookingReferenceSource = bookingReferenceSource
    this.trainRepository = trainRepository
  }

  async handle(request: ReservationRequest): Promise<ReservationResponse> {
    const trainID = request.train_id
    const seatCount = request.count

    const bookingReference = await this.bookingReferenceSource.getNewBookingReference()

    const train = await this.trainRepository.getTrain(trainID)
    const seatFinder = new SeatFinder(train)
    const seats = seatFinder.findSeats(seatCount)
    const seatIds = seats.map(s => s.id)
    const booking = new Booking(bookingReference, seatIds, trainID)

    this.trainRepository.applyBooking(booking)

    const response = {
      reference: bookingReference,
      seats: seats.map(s => s.id.toString())
    }
    return Promise.resolve(response)
  }
}