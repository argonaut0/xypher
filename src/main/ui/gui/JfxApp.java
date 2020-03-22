package ui.gui;

import javafx.application.Application;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.CipherSequence;
import model.Encoder;
import model.ciphers.*;
import ui.XypherApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

public class JfxApp extends Application {
    private ObservableMap<String, Encoder> encoders;
    private XypherApp app;
    private Encoder activeEncoder;

    //EncoderTable
    final ComboBox<String> encoderSelectionBox = new ComboBox<>();
        //EncoderTypePane
    final ComboBox<String> encoderTypesBox = new ComboBox<>();
    final TextField newSequenceNameField = new TextField();
    final TextField newAffineCipherAField = new TextField();
    final TextField newAffineCipherBField = new TextField();
    final TextField newCaesarCipherShiftField = new TextField();
    final Button addButton = new Button("Add");
    final Button removeButton = new Button("Remove");
    final Button saveButton = new Button("Save");
    final Button loadButton = new Button("Load");
    final TextField loadPath = new TextField();
    //ConfigPane
    final Text titleContextText = new Text("Context");
    final ComboBox<String> sequenceEncodersBox = new ComboBox<>();
    final Button addEncoderToSeqButton = new Button("Add Active Cipher to sequence");
    final Button removeEncoderFromSeqButton = new Button("Remove Cipher from sequence");
    final ListView<String> sequenceContents = new ListView<>();
    final TextField removeIndexField = new TextField();
    //Input/Output Pane
    final TextField inputTextField = new TextField();
    final TextField outputTextField = new TextField();
    final Button encodeButton = new Button("Encode");
    final Button decodeButton = new Button("Decode");

    private static class ColumnVBox extends VBox {
        ColumnVBox() {
            this.setSpacing(5);
            this.setPadding(new Insets(10, 0, 0, 10));
        }
    }

    private static class HSpacer extends Region {
        HSpacer() {
            HBox.setHgrow(this, Priority.ALWAYS);
        }
    }

    private static class VSpacer extends Region {
        VSpacer() {
            VBox.setVgrow(this, Priority.ALWAYS);
        }
    }

    /**
     * (Slightly) Hacky entry point, called in psvm
     */
    public static void run(String[] args) {
        launch(args);
    }

    /**
     * EFFECTS: Starts the application
     */
    @Override
    public void start(Stage stage) throws Exception {
        construct();
        stage.setTitle("Xypher");

        GridPane mainGrid = genMainGrid();
        mainGrid.add(genEncoderTable(), 0, 0);
        mainGrid.add(genConfigPane(), 1, 0);
        mainGrid.add(genInputPane(), 0, 2);
        mainGrid.add(genOutputPane(), 1, 2);

        Scene scene = new Scene(mainGrid, 800, 600);

        stage.setScene(scene);
        stage.show();

    }

    /**
     * EFFECTS: Initializes things
     */
    private void construct() {
        app = new XypherApp();
        encoders = app.getEncoders();
    }

    /**
     * EFFECTS: generates the main grid
     */
    private static GridPane genMainGrid() {
        final GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(12);
        grid.setVgap(12);
        grid.setPadding(new Insets(36, 36, 36, 36));
        return grid;
    }

    /**
     * EFFECTS: generates the encoder list
     */
    private VBox genEncoderTable() {
        final VBox box = new ColumnVBox();

        for (Map.Entry<String, Encoder> entry: encoders.entrySet()) {
            encoderSelectionBox.getItems().add(entry.getKey());
        }
        encoderSelectionBox.setOnAction(this::selectEncoder);

        encoders.addListener((MapChangeListener<String, Encoder>) change -> {
            if (change.wasAdded()) {
                encoderSelectionBox.getItems().add(change.getKey());
            } else if (change.wasRemoved()) {
                encoderSelectionBox.getItems().remove(change.getKey());
            }
        });

        box.getChildren().addAll(new Text("Active Encoder:"), encoderSelectionBox, genEncoderTypePane());
        return box;
    }

