package it.polimi.ingsw.view.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import it.polimi.ingsw.model.gamedata.Table;
import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.view.MessageAnalyzer;
import it.polimi.ingsw.view.ViewInterface;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;

public class MatchViewController implements Initializable, GuiInterface {
    private Client client;
    private Stage stage;
    private GuiHandler guiHandler;
    private ObservableList<String> connectedPlayers = FXCollections.observableArrayList();
    private ObservableList<String> gamePlayerlist = FXCollections.observableArrayList();
    private ObservableList<String> playerStatusList = FXCollections.observableArrayList();
    private ObservableList<TitledPane> listTitle = FXCollections.observableArrayList();
    private List<String> toolList;
    private String time;
    private String score;
    private ObservableList<User> userList = FXCollections.observableArrayList();

    @FXML
    private TableColumn<User,String> userNameColumn;

    @FXML
    private TableColumn<User,String> userStatusColumn;

    @FXML
    private JFXButton passButton;

    @FXML
    private HBox boxObjectiveCards;

    @FXML
    private TableView statusTable;

    @FXML
    private Label timerLabel;

    @FXML
    private TextField turnOwnerField;

    @FXML
    private HBox boxToolCards;

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
        ObservableList<Node> textList = gridPane.getChildren();

        for (Node text: textList) {
            if(gridPane.getColumnIndex(text) == y && gridPane.getRowIndex(text) == i) {
                return text;
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
        client.sendCommand("move "+client.getNumOfMatch()+" "+client.getName()+" T;"+toolList.get(findNodeIndexIntoHbox(boxToolCards,(Node)event.getSource())));
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
        /*for (User user:userList) {
            userList.remove(user);
        }*/
        userList = FXCollections.observableArrayList();

        ObservableList<String> playerInfo;

        for (String username:connectedPlayers) {
            for (String player: playerStatusList) {
                playerInfo = FXCollections.observableArrayList(Arrays.asList(player.split(" ")));
                userList.add(new User(username,playerInfo.get(1)));
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
        HBox favorBox = (HBox)(boxChildren.get(1));

        ObservableList<Node> tokenList = favorBox.getChildren();

        for(int i = 0; i < tokenList.size();i++) {
            if(i >= Integer.parseInt(favorsList.get(1))) {
                tokenList.get(i).setVisible(false);
            }
        }

    }

    public void setObjectCard(String objectCard) {
        List<String> objectCardList = Arrays.asList(objectCard.split(","));

        ObservableList<Node> boxChildren = boxObjectiveCards.getChildren();

        for(int i = 0; i < objectCardList.size();i++) {
            ((javafx.scene.image.ImageView)boxChildren.get(i)).setImage(new Image ("/objectivecards/public/"+objectCardList.get(i)+".png"));
        }
    }

    public void setPrivateCard(String privateCard) {
        privateObjectiveCard.setImage(new Image ("/objectivecards/private/"+privateCard+".png"));
    }

    public void updateToolcards(String toolCard) {
        toolList = Arrays.asList(toolCard.split(","));

        ObservableList<Node> boxChildren = boxToolCards.getChildren();

        for(int i = 0; i < toolList.size();i++) {
            ((javafx.scene.image.ImageView)boxChildren.get(i)).setImage(new Image ("/toolcards/"+toolList.get(i)+".png"));
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

        Node text;

        for (String cell: cellList) {
            if(!cell.equals(player)) {
                int x = Character.getNumericValue(cell.charAt(2));
                int y = Character.getNumericValue(cell.charAt(3));
                text = this.getChildrenByIndex(currentWindow,x,y);
                if(text == null) {
                    System.out.println("Sbagliato");
                } else {
                    ((Text)text).setText(cell.charAt(0)+" "+cell.charAt(1));
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
        int i = 0;
        int x = 0;
        int y = 0;
        List<String> draftList = Arrays.asList(draftPoolInfo.split(","));

        for(; i < 9;i++) {
            /*x = i/3;
            y = i%3;
            if(i < draftList.size()) {
                ((Text) this.getChildrenByIndex(draftPool, x, y)).setText(draftList.get(i).charAt(0)+" "+draftList.get(i).charAt(1));
            } else {
                ((Text) this.getChildrenByIndex(draftPool, x, y)).setText("");
            }*/

            if(i < draftList.size()) {
                ((AnchorPane)(draftPool.getChildren().get(i))).getChildren().add(new ImageView("/dice/" + draftList.get(i) + ".png"));
            }
        }
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