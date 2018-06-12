package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.network.client.Client;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class PatternCardChoiceViewController implements Initializable, GuiInterface {
    private Client client;
    private Stage stage;
    private String cardChoiceTimer;
    private String matchTimer;
    private GuiHandler guiHandler;
    private String setup;
    private List<String> givenPatternCards = new ArrayList<>();

    @FXML
    private Label timerLabel;

    @FXML
    private GridPane patternCardGrid;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setClient(Client client) {
        this.client = client;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setTime(String time) {
        this.cardChoiceTimer = time;
    }
    public void setGuiHandler(GuiHandler guiHandler) {
        this.guiHandler = guiHandler;
    }

    public void startTimer() {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> countTimer()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void countTimer() {
        if(!cardChoiceTimer.equals("0")) {
            cardChoiceTimer = Long.toString((Long.parseLong(cardChoiceTimer) - 1));
        } else {
            cardChoiceTimer = "0";
        }

        timerLabel.setText("Timer : " + cardChoiceTimer);
    }

    @FXML
    public void handleCardChoice(javafx.scene.input.MouseEvent event) {
        //gestisci evento
        //risali al numero della carta scelta e mandalo al client
    }

    public void changeScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/matchGui2.fxml"));
        Parent root = fxmlLoader.load();

        // Get the Controller from the FXMLLoader
        MatchViewController controller = fxmlLoader.getController();
        // Set data in the controller
        controller.setClient(client);
        controller.setGuiHandler(guiHandler);
        controller.setStage(stage);
        controller.setTime(matchTimer);

        Scene scene = new Scene(root);
        //chiamate a metodi che devono essere eseguiti prima di visualizzare la gui
        controller.startTimer();
        controller.updateBoard(setup);
        guiHandler.setGui(controller);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Match");
        stage.show();
    }

    @Override
    public void handleClientConnected(String message) {
    }

    @Override
    public void handleTurnMessage(String turnMessage) {
    }

    @Override
    public void handleConnectedPlayers(String playerList) {
    }

    @Override
    public void handleAlert(String message) {
        //AlertWindow.display("Alert", message);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    @Override
    public void handleTimer(String time) {
        this.matchTimer = time;
        try {
            changeScene();
        } catch (IOException e) {
            System.out.println("Errore cambio di scena: patternCardChoice -> Match");
        }
    }

    @Override
    public void handleMatchId(String idMatch) {
        client.setNumMatch(Integer.parseInt(idMatch));
    }

    @Override
    public void updateBoard(String setup) {
        this.setup = setup;
    }

    @Override
    public void setPatternCards(String windows) {
        ObservableList<Node> children = patternCardGrid.getChildren();
        ObservableList<String> patternCardList = FXCollections.observableArrayList(Arrays.asList(windows.split(";")));

        for(int k = 0; k < patternCardList.size();k++) {
            List<String> substring = FXCollections.observableArrayList(Arrays.asList(patternCardList.get(k).split(" ")));

            givenPatternCards.add(substring.get(0));
            List<String> restrictionList = FXCollections.observableArrayList(Arrays.asList(substring.get(1).split(",")));
            GridPane currentWindow = (GridPane)(children.get(k));

            for(int i = 0; i< 4;i++) {
                for(int j = 0; j<5;j++) {
                    this.setRestriction(restrictionList.get(5*i+j) , currentWindow.getChildren().get(5*i+j));
                }
            }
        }
    }

    public void setRestriction(String restriction, Node anchorPane) {
        char colorRestriction = restriction.charAt(0);
        char shadeRestriction = restriction.charAt(1);


        if(colorRestriction == 'P') {
            anchorPane.setStyle("-fx-background-color: purple");
        } else if(colorRestriction == 'R') {
            anchorPane.setStyle("-fx-background-color: red");
        } else if(colorRestriction == 'B') {
            anchorPane.setStyle("-fx-background-color: blue");
        } else if(colorRestriction == 'Y') {
            anchorPane.setStyle("-fx-background-color: yellow");
        } else if(colorRestriction == 'G') {
            anchorPane.setStyle("-fx-background-color: green");
        }

        if(shadeRestriction!= '0') {
            anchorPane.setStyle("-fx-background-image: url('/restrictions/"+shadeRestriction+".png');"+
                    "-fx-background-size: contain;"+
                    "-fx-background-repeat: no-repeat;"+
                    "-fx-background-position: center center;");
        }
    }

    @Override
    public void handleGameState(String gameState) {

    }

    @Override
    public void handleScore(String score) {

    }
}