    /**
     * EFFECTS: generates the config pane
     */
    private VBox genConfigPane() {
        final VBox box = new ColumnVBox();
        HBox titleBox = new HBox();
        Text titleText = new Text("Configure Sequence: ");
        titleBox.getChildren().addAll(titleText, sequenceEncodersBox);
        sequenceEncodersBox.setOnAction(actionEvent -> {
            refreshSequenceContents();
        });

        for (Map.Entry<String, Encoder> entry: encoders.entrySet()) {
            if (entry.getValue().getClass() == CipherSequence.class) {
                sequenceEncodersBox.getItems().add(entry.getKey());
            }
        }

        encoders.addListener(this::encoderChangeListener);

        addEncoderToSeqButton.setOnAction(this::addEncoderToSeqUI);
        removeIndexField.setPromptText("Enter index to remove...");
        removeEncoderFromSeqButton.setOnAction(this::removeEncoderFromSeqUI);

        box.getChildren().addAll(titleBox, sequenceContents, addEncoderToSeqButton,
                removeIndexField, removeEncoderFromSeqButton);
        return box;
    }

    private VBox genEncoderTypePane() {
        final VBox box = new ColumnVBox();

        Text titleText = new Text("Available Encoder Types:");
        encoderTypesBox.getItems().addAll("Sequence", "AtbashCipher", "AffineCipher", "CaesarCipher", "Rot13Cipher");
        //todo remove soft coupling
        encoderTypesBox.setOnAction(this::selectEncoderType);

        newSequenceNameField.setPromptText("Sequence name");
        newAffineCipherAField.setPromptText("Enter key A");
        newAffineCipherBField.setPromptText("Enter key B");
        newCaesarCipherShiftField.setPromptText("Enter Shift");

        addButton.setOnAction(this::addEncoderUI);
        removeButton.setOnAction(this::removeEncoderUI);
        saveButton.setOnAction(this::saveEncoderUI);
        loadButton.setOnAction(this::loadEncoderUI);
        loadPath.setPromptText("Name to load....");

        final HBox addRemove = new HBox();
        addAllHJustify(addRemove, addButton, removeButton, saveButton);
        final HBox loadBox = new HBox();
        addAllHJustify(loadBox, loadButton, loadPath);

        box.getChildren().addAll(titleText, encoderTypesBox, newSequenceNameField, newAffineCipherAField,
                newAffineCipherBField, newCaesarCipherShiftField, addRemove, loadBox);
        return box;
    }

    /**
     * EFFECTS: generates the text input pane
     */
    private VBox genInputPane() {
        final VBox box = new ColumnVBox();

        encodeButton.setOnAction((ActionEvent e) -> {
            outputTextField.setText(activeEncoder.encode(inputTextField.getText()));
        });

        decodeButton.setOnAction((ActionEvent e) -> {
            outputTextField.setText(activeEncoder.decode(inputTextField.getText()));
        });
        inputTextField.setPromptText("Type ALPHABETIC text here");

        HBox buttonBox = new HBox();
        addAllHJustify(buttonBox, encodeButton, decodeButton);
        box.getChildren().addAll(new Text("Input Text:"), inputTextField, buttonBox);
        return box;
    }

    /**
     * EFFECTS: generates the text output pane
     */
    private VBox genOutputPane() {
        final VBox box = new ColumnVBox();

        outputTextField.setPromptText("Output message appears here.");
        box.getChildren().addAll(new Text("Output Text:"), outputTextField);
        return box;
    }

    /**
     * REQUIRES: parent is a horizontal pane
     * MODIFIES: parameter parent
     * EFFECTS: attaches all children to parent with horizontal spacers
     */
    private void addAllHJustify(Pane parent, Region... children) {
        ArrayList<Region> childRegions = new ArrayList<>(Arrays.asList(children));
        Iterator<Region> regionIterator = childRegions.iterator();
        while (regionIterator.hasNext()) {
            parent.getChildren().add(regionIterator.next());
            if (regionIterator.hasNext()) {
                parent.getChildren().add(new HSpacer());
            }
        }
    }

