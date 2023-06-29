package fr.arolla.trainreservation.ticket_office.domain;

import fr.arolla.trainreservation.ticket_office.DummyBookingReferenceSource;
import fr.arolla.trainreservation.ticket_office.Helpers;
import fr.arolla.trainreservation.ticket_office.InMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingProcessorTests {
  private final String trainID = "express_2000";
  private Train train;
  private InMemoryRepository inMemoryRepository;
  private DummyBookingReferenceSource dummyBookingReferenceSource;
  private BookingProcessor bookingProcessor;

  @BeforeEach
  void setup() {
    inMemoryRepository = new InMemoryRepository();
    dummyBookingReferenceSource = new DummyBookingReferenceSource();

    train = Helpers.makeEmptyTrain();
    inMemoryRepository.setTrain(train);

    bookingProcessor = new BookingProcessor(dummyBookingReferenceSource, inMemoryRepository);
  }

  @Test
  public void bookingFourSeatsFromEmptyTrain() {
    var request = new BookingRequest(trainID, 4);
    var booking = bookingProcessor.processRequest(request);

    assertEquals(booking.seatIDs().stream().map(SeatID::toString).toList(), List.of("0A", "1A", "2A", "3A"));
  }

  @Test
  public void bookingFourAdditionalSeats() {
    var request = new BookingRequest(trainID, 4);
    // First booking:
    bookingProcessor.processRequest(request);

    // Second booking:
    var booking = bookingProcessor.processRequest(request);
    assertEquals(booking.seatIDs().stream().map(SeatID::toString).toList(), List.of("0B", "1B", "2B", "3B"));
  }

}
