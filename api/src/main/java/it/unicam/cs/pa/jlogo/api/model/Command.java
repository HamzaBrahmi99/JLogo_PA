package it.unicam.cs.pa.jlogo.api.model;

/**
 * This interface represents the concept of a command.
 */
public interface Command {
    /**
     * This method executes this command.
     */
    void execute();

    /**
     * This method returns a string that represents the execution of this command.
     * @return a string that represents the execution of this command.
     */
    String resultOfCommand();
}
