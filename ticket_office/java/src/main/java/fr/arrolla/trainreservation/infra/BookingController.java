package fr.arrolla.trainreservation.infra;

import fr.arrolla.trainreservation.domain.BookingRequest;
import fr.arrolla.trainreservation.domain.Reservation;
import fr.arrolla.trainreservation.domain.ServiceClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
public class BookingController {
  private final ServiceClient serviceClient;

  BookingController(ServiceClient serviceClient) {
    this.serviceClient = serviceClient;
  }


  @RequestMapping("/reserve")
  String reserve(@RequestBody BookingRequest bookingRequest) {
    var seatCount = bookingRequest.seat_count();
    var trainId = bookingRequest.train_id();


    var bookingReference = serviceClient.getNewBookingReference();
    var trainData = serviceClient.getTrainData(trainId);
    var inFirstCoach = trainData.seats().stream().filter(seat -> seat.coach().toString().equals("A"));
    var availableSeats = inFirstCoach.filter(seat -> seat.bookingReference() == null).toList();

    var seats = new ArrayList<String>();
    for (int i = 0; i < seatCount; i++) {
      var availableSeat = availableSeats.get(i);
      seats.add(availableSeat.id().toString());
    }

    var reservation = new Reservation(trainId, bookingReference, seats);

    var updatedTrainData = serviceClient.makeReservation(reservation);
    var serializer = new TrainDataSerializer();
    return serializer.serialize(updatedTrainData);
  }
}
