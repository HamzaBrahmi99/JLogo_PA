package it.unicam.cs.pa.jlogo.api.model.commands;

import it.unicam.cs.pa.jlogo.api.model.*;

/**
 * This command move the cursor forward towards its direction (if the limits of the area are reached, the cursor stops at the edge);
 * @param <L> type of Location.
 */
public class FORWARD<L extends Location<L>> implements Command {
    private final Cursor<L> cursor;
    private final int distance;
    private final Canvas<L> canvas;
    private String result = "";

    public FORWARD(Cursor<L> cursor, int distance, Canvas<L> canvas) {
        this.cursor = cursor;
        this.distance = distance;
        this.canvas = canvas;
    }

    @Override
    public void execute() {
        result = "";
        L oldLoc = cursor.getLocation();
        cursor.setLocation(cursor.getLocation().nextLocation(cursor.getDirection(),this.distance));
        cursor.setLocation(cursor.getLocation().checkIfReachBorders(cursor.getLocation(), canvas.getWidth(), canvas.getHeight()));
        if(this.cursor.getPlot()) {
            this.result = result+"\nCREATED LINE!";
            if(canvas.logicAboutCreationOfFigures(oldLoc, cursor.getLocation(), cursor.getLineColor(), cursor.getPenSize(), cursor.getAreaColor())) {
                this.result = result + "\nCREATED FIGURE!";
            };
        } else {
            canvas.saveLinesNotContinuous();
        }
    }

    @Override
    public String resultOfCommand() {
        return "Cursor moved forward by "+ distance +"." + result;
    }
}
