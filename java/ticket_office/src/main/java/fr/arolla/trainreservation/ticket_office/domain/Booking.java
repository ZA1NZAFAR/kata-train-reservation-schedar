package fr.arolla.trainreservation.ticket_office.domain;

import java.util.List;

/**
 * Represents a BookingRequest being processed.
 * It has the same fields as the BookingRequest, plus a reference
 * that serves as an ID
 */
public record Booking(String reference, String trainID, List<SeatID> seatIDs) {
}
