package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.network.client.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class IncDecWindow {
    private static final int NUM_ROWS = 0;
    private static final int NUM_COL = 2;
    private static final String INCDECVALUE = "IncDecValue";
    private static final String ALERT_DICE_INC_DEC = "Non puoi aumentare/diminuire il valore di un dado in questa fase del turno.";
    private static Stage stage;
    private static GridPane gridPane;
    private static AnchorPane anchorPane;
    private static double OFFSET = 10;
    private static int actualValue;

    private IncDecWindow() {

    }

    public static void display(Client client, ViewDice dice, int diceChosenRow, int diceChosenColumn, String currentState) {
        ObservableList<ImageView> diceList = FXCollections.observableArrayList();
        List<ViewDice> viewDiceList = new ArrayList<>();
        int valueDecreased;
        int valueIncreased;
        actualValue = Character.getNumericValue(dice.getDiceNumber());

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Aumenta o diminuisci il valore del dado");

        anchorPane = new AnchorPane();
        gridPane = new GridPane();

        if(actualValue == 6) {
            valueDecreased = actualValue-1;
            valueIncreased = 1;
        } else if(actualValue == 1) {
            valueDecreased = 6;
            valueIncreased = actualValue+1;

        } else {
            valueDecreased = actualValue-1;
            valueIncreased = actualValue+1;
        }

        String infoDiceDecreased = dice.getDiceColor()+""+valueDecreased;
        String infoDiceIncreased = dice.getDiceColor()+""+valueIncreased;

        ImageView imageDecrease = new ImageView("/dice/"+infoDiceDecreased+".png");
        ImageView imageIncrease = new ImageView("/dice/"+infoDiceIncreased+".png");
        diceList.addAll(imageDecrease,imageIncrease);
        viewDiceList.add(new ViewDice(imageDecrease,infoDiceDecreased));
        viewDiceList.add(new ViewDice(imageIncrease,infoDiceIncreased));

        for(int i = 0; i < diceList.size();i++) {
            addToGrid(gridPane,diceList.get(i),i);
            diceList.get(i).addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent mouseEvent) -> handleValueChosen(mouseEvent,currentState,client,viewDiceList,diceChosenRow,diceChosenColumn));
        }

        anchorPane.getChildren().add(gridPane);

        AnchorPane.setTopAnchor(gridPane,OFFSET);
        AnchorPane.setLeftAnchor(gridPane,OFFSET);
        AnchorPane.setBottomAnchor(gridPane,OFFSET);
        AnchorPane.setRightAnchor(gridPane,OFFSET);

        Scene scene = new Scene(anchorPane,200,100);
        stage.setScene(scene);
        stage.showAndWait();
    }

    private static void handleValueChosen(MouseEvent event,String currentState, Client client, List<ViewDice> viewDiceList, int r, int c) {
        if(currentState.equals(INCDECVALUE)) {
            Node source = (Node) event.getSource();
            int diceColumn = GridPane.getColumnIndex(source);
            ViewDice chosenDice = findDiceInfo(source, viewDiceList);

            if (chosenDice != null && !(actualValue == 1 && diceColumn == 0) && !(actualValue == 6 && diceColumn == 1)) {
                client.sendCommand("move " + client.getNumOfMatch() + " " + client.getName() + " D;" + chosenDice.getDiceColor() + "," + chosenDice.getDiceNumber() + "," + r + "," + c);
            }
        } else {
            handleAlert(ALERT_DICE_INC_DEC);
        }
        stage.close();
        event.consume();
    }

    private static void addToGrid(GridPane gridPane, ImageView image, int index) {
        GridPane.setConstraints(image,index,NUM_ROWS);
        image.fitHeightProperty().bind(gridPane.heightProperty());
        image.fitWidthProperty().bind(gridPane.widthProperty().divide(NUM_COL));
        gridPane.getChildren().add(image);
    }


    private static ViewDice findDiceInfo(Node node,List<ViewDice> viewDiceList) {
        for (ViewDice dice : viewDiceList) {
            if(dice.getDiceImage() == node) {
                return dice;
            }
        }
        return null;
    }

    public static void handleAlert(String message) {
        //AlertWindow.display("Alert", message);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
