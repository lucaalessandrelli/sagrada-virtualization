package it.polimi.ingsw.view.gui.guicontrollers;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.view.SceneInterface;
import it.polimi.ingsw.view.gui.GuiHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable, SceneInterface {
    private static final int SOCKET = 1;
    private static final int RMI = 2;
    private Client client;
    private String  username;
    private int connectionType = SOCKET;
    private Stage stage;
    private GuiHandler guiHandler;
    private ObservableList<String> connectedPlayerList = FXCollections.observableArrayList();
    private String time;
    private String setup;

    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXCheckBox socketBox;
    @FXML
    private JFXCheckBox rmiBox;

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

    public void setGuiHandler(GuiHandler guiHandler) {
        this.guiHandler = guiHandler;
    }

    @FXML
    public void handleMouseClicked() {
        username = usernameField.getText();

        if(username.equals("") || username.contains(" ")) {
            handleAlert("Nome utente non valido: un nome valido non contiene spazi e non è vuoto.");
        } else {
            setConnection();
        }
    }

    @FXML
    public void handleEnterPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            username = usernameField.getText();
            if(username.equals("") || username.contains(" ")) {
                handleAlert("Nome utente non valido: un nome valido non contiene spazi e non è vuoto.");
            } else {
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

    private void setConnection() {
        if (!username.equals("")) {
            guiHandler.setGui(this);

            client.setName(username);
            client.setKindConnection(connectionType);
            client.connect();
        }
    }

    private void changeSceneToWaitingRoom() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/waitingRoomGui.fxml"));
        Parent root = fxmlLoader.load();

        // Get the Controller from the FXMLLoader
        WaitingRoomViewController controller = fxmlLoader.getController();
        // Set data in the controller
        controller.setClient(client);
        controller.setList(connectedPlayerList);
        controller.setTime(time);
        controller.setStage(stage);
        controller.setGuiHandler(guiHandler);

        Scene scene = new Scene(root);
        controller.loadPlayers();
        controller.startTimer();
        guiHandler.setGui(controller);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Sala d'attesa");
        stage.show();
    }

    private void changeSceneToMatch() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/matchGuiResizable.fxml"));
        Parent root = fxmlLoader.load();

        // Get the Controller from the FXMLLoader
        MatchViewController controller = fxmlLoader.getController();
        // Set data in the controller
        controller.setClient(client);
        controller.setGuiHandler(guiHandler);
        controller.setStage(stage);
        controller.setTime(time);
        //problema: quando ricevo la lista dei giocatori passo alla schermata di waiting room
        // controller.setList(playerList);

        Scene scene = new Scene(root);
        // Calling methods that need to be executed before showing the gui
        controller.startTimer();
        guiHandler.setGui(controller);

        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("Partita");
        stage.show();
    }

    @Override
    public void handleConnectedPlayers(String list) {
        this.connectedPlayerList = FXCollections.observableArrayList(Arrays.asList(list.split(" ")));
        try {
            changeSceneToWaitingRoom();
        } catch (IOException e) {
            handleAlert("Errore nel cambio scena, riavviare il gioco.");
        }
    }

    @Override
    public void handleTimer(String time) {
        this.time = time;
    }

    @Override
    public void handleMatchId(String idMatch) {
        client.setNumMatch(Integer.parseInt(idMatch));
        try {
            changeSceneToMatch();
        } catch (IOException e) {
            handleAlert("Errore nel cambio scena, riavviare il gioco.");
        }
    }

    @Override
    public void updateBoard(String setup) {
        this.setup = setup;
    }
}
