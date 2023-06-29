package fr.arolla.trainreservation.ticket_office.infra;

import fr.arolla.trainreservation.ticket_office.domain.Booking;
import fr.arolla.trainreservation.ticket_office.domain.NotEnoughFreeSeatsException;
import fr.arolla.trainreservation.ticket_office.domain.ServiceClient;
import fr.arolla.trainreservation.ticket_office.domain.TicketOffice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class BookingController {
  private final ServiceClient serviceClient;
  private final TicketOffice ticketOffice;

  BookingController(ServiceClient serviceClient) {
    this.serviceClient = serviceClient;
    this.ticketOffice = new TicketOffice(serviceClient);
  }


  @RequestMapping("/reserve")
  Booking reserve(@RequestBody BookingRequest bookingRequest) {
    var trainId = bookingRequest.train_id();
    var seatCount = bookingRequest.count();

    var booking = ticketOffice.reserve(trainId, seatCount);
    System.out.println("booking = " + booking);
    return booking;
  }

  @ExceptionHandler({NotEnoughFreeSeatsException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public String handleBadRequest(RuntimeException e) {
    return e.toString();
  }
}
