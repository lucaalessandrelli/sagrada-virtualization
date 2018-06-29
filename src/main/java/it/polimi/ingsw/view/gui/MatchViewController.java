package it.polimi.ingsw.view.gui;

import com.jfoenix.controls.JFXButton;
import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.view.SceneInterface;
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
    private static final String START = "StartTurn";
    private static final String DRAFTDICE1 = "ToolBeforeDice";
    private static final String DRAFTDICE2 = "SelectingDraftDice";
    private static final String ROUNDDICE1 = "SelectingRoundTrackDice";
    private static final String WINDOWDICE1 = "SelectingWindowDice";
    private static final String WINDOWDICE2 = "SelectingOptionalWindowDice";
    private static final String INCDECVALUE = "IncDecValue";
    private static final String SELECTVALUE = "SelectingValue";
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
    private JFXButton passButton;

    @FXML
    private GridPane objectiveCardGrid;

    @FXML
    private GridPane roundTrackGrid;

    @FXML
    private GridPane toolCostGrid;

    @FXML
    private TableView statusTable;

    @FXML
    private Label timerLabel;

    @FXML
    private TextField turnOwnerField;

    @FXML
    private GridPane toolCardGrid;

    @FXML
    private GridPane mywindow;

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
        fitImageToParent(privateObjectiveCard,(AnchorPane)privateObjectiveCard.getParent());

        for (Node node:toolCardGrid.getChildren()) {
            ImageView img = (ImageView) node;
            fitImageToParent(img,toolCardGrid);
        }

        for (Node node:objectiveCardGrid.getChildren()) {
            ImageView img = (ImageView) node;
            fitImageToParent(img,objectiveCardGrid);
        }
    }

    public void setTitleWindowPatternCard() {
        listTitle.addAll(myTitle,titlePlayer2,titlePlayer3,titlePlayer4);
        myTitle.setText(client.getName());
        int j = 1;

        for(int i = 0; i < gamePlayerlist.size(); i++) {
            if(!gamePlayerlist.get(i).equals(client.getName())) {
                listTitle.get(j).setText(gamePlayerlist.get(i));
                j++;
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

    private Node getChildrenByIndex(GridPane gridPane,final int i,final int y) {
        ObservableList<Node> children = gridPane.getChildren();

        for (Node pane: children) {
            if(gridPane.getColumnIndex(pane) == y && gridPane.getRowIndex(pane) == i) {
                return pane;
            }
        }
        return null;
    }

    @FXML
    public void handlePassClicked(MouseEvent event) {
        client.sendCommand("move "+client.getNumOfMatch()+" "+client.getName()+" pass");
        event.consume();
    }

    @FXML
    public void handleToolCardClicked(MouseEvent event) {
        client.sendCommand("move "+client.getNumOfMatch()+" "+client.getName()+" T;"+toolList.get(toolCardGrid.getColumnIndex((Node)event.getSource())));
        event.consume();
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
    public void handleClientConnected(String message) {
        /*no need to display to the user a message that says "you are connected"*/
    }

    @Override
    public void handleTurnMessage(String turnMessage) {
        turnOwnerField.setText(turnMessage);
    }

    @Override
    public void handleConnectedPlayers(String connPlayers) {
        connectedPlayers = FXCollections.observableArrayList(Arrays.asList(connPlayers.split(",")));
        this.updateStatusTable();
    }

    @Override
    public void handleAlert(String message) {
        //AlertWindow.display("Alert", message);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    @Override
    public void handleTimer(String time){
        this.setTime(time);
    }

    @Override
    public void handleMatchId(String idMatch) {
        /*matchId is already stored*/
    }

    @Override
    public void setPatternCards(String setup) {
        /*this message will not be received by this controller*/
    }

    @Override
    public void handleGameState(String gameState) {
        this.currentState = gameState;

        Node node = getChildrenByIndex(draftPool,diceChosenRow,diceChosenColumn);
        ViewDice dice = findDiceInfo(node);

        if(dice != null) {
            if(currentState.equals(INCDECVALUE)) {
                /*pop a window to let the player chose to increment or decrement the value of the dice*/
                Platform.runLater(() -> {
                    // Update UI here.
                    SelValueWindow.display(client,dice,diceChosenRow,diceChosenColumn,currentState);
                });
            } else if(currentState.equals(SELECTVALUE)) {
                /*pop a window to let the player chose the value of the dice*/
                Platform.runLater(() -> {
                    // Update UI here.
                    IncDecWindow.display(client, dice, diceChosenRow, diceChosenColumn,currentState);
                });
            }
        }
    }

    @Override
    public void handleScore(String score) {
        this.score = score;
        try {
            changeScene();
        } catch (IOException e) {
            handleAlert("Errore cambio di scena Match->Score");
        }
    }

    @Override
    public void updateBoard(String setup) {
        /*divide the setup string into substrings which contains single messages*/
        List<String> messages = Arrays.asList(setup.split(";"));

        for (String subMessage: messages) {
            /*call a different method based on the start of the string*/
            if(subMessage.startsWith("gamePlayers")) {
                this.handleGamePlayers(subMessage.replace("gamePlayers ", ""));
            } else if(subMessage.startsWith("draftpool")) {
                this.updateDraftPool(subMessage.replace("draftpool ", ""));
            } else if(subMessage.startsWith("toolcards")) {
                this.updateToolcards(subMessage.replace("toolcards ", ""));
            } else if(subMessage.startsWith("publiccards")) {
                this.setObjectCard(subMessage.replace("publiccards ", ""));
            } else if(subMessage.startsWith("privatecard")) {
                this.setPrivateCard(subMessage.replace("privatecard ", ""));
            } else if(subMessage.startsWith("roundtrack")) {
                //this.updateRoundTrack(subMessage.replace("roundtrack ", ""));
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

    private void handleGamePlayers(String gamePlayers) {
        gamePlayerlist = FXCollections.observableArrayList(Arrays.asList(gamePlayers.split(",")));
        this.setTitleWindowPatternCard();
    }

    public void updatePlayersStatus(String status) {
        playerStatusList = FXCollections.observableArrayList(Arrays.asList(status.split(",")));
        this.updateStatusTable();
    }

    public void updateFavTokens(String favors) {
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

        for(int i = 0; i < tokenList.size();i++) {
            AnchorPane pane = (AnchorPane)tokenList.get(i);
            if(pane.getChildren().size() > 0) {
                pane.getChildren().remove(0);
            }
        }

        for(int i = 0; i < tokenList.size();i++) {
            if(i < Integer.parseInt(favorsList.get(1))) {
                ImageView favimg = new ImageView("/viewLogo/favortoken.png");
                ((AnchorPane)tokenList.get(i)).getChildren().add(favimg);
                fitImageToParent(favimg,favorGrid);
                favimg.setPreserveRatio(true);
            }
        }
    }

    public void setObjectCard(String objectCard) {
        List<String> objectCardList = Arrays.asList(objectCard.split("\\\\"));
        ObservableList<Node> gridChildren = objectiveCardGrid.getChildren();

        for(int i = 0; i < objectCardList.size();i++) {
            List<String> infoObjectiveCard = Arrays.asList(objectCardList.get(i).split("/"));
            ((javafx.scene.image.ImageView)gridChildren.get(i)).setImage(new Image ("/objectivecards/public/"+infoObjectiveCard.get(0)+".png"));
        }
    }

    public void setPrivateCard(String privateCard) {
        List<String> infoPrivateCard = Arrays.asList(privateCard.split(","));
        privateObjectiveCard.setImage(new Image ("/objectivecards/private/"+infoPrivateCard.get(0)+".png"));
    }

    public void updateToolcards(String toolCard) {
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

        int numRows = roundTrackGrid.getRowConstraints().size();
        int numColumns = roundTrackGrid.getColumnConstraints().size();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; i < numColumns; i++) {
                deleteDice((AnchorPane)roundTrackGrid.getChildren().get(numRows*i+j));
            }
        }

        Node pane;

        for (String cell: cellList) {
            int y = Character.getNumericValue(cell.charAt(2));
            int x = Character.getNumericValue(cell.charAt(3));
            pane = this.getChildrenByIndex(roundTrackGrid,x,y);
            if(pane == null) {
                System.out.println("Sbagliato");
            } else {
                ImageView image = new ImageView("/dice/" + cell.substring(0,2) + ".png");
                ((AnchorPane)pane).getChildren().add(image);
                fitImageToParent(image,roundTrackGrid);

                /*add the WindowDiceEvent to the dice,plus add the dice to viewDiceList*/
                diceList.add(new ViewDice(image,cell.substring(2,4)));
                addRoundTrackEvent(image);
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
            int numRows = currentWindow.getRowConstraints().size();
            int numColumns = currentWindow.getColumnConstraints().size();
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; i < numColumns; i++) {
                    deleteDice((AnchorPane)currentWindow.getChildren().get(numRows*i+j));
                }
            }
        }

        Node pane;

        for (String cell: cellList) {
            if(!cell.equals(player)) {
                int x = Character.getNumericValue(cell.charAt(2));
                int y = Character.getNumericValue(cell.charAt(3));
                pane = this.getChildrenByIndex(currentWindow,x,y);
                if(pane == null) {
                    System.out.println("Sbagliato");
                } else {
                    ImageView image = new ImageView("/dice/" + cell.substring(0,2) + ".png");
                    ((AnchorPane)pane).getChildren().add(image);
                    fitImageToParent(image,currentWindow);

                    /*If the current window is the player's one then add the WindowDiceEvent to the dice,plus add the dice to viewDiceList*/
                    if(isMyWindow) {
                        diceList.add(new ViewDice(image,cell.substring(2,4)));
                        addWindowEvent(image);
                    }
                }
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
                this.setRestriction(restrictionList.get(5*i+j+1) , currentWindow.getChildren().get(5*i+j));
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

    private void updateDraftPool(String draftPoolInfo) {
        List<String> draftList = Arrays.asList(draftPoolInfo.split(","));

        for(int i = 0; i < DRAFT_LENGTH;i++) {
            AnchorPane pane = ((AnchorPane)(draftPool.getChildren().get(i)));
            deleteDice(pane);
            if(i < draftList.size()) {
                ImageView image = new ImageView("/dice/" + draftList.get(i) + ".png");
                pane.getChildren().add(image);
                diceList.add(new ViewDice(image,draftList.get(i)));
                fitImageToParent(image,draftPool);

                addDraftEvent(image);
            }
        }
    }

    /**
     * Delete the imageView in the given cell of the DraftPoolGrid in order to update the draftpool, also it deletes the reference in the viewDiceList
     * @param pane the pane contained in a cell of the DraftPoolGrid
     */
    private void deleteDice(AnchorPane pane) {
        ObservableList<Node> children = pane.getChildren();
        if(!children.isEmpty()) {
            ViewDice dice = findDiceInfo(children.get(0));
            if(dice != null) {
                diceList.remove(dice);
            }
            children.clear();
        }
    }

    private void addDraftEvent(ImageView image) {
        image.addEventHandler(MouseEvent.MOUSE_PRESSED, this::handleDraftDiceClicked);
    }

    private void addWindowEvent(ImageView image) {
        image.addEventHandler(MouseEvent.MOUSE_PRESSED, this::handleWindowDiceClicked);
    }

    private void addRoundTrackEvent(ImageView image) {
        image.addEventHandler(MouseEvent.MOUSE_PRESSED, this::handleRoundTrackDiceClicked);
    }

    private void handleRoundTrackDiceClicked(MouseEvent mouseEvent) {
        if(!(currentState.equals(WINDOWDICE1) || currentState.equals(WINDOWDICE2) ||
                currentState.equals(DRAFTDICE1) || currentState.equals(DRAFTDICE2) ||
                currentState.equals(START) ||currentState.equals(INCDECVALUE) || currentState.equals(SELECTVALUE))) {
            Node source = (Node) mouseEvent.getSource();
            source.setEffect(new DropShadow());
            ViewDice dice = findDiceInfo(source);

            if(dice != null) {
                int j = roundTrackGrid.getColumnIndex(source.getParent());
                int i = roundTrackGrid.getRowIndex(source.getParent());
                int x = roundTrackGrid.getColumnConstraints().size()*j+i;
                client.sendCommand("move "+client.getNumOfMatch()+" "+client.getName()+" D;"+dice.getDiceNumber()+","+dice.getDiceColor()+","+j+","+i);
            }
        }
        mouseEvent.consume();
    }


    private void handleWindowDiceClicked(MouseEvent mouseEvent) {
        if(!(currentState.equals(START) || currentState.equals(ROUNDDICE1) ||
                currentState.equals(DRAFTDICE1) || currentState.equals(DRAFTDICE2) ||
                currentState.equals(INCDECVALUE) || currentState.equals(SELECTVALUE))) {
            Node source = (Node) mouseEvent.getSource();
            source.setEffect(new DropShadow());
            ViewDice dice = findDiceInfo(source);

            if(dice != null) {
                int i = mywindow.getColumnIndex(source.getParent());
                int j = mywindow.getRowIndex(source.getParent());
                client.sendCommand("move "+client.getNumOfMatch()+" "+client.getName()+" D;"+dice.getDiceNumber()+","+dice.getDiceColor()+","+j+","+i);
            }
        }
        mouseEvent.consume();
    }

    private void handleDraftDiceClicked(MouseEvent mouseEvent) {
        if(!(currentState.equals(WINDOWDICE1) || currentState.equals(WINDOWDICE2) ||
                currentState.equals(ROUNDDICE1) || currentState.equals(INCDECVALUE) || currentState.equals(SELECTVALUE))) {
            Node source = (Node) mouseEvent.getSource();
            source.setEffect(new DropShadow());
            ViewDice dice = findDiceInfo(source);

            if(dice != null) {
                int i = draftPool.getColumnIndex(source.getParent());
                int j = draftPool.getRowIndex(source.getParent());
                diceChosenRow = j;
                diceChosenColumn = i;
                int x = draftPool.getColumnConstraints().size()*j+i;
                client.sendCommand("move "+client.getNumOfMatch()+" "+client.getName()+" D;"+dice.getDiceNumber()+","+dice.getDiceColor()+","+x+",0");
            }
        }
        mouseEvent.consume();
    }

    private ViewDice findDiceInfo(Node node) {
        for (ViewDice dice : diceList) {
            if(dice.getDiceImage() == node) {
                return dice;
            }
        }
        return null;
    }

    @FXML
    void handleCellClicked(MouseEvent event) {
        int y = mywindow.getColumnIndex((Node) event.getSource());
        int x = mywindow.getRowIndex((Node) event.getSource());
        client.sendCommand("move "+client.getNumOfMatch()+" "+client.getName()+" P;"+x+","+y);
        event.consume();
    }

    private void fitImageToParent(ImageView image, AnchorPane pane) {
        image.fitWidthProperty().bind(pane.widthProperty());
        image.fitHeightProperty().bind(pane.heightProperty());
    }

    private void fitImageToParent(ImageView image, GridPane grid) {
        image.fitWidthProperty().bind(grid.widthProperty().divide(grid.getColumnConstraints().size()));
        image.fitHeightProperty().bind(grid.heightProperty().divide(grid.getRowConstraints().size()));
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
}