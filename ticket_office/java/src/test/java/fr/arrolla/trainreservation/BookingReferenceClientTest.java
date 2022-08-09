package fr.arrolla.trainreservation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookingReferenceClientTest {
    @Test
    void canGetSeveralUniqueBookingRefences() {
        BookingReferenceClient client = new BookingReferenceClient();
        String first = client.getNewBookingReference();
        String second = client.getNewBookingReference();
        assertNotEquals(first, second);
    }
}
