package it.polimi.ingsw.view;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.MessageQueue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable, ViewInterface {
    private static final int SOCKET = 1;
    private static final int RMI = 2;
    private Client client;
    private String  username;
    private int connectionType = SOCKET;
    private Stage stage;
    private MessageAnalyzer messageAnalyzer;

    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXCheckBox socketBox;
    @FXML
    private JFXCheckBox rmiBox;

    @FXML
    private JFXTextField test;

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

    public void changeScene(ObservableList<String> list) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/waitingRoomGui.fxml"));
        Parent root = fxmlLoader.load();

        // Get the Controller from the FXMLLoader
        WaitingRoomViewController controller = fxmlLoader.getController();
        // Set data in the controller
        controller.setClient(client);
        controller.setList(list);
        controller.setStage(stage);

        Scene scene = new Scene(root);
        controller.loadPlayers();
        messageAnalyzer.setView(controller);
        stage.setScene(scene);
        stage.setTitle("Waiting room");
        stage.show();
    }

    @Override
    public void handleConnected(String message) {
    }

    @Override
    public void handleService(ObservableList<String> list) {
        try {
            changeScene(list);
        } catch (IOException e) {
            System.out.println("Errore nel cambio scene");
        }
    }

    @Override
    public void handleAlert(String message) {
        AlertWindow.display("Alert", message);
    }

    @FXML
    public void handleTest(KeyEvent keyEvent) {
        AlertWindow.display("Alert","Sbagliato a fare il login");
    }
}
