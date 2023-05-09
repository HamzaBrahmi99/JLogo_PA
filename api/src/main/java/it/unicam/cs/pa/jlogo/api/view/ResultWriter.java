package it.unicam.cs.pa.jlogo.api.view;

import it.unicam.cs.pa.jlogo.api.model.Canvas;
import it.unicam.cs.pa.jlogo.api.model.Location;

import java.io.File;
import java.io.IOException;

/**
 * This interface is used to save the result of a LOGO program into a File.
 */
@FunctionalInterface
public interface ResultWriter<L extends Location<L>> {
    /**
     * This method save the state of the given canvas into the given File.
     * @param canvas to save.
     * @param outputFile to write.
     * @throws IOException if an I/O error occurs while writing the data.
     */
    void writeTo(Canvas<L> canvas, File outputFile) throws IOException;
}
