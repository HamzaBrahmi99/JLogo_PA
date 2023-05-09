package it.unicam.cs.pa.jlogo.api.view;

import it.unicam.cs.pa.jlogo.api.model.*;
import it.unicam.cs.pa.jlogo.api.model.commands.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//TODO: test della view, piu del controller e forse un piu sul modello.
class CommandParserTest {
    @Test
    void checkIfParserIsCorrect(){
        String commandsReadFromFile = "FORWARD 10 LEFT 90 SETPENSIZE 2 SETSCREENCOLOR 255 0 0 RIGHT 45 BACKWARD 20" +
                " CLEARSCREEN HOME PENDOWN PENUP SETFILLCOLOR 0 0 0 SETPENCOLOR 255 255 0 RIPETI 2 [ FORWARD 10 ]";
        Canvas<GridLocation> canvas = new DefaultCanvas<>(50,50,new Color(255,255,255),new GridLocation(25,25));
        Cursor<GridLocation> cursor = new DefaultCursor<>(canvas.getHome(), new Direction(0), new Color(0,0,0), new Color(255,255,255),true,1);
        CommandParser<GridLocation> commandParser = new DefaultCommandParser<>();
        CommandFactory commandFactory = new SwitchCaseCommandFactory<>(canvas,cursor);
        List<Command> commandList = commandParser.parseCommandsFromString(commandsReadFromFile,commandFactory);
        assertEquals(13,commandList.size());
        assertEquals(commandList.get(0).getClass(), FORWARD.class);
        assertEquals(commandList.get(1).getClass(), LEFT.class);
        assertEquals(commandList.get(2).getClass(), SETPENSIZE.class);
        assertEquals(commandList.get(3).getClass(), SETSCREENCOLOR.class);
        assertEquals(commandList.get(4).getClass(), RIGHT.class);
        assertEquals(commandList.get(5).getClass(), BACKWARD.class);
        assertEquals(commandList.get(6).getClass(), CLEARSCREEN.class);
        assertEquals(commandList.get(7).getClass(), HOME.class);
        assertEquals(commandList.get(8).getClass(), PENDOWN.class);
        assertEquals(commandList.get(9).getClass(), PENUP.class);
        assertEquals(commandList.get(10).getClass(), SETFILLCOLOR.class);
        assertEquals(commandList.get(11).getClass(), SETPENCOLOR.class);
        assertEquals(commandList.get(12).getClass(), RIPETI.class);
    }
}