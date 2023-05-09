package it.unicam.cs.pa.jlogo.api.model.commands;

import it.unicam.cs.pa.jlogo.api.model.Command;
import it.unicam.cs.pa.jlogo.api.model.Cursor;
import it.unicam.cs.pa.jlogo.api.model.Location;
/**
 * This command rotate the cursor counterclockwise by the degrees
 * described by the parameter (the angles are indicated as integers in the range [0, 360]);
 * @param <L> type of Location.
 */
public class LEFT<L extends Location<L>> implements Command {
    private final Cursor<L> cursor;
    private final int angle;

    public LEFT(Cursor<L> cursor, int angle) {
        this.cursor = cursor;
        this.angle = angle;
    }

    @Override
    public void execute() {
        this.cursor.setDirection(cursor.getDirection().addDirection(angle));
    }

    @Override
    public String resultOfCommand() {
        return "The cursor turned left by "+angle+".";
    }

}
