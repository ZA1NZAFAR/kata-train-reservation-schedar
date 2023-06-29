package fr.arolla.trainreservation.ticket_office.infra;

public record ReservationRequest(String train_id, int count) {
}
