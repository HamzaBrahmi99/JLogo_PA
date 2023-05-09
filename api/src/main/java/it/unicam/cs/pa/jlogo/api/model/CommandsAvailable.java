package it.unicam.cs.pa.jlogo.api.model;

/**
 * This enum is used to get the number of parameters of each command that is available. Is used to create dynamically the correct command
 * in the CommandFactory class.
 *
 * Nota per il prof: questo enum doveva servire per creare dinamicamente il comando corretto utilizzando la Reflection, però per una serie di
 * problemi personali non ho avuto tempo per implementarla nella classe CommandFactory ed ho optato per una implementazione meno astratta, e quindi
 * meno estendibile, attraverso un semplice switch (vedi classe CommandFactory).
 */
public enum CommandsAvailable {
    FORWARD(1),
    BACKWARD(1),
    HOME(0),
    CLEARSCREEN(0),
    LEFT(1),
    RIGHT(1),
    PENDOWN(0),
    PENUP(0),
    SETFILLCOLOR(3),
    SETPENCOLOR(3),
    SETPENSIZE(1),
    SETSCREENCOLOR(3),
    RIPETI(1);

    CommandsAvailable(int numberOfParameters){
        this.numberOfParameters = numberOfParameters;
    }

    private final int numberOfParameters;
    /**
     * This method returns the number of parameters for that command.
     * @return the number of parameters for that command.
     */
    public int getNumberOfParameters() {
        return numberOfParameters;
    }
}
