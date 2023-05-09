package it.unicam.cs.pa.jlogo.api.model;

import java.util.LinkedList;
import java.util.List;

/**
 * This interface represents the concept of a drawing area, or a canvas, where the draw created by the program is stored.
 * @param <L>
 */
public interface Canvas<L extends Location<L>> {
    /**
     * Returns the width of the canvas.
     * @return the width of the canvas.
     */
    int getWidth();

    /**
     * Returns the height of the canvas.
     * @return the height of the canvas.
     */
    int getHeight();

    /**
     * Returns the HOME of canvas.
     * @return the HOME of canvas.
     */
    L getHome();

    /**
     * Sets the color of the background of the canvas.
     * @param backgroundColor of the canvas.
     */
    void setBackgroundColor(Color backgroundColor);

    /**
     * Returns the color of the background of the canvas.
     * @return the color of the background of the canvas.
     */
    Color getBackgroundColor();

    /**
     * This method clears the screen.
     */
    void clear();

    /**
     * Returns the figures stored in the canvas.
     * @return the figures stored in the canvas.
     */
    List<Figure> getFigures();

    /**
     * This method returns the actual state of the canvas.
     * @return
     */
    Canvas<L> getActualStateOfCanvas();

    /**
     * This method checks if, after a Line is created, it creates a Figure.
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

    /**
     * Returns the consecutive lines.
     * @return the consecutive lines.
     */
    List<Line<L>> getConsecutiveLines();

    /**
     * Returns the figures stored int the Canvas.
     * @return the figures stored int the Canvas.
     */
    List<Figure> getDrawnFigures();

    /**
     * Sets the consecutive lines.
     * @param consecutiveLines to set.
     */
    void setConsecutiveLines(List<Line<L>> consecutiveLines);
}
