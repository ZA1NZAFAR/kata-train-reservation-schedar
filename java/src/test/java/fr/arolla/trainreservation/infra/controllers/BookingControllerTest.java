package fr.arolla.trainreservation.infra.controllers;

import fr.arolla.trainreservation.FakeRestClient;
import fr.arolla.trainreservation.Helpers;
import fr.arolla.trainreservation.domain.Train;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookingControllerTest {
  private BookingController bookingController;
  private Train train;

  @BeforeEach
  public void setUp() {
    train = Helpers.makeEmptyTrain();
    var serviceClient = new FakeRestClient(train);
    bookingController = new BookingController(serviceClient);
  }

  @Test
  void reserve_four_seats_from_empty_train() {
    BookingRequest request = new BookingRequest("expres_2000", 4);
    bookingController.reserve(request);
  }

  @Test
  void reserve_four_additional_seats() {
    BookingRequest request = new BookingRequest("expres_2000", 4);
    bookingController.reserve(request);

    bookingController.reserve(request);
  }

}