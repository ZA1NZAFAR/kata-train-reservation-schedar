package fr.arolla.trainreservation.ticket_office.domain;

import fr.arolla.trainreservation.ticket_office.DummyBookingReferenceSource;
import fr.arolla.trainreservation.ticket_office.Helpers;
import fr.arolla.trainreservation.ticket_office.InMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingProcessorTests {
  private final String trainId = "express_2000";
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
  public void bookingTwoSeatsFromEmptyTrain() {
    var request = new BookingRequest(trainId, 2);
    var booking = bookingProcessor.processRequest(request);

    assertEquals(booking.seatIds().stream().map(SeatId::toString).toList(), List.of("0A", "1A"));
  }

  @Test
  public void bookingTwoAdditionalSeats() {
    var request = new BookingRequest(trainId, 2);
    // First booking:
    bookingProcessor.processRequest(request);

    // Second booking:
    var booking = bookingProcessor.processRequest(request);
    assertEquals(booking.seatIds().stream().map(SeatId::toString).toList(), List.of("2A", "3A"));
  }

}
