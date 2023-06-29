package fr.arolla.trainreservation.ticket_office.domain;

public interface ServiceClient {
  /**
   * Return the train matching the ID.
   * The train contains all the seats and their matching
   * booking references if they have one.
   */
  Train getTrain(String trainID);

  /**
   * Try and apply the given booking in the train
   * Return the updated train if there was no booking
   * conflicts, and throws AlreadyBookedException otherwise
   */
  void applyBooking(Booking booking);

  /**
   * Get a suitable booking reference, different
   * at each call
   */
  String getNewBookingReference();

  /**
   * Reset all booking references for the given train
   * Only use this for testing!
   */
  void resetTrain(String trainID);

}
