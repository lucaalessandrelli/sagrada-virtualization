package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.view.SceneInterface;
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
import javafx.scene.effect.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class PatternCardChoiceViewController implements Initializable, SceneInterface {
    private Client client;
    private Stage stage;
    private String cardChoiceTimer;
    private String matchTimer;
    private GuiHandler guiHandler;
    private String setup;
    private List<String> givenPatternCards = new ArrayList<>();
    private ObservableList<String> playerList;
    private boolean isChosen = false;
    private String idMatch;

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

    public void setList(ObservableList<String> playerList) {
        this.playerList = playerList;
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
        /*int indexChosen;

        if(!isChosen) {
            GridPane source = (GridPane) event.getSource();
            for(int i = 0; i < children.size();i++) {
                if(children.get(i) == source) {
                    indexChosen = i;
                }
            }
        }

        client.sendCommand("move "+client.getNumOfMatch()+" "+client.getName()+" P;"+x+","+y);*/
        if (!isChosen) {
            Node source = (Node) event.getSource();
            //Lighting lightingEffect = new Lighting();
            source.setEffect(new DropShadow());
            int y = patternCardGrid.getColumnIndex(source);
            int x = patternCardGrid.getRowIndex(source);

            String patternCardToSend = givenPatternCards.get(2 * x + y);
            System.out.println("Carta Scelta: "+patternCardToSend);
            client.sendCommand("chooseCard "+idMatch+" "+client.getName()+" "+patternCardToSend);
            isChosen = true;
        }
    }

    public void changeScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/matchGuiResizable.fxml"));
        Parent root = fxmlLoader.load();

        // Get the Controller from the FXMLLoader
        MatchViewController controller = fxmlLoader.getController();
        // Set data in the controller
        controller.setClient(client);
        controller.setGuiHandler(guiHandler);
        controller.setStage(stage);
        controller.setTime(matchTimer);
        controller.setList(playerList);

        Scene scene = new Scene(root);
        //chiamate a metodi che devono essere eseguiti prima di visualizzare la gui
        controller.startTimer();
        controller.updateBoard(setup);
        controller.updateStatusTable();
        guiHandler.setGui(controller);

        stage.setScene(scene);
        stage.setResizable(true);
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
        this.idMatch = idMatch;
        client.setNumMatch(Integer.parseInt(idMatch));
    }

    @Override
    public void updateBoard(String setup) {
        this.setup = setup;
    }

    @Override
    public void setPatternCards(String windows) {
        ObservableList<Node> children = patternCardGrid.getChildren();
        List<String> patternCardList = Arrays.asList(windows.split(";"));

        for(int k = 0; k < patternCardList.size();k++) {
            List<String> substring = Arrays.asList(patternCardList.get(k).split(" "));

            givenPatternCards.add(substring.get(0));
            String numFavors = substring.get(1);
            List<String> restrictionList = Arrays.asList(substring.get(2).split(","));

            VBox currentVbox = (VBox)children.get(k);
            ObservableList<Node> boxChildren = currentVbox.getChildren();

            GridPane currentWindow = (GridPane)(boxChildren.get(0));
            HBox favorBox = (HBox)(boxChildren.get(1));
            ObservableList<Node> tokenList = favorBox.getChildren();

            for(int i = 0; i < tokenList.size();i++) {
                if(i >= Integer.parseInt(numFavors)) {
                    tokenList.get(i).setVisible(false);
                }
            }

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
