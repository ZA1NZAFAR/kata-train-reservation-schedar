package fr.arolla.trainreservation.ticket_office.domain.DTO;

import java.util.List;

public record BookingResponse(String train_id, String booking_reference, List<String> seats) {
}
