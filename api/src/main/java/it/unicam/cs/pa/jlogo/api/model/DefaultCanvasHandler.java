package it.unicam.cs.pa.jlogo.api.model;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

/**
 * This class is a default implementation of a CanvasHandler. Is used to check if 3 or more lines are adjacent and consecutive, then creates a Figure, in this case a Polygon.
 * @param <L> type of Location.
 */
public class DefaultCanvasHandler<L extends Location<L>> implements CanvasHandler<L> {
    private final Canvas<L> canvas;

    /**
     * Creates a DefaultCanvasHandler for a canvas.
     * @param canvas to handle.
     */
    public DefaultCanvasHandler(Canvas<L> canvas) {
        this.canvas = canvas;
    }
    @Override
    public void saveLinesNotContinuous() {
        if (!canvas.getConsecutiveLines().isEmpty()) {
            canvas.getDrawnFigures().addAll(canvas.getConsecutiveLines());
            canvas.setConsecutiveLines(new LinkedList<Line<L>>());
        }
    }

    @Override
    public boolean logicAboutCreationOfFigures(L startingPoint, L endingPoint, Color lineColor, int lineSize, Color areaColor) {
        Line<L> line = new StraightLine<>(startingPoint,endingPoint,lineColor,lineSize);
        storeConsecutiveLine(line);
        if (checkIfCreateAFigure().test(line)) {
            drawPolygon(canvas.getDrawnFigures(), areaColor);
            return true;
        } else return false;
    }

    /**
     * Predicate that checks if a Figure is created.
     * @return if a Figure is created.
     */
    private Predicate<Line<L>> checkIfCreateAFigure() {
        return l -> canvas.getConsecutiveLines().size() >= 3 && l.getEndingPoint().equals(canvas.getConsecutiveLines().get(0).getStaringPoint());
    }

    /**
     * Saves a consecutive line.
     * @param line to store.
     */
    private void storeConsecutiveLine(Line<L> line) {
        canvas.getConsecutiveLines().add(line);
    }

    /**
     * This method creates a Figure, in this case a Polygon.
     * @param elements to add to the Figures of the Canvas.
     * @param areaColor of the created Figure.
     */
    private  void drawPolygon(List<? super Polygon<L>> elements, Color areaColor) {
        elements.add(new Polygon<L>(canvas.getConsecutiveLines(), areaColor));
        canvas.setConsecutiveLines(new LinkedList<Line<L>>());
    }
}