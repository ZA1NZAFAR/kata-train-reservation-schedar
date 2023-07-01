import Booking from "./Booking"
import Train from "./Train"

export default interface TrainRepository {
  getTrain(trainId: string): Promise<Train>
  resetTrain(trainId: string): Promise<void>
  applyBooking(booking: Booking): Promise<void>
}