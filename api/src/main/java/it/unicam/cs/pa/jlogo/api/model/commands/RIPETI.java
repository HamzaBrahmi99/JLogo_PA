package it.unicam.cs.pa.jlogo.api.model.commands;

import it.unicam.cs.pa.jlogo.api.model.Command;
import it.unicam.cs.pa.jlogo.api.model.Location;

import java.util.List;

/**
 * This command repeats the sequence of commands present in the command list. The list must have this format: [ cmd1 params cmd2 params ... ].
 * Must have the space between the brackets and the elements.
 * @param <L> type of Location.
 */
public class RIPETI<L extends Location<L>> implements Command {
    private final List<Command> commandsToExecute;
    private final int n;
    private String results = "RIPETI: \n";
    public RIPETI(int n, List<Command> commandsToExecute) {
        this.commandsToExecute = commandsToExecute;
        this.n = n;
        checkIfnIsCorrect(n);
        checkIfListIsNotEmpty(commandsToExecute);
    }

    private void checkIfListIsNotEmpty(List<Command> commandsToExecute) {
        if (commandsToExecute.isEmpty())  throw new IllegalArgumentException("Can't repeat an empty list of commands...");
    }

    private void checkIfnIsCorrect(int n) {
        if (n<1) throw new IllegalArgumentException("Can't repeat commands for a negative number...");
    }

    @Override
    public void execute() {
        for (int i = 0; i < n; i++) {
            commandsToExecute.forEach(cmd -> {
                cmd.execute();
                this.results = results + cmd.resultOfCommand() + "\n";
            });
        }
    }

    @Override
    public String resultOfCommand() {
        return this.results;
    }
}
