package it.unicam.cs.pa.jlogo.api.model;

import java.util.Objects;

/**
 * This class represents a default Cursor.
 * @param <L> type of Location.
 */
public class DefaultCursor<L extends Location<L>> implements Cursor<L> {
    private L location;
    private Direction direction;
    private Color lineColor;
    private Color areaColor;
    private boolean plot;
    private int penSize;

    /**
     * Creates a Cursor.
     * @param location of the cursor,
     * @param direction of the cursor,
     * @param lineColor of the cursor,
     * @param areaColor of the cursor,
     * @param plot of the cursor,
     * @param penSize of the cursor.
     */
    public DefaultCursor(L location, Direction direction, Color lineColor, Color areaColor, boolean plot, int penSize) {
        this.location = location;
        this.direction = direction;
        this.lineColor = lineColor;
        this.areaColor = areaColor;
        this.plot = plot;
        checkIfCorrectSize(penSize);
        this.penSize = penSize;
    }

    private void checkIfCorrectSize(int penSize) {
        if(penSize<=0) throw new IllegalArgumentException("Pen size must be >=0...");
    }

    @Override
    public L getLocation() {
        return location;
    }

    @Override
    public void setLocation(L location) {
        this.location = location;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultCursor<?> that = (DefaultCursor<?>) o;
        return plot == that.plot && penSize == that.penSize && Objects.equals(location, that.location) && Objects.equals(direction, that.direction) && Objects.equals(lineColor, that.lineColor) && Objects.equals(areaColor, that.areaColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, direction, lineColor, areaColor, plot, penSize);
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public Color getLineColor() {
        return lineColor;
    }

    @Override
    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    @Override
    public Color getAreaColor() {
        return areaColor;
    }

    @Override
    public void setAreaColor(Color areaColor) {
        this.areaColor = areaColor;
    }

    @Override
    public boolean getPlot() {
        return plot;
    }

    @Override
    public void setPlot(boolean plot) {
        this.plot = plot;
    }

    @Override
    public int getPenSize() {
        return this.penSize;
    }

    @Override
    public void setPenSize(int penSize) {
        checkIfCorrectSize(penSize);
        this.penSize = penSize;
    }

    @Override
    public Cursor<L> getActualStateOfCursor() {
        return new DefaultCursor<>(location, new Direction(direction.getDirection()), new Color(lineColor.getColor()), new Color(areaColor.getColor()), plot, penSize);
    }
}
