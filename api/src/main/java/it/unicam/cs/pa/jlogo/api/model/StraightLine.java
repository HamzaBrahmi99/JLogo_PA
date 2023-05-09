package it.unicam.cs.pa.jlogo.api.model;

import java.util.Objects;

/**
 * This class represents the concept of a straight line. It stores the information about the line.
 * @param <L> type of Location.
 */
public class StraightLine<L extends Location<L>> implements Line<L> {
    private final L staringPoint;
    private final L endingPoint;
    private final Color lineColor;
    private final int sizeLine;

    /**
     * Creates a straightLine.
     * @param staringPoint of the straightLine,
     * @param endingPoint of the straightLine,
     * @param lineColor of the straightLine,
     * @param sizeLine of the straightLine.
     */
    public StraightLine(L staringPoint, L endingPoint, Color lineColor, int sizeLine) {
        this.staringPoint = staringPoint;
        this.endingPoint = endingPoint;
        this.lineColor = lineColor;
        this.sizeLine = sizeLine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StraightLine<?> that = (StraightLine<?>) o;
        return sizeLine == that.sizeLine && Objects.equals(staringPoint, that.staringPoint) && Objects.equals(endingPoint, that.endingPoint) && Objects.equals(lineColor, that.lineColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(staringPoint, endingPoint, lineColor, sizeLine);
    }
    @Override
    public L getStaringPoint() {
        return staringPoint;
    }
    @Override
    public L getEndingPoint() {
        return endingPoint;
    }
    @Override
    public String getFigure() {
        return "LINE " + staringPoint.toString() + " " + endingPoint.toString() + " " + lineColor.toString() + " " + this.sizeLine +"\n";
    }

    @Override
    public Color getColor() {
        return lineColor;
    }

    @Override
    public int getSizeLine() {
        return sizeLine;
    }

    @Override
    public String toString() {
        return staringPoint +
                " " + endingPoint +
                " " + lineColor +
                " " + sizeLine +"\n";
    }
}
