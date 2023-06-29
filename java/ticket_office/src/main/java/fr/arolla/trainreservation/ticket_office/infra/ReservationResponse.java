package fr.arolla.trainreservation.ticket_office.infra;

import java.util.List;

public record ReservationResponse(String reference, String trainID, List<String> seats) {
}
