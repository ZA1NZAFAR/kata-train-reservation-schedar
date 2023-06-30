import Booking from "../domain/Booking"
import BookingReferenceSource from "../domain/BookingReferenceSource"
import Seat, { CoachID, SeatID, SeatNumber } from "../domain/Seat"
import Train from "../domain/Train"
import TrainRepository from "../domain/TrainRepository"

export default class RestClient implements BookingReferenceSource, TrainRepository {
  async getTrain(trainID: string): Promise<Train> {
    const response = await fetch(`http://localhost:8081/data_for_train/${trainID}`)
    const json = await response.json()
    return parseTrain(json)
  }

  async applyBooking(booking: Booking): Promise<void> {
    const { bookingReference, seatIDs, trainID } = booking
    const reservation = {
      booking_reference: bookingReference,
      seats: seatIDs,
      train_id: trainID,
    }
    const response = await fetch(`http://localhost:8081/reserve`, {
      method: 'POST',
      body: JSON.stringify(reservation),
      headers: { 'Content-Type': 'application/json' }
    })
    const status = response.status
    if (status != 200) {
      // TODO!
    }
  }

  async getNewBookingReference(): Promise<string> {
    const response = await fetch("http://localhost:8082/booking_reference")
    return response.text()
  }

}

type JsonSeat = {
  coach: string,
  seat_number: string,
  booking_reference: string,
}

type JsonTrain = {
  "seats": Map<string, JsonSeat>,
}

const parseTrain = (json: JsonTrain): Train => {
  const seats: Seat[] = []
  const jsonSeats = Object.values(json.seats)
  jsonSeats.forEach(jsonSeat => {
    const { coach, seat_number, booking_reference } = jsonSeat
    const coachID = new CoachID(coach)
    const seatNumber = new SeatNumber(seat_number)
    const seatID = new SeatID(seatNumber, coachID)
    const seat = new Seat(seatID, booking_reference)
    seats.push(seat)
  })
  return new Train(seats)
}