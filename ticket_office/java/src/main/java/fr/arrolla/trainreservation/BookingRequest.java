package fr.arrolla.trainreservation;

public record BookingRequest(String train_id, int seat_count) {
}
