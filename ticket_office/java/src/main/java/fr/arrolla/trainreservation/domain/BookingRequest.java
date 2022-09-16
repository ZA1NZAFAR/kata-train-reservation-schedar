package fr.arrolla.trainreservation.domain;

public record BookingRequest(String train_id, int seat_count) {
}
