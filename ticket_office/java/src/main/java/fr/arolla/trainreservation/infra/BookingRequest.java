package fr.arolla.trainreservation.infra;

public record BookingRequest(String train_id, int seat_count) {
}
