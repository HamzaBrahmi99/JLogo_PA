package it.unicam.cs.pa.jlogo.api.model;

import it.unicam.cs.pa.jlogo.api.model.commands.FORWARD;
import it.unicam.cs.pa.jlogo.api.model.commands.RIPETI;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckIfParametersAreCorrectTest {
    @Test
    void checkIfColorParametersAreCorrect(){
        assertDoesNotThrow(() -> new Color(10,250,33));
        assertThrows(IllegalArgumentException.class,() -> new Color(-10,350,-33));
    }
    @Test
    void checkIfPenSizeParameterIsCorrect(){
        Canvas<GridLocation> canvas = new DefaultCanvas<>(50,50,new Color(255,255,255),new GridLocation(25,25));
        Cursor<GridLocation> cursor = new DefaultCursor<>(canvas.getHome(), new Direction(0), new Color(0,0,0), new Color(255,255,255),true,1);
        assertDoesNotThrow(() -> cursor.setPenSize(10));
        assertDoesNotThrow(() -> cursor.setPenSize(2));
        assertThrows(IllegalArgumentException.class,() -> cursor.setPenSize(-10));
        assertThrows(IllegalArgumentException.class,() -> cursor.setPenSize(0));
    }
    @Test
    void checkIfRipetiParametersAreCorrect(){
        Canvas<GridLocation> canvas = new DefaultCanvas<>(50,50,new Color(255,255,255),new GridLocation(25,25));
        Cursor<GridLocation> cursor = new DefaultCursor<>(canvas.getHome(), new Direction(0), new Color(0,0,0), new Color(255,255,255),true,1);
        assertDoesNotThrow(() -> new RIPETI<>(10, List.of(new FORWARD<>(cursor,10,canvas))));
        assertThrows(IllegalArgumentException.class,() -> new RIPETI<>(-2, List.of(new FORWARD<>(cursor,10,canvas))));
        assertThrows(IllegalArgumentException.class,() -> new RIPETI<>(10, List.of()));
    }

}