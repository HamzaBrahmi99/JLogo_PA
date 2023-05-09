package it.unicam.cs.pa.jlogo.api.model;

import java.util.Objects;

/**
 * This class represents the concept of Direction. That is between 0 and 360.
 */
public class Direction {
    private final int direction;

    /**
     * Creates a direction.
     * @param direction to create.
     */
    public Direction(int direction) {
        this.direction = direction;
    }

    /**
     * This method returns the direction.
     * @return the direction.
     */
    public int getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direction direction1 = (Direction) o;
        return direction == direction1.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction);
    }

    /**
     * This method adds the given angle to the direction.
     * @param angle to add.
     * @return the new Directions.
     */
    public Direction addDirection(int angle) {
        return new Direction((this.direction + angle)%360);
    }
}
