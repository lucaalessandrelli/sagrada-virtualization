package it.polimi.ingsw.view;

import com.jfoenix.controls.JFXListView;
import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.MessageQueue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ScoreViewController implements Initializable, ViewInterface {
    private Client client;
    private Stage stage;
    private ObservableList<String> connectedPlayerList = FXCollections.observableArrayList();
    private String time;
    private MessageAnalyzer messageAnalyzer;

    @FXML
    private JFXListView<String> scoreList;

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




    public void loadScores(String score) {
        String us1 = "Luca";
        String us2 = "Vincenzo";
        String us3 = "Gionny";
        connectedPlayerList.addAll(us1,us2,us3);
        scoreList.setItems(connectedPlayerList);
    }

    public void changeScene() throws IOException {
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

    @Override
    public void handleClientConnected(String message) {
    }

    @Override
    public void handleTurnMessage(String turnMessage) {
    }

    @Override
    public void handleConnectedPlayers(String connectedPlayers) {
        this.connectedPlayerList = FXCollections.observableArrayList(Arrays.asList(connectedPlayers.split(" ")));
    }

    @Override
    public void handleAlert(String message) {
        AlertWindow.display("Alert", message);
    }

    @Override
    public void handleTimer(String time) {
        this.time = time;
        try {
            changeScene();
        } catch (IOException e) {
            System.out.println("Errore cambio di scena: scoreView -> WaitingRoom");
        }
    }

    @Override
    public void handleMatchId(String idMatch) {

    }

    @Override
    public void updateBoard(String setup) {

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
