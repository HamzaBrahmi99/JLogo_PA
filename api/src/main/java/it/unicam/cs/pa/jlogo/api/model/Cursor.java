package it.unicam.cs.pa.jlogo.api.model;

/**
 * This interface represents the concept of a cursor. It keeps track of its current position,
 * its current direction and information about the lines and shapes it creates.
 * @param <L> type of Locations.
 */
public interface Cursor<L extends Location<L>> {
    /**
     * Returns the location of the cursor.
     * @return the location of the cursor.
     */
    L getLocation();

    /**
     * Sets the location of the cursor.
     * @param location of the cursor.
     */
    void setLocation(L location);

    /**
     * Returns the direction of the cursor.
     * @return the direction of the cursor.
     */
    Direction getDirection();

    /**
     * Sets the direction of the cursor.
     * @param direction of the cursor.
     */
    void setDirection(Direction direction);

    /**
     * Returns the color of the pen.
     * @return the color of the pen.
     */
    Color getLineColor();

    /**
     * Sets the color of the line.
     * @param lineColor of the line.
     */
    void setLineColor(Color lineColor);

    /**
     * Returns the color of the closed area.
     * @return the color of the closed area.
     */
    Color getAreaColor();

    /**
     * Sets the color of the closed area.
     * @param areaColor of the closed area.
     */
    void setAreaColor(Color areaColor);

    /**
     * Returns if the movement of the cursor will draw a Line.
     * @return if the movement of the cursor will draw a line.
     */
    boolean getPlot();

    /**
     * Sets the plot of the cursor.
     * @param plot of the cursor.
     */
    void setPlot(boolean plot);

    /**
     * Returns the size of the pen.
     * @return the size of the pen.
     */
    int getPenSize();
    /**
     * Sets the size of the pen.
     * @param penSize of the pen.
     */
    void setPenSize(int penSize);

    /**
     * Returns the actual state of this cursor.
     * @return the actual state of this cursor.
     */
    Cursor<L> getActualStateOfCursor();
}
