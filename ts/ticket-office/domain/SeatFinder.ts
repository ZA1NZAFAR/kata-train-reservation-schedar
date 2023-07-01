import Seat from "./Seat"
import Train from "./Train"

export default class SeatFinder {
  train: Train

  constructor(train: Train) {
    this.train = train
  }

  findSeats(count: number): Seat[] {
    const seats = this.train.getSeats()
    const availableSeats = seats.filter(s => s.coach === "A" && s.isFree())
    return availableSeats.slice(0, count)
  }
}