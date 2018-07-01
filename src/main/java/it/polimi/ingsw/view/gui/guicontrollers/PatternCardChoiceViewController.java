package it.polimi.ingsw.view.gui.guicontrollers;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.view.SceneInterface;
import it.polimi.ingsw.view.gui.drawers.DrawPatternCard;
import it.polimi.ingsw.view.gui.GuiHandler;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.*;
import javafx.scene.layout.GridPane;
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
        /*no need to initialize something*/
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
        if (!isChosen) {
            Node source = (Node) event.getSource();
            source.setEffect(new DropShadow());
            int col = GridPane.getColumnIndex(source);
            int row = GridPane.getRowIndex(source);
            String patternCardToSend = givenPatternCards.get(2 * row + col);
            client.sendCommand("chooseCard "+idMatch+" "+client.getName()+" "+patternCardToSend);
            isChosen = true;
        }
    }

    private void changeScene() throws IOException {
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
        stage.setTitle("Partita");
        stage.show();
    }

    @Override
    public void handleTimer(String time) {
        this.matchTimer = time;
        try {
            changeScene();
        } catch (IOException e) {
            handleAlert("Errore nel cambio scena, riavviare il gioco.");
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
            List<String> restrictionList = Arrays.asList(substring.get(2).split(","));

            VBox currentVbox = (VBox)children.get(k);
            ObservableList<Node> boxChildren = currentVbox.getChildren();

            GridPane currentWindow = (GridPane)(boxChildren.get(0));
            GridPane favorGrid = (GridPane) (boxChildren.get(1));

            ObservableList<Node> tokenList = favorGrid.getChildren();

            /*redraw all favor tokens*/
            DrawPatternCard.removeFavTokens(tokenList);
            DrawPatternCard.drawFavTokens(tokenList,substring,favorGrid);


            for(int i = 0; i< 4;i++) {
                for(int j = 0; j<5;j++) {
                    DrawPatternCard.drawRestrictions(restrictionList.get(5*i+j) , currentWindow.getChildren().get(5*i+j));
                }
            }
        }
    }
}
