package fr.arolla.trainreservation.ticket_office.domain;

import java.util.List;

public record Reservation(String trainID, String bookingReference, List<SeatID> seatIDs) {
}
