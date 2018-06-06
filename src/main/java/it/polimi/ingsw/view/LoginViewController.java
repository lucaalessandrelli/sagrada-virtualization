package it.polimi.ingsw.view;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import it.polimi.ingsw.network.client.Client;
import javafx.collections.FXCollections;
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
import java.util.Arrays;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable, ViewInterface {
    private static final int SOCKET = 1;
    private static final int RMI = 2;
    private Client client;
    private String  username;
    private int connectionType = SOCKET;
    private Stage stage;
    private MessageAnalyzer messageAnalyzer;
    private ObservableList<String> list = FXCollections.observableArrayList();

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

    public void changeScene(String time) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/waitingRoomGui.fxml"));
        Parent root = fxmlLoader.load();

        // Get the Controller from the FXMLLoader
        WaitingRoomViewController controller = fxmlLoader.getController();
        // Set data in the controller
        controller.setClient(client);
        controller.setList(list);
        controller.setTime(time);
        controller.setStage(stage);
        controller.setMessageAnalyzer(messageAnalyzer);

        Scene scene = new Scene(root);
        controller.loadPlayers();
        controller.startTimer();
        messageAnalyzer.setView(controller);
        stage.setScene(scene);
        stage.setTitle("Waiting room");
        stage.show();
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
        controller.setList(list);
        //controller.setTime(time);

        Scene scene = new Scene(root);
        //chiamate a metodi che devono essere eseguiti prima di visualizzare la gui
        //controller.loadPlayers();
        //controller.startTimer();
        controller.setWindows();
        controller.startTimer();
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
    public void handleService(String list) {
        this.list = FXCollections.observableArrayList(Arrays.asList(list.split(" ")));
    }

    @Override
    public void handleAlert(String message) {
        AlertWindow.display("Alert", message);
    }

    @Override
    public void handleTimer(String time) {
        try {
            changeScene(time);
        } catch (IOException e) {
            System.out.println("Errore nel cambio scene");
        }
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
