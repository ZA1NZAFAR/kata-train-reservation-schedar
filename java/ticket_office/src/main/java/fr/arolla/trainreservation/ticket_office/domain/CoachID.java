package fr.arolla.trainreservation.ticket_office.domain;

import org.jetbrains.annotations.NotNull;

public record CoachID(String coach) implements Comparable<CoachID> {

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof CoachID other)) {
      return false;
    }

    return other.coach().equals(this.coach);
  }

  @Override
  public int hashCode() {
    return coach.hashCode();
  }

  public String toString() {
    return coach;
  }


  @Override
  public int compareTo(@NotNull CoachID other) {
    return this.coach.compareTo(other.coach());
  }
}
