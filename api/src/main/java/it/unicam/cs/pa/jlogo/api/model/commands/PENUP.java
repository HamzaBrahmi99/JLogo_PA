package it.unicam.cs.pa.jlogo.api.model.commands;

import it.unicam.cs.pa.jlogo.api.model.Command;
import it.unicam.cs.pa.jlogo.api.model.Cursor;
import it.unicam.cs.pa.jlogo.api.model.Location;

/**
 * This command detach the pen from the sheet;
 * @param <L> type of Location.
 */
public class PENUP<L extends Location<L>> implements Command {
    private final Cursor<L> cursor;
    private final boolean plot;

    public PENUP(Cursor<L> cursor) {
        this.cursor = cursor;
        this.plot = false;
    }

    @Override
    public void execute() {
        this.cursor.setPlot(this.plot);
    }

    @Override
    public String resultOfCommand() {
        return "The pen is up.";
    }
}