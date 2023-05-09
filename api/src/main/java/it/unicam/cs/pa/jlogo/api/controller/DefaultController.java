package it.unicam.cs.pa.jlogo.api.controller;

import it.unicam.cs.pa.jlogo.api.model.*;
import it.unicam.cs.pa.jlogo.api.view.CommandReader;
import it.unicam.cs.pa.jlogo.api.view.ExecutionWriter;
import it.unicam.cs.pa.jlogo.api.view.ResultWriter;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is used to control a JLOGO program.
 * @param <L> type of Location.
 */
public class DefaultController<L extends Location<L>> implements Controller<L> {
    private final CommandReader<L> commandReader;
    private final ExecutionWriter executionWriter;
    private final ResultWriter<L> resultWriter;
    private Canvas<L> canvas;
    private Cursor<L> cursor;
    private final CommandParser<L> defaultCommandParser;
    private final CommandFactory commandFactory;
    private String currentExe = "STARTING...\n";
    private final List<String> historyExe;
    private List<Command> commands;
    private final List<Cursor<L>> historyCursor;
    private final List<Canvas<L>> historyCanvas;
    private int actualCommand = 0;
    private boolean isLastCommand = false;

    /**
     * Constructor of a default controller of a Logo program.
     * @param defaultCommandParser parse the commands,
     * @param commandReader reads the commands,
     * @param executionWriter writes the execution of the program,
     * @param resultWriter writes the result of the program,
     * @param canvas to use,
     * @param cursor to use.
     */
    public DefaultController(CommandParser<L> defaultCommandParser, CommandReader<L> commandReader, ExecutionWriter executionWriter, ResultWriter<L> resultWriter, Canvas<L> canvas, Cursor<L> cursor) {
        this.defaultCommandParser = defaultCommandParser;
        this.commandReader = commandReader;
        this.executionWriter = executionWriter;
        this.resultWriter = resultWriter;
        this.canvas = canvas;
        this.cursor = cursor;
        this.commandFactory = new SwitchCaseCommandFactory<>(canvas,cursor);
        this.historyCursor = new LinkedList<>();
        this.historyCanvas = new LinkedList<>();
        this.commands = new LinkedList<>();
        this.historyExe = new LinkedList<>();
    }

    @Override
    public void openFileAndReadCommands(File file) throws IOException {
        commands = commandReader.parse(file, defaultCommandParser, commandFactory);
    }

    public void executeAllCommands(){
        for (int i = 0; i < commands.size(); i++) {
           stepForward();
        }
        canvas.saveLinesNotContinuous();
        this.isLastCommand = true;
    }

    @Override
    public void stepForward() {
        if(actualCommand<commands.size()){
            addToHistory();
            commands.get(actualCommand).execute();
            appendExecutionOfProgram(commands.get(actualCommand).resultOfCommand());
            actualCommand++;
        }
        if(actualCommand==commands.size()) {
            canvas.saveLinesNotContinuous();
            addToHistory();
            this.isLastCommand = true;
            appendExecutionOfProgram("TERMINATED...\n");
            actualCommand++;
        }
    }

    /**
     * This utility method adds this state to the history.
     */
    private void addToHistory() {
        historyCanvas.add(this.canvas.getActualStateOfCanvas());
        historyCursor.add(this.cursor.getActualStateOfCursor());
        historyExe.add(this.currentExe);
    }

    @Override
    public boolean isLastCommand() {
        return isLastCommand;
    }

//    /**
//     * Anche il comando stepBack non ho avuto il tempo di metterlo a punto.
//     */
    @Override
    public void stepBackward() {
//        if(!historyCursor.isEmpty() && !historyCanvas.isEmpty() && (historyExe.size()>1)) {
//            if(commands.size()<=actualCommand){
//                actualCommand--;
//                actualCommand--;
//                canvas = historyCanvas.remove(historyCursor.size()-1);
//                cursor = historyCursor.remove(historyCursor.size()-1);
//                currentExe = historyExe.remove(historyCursor.size()-1);
//                this.isLastCommand = false;
//            } else {
//                actualCommand--;
//                canvas = historyCanvas.remove(historyCursor.size()-1);
//                cursor = historyCursor.remove(historyCursor.size()-1);
//                currentExe = historyExe.remove(historyCursor.size()-1);
//                this.isLastCommand = false;
//            }
//        }
    }

    @Override
    public String writeExecutionOfProgram() {
        return this.currentExe;
    }

    /**
     * This private method appends the result of the execution of that command to the execution of the program.
     * @param string to append to the execution of the program.
     */
    private void appendExecutionOfProgram(String string) {
        this.currentExe = currentExe + this.executionWriter.writeExecutionToOutput(string);
    }

    @Override
    public void writeResultsOnFile(File outputFile) throws IOException {
        this.resultWriter.writeTo(canvas, outputFile);
    }

    @Override
    public void clear() {
        this.canvas.clear();
        this.cursor.setLocation(this.canvas.getHome());
        actualCommand = 0;
    }

}
