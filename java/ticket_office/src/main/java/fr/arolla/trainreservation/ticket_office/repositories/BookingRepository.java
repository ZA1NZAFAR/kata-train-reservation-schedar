package fr.arolla.trainreservation.ticket_office.repositories;

import fr.arolla.trainreservation.ticket_office.entities.Seat;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class BookingRepository {
  ArrayList<Seat> seats = new ArrayList<>();


  public void saveSeat(Seat seat){
    seats.add(seat);
  }

}
