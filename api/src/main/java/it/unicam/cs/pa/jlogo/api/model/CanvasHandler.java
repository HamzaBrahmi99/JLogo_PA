package it.unicam.cs.pa.jlogo.api.model;

/**
 * This interface is used to handle the logic of how to create and store figures into a Canvas.
 * @param <L> type of Location.
 */
public interface CanvasHandler<L extends Location<L>> {
    /**
     * Checks if, after a Line is created, it creates a Figure.
     * @param startingPoint of the line.
     * @param endingPoint of the line.
     * @param lineColor of the line.
     * @param lineSize of the line.
     * @param areaColor of the Figure if is created.
     * @return true if it creates a Figure, false otherwise.
     */
    boolean logicAboutCreationOfFigures(L startingPoint, L endingPoint, Color lineColor, int lineSize, Color areaColor);

    /**
     * Saves the lines that doesn't form a Figure.
     */
    void saveLinesNotContinuous();
}
