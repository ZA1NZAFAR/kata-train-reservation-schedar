package fr.arolla.trainreservation.infra.controllers;

import java.util.List;

public record BookingResponse(String bookingReference, List<String> seats) {
}
