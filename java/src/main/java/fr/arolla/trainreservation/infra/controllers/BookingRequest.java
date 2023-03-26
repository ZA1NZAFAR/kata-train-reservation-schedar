package fr.arolla.trainreservation.infra.controllers;

public record BookingRequest(String train_id, int seat_count) {
}
