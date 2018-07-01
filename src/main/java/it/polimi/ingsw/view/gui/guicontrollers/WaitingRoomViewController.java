package it.polimi.ingsw.view.gui.guicontrollers;

import com.jfoenix.controls.JFXListView;
import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.view.SceneInterface;
import it.polimi.ingsw.view.gui.GuiHandler;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class WaitingRoomViewController implements Initializable, SceneInterface {
    private Client client;
    private Stage stage;
    private ObservableList<String> playerList = FXCollections.observableArrayList();
    private String fixedTime;
    private String tempTime;
    private String choseCardTimer;
    private GuiHandler guiHandler;

    @FXML
    private JFXListView<String> playerListView;

    @FXML
    private Label timerLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*no need to initialize something*/
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

    public void setGuiHandler(GuiHandler guiHandler) {
        this.guiHandler = guiHandler;
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

        timerLabel.setText("Sei attualmente in coda. Aspettando ulteriori giocatori...                       Timer : " + tempTime);
    }

    private void changeScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/patternCardChoiceGui.fxml"));
        Parent root = fxmlLoader.load();

        // Get the Controller from the FXMLLoader
        PatternCardChoiceViewController controller = fxmlLoader.getController();
        // Set data in the controller
        controller.setClient(client);
        controller.setGuiHandler(guiHandler);
        controller.setStage(stage);
        controller.setTime(choseCardTimer);
        controller.setList(playerList);

        Scene scene = new Scene(root);
        //chiamate a metodi che devono essere eseguiti prima di visualizzare la gui
        controller.startTimer();
        guiHandler.setGui(controller);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Scelta della vetrata");
        stage.show();
    }

    @Override
    public void handleConnectedPlayers(String playerList) {
        this.playerList = FXCollections.observableArrayList(Arrays.asList(playerList.split(" ")));
        loadPlayers();
    }

    @Override
    public void handleTimer(String time) {
        this.choseCardTimer = time;
        try {
            changeScene();
        } catch (IOException e) {
            handleAlert("Errore nel cambio scena, riavviare il gioco.");
        }
    }
}