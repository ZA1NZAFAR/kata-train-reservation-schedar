package fr.arrolla.trainreservation;

import fr.arrolla.trainreservation.domain.CoachID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoachIDTests {

  @Test
  void coachIDsAreValueObjects() {
    var c1 = new CoachID("A");
    var c2 = new CoachID("B");
    var c3 = new CoachID("A");

    assertNotEquals(c1, c2);
    assertEquals(c1, c3);
  }
}
