package fr.arolla.trainreservation.ticket_office.domain;

public interface TrainRepository {
  /**
   * Get a suitable booking reference, different
   * at each call
   */
  String getNewBookingReference();

  /**
   * Return the train matching the ID.
   * The train contains all the seats and their matching
   * booking references if they have one.
   */
  Train getTrain(String trainID);

  /**
   * Reset all booking references for the given train
   * Only use this for testing!
   */
  void resetTrain(String trainID);

  /**
   * Try and apply the given booking for the matching train
   *
   * @throws AlreadyBookedException if there was a booking conflict
   */
  void applyBooking(Booking booking);
}
