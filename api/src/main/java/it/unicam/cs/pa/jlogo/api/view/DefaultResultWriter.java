package it.unicam.cs.pa.jlogo.api.view;

import it.unicam.cs.pa.jlogo.api.model.Canvas;
import it.unicam.cs.pa.jlogo.api.model.Figure;
import it.unicam.cs.pa.jlogo.api.model.Location;

import java.io.*;
import java.nio.file.Files;
import java.util.stream.Collectors;

/**
 * This class is used to write the result of a LOGO program into a File.
 * @param <L> type of Location.
 */
public class DefaultResultWriter<L extends Location<L>> implements ResultWriter<L>{
    @Override
    public void writeTo(Canvas<L> canvas, File outputFile) throws IOException {
        String stringToWriteIntoTheFile = "SIZE " + canvas.getWidth() + " " + canvas.getHeight() + " " +canvas.getBackgroundColor().toString() + "\n";
        String shapesStoredInTheCanvas = stringToWriteIntoTheFile + canvas.getFigures()
                .stream()
                .map(Figure::getFigure)
                .collect(Collectors.joining());
        Files.write(outputFile.toPath(), shapesStoredInTheCanvas.getBytes());
    }
}
