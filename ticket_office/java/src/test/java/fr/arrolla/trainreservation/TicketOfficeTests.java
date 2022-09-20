package fr.arrolla.trainreservation;

import fr.arrolla.trainreservation.domain.TicketOffice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketOfficeTests {
  private FakeServiceClient fakeServiceClient;

  @BeforeEach
  void setup() {
    this.fakeServiceClient = new FakeServiceClient();
  }

  @Test
  public void bookingFourSeatsFromEmptyTrain() {
    final String trainID = "express_2000";
    var train = Helpers.makeEmptyTrain();
    fakeServiceClient.setTrain(train);
    var ticketOffice = new TicketOffice(fakeServiceClient);

    var newTrain = ticketOffice.reserve(trainID, 4);

    var seatsWithReservation = newTrain.seats().filter(seat -> !seat.isFree()).toList();
    assertEquals(4, seatsWithReservation.size());
    var seatNumbers = seatsWithReservation.stream().map(seat -> seat.id().toString()).sorted().toList();
    var expected = List.of("1A", "2A", "3A", "4A");
    assertEquals(expected, seatNumbers);
  }

}
