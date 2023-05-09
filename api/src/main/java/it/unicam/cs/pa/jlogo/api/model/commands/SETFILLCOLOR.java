package it.unicam.cs.pa.jlogo.api.model.commands;

import it.unicam.cs.pa.jlogo.api.model.Color;
import it.unicam.cs.pa.jlogo.api.model.Command;
import it.unicam.cs.pa.jlogo.api.model.Cursor;
import it.unicam.cs.pa.jlogo.api.model.Location;

/**
 * This command sets the color of the fill of an enclosed area;
 * @param <L> type of Location.
 */
public class SETFILLCOLOR<L extends Location<L>> implements Command {
    private final Cursor<L> cursor;
    private final Color areaColor;

    public SETFILLCOLOR(Cursor<L> cursor, Color areaColor) {
        this.cursor = cursor;
        this.areaColor = areaColor;
    }
    @Override
    public void execute() {
        this.cursor.setAreaColor(areaColor);
    }

    @Override
    public String resultOfCommand() {
        return "The color of the area is set to "+areaColor+".";
    }

}
