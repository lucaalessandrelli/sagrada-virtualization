package it.polimi.ingsw.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.effect.Glow;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewFXController implements Initializable {
    //private Client client;

    @FXML
    private Text txt2;

    @FXML
    private Text txt10;

    @FXML
    private GridPane mywindow;

    @FXML
    private GridPane windowplayer2;

    @FXML
    private GridPane windowplayer3;

    @FXML
    private GridPane windowplayer4;

    @FXML
    private GridPane riserva;

    @FXML
    private Text textReceiver;

    @FXML
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

    private RiservaFalsa riservaFalsa = new RiservaFalsa();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setMyWindow(mywindow,currentPlayerWindow);
        this.setMyWindow(windowplayer2,player2Window);
        this.setMyWindow(windowplayer3,player3Window);
        this.setMyWindow(windowplayer4,player4Window);
        this.setRiserva();
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

    public void setRiserva() {
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

        event.consume();
    }

    @FXML
    public void handleMouseClicked(MouseEvent event) {
        source = (Text) event.getSource();

        Glow glow = new Glow();
        source.setEffect(glow);

        System.out.println(source.getText());
        System.out.println("SOURCE -> \nColumn : "+ riserva.getColumnIndex(source) +" ------- Row :"+ riserva.getRowIndex(source));
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
    }

    @FXML
    public void handleDragDone(DragEvent event) {

        System.out.println("Source: "+source.getText() +" ------- Target: "+textReceiver.getText());
        if(textReceiver.getText().equals(source.getText())) {
            String str = "";
            source.setText(str);
        }
    }
}
