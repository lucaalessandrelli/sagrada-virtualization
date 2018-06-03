package it.polimi.ingsw.view;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.MessageQueue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.effect.Glow;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

    @FXML
    private GridPane mywindow;

    @FXML
    private GridPane windowplayer2;

    @FXML
    private GridPane windowplayer3;

    @FXML
    private GridPane windowplayer4;

    @FXML
    private GridPane draftPool;


    private Text textReceiver;
    private Text source;

    private String[][] window1 = {{"a1","a2","a3", "a4"},
            {"b1","b2","b3","b4"},
            {"c1","c2","c3","c4"},
            {"d1","d2","d3","d4"},
            {"e1","e2","e3","e4"}};

    private String[][] window2 = {{"p21","a2","a3", "a4"},
            {"b1","b2","b3","b4"},
            {"c1","c2","c3","c4"},
            {"d1","d2","d3","d4"},
            {"e1","e2","e3","e4"}};

    private String[][] window3 = {{"p31","a2","a3", "a4"},
            {"b1","b2","b3","b4"},
            {"c1","c2","c3","c4"},
            {"d1","d2","d3","d4"},
            {"e1","e2","e3","e4"}};

    private String[][] window4 = {{"p41","a2","a3", "a4"},
            {"b1","b2","b3","b4"},
            {"c1","c2","c3","c4"},
            {"d1","d2","d3","d4"},
            {"e1","e2","e3","e4"}};


    private WindowFalsa currentPlayerWindow = new WindowFalsa(window1);
    private WindowFalsa player2Window = new WindowFalsa(window2);
    private WindowFalsa player3Window = new WindowFalsa(window3);
    private WindowFalsa player4Window = new WindowFalsa(window4);

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

    public void setClient(Client client) {
        this.client = client;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setMessageAnalyzer(MessageAnalyzer messageAnalyzer) {
        this.messageAnalyzer = messageAnalyzer;
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

    }

    @Override
    public void handleAlert(String message) {
        AlertWindow.display("Alert", message);
    }

    @Override
    public void handleTimer(String time) {

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
                subMessage.replace("toolcards ", "");

            } else if(subMessage.startsWith("favors")) {
                subMessage.replace("favors ", "");

            } else if(subMessage.startsWith("publiccards")) {
                subMessage.replace("publiccards ", "");

            } else if(subMessage.startsWith("privatecard")) {
                subMessage.replace("privatecard ", "");

            } else if(subMessage.startsWith("roundtrack")) {
                subMessage.replace("roundtrack ", "");

            } else if(subMessage.startsWith("restrictions")) {
                subMessage.replace("restrictions ", "");

            } else if(subMessage.startsWith("dices")) {
                subMessage.replace("dices ", "");
            }

        }
    }

    private void updateDraftPool(String draftPoolInfo) {
        int i = 0;
        int x = 0;
        int y = 0;
        List<String> list = Arrays.asList(draftPoolInfo.split(","));

        for(; i < 9;i++) {
            x = i/3;
            y = i%3;
            if(i < list.size()) {
                ((Text) this.getChildrenByIndex(draftPool, x, y)).setText(list.get(i));
            } else {
                ((Text) this.getChildrenByIndex(draftPool, x, y)).setText("");
            }
        }
    }
}
