package it.polimi.ingsw.view.gui.guicontrollers;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.view.SceneInterface;
import it.polimi.ingsw.view.gui.*;
import it.polimi.ingsw.view.gui.data.User;
import it.polimi.ingsw.view.gui.data.ViewDice;
import it.polimi.ingsw.view.gui.drawers.DrawDraftPool;
import it.polimi.ingsw.view.gui.drawers.DrawPatternCard;
import it.polimi.ingsw.view.gui.drawers.DrawRoundTrack;
import it.polimi.ingsw.view.gui.drawers.GeneralFunctionalities;
import it.polimi.ingsw.view.gui.utilitywindows.IncDecWindow;
import it.polimi.ingsw.view.gui.utilitywindows.SelValueWindow;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MatchViewController implements Initializable, SceneInterface {
    private static final int DRAFT_LENGTH = 9;
    private static final String MOVE = "move";
    private static final String START = "StartTurn";
    private static final String DRAFTDICE1 = "ToolBeforeDice";
    private static final String DRAFTDICE2 = "SelectingDraftDice";
    private static final String ROUNDDICE1 = "SelectingRoundTrackDice";
    private static final String WINDOWDICE1 = "SelectingWindowDice";
    private static final String WINDOWDICE2 = "SelectingOptionalWindowDice";
    private static final String INCDECVALUE = "IncDecValue";
    private static final String SELECTVALUE = "SelectingValue";
    private static final String ALERT_DICE_DRAFT = "Non puoi scegliere un dado dalla Riserva in questa fase del turno.";
    private static final String ALERT_DICE_ROUNDTRACK = "Non puoi scegliere un dado dal Tracciato dei Round  in questa fase del turno.";
    private static final String ALERT_DICE_PATTERN = "Non puoi scegliere un dado dalla Vetrata in questa fase del turno.";

    private Client client;
    private Stage stage;
    private GuiHandler guiHandler;
    private ObservableList<String> connectedPlayers = FXCollections.observableArrayList();
    private ObservableList<String> gamePlayerlist = FXCollections.observableArrayList();
    private ObservableList<String> playerStatusList = FXCollections.observableArrayList();
    private ObservableList<TitledPane> listTitle = FXCollections.observableArrayList();
    private List<String> toolList = new ArrayList<>();
    private String time;
    private String score;
    private int diceChosenColumn = 0;
    private int diceChosenRow = 0;
    private List<ViewDice> diceList = new ArrayList<>();
    private String currentState = START;

    @FXML
    private TableColumn<User,String> userNameColumn;

    @FXML
    private TableColumn<User,String> userStatusColumn;

    @FXML
    private GridPane objectiveCardGrid;

    @FXML
    private GridPane roundTrackGrid;

    @FXML
    private GridPane toolCostGrid;

    @FXML
    private TableView<User> statusTable;

    @FXML
    private Label timerLabel;

    @FXML
    private TextField turnOwnerField;

    @FXML
    private GridPane toolCardGrid;

    @FXML
    private TitledPane myTitle;

    @FXML
    private ImageView privateObjectiveCard;

    @FXML
    private TitledPane titlePlayer2;

    @FXML
    private TitledPane titlePlayer3;

    @FXML
    private TitledPane titlePlayer4;

    @FXML
    private GridPane draftPool;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GeneralFunctionalities.fitImageToParent(privateObjectiveCard,(AnchorPane)privateObjectiveCard.getParent());

        for (Node node:toolCardGrid.getChildren()) {
            ImageView img = (ImageView) node;
            GeneralFunctionalities.fitImageToParent(img,toolCardGrid);
        }

        for (Node node:objectiveCardGrid.getChildren()) {
            ImageView img = (ImageView) node;
            GeneralFunctionalities.fitImageToParent(img,objectiveCardGrid);
        }
    }

    private void setTitleWindowPatternCard() {
        listTitle.addAll(myTitle,titlePlayer2,titlePlayer3,titlePlayer4);
        myTitle.setText(client.getName());
        int numTitle;

        for (String player:gamePlayerlist) {
            if(!player.equals(client.getName())) {
                numTitle = gamePlayerlist.indexOf(player);
                listTitle.get(numTitle).setText(player);
            }
        }
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
    public void setList(ObservableList<String> connectedPlayers) {
        this.connectedPlayers = connectedPlayers;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public void startTimer() {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> countTimer()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void countTimer() {
        if (!time.equals("0")) {
            time = Long.toString((Long.parseLong(time) - 1));
        } else {
            time = "0";
        }

        timerLabel.setText("Timer :" + time);
    }

    private void changeScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/scoreGui.fxml"));
        Parent root = fxmlLoader.load();

        // Get the Controller from the FXMLLoader
        ScoreViewController controller = fxmlLoader.getController();
        // Set data in the controller
        controller.setClient(client);
        controller.setGuiHandler(guiHandler);
        controller.setStage(stage);

        Scene scene = new Scene(root);
        //chiamate a metodi che devono essere eseguiti prima di visualizzare la gui
        controller.loadScores(score);
        guiHandler.setGui(controller);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Punteggi");
        stage.show();
    }

    @Override
    public void handleTurnMessage(String turnMessage) {
        turnOwnerField.setText(turnMessage);
    }

    @Override
    public void handleConnectedPlayers(String connPlayers) {
        connectedPlayers = FXCollections.observableArrayList(Arrays.asList(connPlayers.split(" ")));
        this.updateStatusTable();
    }

    @Override
    public void handleTimer(String time){
        this.setTime(time);
    }

    @Override
    public void handleGameState(String gameState) {
        this.currentState = gameState;

        if(currentState.equals(INCDECVALUE) || currentState.equals(SELECTVALUE)) {
            Node node = GeneralFunctionalities.getChildrenByIndex(draftPool,diceChosenRow,diceChosenColumn);
            ViewDice dice = GeneralFunctionalities.findDiceInfo(node,diceList);

            /*pop a window to let the player chose to increment or decrement the value of the dice*/
            if (currentState.equals(INCDECVALUE)) {
                Platform.runLater(() -> SelValueWindow.display(client, dice, diceChosenRow, diceChosenColumn, currentState));
            } else { /*pop a window to let the player chose the value of the dice*/
                Platform.runLater(() -> IncDecWindow.display(client, dice, diceChosenRow, diceChosenColumn, currentState));
            }
        }
    }

    @Override
    public void handleScore(String score) {
        this.score = score;
        try {
            changeScene();
        } catch (IOException e) {
            handleAlert("Errore nel cambio scena, riavviare il gioco.");
        }
    }

    @Override
    public void updateBoard(String setup) {
        /*divide the setup string into substrings which contains single messages*/
        List<String> messages = Arrays.asList(setup.split(";"));

        for (String subMessage: messages) {
            /*call a different method based on the start of the string*/
            if(subMessage.startsWith("gamePlayers")) {
                this.updateGamePlayers(subMessage.replace("gamePlayers ", ""));
            } else if(subMessage.startsWith("draftpool")) {
                this.updateDraftPool(subMessage.replace("draftpool ", ""));
            } else if(subMessage.startsWith("toolcards")) {
                this.updateToolCards(subMessage.replace("toolcards ", ""));
            } else if(subMessage.startsWith("publiccards")) {
                this.updateObjectCard(subMessage.replace("publiccards ", ""));
            } else if(subMessage.startsWith("privatecard")) {
                this.updatePrivateCard(subMessage.replace("privatecard ", ""));
            } else if(subMessage.startsWith("roundtrack")) {
                this.updateRoundTrack(subMessage.replace("roundtrack ", ""));
            } else if(subMessage.startsWith("restrictions")) {
                this.updateRestriction(subMessage.replace("restrictions ", ""));
            } else if(subMessage.startsWith("dices")) {
                this.updateWindowCards(subMessage.replace("dices ", ""));
            } else if(subMessage.startsWith("state")) {
                this.updatePlayersStatus(subMessage.replace("state ", ""));
            } else if(subMessage.startsWith("favors")) {
                this.updateFavTokens(subMessage.replace("favors ", ""));
            }
        }
    }

    public void updateStatusTable() {
        ObservableList<User> userList = FXCollections.observableArrayList();
        ObservableList<String> playerInfo;

        for (String username:connectedPlayers) {
            for (String player: playerStatusList) {
                playerInfo = FXCollections.observableArrayList(Arrays.asList(player.split(" ")));
                if(username.equals(playerInfo.get(0))) {
                    userList.add(new User(username,playerInfo.get(1)));
                }
            }
        }

        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        userStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusTable.setItems(userList);
    }

    private void updateGamePlayers(String gamePlayers) {
        gamePlayerlist = FXCollections.observableArrayList(Arrays.asList(gamePlayers.split(",")));
        this.setTitleWindowPatternCard();
    }

    private void updatePlayersStatus(String status) {
        playerStatusList = FXCollections.observableArrayList(Arrays.asList(status.split(",")));
        this.updateStatusTable();
    }

    private void updateFavTokens(String favors) {
        List<String> favorsList = Arrays.asList(favors.split(","));

        String player = favorsList.get(0);

        VBox vBox = new VBox();

        for (TitledPane titlePane: listTitle) {
            if(titlePane.getText().equals(player)) {
                vBox = (VBox)((Parent) titlePane.getContent()).getChildrenUnmodifiable().get(0);
            }
        }

        ObservableList<Node> boxChildren = vBox.getChildren();
        GridPane favorGrid = (GridPane) (boxChildren.get(1));
        ObservableList<Node> tokenList = favorGrid.getChildren();

        /*redraw all favor tokens*/
        DrawPatternCard.removeFavTokens(tokenList);
        DrawPatternCard.drawFavTokens(tokenList,favorsList,favorGrid);
    }

    private void updateObjectCard(String objectCard) {
        List<String> objectCardList = Arrays.asList(objectCard.split("\\\\"));
        ObservableList<Node> gridChildren = objectiveCardGrid.getChildren();

        for(int i = 0; i < objectCardList.size();i++) {
            List<String> infoObjectiveCard = Arrays.asList(objectCardList.get(i).split("/"));
            ((javafx.scene.image.ImageView)gridChildren.get(i)).setImage(new Image ("/objectivecards/public/"+infoObjectiveCard.get(0)+".png"));
        }
    }

    private void updatePrivateCard(String privateCard) {
        List<String> infoPrivateCard = Arrays.asList(privateCard.split(","));
        privateObjectiveCard.setImage(new Image ("/objectivecards/private/"+infoPrivateCard.get(0)+".png"));
    }

    private void updateToolCards(String toolCard) {
        List<String> tools = Arrays.asList(toolCard.split("\\\\"));
        ObservableList<Node> toolGridChildren = toolCardGrid.getChildren();
        ObservableList<Node> toolCostGridChildren = toolCostGrid.getChildren();

        for(int i = 0; i < tools.size();i++) {
            List<String> infoToolCard = Arrays.asList(tools.get(i).split("/"));
            ((javafx.scene.image.ImageView)toolGridChildren.get(i)).setImage(new Image ("/toolcards/"+infoToolCard.get(0)+".png"));
            toolList.add(i,infoToolCard.get(0));

            if(Integer.parseInt(infoToolCard.get(1)) > 1) {
                toolGridChildren.get(i).setEffect(new DropShadow());
            }
            Text txt = (Text)toolCostGridChildren.get(i);
            txt.setText(infoToolCard.get(1));
        }
    }

    private void updateRoundTrack(String roundTrackList) {
        List<String> cellList = Arrays.asList(roundTrackList.split(","));

        GeneralFunctionalities.removeDiceFromGrid(roundTrackGrid,diceList);

        for (String cell: cellList) {
            if(!cell.equals("")) {
                int col = Character.getNumericValue(cell.charAt(2));
                int row = Character.getNumericValue(cell.charAt(3));
                DrawRoundTrack.drawDiceRoundTrack(roundTrackGrid,row,col,diceList,cell,this);
            }
        }
    }

    private void updateWindowCards(String windowPatternCard) {
        boolean isMyWindow = false;
        List<String> cellList = Arrays.asList(windowPatternCard.split(","));

        String player = cellList.get(0);

        if(player.equals(client.getName())) {
            isMyWindow = true;
        }

        VBox vBox = new VBox();

        for (TitledPane titlePane: listTitle) {
            if(titlePane.getText().equals(player)) {
                vBox = (VBox)((Parent) titlePane.getContent()).getChildrenUnmodifiable().get(0);
            }
        }

        ObservableList<Node> boxChildren = vBox.getChildren();
        GridPane currentWindow = (GridPane)(boxChildren.get(0));

        /*Removes all dices from the player window,plus delete them from viewDiceList*/
        if(isMyWindow) {
            GeneralFunctionalities.removeDiceFromGrid(currentWindow,diceList);
        }

        for (String cell: cellList) {
            if(!cell.equals(player)) {
                int row = Character.getNumericValue(cell.charAt(2));
                int col = Character.getNumericValue(cell.charAt(3));
                DrawPatternCard.drawDicePatternCard(currentWindow,row,col,isMyWindow,diceList,cell,this);
            }
        }
    }

    private void updateRestriction(String resctriction) {
        List<String> restrictionList = Arrays.asList(resctriction.split(","));

        String player = restrictionList.get(0);

        VBox vBox = new VBox();

        for (TitledPane titlePane: listTitle) {
            if(titlePane.getText().equals(player)) {
                vBox = (VBox)((Parent) titlePane.getContent()).getChildrenUnmodifiable().get(0);
            }
        }

        ObservableList<Node> boxChildren = vBox.getChildren();
        GridPane currentWindow = (GridPane)(boxChildren.get(0));

        for(int i = 0; i< 4;i++) {
            for(int j = 0; j<5;j++) {
                DrawPatternCard.drawRestrictions(restrictionList.get(5*i+j+1) , currentWindow.getChildren().get(5*i+j));
            }
        }
    }

    private void updateDraftPool(String draftPoolInfo) {
        List<String> draftList = Arrays.asList(draftPoolInfo.split(","));

        for(int i = 0; i < DRAFT_LENGTH;i++) {
            DrawDraftPool.drawDiceIntoDraft(draftPool,i,draftList,diceList,this);
        }
    }

    public void handleRoundTrackDiceClicked(MouseEvent mouseEvent) {
        if(!(currentState.equals(WINDOWDICE1) || currentState.equals(WINDOWDICE2) ||
                currentState.equals(DRAFTDICE1) || currentState.equals(DRAFTDICE2) ||
                currentState.equals(START) ||currentState.equals(INCDECVALUE) || currentState.equals(SELECTVALUE))) {
            Node source = (Node) mouseEvent.getSource();
            source.setEffect(new DropShadow());
            ViewDice dice = GeneralFunctionalities.findDiceInfo(source,diceList);
            client.sendCommand(MOVE+" "+client.getNumOfMatch()+" "+client.getName()+" D;"+dice.getDiceNumber()+","+dice.getDiceColor()+","+GridPane.getColumnIndex(source.getParent())+","+GridPane.getRowIndex(source.getParent()));
        } else {
            handleAlert(ALERT_DICE_ROUNDTRACK);
        }
        mouseEvent.consume();
    }


    public void handleWindowDiceClicked(MouseEvent mouseEvent) {
        if(!(currentState.equals(START) || currentState.equals(ROUNDDICE1) ||
                currentState.equals(DRAFTDICE1) || currentState.equals(DRAFTDICE2) ||
                currentState.equals(INCDECVALUE) || currentState.equals(SELECTVALUE))) {
            Node source = (Node) mouseEvent.getSource();
            source.setEffect(new DropShadow());
            ViewDice dice = GeneralFunctionalities.findDiceInfo(source,diceList);
            client.sendCommand(MOVE+" "+client.getNumOfMatch()+" "+client.getName()+" D;"+dice.getDiceNumber()+","+dice.getDiceColor()+","+GridPane.getRowIndex(source.getParent())+","+GridPane.getColumnIndex(source.getParent()));
        } else {
            handleAlert(ALERT_DICE_PATTERN);
        }
        mouseEvent.consume();
    }

    public void handleDraftDiceClicked(MouseEvent mouseEvent) {
        if(!(currentState.equals(WINDOWDICE1) || currentState.equals(WINDOWDICE2) ||
                currentState.equals(ROUNDDICE1) || currentState.equals(INCDECVALUE) || currentState.equals(SELECTVALUE))) {
            Node source = (Node) mouseEvent.getSource();
            source.setEffect(new DropShadow());
            ViewDice dice = GeneralFunctionalities.findDiceInfo(source,diceList);
            int col = GridPane.getColumnIndex(source.getParent());
            int row = GridPane.getRowIndex(source.getParent());
            diceChosenRow = row;
            diceChosenColumn = col;
            int x = draftPool.getColumnConstraints().size()*row+col;
            client.sendCommand(MOVE+" "+client.getNumOfMatch()+" "+client.getName()+" D;"+dice.getDiceNumber()+","+dice.getDiceColor()+","+x+",0");
        } else {
            handleAlert(ALERT_DICE_DRAFT);
        }
        mouseEvent.consume();
    }

    @FXML
    public void handlePassClicked(MouseEvent event) {
        client.sendCommand(MOVE+" "+client.getNumOfMatch()+" "+client.getName()+" pass");
        event.consume();
    }

    @FXML
    public void handleToolCardClicked(MouseEvent event) {
        client.sendCommand(MOVE+" "+client.getNumOfMatch()+" "+client.getName()+" T;"+toolList.get(GridPane.getColumnIndex((Node)event.getSource())));

        event.consume();
    }
    @FXML
    void handleCellClicked(MouseEvent event) {
        int y = GridPane.getColumnIndex((Node) event.getSource());
        int x = GridPane.getRowIndex((Node) event.getSource());
        client.sendCommand("move "+client.getNumOfMatch()+" "+client.getName()+" P;"+x+","+y);
        event.consume();
    }
}