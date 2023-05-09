package it.unicam.cs.pa.jlogo.api.model;

/**
 * This interface represents the concept of a location in the space. Is used to get the next
 * location in a direction and distance.
 * @param <L>
 */
public interface Location<L> {
    /**
     * Returns the next location in that direction
     * @param direction where is the next location
     * @param distance to move.
     * @return the new Location after the movement.
     */
    L nextLocation(Direction direction, int distance);

    /**
     * This method checks if the location ha reached the given borders.
     * If the area limits are reached, the location stops at the edge.
     * @param location to check.
     * @param x to check.
     * @param y to check.
     */
    L checkIfReachBorders(L location, int x, int y);
}