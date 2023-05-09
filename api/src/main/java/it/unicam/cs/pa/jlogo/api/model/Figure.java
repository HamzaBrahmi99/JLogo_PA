package it.unicam.cs.pa.jlogo.api.model;

/**
 * This interface represents the figures that can be stored in a Canvas.
 */
public interface Figure {
    /**
     * This method returns the representation of this figure.
     * @return the representation of this figure.
     */
    String getFigure();

    /**
     * This method return the Color of this figure.
     * @return the Color of this figure.
     */
    Color getColor();
}