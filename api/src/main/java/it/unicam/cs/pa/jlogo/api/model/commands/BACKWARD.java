package it.unicam.cs.pa.jlogo.api.model.commands;

import it.unicam.cs.pa.jlogo.api.model.*;
/**
 * This command move the cursor backward towards its direction (if the limits of the area are reached, the cursor stops at the edge);
 * @param <L> type of Location.
 */
public class BACKWARD <L extends Location<L>> implements Command {
    private final Command command;
    private final int distance;

    public BACKWARD(Cursor<L> cursor, int distance, Canvas<L> canvas) {
        this.distance = distance;
        this.command = new FORWARD<>(cursor,-distance,canvas);
    }

    @Override
    public void execute() {
        this.command.execute();
    }

    @Override
    public String resultOfCommand() {
        return "Cursor moved backward by "+ distance +".";
    }
}
