package fr.arolla.trainreservation.ticket_office.domain;

public interface BookingReferenceSource {
  /**
   * Get a suitable booking reference, different
   * at each call
   */
  String getNewBookingReference();
}
