package it.unicam.cs.pa.jlogo.api.model.commands;

import it.unicam.cs.pa.jlogo.api.model.Canvas;
import it.unicam.cs.pa.jlogo.api.model.Command;
import it.unicam.cs.pa.jlogo.api.model.Location;

/**
 * This command delete what was drawn;
 * @param <L> type of Location.
 */
public class CLEARSCREEN<L extends Location<L>> implements Command {
    private final Canvas<L> canvas;

    public CLEARSCREEN(Canvas<L> canvas) {
        this.canvas = canvas;
    }

    @Override
    public void execute() {
        canvas.clear();
    }

    @Override
    public String resultOfCommand() {
        return "The screen has been cleared";
    }
}