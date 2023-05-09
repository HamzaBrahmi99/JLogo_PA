package it.unicam.cs.pa.jlogo.api.view;

/**
 * This interface is used to write the execution of a LOGO program.
 */
@FunctionalInterface
public interface ExecutionWriter {
    /**
     * This method writes the given string to the execution output.
     * @param string that represents the execution of a Command.
     */
    String writeExecutionToOutput(String string);
}
