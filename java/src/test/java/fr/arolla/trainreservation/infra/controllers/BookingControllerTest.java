package fr.arolla.trainreservation.infra.controllers;

import fr.arolla.trainreservation.FakeRestClient;
import org.junit.jupiter.api.Test;

class BookingControllerTest {
  private final BookingController bookingController;

  BookingControllerTest() {
    var serviceClient = new FakeRestClient();
    bookingController = new BookingController(serviceClient);
  }

  @Test
  void reserve_four_seats_from_empty_train() {
    BookingRequest request = new BookingRequest("expres_2000", 4);
    bookingController.reserve(request);
  }

}