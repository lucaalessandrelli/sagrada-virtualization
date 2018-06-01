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
import javafx.scene.control.TextArea;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WaitingRoomViewController implements Initializable, ViewInterface {
    private Client client;
    private Stage stage;
    private ObservableList<String> list = FXCollections.observableArrayList();

    @FXML
    private JFXListView<String> playerList;
    @FXML
    private TextArea textArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //dovrei riuscire a prendere il nome del player da client e aggiungerlo tra quelli che si sono connessi
        //loadPlayers();
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

    }

    @Override
    public void handleAlert(String message) {
        AlertWindow.display("Alert", message);
    }
}
