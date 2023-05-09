package it.unicam.cs.pa.jlogo.api.model.commands;

import it.unicam.cs.pa.jlogo.api.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CursorMovementsTest {
    @Test
    public void cursorShouldStopAtBorder(){
        Canvas<GridLocation> canvas = new DefaultCanvas<>(50,50,new Color(255,255,255),new GridLocation(25,25));
        Cursor<GridLocation> cursor = new DefaultCursor<>(canvas.getHome(), new Direction(0), new Color(0,0,0), new Color(255,255,255),true,1);
        Command forward = new FORWARD<>(cursor, 10, canvas);
        for (int i = 0; i < 8; i++) {
            forward.execute();
        }
        assertEquals(new GridLocation(50,25),cursor.getLocation());
    }
}