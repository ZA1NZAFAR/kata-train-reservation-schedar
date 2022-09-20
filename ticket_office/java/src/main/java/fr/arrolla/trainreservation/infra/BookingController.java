package fr.arrolla.trainreservation.infra;

import fr.arrolla.trainreservation.domain.BookingRequest;
import fr.arrolla.trainreservation.domain.ServiceClient;
import fr.arrolla.trainreservation.domain.TicketOffice;
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
  String reserve(@RequestBody BookingRequest bookingRequest) {
    var trainId = bookingRequest.train_id();
    var seatCount = bookingRequest.seat_count();

    var newTrain = this.ticketOffice.reserve(trainId, seatCount);

    var serializer = new TrainSerializer();
    return serializer.serialize(newTrain);
  }
}
