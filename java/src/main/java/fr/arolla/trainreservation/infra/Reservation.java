package fr.arolla.trainreservation.infra;

import java.util.List;

public record Reservation(String trainId, List<String> seats, String bookingReference) {
}
