package it.unicam.cs.pa.jlogo.api.model.commands;

import it.unicam.cs.pa.jlogo.api.model.Canvas;
import it.unicam.cs.pa.jlogo.api.model.Command;
import it.unicam.cs.pa.jlogo.api.model.Cursor;
import it.unicam.cs.pa.jlogo.api.model.Location;

/**
 * This command moves the cursor to the home position;
 * @param <L> type of Location.
 */
public class HOME<L extends Location<L>> implements Command {
    private final Cursor<L> cursor;
    private final Canvas<L> canvas;

    public HOME(Cursor<L> cursor, Canvas<L> canvas) {
        this.cursor = cursor;
        this.canvas = canvas;
    }

    @Override
    public void execute() {
        this.cursor.setLocation(canvas.getHome());
    }

    @Override
    public String resultOfCommand() {
        return "The cursor moved to HOME";
    }
}
