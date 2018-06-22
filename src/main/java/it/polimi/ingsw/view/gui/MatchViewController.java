package it.polimi.ingsw.view.gui;

import com.jfoenix.controls.JFXButton;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.view.SceneInterface;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.omg.CORBA.IMP_LIMIT;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;

public class MatchViewController implements Initializable, SceneInterface {
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
    private ImageView diceChosen = new ImageView();
    private List<ViewDice> diceList = new ArrayList<>();

    @FXML
    private TableColumn<User,String> userNameColumn;

    @FXML
    private TableColumn<User,String> userStatusColumn;

    @FXML
    private JFXButton passButton;

    @FXML
    private HBox boxObjectiveCards;

    @FXML
    private GridPane objectiveCardGrid;

    @FXML
    private GridPane toolCostGrid;

    @FXML
    private TableView statusTable;

    @FXML
    private Label timerLabel;

    @FXML
    private TextField turnOwnerField;

    @FXML
    private HBox boxToolCards;

    @FXML
    private GridPane toolCardGrid;

    @FXML
    private GridPane mywindow;

    @FXML
    private TitledPane myTitle;

    @FXML
    private javafx.scene.image.ImageView privateObjectiveCard;

    @FXML
    private TitledPane titlePlayer2;

    @FXML
    private TitledPane titlePlayer3;

    @FXML
    private TitledPane titlePlayer4;

    @FXML
    private GridPane draftPool;

