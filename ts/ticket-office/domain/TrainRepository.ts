import Booking from "./Booking"
import Train from "./Train"

export default interface TrainRepository {
  getTrain(trainID: string): Promise<Train>
  applyBooking(booking: Booking): Promise<void>
}