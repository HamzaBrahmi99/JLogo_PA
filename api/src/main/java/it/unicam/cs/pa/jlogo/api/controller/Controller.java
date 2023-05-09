package it.unicam.cs.pa.jlogo.api.controller;

import it.unicam.cs.pa.jlogo.api.model.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * This interface represents a controller of a JLOGO program.
 * @param <L> type of the Location.
 */
public interface Controller<L extends Location<L>> {
    /**
     * This method opens the given file and reads the commands.
     * @param file of commands to read.
     * @throws IOException if an I/O error occurs while reading the data.
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    void openFileAndReadCommands(File file) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException;

    /**
     * This method writes the execution of the program.
     * @return the execution of the program.
     */
    String writeExecutionOfProgram();

    /**
     * This method wites the result of the JLOGO program into the given file.
     * @param outputFile to write the result of the program.
     * @throws IOException if an I/O error occurs while writing the data.
     */
    void writeResultsOnFile(File outputFile) throws IOException;

    /**
     * This method is used to execute in auto the JLOGO program.
     */
    void executeAllCommands();

    /**
     * This method is used to execute one step forward of the JLOGO program.
     */
    void stepBackward();

    /**
     * This method is used to execute one step backward of the JLOGO program.
     */
    void stepForward();

    /**
     * This method clears and resets the JLOGO program.
     */
    void clear();

    /**
     * Returns if the executed command was the last.
     * @return true if it was the last oen, false otherwise.
     */
    boolean isLastCommand();
}