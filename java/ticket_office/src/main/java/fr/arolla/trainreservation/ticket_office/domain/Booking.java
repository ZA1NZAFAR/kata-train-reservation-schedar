package fr.arolla.trainreservation.ticket_office.domain;

import java.util.List;

/**
 * Represents a BookingRequest being processed.
 * It has the same fields as the BookingRequest, plus a reference
 * that serves as an Id
 */
public record Booking(String reference, String trainId, List<SeatId> seatIds) {
}
