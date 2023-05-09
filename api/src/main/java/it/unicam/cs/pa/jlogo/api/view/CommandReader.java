package it.unicam.cs.pa.jlogo.api.view;

import it.unicam.cs.pa.jlogo.api.model.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * This interface is used to read commands from a File.
 */
@FunctionalInterface
public interface CommandReader<L extends Location<L>> {
    /**
     * This method reads the content of a File and parse it to a list of Command.
     * @param file to read;
     * @return a list of Command;
     * @throws IOException if an I/O error occurs when reading from the file.
     */
    List<Command> parse(File file, CommandParser<L> defaultCommandParser, CommandFactory commandFactory) throws IOException;
}
