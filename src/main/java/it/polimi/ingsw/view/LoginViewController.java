package it.polimi.ingsw.view;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import it.polimi.ingsw.network.client.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable, ViewInterface {
    private static final int SOCKET = 1;
    private static final int RMI = 2;
    private Client client;
    private String  username;
    private int connectionType = SOCKET;
    private Stage stage;

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

    @FXML
    public void handleMouseClicked(MouseEvent event) {
        username = usernameField.getText();
        setConnection();
        try {
            changeScene();
        }catch(IOException e) {
            System.out.println("Errore nel cambio di scena");
        }
    }

    @FXML
    public void handleEnterPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            username = usernameField.getText();
            setConnection();
            try {
                changeScene();
            }catch(IOException e) {
                System.out.println("Errore nel cambio di scena");
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
            client.setName(username);
            client.setKindConnection(connectionType);
            client.connect();
        }
    }

    public void changeScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/waitingRoomGui.fxml"));
        Parent root = fxmlLoader.load();

        // Get the Controller from the FXMLLoader
        WaitingRoomViewController controller = fxmlLoader.getController();
        // Set data in the controller
        controller.setClient(client);
        controller.setStage(stage);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Waiting room");
        stage.show();
    }

    @Override
    public void sendDraftPools(String draftPools) {

    }

    @Override
    public void sendRestrictions(String restrictions) {

    }

    @Override
    public void sendWindowPatternCards(String windowPatternCards) {

    }

    @Override
    public void sendRoundTrack(String roundTrack) {

    }
}
