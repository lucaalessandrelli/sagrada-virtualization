package it.polimi.ingsw.view.gui;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.view.MessageAnalyzer;
import it.polimi.ingsw.view.ViewInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable, ViewInterface {
    private static final int SOCKET = 1;
    private static final int RMI = 2;
    private Client client;
    private String  username;
    private int connectionType = SOCKET;
    private Stage stage;
    private MessageAnalyzer messageAnalyzer;
    private ObservableList<String> connectedPlayerList = FXCollections.observableArrayList();
    private String time;
    private String setup;

    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXCheckBox socketBox;
    @FXML
    private JFXCheckBox rmiBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMessageAnalyzer(MessageAnalyzer messageAnalyzer) {
        this.messageAnalyzer = messageAnalyzer;
    }

    @FXML
    public void handleMouseClicked(MouseEvent event) {
        username = usernameField.getText();
        if (!username.equals("")) {
            setConnection();
        }
    }

    @FXML
    public void handleEnterPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            username = usernameField.getText();
            if (!username.equals("")) {
                setConnection();
            }
        }
    }

    @FXML
    public void handleSocketBox() {
        if(socketBox.isSelected()) {
            rmiBox.setSelected(false);
            connectionType = SOCKET;
        }
    }

    @FXML
    public void handleRmiBox() {
        if(rmiBox.isSelected()) {
            socketBox.setSelected(false);
            connectionType = RMI;
        }
    }

    public void setConnection() {
        if (!username.equals("")) {
            messageAnalyzer.setView(this);

            client.setKindConnection(connectionType);
            client.setName(username);
            client.connect();
        }
    }

    public void changeSceneToWaitingRoom() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/waitingRoomGui.fxml"));
        Parent root = fxmlLoader.load();

        // Get the Controller from the FXMLLoader
        WaitingRoomViewController controller = fxmlLoader.getController();
        // Set data in the controller
        controller.setClient(client);
        controller.setList(connectedPlayerList);
        controller.setTime(time);
        controller.setStage(stage);
        controller.setMessageAnalyzer(messageAnalyzer);

        Scene scene = new Scene(root);
        controller.loadPlayers();
        controller.startTimer();
        messageAnalyzer.setView(controller);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Waiting room");
        stage.show();
    }

    public void changeSceneToMatch() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/matchGui.fxml"));
        Parent root = fxmlLoader.load();

        // Get the Controller from the FXMLLoader
        MatchViewController controller = fxmlLoader.getController();
        // Set data in the controller
        controller.setClient(client);
        controller.setMessageAnalyzer(messageAnalyzer);
        controller.setStage(stage);
        controller.setTime(time);

        Scene scene = new Scene(root);
        //chiamate a metodi che devono essere eseguiti prima di visualizzare la gui
        controller.startTimer();
        controller.updateBoard(setup);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Match");
        stage.show();
    }

    @Override
    public void handleClientConnected(String message) {
    }

    @Override
    public void handleTurnMessage(String turnMessage) {
    }

    @Override
    public void handleConnectedPlayers(String list) {
        this.connectedPlayerList = FXCollections.observableArrayList(Arrays.asList(list.split(" ")));
        try {
            changeSceneToWaitingRoom();
        } catch (IOException e) {
            System.out.println("Errore cambio di scena: login -> waitingRoom");
        }
    }

    @Override
    public void handleAlert(String message) {
        //AlertWindow.display("Alert", message);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    @Override
    public void handleTimer(String time) {
        this.time = time;
    }

    @Override
    public void handleMatchId(String idMatch) {
        client.setNumMatch(Integer.parseInt(idMatch));
        try {
            changeSceneToMatch();
        } catch (IOException e) {
            System.out.println("Errore cambio di scena: login -> Match");
        }
    }

    @Override
    public void updateBoard(String setup) {
        this.setup = setup;
    }

    @Override
    public void setPatternCards(String setup) {

    }

    @Override
    public void handleGameState(String gameState) {

    }

    @Override
    public void handleScore(String score) {

    }
}
