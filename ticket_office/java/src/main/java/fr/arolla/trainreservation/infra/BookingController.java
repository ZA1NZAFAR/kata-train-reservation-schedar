package fr.arolla.trainreservation.infra;

import fr.arolla.trainreservation.domain.NotEnoughFreeSeatsException;
import fr.arolla.trainreservation.domain.ServiceClient;
import fr.arolla.trainreservation.domain.TicketOffice;
import fr.arolla.trainreservation.domain.Train;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BookingController {
  private final ServiceClient serviceClient;
  private final TicketOffice ticketOffice;

  BookingController(ServiceClient serviceClient) {
    this.serviceClient = serviceClient;
    this.ticketOffice = new TicketOffice(serviceClient);
  }


  @RequestMapping("/reserve")
  ResponseEntity<String> reserve(@RequestBody BookingRequest bookingRequest) {
    var trainId = bookingRequest.train_id();
    var seatCount = bookingRequest.seat_count();

    Train newTrain = null;
    try {
      newTrain = this.ticketOffice.reserve(trainId, seatCount);
    } catch (NotEnoughFreeSeatsException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
    }

    var serializer = new TrainSerializer();
    return ResponseEntity.status(HttpStatus.OK).body(serializer.serialize(newTrain));
  }
}
