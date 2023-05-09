package it.unicam.cs.pa.jlogo.api.controller;

import it.unicam.cs.pa.jlogo.api.model.*;
import it.unicam.cs.pa.jlogo.api.model.commands.FORWARD;
import it.unicam.cs.pa.jlogo.api.model.commands.LEFT;
import it.unicam.cs.pa.jlogo.api.model.commands.RIGHT;
import it.unicam.cs.pa.jlogo.api.model.commands.SETFILLCOLOR;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultControllerOfCreationOfFiguresTest {

    @Test
    public void CreationOfBlackSquare(){
        Canvas<GridLocation> canvas = new DefaultCanvas<>(50,50,new Color(255,255,255),new GridLocation(25,25));
        Cursor<GridLocation> cursor = new DefaultCursor<>(canvas.getHome(), new Direction(0), new Color(0,0,0), new Color(255,255,255),true,1);
        Command forward = new FORWARD<>(cursor, 10, canvas);
        Command left = new LEFT<>(cursor,90);
        Command fillColorBlack = new SETFILLCOLOR<>(cursor,new Color(0,0,0));
        fillColorBlack.execute();
        createASquare(forward,left);
        assertEquals(1,canvas.getFigures().size());
        assertEquals(new Color(0,0,0),canvas.getFigures().get(0).getColor());
        String blackSquare = "POLYGON 4 0 0 0\n" +
                "[LINE 25 25 35 25 0 0 0 1\n" +
                ", LINE 35 25 35 35 0 0 0 1\n" +
                ", LINE 35 35 25 35 0 0 0 1\n" +
                ", LINE 25 35 25 25 0 0 0 1\n" +
                "]\n";
        assertEquals(blackSquare,canvas.getFigures().get(0).getFigure());
    }
    private void createASquare(Command forward, Command left) {
        for (int i = 0; i < 4; i++) {
            forward.execute();
            left.execute();
        }
    }

    @Test
    public void CreationOfGreenTriangle(){
        Canvas<GridLocation> canvas = new DefaultCanvas<>(50,50,new Color(255,255,255),new GridLocation(25,25));
        Cursor<GridLocation> cursor = new DefaultCursor<>(canvas.getHome(), new Direction(0), new Color(0,0,0), new Color(255,255,255),true,1);
        Command forward = new FORWARD<>(cursor, 50, canvas);
        Command right = new RIGHT<>(cursor,90);
        Command fillColorGreen = new SETFILLCOLOR<>(cursor,new Color(0,255,0));
        Command right2 = new RIGHT<>(cursor, 120);
        fillColorGreen.execute();
        right.execute();
        forward.execute();
        right2.execute();
        forward.execute();
        right2.execute();
        forward.execute();

        assertEquals(1,canvas.getFigures().size());
        assertEquals(new Color(0,255,0),canvas.getFigures().get(0).getColor());
        String greenTriangle = "POLYGON 3 0 255 0\n" +
                "[LINE 25 25 25 -25 0 0 0 1\n" +
                ", LINE 25 -25 -18 0 0 0 0 1\n" +
                ", LINE -18 0 25 25 0 0 0 1\n" +
                "]\n";
        assertEquals(greenTriangle,canvas.getFigures().get(0).getFigure());
    }

}