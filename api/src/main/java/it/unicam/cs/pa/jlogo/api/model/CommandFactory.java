package it.unicam.cs.pa.jlogo.api.model;

import java.util.List;

/**
 * This interface is used to create commands from a string.
 */
public interface CommandFactory {
    /**
     * Returns a list of Commands from a given list of strings.
     * @param stringsFromFile to read,
     * @return a list of commands.
     */
    List<Command> stringsToCommands(List<String> stringsFromFile);
}
