package fr.arrolla.trainreservation;

import java.util.List;

public record Reservation(String trainId, String booking_reference, List<String> seats) {
}
