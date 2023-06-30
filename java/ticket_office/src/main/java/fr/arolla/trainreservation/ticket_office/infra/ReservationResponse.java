package fr.arolla.trainreservation.ticket_office.infra;

import java.util.List;

public record ReservationResponse(String reference, String train_id, List<String> seats) {
}