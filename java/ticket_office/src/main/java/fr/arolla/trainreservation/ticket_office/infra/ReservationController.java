package fr.arolla.trainreservation.ticket_office.infra;

import fr.arolla.trainreservation.ticket_office.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class ReservationController {
  private final TrainRepository trainRepository;
  private final TicketOffice ticketOffice;

  ReservationController(TrainRepository trainRepository) {
    this.trainRepository = trainRepository;
    this.ticketOffice = new TicketOffice(trainRepository);
  }


  @RequestMapping("/reserve")
  ReservationResponse reserve(@RequestBody ReservationRequest request) {
    var bookingRequest = new BookingRequest(
      request.train_id(),
      request.count()
    );

    var booking = ticketOffice.processRequest(bookingRequest);

    return new ReservationResponse(
      booking.reference(),
      booking.trainID(),
      booking.seatIDs().stream().map(SeatID::toString).toList()
    );
  }

  @ExceptionHandler({NotEnoughFreeSeatsException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public String handleBadRequest(RuntimeException e) {
    return e.toString();
  }
}
