package it.polimi.ingsw.view.gui;

import com.jfoenix.controls.JFXButton;
import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.view.SceneInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;

public class ScoreViewController implements Initializable, SceneInterface {
    private Client client;
    private Stage stage;
    private ObservableList<String> connectedPlayerList = FXCollections.observableArrayList();
    private String time;
    private GuiHandler guiHandler;
    private ObservableList<User> userList = FXCollections.observableArrayList();

    @FXML
    private JFXButton playAgainButton;

    @FXML
    private Label announcementLabel;

    @FXML
    private TableView scoreTable;

    @FXML
    private TableColumn<User,String> playerColumn;

    @FXML
    private TableColumn<User,String> scoreColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    public void loadScores(String playerScore) {
        ObservableList<String> scoreList = FXCollections.observableArrayList(Arrays.asList(playerScore.split(",")));

        for (String score:scoreList) {
            ObservableList<String> currentPlayerScore = FXCollections.observableArrayList(Arrays.asList(score.split(" ")));
            User user = new User(currentPlayerScore.get(0));
            user.setScore(currentPlayerScore.get(1));
            userList.add(user);
        }

        playerColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        scoreTable.setItems(userList);
        displayWinner();
    }

    private void displayWinner() {
        int maxScore = -1;
        int tempScore;
        boolean draw = false;
        String winnerName = "";

        for (User user: userList) {
            tempScore = Integer.parseInt(user.getScore());
            if(tempScore > maxScore) {
                maxScore = tempScore;
                winnerName = user.getUsername();
            } else if (tempScore == maxScore) {
                draw = true;
            }
        }

        if(draw) {
            announcementLabel.setText("Pareggio!");
        } else {
            announcementLabel.setText(winnerName+" ha vinto la partita!");
        }

    }

    @FXML
    public void handleMouseClicked(MouseEvent event) {
        client.sendCommand("playAgain "+client.getName());
    }

    public void changeScene() throws IOException {
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

    @Override
    public void handleClientConnected(String message) {
    }

    @Override
    public void handleTurnMessage(String turnMessage) {
    }

    @Override
    public void handleConnectedPlayers(String connectedPlayers) {
        this.connectedPlayerList = FXCollections.observableArrayList(Arrays.asList(connectedPlayers.split(" ")));
    }

    @Override
    public void handleAlert(String message) {
        AlertWindow.display("Alert", message);
    }

    @Override
    public void handleTimer(String time) {
        this.time = time;
        try {
            changeScene();
        } catch (IOException e) {
            System.out.println("Errore cambio di scena: scoreView -> WaitingRoom");
        }
    }

    @Override
    public void handleMatchId(String idMatch) {

    }

    @Override
    public void updateBoard(String setup) {

    }

    @Override
    public void setPatternCards(String setup) {

    }

    @Override
    public void handleGameState(String gameState) {

    }

    @Override
    public void handleScore(String score) {

    }
}
