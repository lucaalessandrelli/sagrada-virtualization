package it.polimi.ingsw.view.gui;

import com.jfoenix.controls.JFXListView;
import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.view.MessageAnalyzer;
import it.polimi.ingsw.view.ViewInterface;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.animation.Animation;

public class WaitingRoomViewController implements Initializable, ViewInterface {
    private Client client;
    private Stage stage;
    private ObservableList<String> playerList = FXCollections.observableArrayList();
    private String fixedTime;
    private String tempTime;
    private String choseCardTimer;
    private MessageAnalyzer messageAnalyzer;

    @FXML
    private JFXListView<String> playerListView;
    @FXML
    private TextArea textArea;
    @FXML
    private Label timerLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setList(ObservableList<String> list) {
        this.playerList = list;
    }

    public void setTime(String time) {
        this.fixedTime = time;
        this.tempTime = time;
    }

    public void setMessageAnalyzer(MessageAnalyzer messageAnalyzer) {
        this.messageAnalyzer = messageAnalyzer;
    }

    public void loadPlayers() {
        playerListView.setItems(playerList);
    }

    public void startTimer() {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> countTimer()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void countTimer() {
        if(playerList.size() >= 2 && !tempTime.equals("0")) {
            tempTime = Long.toString((Long.parseLong(tempTime) - 1));
        } else {
            tempTime = fixedTime;
        }

        timerLabel.setText("You are currently in queue. Waiting for players...                       Timer : " + tempTime);
    }

    public void changeScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/patternCardChoiceGui.fxml"));
        Parent root = fxmlLoader.load();

        // Get the Controller from the FXMLLoader
        PatternCardChoiceViewController controller = fxmlLoader.getController();
        // Set data in the controller
        controller.setClient(client);
        controller.setMessageAnalyzer(messageAnalyzer);
        controller.setStage(stage);
        controller.setTime(choseCardTimer);

        Scene scene = new Scene(root);
        //chiamate a metodi che devono essere eseguiti prima di visualizzare la gui
        controller.startTimer();
        messageAnalyzer.setView(controller);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("PatternCard Choice");
        stage.show();
    }


    @Override
    public void handleClientConnected(String message) {
    }

    @Override
    public void handleTurnMessage(String turnMessage) {
    }

    @Override
    public void handleConnectedPlayers(String playerList) {
        this.playerList = FXCollections.observableArrayList(Arrays.asList(playerList.split(" ")));
        loadPlayers();
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
        this.choseCardTimer = time;
        try {
            changeScene();
        } catch (IOException e) {
            System.out.println("Errore cambio di scena: waitingRoom -> PatternCardChoiceView");
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
