package it.unicam.cs.pa.jlogo.api.model;

import java.util.List;
import java.util.Objects;

/**
 * This interface represents the concept of the color.
 */
public class Color {
    private final int r;
    private final int g;
    private final int b;

    /**
     * Creates a Color by the given 3 Integers.
     * @param r the red,
     * @param g the green,
     * @param b the blu.
     */
    public Color(int r, int g, int b) {
        checkIfCorrect(r,b,g);
        this.r = r;
        this.g = g;
        this.b = b;
    }

    private void checkIfCorrect(int r, int b, int g) {
        if(!(0<=r && r<=255 && 0<=g && g<=255 && 0<=b && b<=255)){
            throw new IllegalArgumentException("Color not valid...");
        }
    }

    /**
     * Creates a Color by the given list of Integer.
     * @param color the color.
     */
    public Color(List<Integer> color) {
        checkIfCorrect(color.get(0),color.get(1),color.get(2));
        this.r = color.get(0);
        this.b = color.get(1);
        this.g = color.get(2);
    }

    /**
     * Returns the color.
     * @return the color.
     */
    public List<Integer> getColor() {
        return List.of(r,g,b);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Color color = (Color) o;
        return r == color.r && g == color.g && b == color.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, g, b);
    }

    @Override
    public String toString() {
        return r +
                " " + g +
                " " + b;
    }
}