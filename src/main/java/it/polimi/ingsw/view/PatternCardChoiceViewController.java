package it.polimi.ingsw.view;

import it.polimi.ingsw.network.client.Client;
import javafx.animation.Animation;
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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class PatternCardChoiceViewController implements Initializable, ViewInterface {
    private Client client;
    private Stage stage;
    private String cardChoiceTimer;
    private String matchTimer;
    private MessageAnalyzer messageAnalyzer;

    @FXML
    private Label timerLabel;

    @FXML
    private HBox patternCardBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setClient(Client client) {
        this.client = client;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setTime(String time) {
        this.cardChoiceTimer = time;
    }
    public void setMessageAnalyzer(MessageAnalyzer messageAnalyzer) {
        this.messageAnalyzer = messageAnalyzer;
    }

    public void startTimer() {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> countTimer()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void countTimer() {
        if(!cardChoiceTimer.equals("0")) {
            cardChoiceTimer = Long.toString((Long.parseLong(cardChoiceTimer) - 1));
        } else {
            cardChoiceTimer = "0";
        }

        timerLabel.setText("Timer : " + cardChoiceTimer);
    }

    @FXML
    public void handleCardChoice(javafx.scene.input.MouseEvent event) {
        //gestisci evento
        //risali al numero della carta scelta e mandalo al client
    }

    public void changeScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/matchGui.fxml"));
        Parent root = fxmlLoader.load();

        // Get the Controller from the FXMLLoader
        MatchViewController controller = fxmlLoader.getController();
        // Set data in the controller
        controller.setClient(client);
        controller.setMessageAnalyzer(messageAnalyzer);
        controller.setStage(stage);
        controller.setTime(matchTimer);

        Scene scene = new Scene(root);
        //chiamate a metodi che devono essere eseguiti prima di visualizzare la gui
        controller.startTimer();
        messageAnalyzer.setView(controller);

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
    public void handleConnectedPlayers(String playerList) {
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
        this.matchTimer = time;
        try {
            changeScene();
        } catch (IOException e) {
            System.out.println("Errore cambio di scena: patternCardChoice -> Match");
        }
    }

    @Override
    public void handleMatchId(String idMatch) {
        client.setNumMatch(Integer.parseInt(idMatch));
    }

    @Override
    public void updateBoard(String setup) {

    }

    @Override
    public void setPatternCards(String setup) {
        //setCards
    }

    @Override
    public void handleGameState(String gameState) {

    }

    @Override
    public void handleScore(String score) {

    }
}
