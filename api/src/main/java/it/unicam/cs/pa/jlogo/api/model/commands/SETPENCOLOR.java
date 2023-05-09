package it.unicam.cs.pa.jlogo.api.model.commands;

import it.unicam.cs.pa.jlogo.api.model.Color;
import it.unicam.cs.pa.jlogo.api.model.Command;
import it.unicam.cs.pa.jlogo.api.model.Cursor;
import it.unicam.cs.pa.jlogo.api.model.Location;

/**
 * This command sets the color of the pen to the color represented by the RGB color represented by the three data bytes
 * @param <L> type of Location.
 */
public class SETPENCOLOR<L extends Location<L>> implements Command {
    private final Cursor<L> cursor;
    private final Color penColor;

    public SETPENCOLOR(Cursor<L> cursor, Color penColor) {
        this.cursor = cursor;
        this.penColor = penColor;
    }

    @Override
    public void execute() {
        this.cursor.setLineColor(penColor);
    }

    @Override
    public String resultOfCommand() {
        return "The color of the pen is set to "+ penColor +".";
    }

}
