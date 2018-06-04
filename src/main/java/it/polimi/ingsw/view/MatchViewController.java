package it.polimi.ingsw.view;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.MessageQueue;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.text.html.ImageView;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;

public class MatchViewController implements Initializable, ViewInterface {
    private Client client;
    private Stage stage;
    private MessageAnalyzer messageAnalyzer;
    private ObservableList<String> list = FXCollections.observableArrayList();
    private ObservableList<TitledPane> listTitle = FXCollections.observableArrayList();
    private String time;

    @FXML
    private HBox boxObjectiveCards;

    @FXML
    private Label timerLabel;

    @FXML
    private TextField notifyField;

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

    //private RiservaFalsa riservaFalsa = new RiservaFalsa();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //this.setMyWindow(mywindow,currentPlayerWindow);
        //this.setMyWindow(windowplayer2,player2Window);
        //this.setMyWindow(windowplayer3,player3Window);
        //this.setMyWindow(windowplayer4,player4Window);
        //this.setRiserva();
        //this.modeRep = new ModeRep();
    }

    public void setWindows() {
        listTitle.addAll(myTitle,titlePlayer2,titlePlayer3,titlePlayer4);
        myTitle.setText(client.getName());
        int j = 1;

        for(int i = 0; i < list.size(); i++) {
            if(!list.get(i).equals(client.getName())) {
                listTitle.get(j).setText(list.get(i));
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
    public void setMessageAnalyzer(MessageAnalyzer messageAnalyzer) {
        this.messageAnalyzer = messageAnalyzer;
    }

    public void setList(ObservableList<String> list) {
        this.list = list;
    }

    public void notifyNewModelRep(String draftPool, String restrictions, String diceWindow) {
        //this.draftPool = draftPool;
        //this.restrictions = restrictions;
        //this.diceWindow = diceWindow;
        //System.out.println(draftPool+"\n"+ restrictions +"\n"+diceWindow);
    }

    public void setMyWindow(GridPane gridPane, WindowFalsa window) {
        Node text;

        for(int i = 0; i< 5;i++) {
            for(int j = 0; j<4;j++) {
                text = this.getChildrenByIndex(gridPane,i,j);
                if(text == null) {
                    System.out.println("Sbagliato");
                } else {
                    ((Text)text).setText(window.getStr(i,j));
                }
            }
        }
    }

    /*public void setRiserva() {
        Node text;

        for(int i = 0; i< 3;i++) {
            for(int j = 0; j<3;j++) {
                text = this.getChildrenByIndex(riserva,i,j);
                if(text == null) {
                    System.out.println("Sbagliato");
                } else {
                    if(3*i+j < riservaFalsa.getRiserva().size()) {
                        ((Text) text).setText(riservaFalsa.getRiserva().get(3 * i + j));
                    } else {
                        ((Text) text).setText("");
                    }
                }
            }
        }
    }*/

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

        client.sendCommand("move "+client.getNumOfMatch()+" "+client.getName()+" P;"+x+","+y);


    }

    @FXML
    public void handleDragDone(DragEvent event) {

        System.out.println("Source: "+source.getText() +" ------- Target: "+textReceiver.getText());
        if(textReceiver.getText().equals(source.getText())) {
            String str = "";
            source.setText(str);
        }
    }


    @Override
    public void handleConnected(String message) {
    }

    @Override
    public void handleService(ObservableList<String> list) {
        String message = "";

        for(int i = 0 ; i < list.size();i++) {
            message += (list.get(i)+" ");
        }

        notifyField.setText(message);
    }

    @Override
    public void handleAlert(String message) {
        AlertWindow.display("Alert", message);
    }

    @Override
    public void handleTimer(String time) {
        this.time = time;
        startTimer();
    }

    @Override
    public void handleMatchId(String idMatch) {

    }

    @Override
    public void updateBoard(String setup) {
        /*ricevo la stringa con tutto il messaggione, ora creo una classe che mi analizza questo messaggione, dividendomelo in
        una lista di stringhe contenenti ognuna una parte della gui da aggiornare. Per ciascuna utilizzo un algoritmo che mi
        divide atomicamente le informazioni che mi servono per aggiornare la Gui, chiamo infine un metodo della gui che dato
        come parametro questa info mi aggiorna la gui.*/


        //divido la stringa grande con tutte le cose da aggiornare in più stringhe ognuna contenente solo un componente da aggiornare
        List<String> messages = Arrays.asList(setup.split(";"));

        for (String subMessage: messages) {
            //qui ho il subMessage quindi devo fare starts with -> chiamo funzione sulla view diversa in base alla intestazione
            if(subMessage.startsWith("draftpool")) {
                this.updateDraftPool(subMessage.replace("draftpool ", ""));
            } else if(subMessage.startsWith("toolcards")) {
                this.updateToolcards(subMessage.replace("toolcards ", ""));
            } else if(subMessage.startsWith("favors")) {
                subMessage.replace("favors ", "");

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
        List<String> toolList = Arrays.asList(toolCard.split(","));

        ObservableList<Node> boxChildren = boxToolCards.getChildren();

        for(int i = 0; i < toolList.size();i++) {
            ((javafx.scene.image.ImageView)boxChildren.get(i)).setImage(new Image ("/toolcards/"+toolList.get(i)+".png"));
        }
    }

    private void updateWindowCards(String windowPatternCard) {
        List<String> cellList = Arrays.asList(windowPatternCard.split(","));

        String player = cellList.get(0);
        TitledPane container = new TitledPane();

        Node node = new Text();

        for (TitledPane titlePane: listTitle) {
            if(titlePane.getText().equals(player)) {
                node = titlePane.getContent();
            }
        }


        GridPane currentWindow = (GridPane)((Parent) node).getChildrenUnmodifiable().get(0);

        Node text;

        for (String cell: cellList) {
            if(!cell.equals(player)) {
                int x = cell.charAt(2);
                int y = cell.charAt(3);
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

        Node node = new Text();

        for (TitledPane titlePane: listTitle) {
            if(titlePane.getText().equals(player)) {
                node = titlePane.getContent();
            }
        }


        GridPane currentWindow = (GridPane)((Parent) node).getChildrenUnmodifiable().get(0);

        Node text;

        for(int i = 0; i< 4;i++) {
            for(int j = 0; j<5;j++) {
                text = this.getChildrenByIndex(currentWindow,i,j);
                if(text == null) {
                    System.out.println("Sbagliato");
                } else {
                    ((Text)text).setText(restrictionList.get(5*i+j+1));
                }
            }
        }
    }

    private void updateDraftPool(String draftPoolInfo) {
        int i = 0;
        int x = 0;
        int y = 0;
        List<String> draftList = Arrays.asList(draftPoolInfo.split(","));

        for(; i < 9;i++) {
            x = i/3;
            y = i%3;
            if(i < draftList.size()) {
                ((Text) this.getChildrenByIndex(draftPool, x, y)).setText(draftList.get(i).charAt(0)+" "+draftList.get(i).charAt(1));
            } else {
                ((Text) this.getChildrenByIndex(draftPool, x, y)).setText("");
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
        if(list.size() >= 2) {
            if (!time.equals("0")) {
                time = Long.toString((Long.parseLong(time) - 1));
            } else {
                time = "0";
            }
        }

        timerLabel.setText("You are currently in queue. Waiting for players...                       Timer : " + time);
    }
}


