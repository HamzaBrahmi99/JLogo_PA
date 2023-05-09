package it.unicam.cs.pa.jlogo.app;

import it.unicam.cs.pa.jlogo.api.controller.Controller;
import it.unicam.cs.pa.jlogo.api.controller.DefaultController;
import it.unicam.cs.pa.jlogo.api.model.*;
import it.unicam.cs.pa.jlogo.api.view.DefaultCommandParser;
import it.unicam.cs.pa.jlogo.api.view.DefaultCommandReader;
import it.unicam.cs.pa.jlogo.api.view.DefaultExecutionWrite;
import it.unicam.cs.pa.jlogo.api.view.DefaultResultWriter;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * JavaFX controller for the JLOGO program.
 */
public class JLogoFXController {
    @FXML
    public Button openButton;
    @FXML
    public Button startButton;
    @FXML
    public TextField textFieldHeight;
    @FXML
    public TextField textFieldWidth;
    @FXML
    public TextArea executionTextArea;
    @FXML
    public Button saveButton;
    @FXML
    public Button stepBackwardButton;
    @FXML
    public Button stepForwardButton;
    @FXML
    public Pane executionPane;
    @FXML
    public Pane selectionPane;
    @FXML
    public Button resetButton;
    private Controller<GridLocation> controller;
    private File inputFile;

    /**
     * This method is called when the JavaFX starts. Is used to disable the open button while the two textFields are empty.
     */
    public void initialize(){
        openButton.disableProperty().bind(
                Bindings.isEmpty(textFieldWidth.textProperty())
                        .or(Bindings.isEmpty(textFieldHeight.textProperty())));
    }

    /**
     * This is the method used to handle the open command.
     * @param actionEvent the triggering event.
     */
    @FXML
    public void onOpenCommand(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open JLogo file");
        fileChooser.getExtensionFilters().add( new FileChooser.ExtensionFilter("Txt Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(((Node) actionEvent.getSource()).getScene().getWindow());
        if(selectedFile != null) {
            this.inputFile = selectedFile;
            selectionPane.setDisable(true);
            this.startButton.setDisable(false);
            this.stepForwardButton.setDisable(false);
//            this.stepBackwardButton.setDisable(false);
            executionPane.setDisable(false);
            checkIfWidthAndHeightAreCorrect(textFieldWidth,textFieldHeight);
            Canvas<GridLocation> canvas = new DefaultCanvas<>(Integer.parseInt(textFieldHeight.getText()), Integer.parseInt(textFieldHeight.getText()), new Color(255, 255, 255), new GridLocation(Integer.parseInt(textFieldHeight.getText()) / 2, Integer.parseInt(textFieldHeight.getText()) / 2));
            Cursor<GridLocation> cursor = new DefaultCursor<>(new GridLocation(Integer.parseInt(textFieldHeight.getText()) / 2, Integer.parseInt(textFieldHeight.getText()) / 2), new Direction(0), new Color(0, 0, 0), new Color(255, 255, 255), true, 1);
            this.controller = new DefaultController<>(new DefaultCommandParser<>(),new DefaultCommandReader<>(),new DefaultExecutionWrite(), new DefaultResultWriter<>(), canvas, cursor);
        }
    }

    private void checkIfWidthAndHeightAreCorrect(TextField textFieldWidth, TextField textFieldHeight) {
        if(Integer.parseInt(textFieldWidth.getText())<0 || Integer.parseInt(textFieldHeight.getText())<0)
            throw new IllegalArgumentException("Canvas dimensions can't be negative...");
    }

    /**
     * This is the method used to handle the auto execution command.
     * @param actionEvent the triggering event.
     */
    @FXML
    public void onAutoExecutionCommand(ActionEvent actionEvent) {
        startLogoProgram();
        controller.executeAllCommands();
        this.executionTextArea.appendText(controller.writeExecutionOfProgram());
        this.startButton.setDisable(true);
        this.stepForwardButton.setDisable(true);
        this.stepBackwardButton.setDisable(true);
        this.saveButton.setDisable(false);
    }

    /**
     * This method is called when the program is ready to start the execution.
     */
    private void startLogoProgram() {
        try {
            this.controller.openFileAndReadCommands(inputFile);
        } catch (IOException | NoSuchMethodException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error...");
            alert.setHeaderText(e.getMessage());
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This is the method used to handle the save command.
     * @param actionEvent the triggering event.
     */
    @FXML
    public void onSaveCommand(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save JLogo File");
        fileChooser.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("JLogo Files", "*.txt"));
        File selectedFile = fileChooser.showSaveDialog(((Node) actionEvent.getSource()).getScene().getWindow());
        if (selectedFile != null) {
            try {
                controller.writeResultsOnFile(selectedFile);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error...");
                alert.setHeaderText(e.getMessage());
            }
        }
    }

    /**
     * This is the method used to handle the step forward command.
     * @param actionEvent the triggering event.
     */
    @FXML
    public void onStepForwardCommand(ActionEvent actionEvent) {
        this.startButton.setDisable(true);
        startLogoProgram();
        controller.stepForward();
        this.executionTextArea.setText(controller.writeExecutionOfProgram());
        if(controller.isLastCommand()) saveButton.setDisable(false);
    }

    /**
     * This is the method used to handle the step backward command.
     * @param actionEvent the triggering event.
     */
    @FXML
    public void onStepBackwardCommand(ActionEvent actionEvent) {
//        controller.stepBackward();
//        this.executionTextArea.setText(controller.writeExecutionOfProgram());
//        if(!controller.isLastCommand()){
//            saveButton.setDisable(true);
//        }
    }

    /**
     * This is the method used to handle the reset command.
     * @param actionEvent the triggering event.
     */
    @FXML
    public void onResetCommand(ActionEvent actionEvent) {
        this.selectionPane.setDisable(false);
        this.executionPane.setDisable(true);
        this.executionTextArea.setText("");
        this.textFieldWidth.setText("");
        this.textFieldHeight.setText("");
        this.inputFile = null;
        this.controller.clear();
        saveButton.setDisable(true);
    }
}
