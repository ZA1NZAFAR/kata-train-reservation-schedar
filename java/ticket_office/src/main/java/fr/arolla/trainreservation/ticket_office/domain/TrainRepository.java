package fr.arolla.trainreservation.ticket_office.domain;

public interface TrainRepository {
  /**
   * Return the train matching the Id.
   * The train contains all the seats and their matching
   * booking references if they have one.
   */
  Train getTrain(String trainId);

  /**
   * Reset all booking references for the given train
   * Only use this for testing!
   */
  void resetTrain(String trainId);

  /**
   * Try and apply the given booking for the matching train
   *
   * @throws AlreadyBookedException if there was a booking conflict
   */
  void applyBooking(Booking booking);
}
