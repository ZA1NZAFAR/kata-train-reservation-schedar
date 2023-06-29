package fr.arolla.trainreservation.ticket_office.domain;

import fr.arolla.trainreservation.ticket_office.Helpers;
import fr.arolla.trainreservation.ticket_office.InMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketOfficeTests {
  private final String trainID = "express_2000";
  private Train train;
  private InMemoryRepository fakeServiceClient;
  private TicketOffice ticketOffice;


  @BeforeEach
  void setup() {
    fakeServiceClient = new InMemoryRepository();

    train = Helpers.makeEmptyTrain();
    fakeServiceClient.setTrain(train);

    ticketOffice = new TicketOffice(fakeServiceClient);
  }

  @Test
  public void bookingFourSeatsFromEmptyTrain() {
    var request = new BookingRequest(trainID, 4);
    var booking = ticketOffice.processRequest(request);

    assertEquals(booking.seatIDs().stream().map(SeatID::toString).toList(), List.of("0A", "1A", "2A", "3A"));
  }

}
