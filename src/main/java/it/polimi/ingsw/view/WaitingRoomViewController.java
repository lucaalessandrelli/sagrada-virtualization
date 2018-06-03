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
        if(list.size() >= 2) {
            if (!time.equals("0")) {
                time = Long.toString((Long.parseLong(time) - 1));
            } else {
                time = "0";
            }
        }

        timerLabel.setText("You are currently in queue. Waiting for players...                       Timer : " + time);
    }

    public void changeScene() throws IOException {
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
        MatchViewController controller = fxmlLoader.getController();
        // Set data in the controller
        controller.setClient(client);
        controller.setMessageAnalyzer(messageAnalyzer);
        controller.setStage(stage);
        //controller.setTime(time);

        Scene scene = new Scene(root);
        //chiamate a metodi che devono essere eseguiti prima di visualizzare la gui
        //controller.loadPlayers();
        //controller.startTimer();
        messageAnalyzer.setView(controller);

        stage.setScene(scene);
        stage.setResizable(true);
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
        client.setNumMatch(Integer.parseInt(idMatch));
        try {
            changeScene();
        } catch (IOException e) {
            System.out.println("Errore cambio di scena: waitingRoom -> Match");
        }

    }

    @Override
    public void updateBoard(String setup) {

    }
}
