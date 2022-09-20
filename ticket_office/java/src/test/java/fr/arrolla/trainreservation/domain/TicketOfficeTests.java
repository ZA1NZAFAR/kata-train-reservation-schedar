package fr.arrolla.trainreservation.domain;

import fr.arrolla.trainreservation.FakeServiceClient;
import fr.arrolla.trainreservation.Helpers;
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
    var newTrain = ticketOffice.reserve(trainID, 4);

    checkReservation(newTrain);
  }

  private static void checkReservation(Train train) {
    var seatsWithReservation = train.seats().filter(seat -> seat.isBooked()).toList();
    var seatNumbers = seatsWithReservation.stream().map(seat -> seat.id().toString()).sorted().toList();
    var expected = List.of("0A", "1A", "2A", "3A");
    assertEquals(expected, seatNumbers);
  }

}
