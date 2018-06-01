package it.polimi.ingsw.view;

import com.jfoenix.controls.JFXListView;
import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.MessageQueue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.util.Duration;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class WaitingRoomViewController implements Initializable, ViewInterface {
    private Client client;
    private Stage stage;
    private ObservableList<String> list = FXCollections.observableArrayList();
    private String time = "30";

    @FXML
    private JFXListView<String> playerList;
    @FXML
    private TextArea textArea;
    @FXML
    private Label timerLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> doTimer()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
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

    public void loadPlayers() {
        playerList.setItems(list);
        //textArea.appendText(player+" has connected.\n");
    }

    public void doTimer() {
        if(!time.equals("0")) {
            time = Integer.toString(Integer.parseInt(time) - 1);
        } else {
            time = "30";
        }
        timerLabel.setText("You are currently in queue. Waiting for players...                       Timer : " + time);
    }

    public void changeScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/matchGui.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Match");
        stage.setResizable(true);
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
}
