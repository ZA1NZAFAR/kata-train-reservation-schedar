package fr.arrolla.trainreservation;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
public class BookingController {
  private final TrainDataClient trainDataClient;
  private final BookingReferenceClient bookingReferenceClient;

  BookingController() {
    trainDataClient = new TrainDataClient();
    bookingReferenceClient = new BookingReferenceClient();
  }


  @RequestMapping("/reserve")
  String reserve(@RequestBody BookingRequest bookingRequest) {
    var seatCount = bookingRequest.seat_count();
    var trainId = bookingRequest.train_id();


    var bookingReference = bookingReferenceClient.getNewBookingReference();
    var trainData = trainDataClient.getTrainData(trainId);
    var availableSeats = trainData.seats().stream().filter(seat -> seat.bookingReference() == null).toList();
    var seats = new ArrayList<String>();

    for (int i = 0; i < seatCount; i++) {
      var availableSeat = availableSeats.get(i);
      seats.add(availableSeat.number() + availableSeat.coach());
    }

    var reservation = new Reservation(trainId, bookingReference, seats);

    trainDataClient.makeReservation(reservation);

    return "ok";
  }
}
