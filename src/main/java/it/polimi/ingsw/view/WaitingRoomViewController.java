package it.polimi.ingsw.view;

import com.jfoenix.controls.JFXListView;
import it.polimi.ingsw.network.client.Client;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation;

public class WaitingRoomViewController implements Initializable, ViewInterface {
    private final static String RESET_TIME = "30";
    private Client client;
    private Stage stage;
    private ObservableList<String> list = FXCollections.observableArrayList();
    private String time;
    private MessageAnalyzer messageAnalyzer;

    @FXML
    private JFXListView<String> playerList;
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
        this.list = list;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMessageAnalyzer(MessageAnalyzer messageAnalyzer) {
        this.messageAnalyzer = messageAnalyzer;
    }

    public void loadPlayers() {
        playerList.setItems(list);
        //textArea.appendText(player+" has connected.\n");
    }

    public void startTimer() {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> countTimer()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void countTimer() {
        if(!time.equals("0")) {
            time = Integer.toString(Integer.parseInt(time) - 1);
        } else {
            time = RESET_TIME;
        }
        timerLabel.setText("You are currently in queue. Waiting for players...                       Timer : " + time);
    }

    public void changeScene(String idMatch) throws IOException {
        /*FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/matchGui.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Match");
        stage.setResizable(true);
        stage.show();*/
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/matchGui.fxml"));
        Parent root = fxmlLoader.load();

        // Get the Controller from the FXMLLoader
        WaitingRoomViewController controller = fxmlLoader.getController();
        // Set data in the controller
        controller.setClient(client);
        //controller.setTime(time);
        controller.setMessageAnalyzer(messageAnalyzer);

        controller.setStage(stage);
        Scene scene = new Scene(root);

        //chiamate a metodi che devono essere eseguiti prima di visualizzare la gui
        //controller.loadPlayers();
        //controller.startTimer();
        messageAnalyzer.setView(controller);
        stage.setScene(scene);
        stage.setTitle("Match");
        stage.show();
    }


    @Override
    public void handleConnected(String message) {
    }

    @Override
    public void handleService(ObservableList<String> list) {
        this.list = list;
        loadPlayers();
    }

    @Override
    public void handleAlert(String message) {
        AlertWindow.display("Alert", message);
    }

    @Override
    public void handleTimer(String time) {

    }

    @Override
    public void handleMatchId(String idMatch) {
        //client.setMatchId(idMatch);
    }

    @Override
    public void handleMatchSetup(String matchSetup) {
        try {
            changeScene(matchSetup);
        } catch (IOException e) {
            System.out.println("Errore cambio di scena: waitingRoom -> Match");
        }
    }
}