    private void encoderChangeListener(MapChangeListener.Change<? extends String,? extends Encoder> change) {
        if (change.wasRemoved()) {
            sequenceEncodersBox.getItems().remove(change.getKey());
        } else if (change.wasAdded() && change.getKey().indexOf("Sequence") != -1) {
            sequenceEncodersBox.getItems().add(change.getKey());
        }
    }

    private void selectEncoder(ActionEvent e) {
        activeEncoder = encoders.get(encoderSelectionBox.getSelectionModel().getSelectedItem());
        titleContextText.setText(encoderSelectionBox.getSelectionModel().getSelectedItem());
        System.out.println("[DEBUG] active encoder changed to " + activeEncoder.toString()); //todo debugprint
    }

    private void selectEncoderType(ActionEvent e) {
        clearNewEncoderOptions();
        switch (encoderTypesBox.getSelectionModel().getSelectedItem()) {
            case "Sequence":
                newSequenceNameField.setVisible(true);
                break;
            case "AffineCipher":
                newAffineCipherBField.setVisible(true);
                newAffineCipherAField.setVisible(true);
                break;
            case "CaesarCipher":
                newCaesarCipherShiftField.setVisible(true);
                break;
            default:
        }
    }

    private void addEncoderUI(ActionEvent e) {
        switch (encoderTypesBox.getSelectionModel().getSelectedItem()) {
            case "Sequence":
                app.addEncoder(new CipherSequence(newSequenceNameField.getText() + "Sequence"));
                break;
            case "AtbashCipher":
                app.addEncoder(new AtbashCipher());
                break;
            case "AffineCipher":
                app.addEncoder(new AffineCipher(Integer.parseInt(newAffineCipherAField.getText()),
                        Integer.parseInt(newAffineCipherBField.getText())));
                break;
            case "CaesarCipher":
                app.addEncoder(new CaesarCipher(Integer.parseInt(newCaesarCipherShiftField.getText())));
                break;
            case "Rot13Cipher":
                app.addEncoder(new Rot13Cipher());
                break;
            default:
        }
    }

    private void removeEncoderUI(ActionEvent e) {
        app.deleteEncoder(encoderSelectionBox.getSelectionModel().getSelectedItem());
    }

    private void loadEncoderUI(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Error reading file");
        try {
            if (loadPath.getText().contains("Cipher")) {
                app.loadCipher(loadPath.getText());
            } else {
                app.loadSequence(loadPath.getText());
            }
        } catch (IOException ie) {
            alert.show();
        }
    }

    private void saveEncoderUI(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Error writing file");
        try {
            if (activeEncoder.getClass() == CipherSequence.class) {
                app.saveSequence(activeEncoder.toString());
            } else {
                app.saveCipher(activeEncoder.toString());
            }
        } catch (IOException ie) {
            alert.show();
        }
    }

    private void addEncoderToSeqUI(ActionEvent e) {
        if (activeEncoder.getClass() != CipherSequence.class) {
            app.getSequence(sequenceEncodersBox.getSelectionModel().getSelectedItem())
                    .pushCipher((Cipher) activeEncoder);
        }
        refreshSequenceContents();
    }

    private void removeEncoderFromSeqUI(ActionEvent e) {
        app.getSequence(sequenceEncodersBox.getSelectionModel().getSelectedItem())
                .removeCipher(Integer.parseInt(removeIndexField.getText()));
        refreshSequenceContents();
    }

    private void clearNewEncoderOptions() {
        newSequenceNameField.setVisible(false);
        newAffineCipherAField.setVisible(false);
        newAffineCipherBField.setVisible(false);
        newCaesarCipherShiftField.setVisible(false);
    }

    private void refreshSequenceContents() {
        sequenceContents.getItems().clear();
        for (Cipher c : app.getSequence(sequenceEncodersBox.getSelectionModel().getSelectedItem()).getCipherList()) {
            sequenceContents.getItems().add(c.toString());
        }
    }
}
