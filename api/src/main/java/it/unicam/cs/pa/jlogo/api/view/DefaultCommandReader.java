package it.unicam.cs.pa.jlogo.api.view;

import it.unicam.cs.pa.jlogo.api.model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is used to read commands from a File.
 */
public class DefaultCommandReader<L extends Location<L>> implements CommandReader<L> {
    public List<Command> parse(File file, CommandParser<L> defaultCommandParser, CommandFactory commandFactory) throws IOException {
        return  new LinkedList<>(defaultCommandParser.parseCommandsFromString(readFromFile(file), commandFactory));
    }

    /**
     * Reads all content from the given File and save it into a String.
     * @param file to read.
     * @return all the content from the file.
     * @throws IOException if an I/O error occurs when reading from the file.
     */
    private String readFromFile(File file) throws IOException {
        return Files.readString(file.toPath());
    }

}
