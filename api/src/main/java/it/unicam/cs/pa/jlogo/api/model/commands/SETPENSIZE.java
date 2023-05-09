package it.unicam.cs.pa.jlogo.api.model.commands;

import it.unicam.cs.pa.jlogo.api.model.Command;
import it.unicam.cs.pa.jlogo.api.model.Cursor;
import it.unicam.cs.pa.jlogo.api.model.Location;

/**
 * This command sets the size of the pen.
 * @param <L> type of Location.
 */
public class SETPENSIZE<L extends Location<L>> implements Command {
    private final Cursor<L> cursor;
    private final int penSize;

    public SETPENSIZE(Cursor<L> cursor, int penSize) {
        this.cursor = cursor;
        this.penSize = penSize;
    }

    @Override
    public void execute() {
        this.cursor.setPenSize(penSize);
    }

    @Override
    public String resultOfCommand() {
        return "The size of the pen is set to "+ penSize +".";
    }

}
