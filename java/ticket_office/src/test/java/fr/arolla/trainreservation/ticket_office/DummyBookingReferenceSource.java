package fr.arolla.trainreservation.ticket_office;

import fr.arolla.trainreservation.ticket_office.domain.BookingReferenceSource;

public class DummyBookingReferenceSource implements BookingReferenceSource {
  private int counter = 1;

  @Override
  public String getNewBookingReference() {
    counter++;
    return Integer.toString(counter);
  }
}
