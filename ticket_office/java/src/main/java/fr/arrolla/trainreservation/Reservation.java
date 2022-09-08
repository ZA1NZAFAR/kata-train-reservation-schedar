package fr.arrolla.trainreservation;

import java.util.List;

public record Reservation(String trainId, String bookingReference, List<String> seats) {
}
