package fr.arolla.trainreservation.domain;

public interface ServiceClient {
  /**
   * Return the train matching the ID.
   * The train contains all the seats and their matching
   * booking references if they have one.
   */
  Train getTrain(String trainId);

  /**
   *  Try and apply the given reservation in the train
   *  Return the updated train if there was no booking
   *  conflicts, and throws AlreadyBookedException otherwise
   */
  Train makeReservation(Reservation reservation);

  /**
   * Get a suitable booking reference, different
   * at each call
   */
  String getNewBookingReference();

  /**
   * Reset all booking references for the given train
   * Only use this for testing!
   */
  void reset(String trainId);

}
