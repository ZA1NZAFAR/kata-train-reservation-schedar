package fr.arrolla.trainreservation.domain;

import java.util.List;

public record Reservation(String trainId, String booking_reference, List<String> seats) {
}
