package it.unicam.cs.pa.jlogo.api.model.commands;

import it.unicam.cs.pa.jlogo.api.model.Canvas;
import it.unicam.cs.pa.jlogo.api.model.Command;
import it.unicam.cs.pa.jlogo.api.model.Cursor;
import it.unicam.cs.pa.jlogo.api.model.Location;

/**
 * This command attach the pen to the paper;
 * @param <L> type of Location.
 */
public class PENDOWN<L extends Location<L>> implements Command {
    private final Cursor<L> cursor;
    private final boolean plot;

    public PENDOWN(Cursor<L> cursor) {
        this.cursor = cursor;
        this.plot = true;
    }

    @Override
    public void execute() {
        this.cursor.setPlot(this.plot);
    }

    @Override
    public String resultOfCommand() {
        return "The pen is down.";
    }
}
