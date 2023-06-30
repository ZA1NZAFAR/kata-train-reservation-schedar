import Booking from "../domain/Booking"
import BookingReferenceSource from "../domain/BookingReferenceSource"
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
    const seatsInTrain = train.getSeats()
    const inFirstCoach = seatsInTrain.filter(s => s.coach === "A" && s.booking_reference == "")

    const toReserve = inFirstCoach.slice(0, seatCount)
    const seatIds = toReserve.map(s => `${s.number}${s.coach}`)
    const booking = new Booking(bookingReference, seatIds, trainID)

    this.trainRepository.applyBooking(booking)

    const response = {
      reference: bookingReference,
      seats: toReserve.map(s => s.id)
    }
    return Promise.resolve(response)
  }
}