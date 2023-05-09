package it.unicam.cs.pa.jlogo.api.model;

import java.util.List;

/**
 * This interface is used to parse the commands from a string.
 * @param <L> type of Location.
 */
public interface CommandParser<L extends Location<L>> {
    /**
     * This method parse the given string with the given command factory and returns the commands.
     * @param string that contains the strings.
     * @param commandFactory that creates the command.
     * @return a list of command read and parsed from the given string.
     */
    List<Command> parseCommandsFromString(String string, CommandFactory commandFactory);
}
