package it.unicam.cs.pa.jlogo.api.view;

import it.unicam.cs.pa.jlogo.api.model.*;

import java.util.List;

/**
 * This class is used to parse the commands from a string.
 * @param <L> type of Location.
 */
public class DefaultCommandParser<L extends Location<L>> implements CommandParser<L> {
    @Override
    public List<Command> parseCommandsFromString(String string, CommandFactory commandFactory) {
        List<String> stringsInTheFile = List.of(string.split("\\s+"));
        return commandFactory.stringsToCommands(stringsInTheFile);
    }
}
