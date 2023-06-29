package fr.arolla.trainreservation.ticket_office.domain;

import fr.arolla.trainreservation.ticket_office.FakeServiceClient;
import fr.arolla.trainreservation.ticket_office.Helpers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketOfficeTests {
  private final String trainID = "express_2000";
  private Train train;
  private FakeServiceClient fakeServiceClient;
  private TicketOffice ticketOffice;


  @BeforeEach
  void setup() {
    fakeServiceClient = new FakeServiceClient();

    train = Helpers.makeEmptyTrain();
    fakeServiceClient.setTrain(train);

    ticketOffice = new TicketOffice(fakeServiceClient);
  }

  @Test
  public void bookingFourSeatsFromEmptyTrain() {
    var booking = ticketOffice.reserve(trainID, 4);

    assertEquals(booking.seats(), List.of("0A", "1A", "2A", "3A"));
  }

}
