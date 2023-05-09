package it.unicam.cs.pa.jlogo.api.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class DefaultCanvas<L extends Location<L>> implements Canvas<L>{
    private final int width;
    private final int height;
    private final CanvasHandler<L> defaultCanvasHandler = new DefaultCanvasHandler<L>(this);
    private  Color backgroundColor;
    private  final L home;
    private List<Figure> drawnFigures;
    private List<Line<L>> consecutiveLines;

    @Override
    public List<Figure> getDrawnFigures() {
        return drawnFigures;
    }
    @Override
    public List<Line<L>> getConsecutiveLines() {
        return consecutiveLines;
    }
    @Override
    public void setConsecutiveLines(List<Line<L>> consecutiveLines) {
        this.consecutiveLines = consecutiveLines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultCanvas<?> that = (DefaultCanvas<?>) o;
        return width == that.width && height == that.height && Objects.equals(defaultCanvasHandler, that.defaultCanvasHandler) && Objects.equals(backgroundColor, that.backgroundColor) && Objects.equals(home, that.home) && Objects.equals(drawnFigures, that.drawnFigures) && Objects.equals(consecutiveLines, that.consecutiveLines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, defaultCanvasHandler, backgroundColor, home, drawnFigures, consecutiveLines);
    }

    /**
     * Creates a DefaultCanvas.
     * @param width of the canvas,
     * @param height of the canvas,
     * @param backgroundColor of the canvas,
     * @param home of the canvas.
     */
    public DefaultCanvas(int width, int height, Color backgroundColor, L home) {
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
        this.home = home;
        drawnFigures = new LinkedList<>();
        consecutiveLines = new LinkedList<>();
    }
    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public L getHome() {
        return home;
    }

    @Override
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @Override
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void clear(){
        this.consecutiveLines = new LinkedList<>();
        this.drawnFigures = new LinkedList<>();
    }

    @Override
    public List<Figure> getFigures() {
        return this.drawnFigures;
    }

    @Override
    public void saveLinesNotContinuous() {
        defaultCanvasHandler.saveLinesNotContinuous();
    }

    @Override
    public boolean logicAboutCreationOfFigures(L startingPoint, L endingPoint, Color lineColor, int lineSize, Color areaColor) {
        return defaultCanvasHandler.logicAboutCreationOfFigures(startingPoint, endingPoint, lineColor, lineSize, areaColor);
    }
    @Override
    public Canvas<L> getActualStateOfCanvas(){
        return new DefaultCanvas<>(width, height, backgroundColor, home,drawnFigures, consecutiveLines);
    }

    /**
     * Another construcor for a DefaultCanvas used to get the state of this DefaultCanvas.
     * @param width of the canvas,
     * @param height of the canvas,
     * @param backgroundColor of the canvas,
     * @param home of the canvas,
     * @param drawnFigures of the canvas,
     * @param consecutiveLines of the canvas.
     */
    private DefaultCanvas(int width, int height, Color backgroundColor, L home, List<Figure> drawnFigures, List<Line<L>> consecutiveLines) {
        this.width = width;
        this.height = height;
        this.backgroundColor = new Color(backgroundColor.getColor());
        this.home = home;
        this.drawnFigures = new LinkedList<>(drawnFigures);
        this.consecutiveLines = new LinkedList<>(consecutiveLines);
    }
}
