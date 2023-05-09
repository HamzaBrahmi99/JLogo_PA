package it.unicam.cs.pa.jlogo.api.model.commands;

import it.unicam.cs.pa.jlogo.api.model.Canvas;
import it.unicam.cs.pa.jlogo.api.model.Color;
import it.unicam.cs.pa.jlogo.api.model.Command;
import it.unicam.cs.pa.jlogo.api.model.Location;

/**
 * This command sets the background color of the drawing area;
 * @param <L> type of Location.
 */
public class SETSCREENCOLOR<L extends Location<L>> implements Command {
    private final Canvas<L> canvas;
    private final Color screenColor;

    public SETSCREENCOLOR(Canvas<L> canvas, Color screenColor) {
        this.canvas = canvas;
        this.screenColor = screenColor;
    }

    @Override
    public void execute() {
        this.canvas.setBackgroundColor(screenColor);
    }

    @Override
    public String resultOfCommand() {
        return "The screen color is set to "+screenColor+".";
    }
}