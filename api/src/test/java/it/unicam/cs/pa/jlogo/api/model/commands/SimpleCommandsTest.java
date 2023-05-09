package it.unicam.cs.pa.jlogo.api.model.commands;

import it.unicam.cs.pa.jlogo.api.model.*;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleCommandsTest {
    @Test
    public void MovementShouldCreateLines(){
        Canvas<GridLocation> canvas = new DefaultCanvas<>(50,50,new Color(255,255,255),new GridLocation(25,25));
        Cursor<GridLocation> cursor = new DefaultCursor<>(canvas.getHome(), new Direction(0), new Color(0,0,0), new Color(255,255,255),true,1);
        Command forward = new FORWARD<>(cursor, 10, canvas);
        Command penUp = new PENUP<>(cursor);
        Command penDown = new PENDOWN<>(cursor);
        assertEquals(0,canvas.getFigures().size());
        createSingleLine(forward, penUp);
        forward.execute();
        assertEquals(1,canvas.getFigures().size());
        penDown.execute();
        createSingleLine(forward,penUp);
        forward.execute();
        assertEquals(2,canvas.getFigures().size());
    }

    private static void createSingleLine(Command forward, Command penUp) {
        forward.execute();
        penUp.execute();
    }


    @Test
    public void ScreenShouldBeClearedTest(){
        Canvas<GridLocation> canvas = new DefaultCanvas<>(50,50,new Color(255,255,255),new GridLocation(25,25));
        Cursor<GridLocation> cursor = new DefaultCursor<>(canvas.getHome(), new Direction(0), new Color(0,0,0), new Color(255,255,255),true,1);
        Command forward = new FORWARD<>(cursor, 10, canvas);
        Command left= new LEFT<>(cursor,90);
        Command clearScreen = new CLEARSCREEN<>(canvas);
        createASquare(forward,left);
        assertEquals(1, canvas.getFigures().size());
        clearScreen.execute();
        assertEquals(0, canvas.getFigures().size());
    }

    private void createASquare(Command forward, Command left) {
        for (int i = 0; i < 4; i++) {
            forward.execute();
            left.execute();
        }
    }

    @Test
    public void cursorShouldMoveToHome(){
        Canvas<GridLocation> canvas = new DefaultCanvas<>(50,50,new Color(255,255,255),new GridLocation(25,25));
        Cursor<GridLocation> cursor = new DefaultCursor<>(canvas.getHome(), new Direction(0), new Color(0,0,0), new Color(255,255,255),true,1);
        Command forward = new FORWARD<>(cursor, 10, canvas);
        Command home = new HOME<>(cursor,canvas);
        forward.execute();
        forward.execute();
        assertNotEquals(cursor.getLocation(),canvas.getHome());
        home.execute();
        assertEquals(cursor.getLocation(),canvas.getHome());
    }

    @Test
    public void cursorShouldRotate(){
        Canvas<GridLocation> canvas = new DefaultCanvas<>(50,50,new Color(255,255,255),new GridLocation(25,25));
        Cursor<GridLocation> cursor = new DefaultCursor<>(canvas.getHome(), new Direction(0), new Color(0,0,0), new Color(255,255,255),true,1);
        Command left= new LEFT<>(cursor,90);
        Command right= new RIGHT<>(cursor,45);
        left.execute();
        assertEquals(90,cursor.getDirection().getDirection());
        right.execute();
        assertEquals(45,cursor.getDirection().getDirection());
    }

    @Test
    public void cursorPenShouldGoUpAndDown(){
        Canvas<GridLocation> canvas = new DefaultCanvas<>(50,50,new Color(255,255,255),new GridLocation(25,25));
        Cursor<GridLocation> cursor = new DefaultCursor<>(canvas.getHome(), new Direction(0), new Color(0,0,0), new Color(255,255,255),true,1);
        Command penUp = new PENUP<>(cursor);
        penUp.execute();
        assertFalse(cursor.getPlot());
        Command penDown = new PENDOWN<>(cursor);
        penDown.execute();
        assertTrue(cursor.getPlot());
    }
    @Test
    public void penColorShouldChange(){
        Canvas<GridLocation> canvas = new DefaultCanvas<>(50,50,new Color(255,255,255),new GridLocation(25,25));
        Cursor<GridLocation> cursor = new DefaultCursor<>(canvas.getHome(), new Direction(0), new Color(0,0,0), new Color(255,255,255),true,1);
        Command penColorWhite = new SETPENCOLOR<>(cursor,new Color(255,255,255));
        Command penColorBlack = new SETPENCOLOR<>(cursor,new Color(0,0,0));
        penColorBlack.execute();
        assertEquals(List.of(0,0,0),cursor.getLineColor().getColor());
        penColorWhite.execute();
        assertEquals(List.of(255,255,255),cursor.getLineColor().getColor());
    }
    @Test
    public void screenColorShouldChange(){
        Canvas<GridLocation> canvas = new DefaultCanvas<>(50,50,new Color(255,255,255),new GridLocation(25,25));
        Cursor<GridLocation> cursor = new DefaultCursor<>(canvas.getHome(), new Direction(0), new Color(0,0,0), new Color(255,255,255),true,1);
        Command screenColorWhite = new SETSCREENCOLOR<>(canvas,new Color(255,255,255));
        Command screenColorBlack = new SETSCREENCOLOR<>(canvas,new Color(0,0,0));
        screenColorBlack.execute();
        assertEquals(List.of(0,0,0),canvas.getBackgroundColor().getColor());
        screenColorWhite.execute();
        assertEquals(List.of(255,255,255),canvas.getBackgroundColor().getColor());
    }
    @Test
    public void fillColorShouldChange(){
        Canvas<GridLocation> canvas = new DefaultCanvas<>(50,50,new Color(255,255,255),new GridLocation(25,25));
        Cursor<GridLocation> cursor = new DefaultCursor<>(canvas.getHome(), new Direction(0), new Color(0,0,0), new Color(255,255,255),true,1);
        Command fillColorWhite = new SETFILLCOLOR<>(cursor,new Color(255,255,255));
        Command fillColorBlack = new SETFILLCOLOR<>(cursor,new Color(0,0,0));
        fillColorBlack.execute();
        assertEquals(List.of(0,0,0),cursor.getAreaColor().getColor());
        fillColorWhite.execute();
        assertEquals(List.of(255,255,255),cursor.getAreaColor().getColor());
    }
    @Test
    public void penSizeShouldChange(){
        Canvas<GridLocation> canvas = new DefaultCanvas<>(50,50,new Color(255,255,255),new GridLocation(25,25));
        Cursor<GridLocation> cursor = new DefaultCursor<>(canvas.getHome(), new Direction(0), new Color(0,0,0), new Color(255,255,255),true,1);
        Command penSize = new SETPENSIZE<>(cursor,2);
        penSize.execute();
        assertEquals(2,cursor.getPenSize());
    }
    @Test
    public void repeatMoreCommandsTest(){
        Canvas<GridLocation> canvas = new DefaultCanvas<>(50,50,new Color(255,255,255),new GridLocation(25,25));
        Cursor<GridLocation> cursor = new DefaultCursor<>(canvas.getHome(), new Direction(0), new Color(0,0,0), new Color(255,255,255),true,1);
        Command ripetiToCreateASquare = new RIPETI<>(4, List.of(new FORWARD<>(cursor,10,canvas),new LEFT<>(cursor,90)));
        ripetiToCreateASquare.execute();
        assertEquals(1, canvas.getFigures().size());
        canvas.clear();
        List<Command> cmds = new LinkedList<>();
        Command forward = new FORWARD<>(cursor, 10, canvas);
        cmds.add(forward);
        Command ripetiToCreateFiveLines = new RIPETI<>(5, cmds);
        ripetiToCreateFiveLines.execute();
        Command penUp = new PENUP<>(cursor);
        penUp.execute();
        forward.execute();
        assertEquals(5,canvas.getFigures().size());
    }
}