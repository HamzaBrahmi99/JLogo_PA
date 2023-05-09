package it.unicam.cs.pa.jlogo.api.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class represents a Polygon. A Polygon is a plane figure that is described by a finite number of straight line segments
 * connected to form a closed perimeter. In general is formed by 3 or more segments.
 * @param <L> type of Location.
 */
public class Polygon<L extends Location<L>> implements Figure {
    private final int numberOfLines;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polygon<?> polygon = (Polygon<?>) o;
        return numberOfLines == polygon.numberOfLines && Objects.equals(perimeter, polygon.perimeter) && Objects.equals(areaColor, polygon.areaColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfLines, perimeter, areaColor);
    }

    private final List<Line<L>> perimeter;
    private final Color areaColor;

    /**
     * Creates a Polygon.
     * @param perimeter of the polygon,
     * @param areaColor of the polygon.
     */
    public Polygon(List<Line<L>> perimeter, Color areaColor){
        this.perimeter = perimeter;
        this.numberOfLines = perimeter.size();
        this.areaColor = areaColor;
    }

    public String getFigure() {
        return "POLYGON " + numberOfLines + " " + areaColor.toString() + "\n" +
                this.perimeter
                        .stream()
                        .map(Line<L>::getFigure)
                        .toList() +"\n";
    }

    @Override
    public String toString() {
        return "POLYGON " + numberOfLines + " " + areaColor.toString() + "\n" +
                this.perimeter.stream()
                        .map(Figure::toString)
                        .toList() +"\n";
    }

    @Override
    public Color getColor() {
        return areaColor;
    }
}
