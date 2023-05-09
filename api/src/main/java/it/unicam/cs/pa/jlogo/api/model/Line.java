package it.unicam.cs.pa.jlogo.api.model;

/**
 * This interface represents a Line.
 * @param <L> type of Location.
 */
public interface Line<L extends Location<L>> extends Figure{
    /**
     * Returns the starting point of the line.
     * @return the starting point of the line.
     */
    L getStaringPoint();

    /**
     * Returns the starting point of the line.
     * @return the starting point of the line.
     */
    L getEndingPoint();

    /**
     * Returns the size of the line.
     * @return the size of the line.
     */
    int getSizeLine();
}