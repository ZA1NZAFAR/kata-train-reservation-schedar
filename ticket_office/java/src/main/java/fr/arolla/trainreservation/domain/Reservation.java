package fr.arolla.trainreservation.domain;

import java.util.List;

public record Reservation(String trainID, String bookingReference, List<SeatID> seats) {
}