    private Text textReceiver;
    private Text source;


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
    public void handleDragDetection(MouseEvent event) {
        source = (Text) event.getSource();
        Dragboard dragboard = source.startDragAndDrop(TransferMode.ANY);
        ClipboardContent clipboardContent = new ClipboardContent();

        clipboardContent.putString(source.getText());
        dragboard.setContent(clipboardContent);

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {

                int j = draftPool.getColumnIndex(source);
                int i = draftPool.getRowIndex(source);
                List<String> list = Arrays.asList(source.getText().split(" "));

                String x = Integer.toString(3 * i + j);

                client.sendCommand("move "+client.getNumOfMatch()+" "+client.getName()+" D;"+list.get(1)+","+list.get(0)+","+x+",0");
            }
        });

        event.consume();

    }

    @FXML
    public void handlePassClicked(MouseEvent event) {
        client.sendCommand("move "+client.getNumOfMatch()+" "+client.getName()+" pass");
    }

    @FXML
    public void handleToolCardClicked(MouseEvent event) {
        //client.sendCommand("move "+client.getNumOfMatch()+" "+client.getName()+" T;"+toolList.get(findNodeIndexIntoHbox(boxToolCards,(Node)event.getSource())));
        int n = toolCardGrid.getColumnIndex((Node)event.getSource());
        client.sendCommand("move "+client.getNumOfMatch()+" "+client.getName()+" T;"+toolList.get(toolCardGrid.getColumnIndex((Node)event.getSource())));
    }

    private int findNodeIndexIntoHbox(HBox boxToolCards, Node toolcard) {
        ObservableList<Node> nodeList = boxToolCards.getChildren();
        for(int i = 0; i < nodeList.size();i++) {
            if(nodeList.get(i) == toolcard) {
                return i;
            }
        }

        //cambia,sbagliato
        return 0;
    }

    @FXML
    public void handleMouseClicked(MouseEvent event) {
        source = (Text) event.getSource();

        Glow glow = new Glow();
        source.setEffect(glow);

        System.out.println(source.getText());
        System.out.println("SOURCE -> \nColumn : "+ draftPool.getColumnIndex(source) +" ------- Row :"+ draftPool.getRowIndex(source));
    }

    @FXML
    public void handleDragOver(DragEvent event) {
        if(event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    public void handleDragDropped(DragEvent event) {
        textReceiver = (Text) event.getTarget();
        String str = event.getDragboard().getString();

        System.out.println("RECEIVER \nColumn : "+ mywindow.getColumnIndex(textReceiver) +" ------- Row :"+ mywindow.getRowIndex(textReceiver));
        System.out.println("SOURCE -> \nColumn : "+ mywindow.getColumnIndex(source) +" ------- Row :"+ mywindow.getRowIndex(source));
        textReceiver.setText(str);


        int y = mywindow.getColumnIndex(textReceiver);
        int x = mywindow.getRowIndex(textReceiver);

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                client.sendCommand("move "+client.getNumOfMatch()+" "+client.getName()+" P;"+x+","+y);
            }
        });
    }

    @FXML
    public void handleDragDone(DragEvent event) {

        System.out.println("Source: "+source.getText() +" ------- Target: "+textReceiver.getText());
        if(textReceiver.getText().equals(source.getText())) {
            String str = "";
            source.setText(str);
        }
    }

    public void changeScene() throws IOException {
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
        stage.setTitle("Score");
        stage.show();
    }

    @Override
    public void handleClientConnected(String message) {
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
        alert.setTitle("Error");
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

    }

    @Override
    public void setPatternCards(String setup) {

    }

    @Override
    public void handleGameState(String gameState) {

    }

    @Override
    public void handleScore(String score) {
        this.score = score;
        try {
            changeScene();
        } catch (IOException e) {
            System.out.println("Errore cambio di scena: matchView -> scoreView");
        }
    }

    @Override
    public void updateBoard(String setup) {
        //divido la stringa grande con tutte le cose da aggiornare in più stringhe ognuna contenente solo un componente da aggiornare
        List<String> messages = Arrays.asList(setup.split(";"));

        for (String subMessage: messages) {
            //qui ho il subMessage quindi devo fare starts with -> chiamo funzione sulla view diversa in base alla intestazione
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
                subMessage.replace("roundtrack ", "");

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
        TitledPane container = new TitledPane();

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

        /*ObservableList<Node> boxChildren = boxObjectiveCards.getChildren();

        for(int i = 0; i < objectCardList.size();i++) {
            ((javafx.scene.image.ImageView)boxChildren.get(i)).setImage(new Image ("/objectivecards/public/"+objectCardList.get(i)+".png"));
        }*/

        ObservableList<Node> gridChildren = objectiveCardGrid.getChildren();

        for(int i = 0; i < toolList.size();i++) {
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

        /*ObservableList<Node> boxChildren = boxToolCards.getChildren();

        for(int i = 0; i < toolList.size();i++) {
            ((javafx.scene.image.ImageView)boxChildren.get(i)).setImage(new Image ("/toolcards/"+toolList.get(i)+".png"));
        }*/

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

    private void updateWindowCards(String windowPatternCard) {
        List<String> cellList = Arrays.asList(windowPatternCard.split(","));

        String player = cellList.get(0);
        TitledPane container = new TitledPane();

        VBox vBox = new VBox();

        for (TitledPane titlePane: listTitle) {
            if(titlePane.getText().equals(player)) {
                vBox = (VBox)((Parent) titlePane.getContent()).getChildrenUnmodifiable().get(0);
            }
        }

        ObservableList<Node> boxChildren = vBox.getChildren();
        GridPane currentWindow = (GridPane)(boxChildren.get(0));

        Node pane;

        for (String cell: cellList) {
            if(!cell.equals(player)) {
                int x = Character.getNumericValue(cell.charAt(2));
                int y = Character.getNumericValue(cell.charAt(3));
                pane = this.getChildrenByIndex(currentWindow,x,y);
                if(pane == null) {
                    System.out.println("Sbagliato");
                } else {
                    //((Text)text).setText(cell.charAt(0)+" "+cell.charAt(1));
                    ImageView image = new ImageView("/dice/" + cell.substring(0,2) + ".png");
                    ((AnchorPane)pane).getChildren().add(image);
                    fitImageToParent(image,currentWindow);
                }
            }
        }
    }

    private void updateRestriction(String resctriction) {
        List<String> restrictionList = Arrays.asList(resctriction.split(","));

        String player = restrictionList.get(0);
        TitledPane container = new TitledPane();

        VBox vBox = new VBox();

        for (TitledPane titlePane: listTitle) {
            if(titlePane.getText().equals(player)) {
                vBox = (VBox)((Parent) titlePane.getContent()).getChildrenUnmodifiable().get(0);
            }
        }

        ObservableList<Node> boxChildren = vBox.getChildren();
        GridPane currentWindow = (GridPane)(boxChildren.get(0));

        Node text;

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

        for(int i = 0; i < 9;i++) {
            if(i < draftList.size()) {
                ImageView image = new ImageView("/dice/" + draftList.get(i) + ".png");
                AnchorPane pane = ((AnchorPane)(draftPool.getChildren().get(i)));
                pane.getChildren().add(image);
                diceList.add(new ViewDice(image,draftList.get(i)));

                image.fitWidthProperty().bind(draftPool.widthProperty().divide(3));
                image.fitHeightProperty().bind(draftPool.heightProperty().divide(3));

                addDraftEvent(image);
                //((AnchorPane)(draftPool.getChildren().get(i))).getChildren().add(new ImageView("/dice/" + draftList.get(i) + ".png"));
            }
        }
    }

    private void addDraftEvent(ImageView image) {
        image.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleDraftDiceClicked((Node) mouseEvent.getSource());
            }
        });
    }

    private void handleDraftDiceClicked(Node node) {
        diceChosen.setEffect(null);
        diceChosen = (ImageView) node;
        diceChosen.setEffect(new DropShadow());
        ViewDice dice = findDiceInfo(node);
        if(dice != null) {
            //int x = draftPool.getColumnConstraints().size()*draftPool.getRowIndex(node)+draftPool.getColumnIndex(node);
            int i = draftPool.getColumnIndex((Node)node.getParent());
            int j = draftPool.getRowIndex((Node)node.getParent());
            int x = 3*j+i;
            client.sendCommand("move " + client.getNumOfMatch() + " " + client.getName() + " D;" + dice.getDiceColor() + "," + dice.getDiceNumber() + "," + x + ",0");
        }
    }

    private ViewDice findDiceInfo(Node node) {
        for (ViewDice dice : diceList) {
            if(dice.getDiceImage() == (ImageView)node) {
                return dice;
            }
        }
        return null;
    }

    @FXML
    void handleCellClicked(MouseEvent event) {
        //AnchorPane source = (AnchorPane) event.getSource();
        /*source.getChildren().add(diceChosen);
        diceChosen.fitHeightProperty().unbind();
        diceChosen.fitWidthProperty().unbind();
        fitImageToParent(diceChosen, mywindow);*/
        int y = mywindow.getColumnIndex((Node) event.getSource());
        int x = mywindow.getRowIndex((Node)event.getSource());
        client.sendCommand("move "+client.getNumOfMatch()+" "+client.getName()+" P;"+x+","+y);
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