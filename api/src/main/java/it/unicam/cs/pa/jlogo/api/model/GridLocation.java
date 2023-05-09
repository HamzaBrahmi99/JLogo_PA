package it.unicam.cs.pa.jlogo.api.model;

import java.util.Objects;

/**
 * This class represents a location in a grid space. Each location is identified by a coordinate (x, y).
 */
public class GridLocation implements Location<GridLocation> {
    private final int x;
    private final int y;

    public GridLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public GridLocation nextLocation(Direction direction, int distance) {
        double posX = this.x + Math.cos(Math.toRadians(direction.getDirection() % 360)) * distance;
        double posY = this.y + Math.sin(Math.toRadians(direction.getDirection() % 360)) * distance;
        return new GridLocation((int) Math.round(posX), (int) Math.round(posY));
    }

    @Override
    public GridLocation checkIfReachBorders(GridLocation location, int x, int y) {
        if(location.x > x && location.y > y){
            return new GridLocation(location.x, location.y);
        } else if(location.x > x){
            return new GridLocation(x, location.y);
        } else if (location.y > y) {
            return new GridLocation(location.x, y);
        }
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GridLocation that = (GridLocation) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}