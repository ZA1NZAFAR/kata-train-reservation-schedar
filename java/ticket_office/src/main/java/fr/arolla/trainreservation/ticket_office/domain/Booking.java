package fr.arolla.trainreservation.ticket_office.domain;

import java.util.List;

public record Booking(String trainId, String bookingReference, List<String> seats) {
}
