package fr.arolla.trainreservation.ticket_office.domain;

public record BookingRequest(String trainId, int seatCount) {
}
