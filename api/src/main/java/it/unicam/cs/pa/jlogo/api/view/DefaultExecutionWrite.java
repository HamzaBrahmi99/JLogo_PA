package it.unicam.cs.pa.jlogo.api.view;

/**
 * This class is used to write the execution of a LOGO program.
 */
public class DefaultExecutionWrite implements ExecutionWriter{
   @Override
    public String writeExecutionToOutput(String string) {
        return string+"\n";
    }
}
