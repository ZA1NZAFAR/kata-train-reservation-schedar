import Booking from "./Booking"
import Train from "./Train"

export default interface TrainRepository {
  getTrain(trainId: string): Promise<Train>
  applyBooking(booking: Booking): Promise<void>
}