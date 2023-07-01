package fr.arolla.trainreservation.ticket_office.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CoachIdTests {

  @Test
  void coachIdsAreValueObjects() {
    var c1 = new CoachId("A");
    var c2 = new CoachId("B");
    var c3 = new CoachId("A");

    assertNotEquals(c1, c2);
    assertEquals(c1, c3);
  }
}
