package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.network.client.Client;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SelValueWindow {
    private static final int NUM_ROWS = 0;
    private static final int NUM_COL = 6;
    private static final String SELVALUE = "SelectingValue";
    private static final String ALERT_DICE_SEL_VALUE = "Non puoi scegliere il valore di un dado in questa fase del turno.";
    private static Stage stage;
    private static GridPane gridPane;
    private static AnchorPane anchorPane;
    private static double OFFSET = 10;

    public static void display(Client client, ViewDice dice, int diceChosenRow, int diceChosenColumn,String currentState) {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Seleziona il valore del dado");

        anchorPane = new AnchorPane();
        gridPane = new GridPane();

        for(int i = 0; i < NUM_COL;i++) {
            ImageView image = new ImageView("/dice/"+dice.getDiceColor()+(i+1)+".png");
            gridPane.setConstraints(image,i,NUM_ROWS);
            image.fitHeightProperty().bind(gridPane.heightProperty());
            image.fitWidthProperty().bind(gridPane.widthProperty().divide(NUM_COL));
            gridPane.getChildren().add(image);
            image.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent mouseEvent) -> handleValueChosen(mouseEvent,currentState,client,dice,diceChosenRow,diceChosenColumn));
        }

        anchorPane.getChildren().add(gridPane);

        anchorPane.setTopAnchor(gridPane,OFFSET);
        anchorPane.setLeftAnchor(gridPane,OFFSET);
        anchorPane.setBottomAnchor(gridPane,OFFSET);
        anchorPane.setRightAnchor(gridPane,OFFSET);

        Scene scene = new Scene(anchorPane,600,100);
        stage.setScene(scene);
        stage.showAndWait();
    }

    private static void handleValueChosen(MouseEvent event,String currentState, Client client,ViewDice dice,int r,int c) {
        if(currentState.equals(SELVALUE)) {
            client.sendCommand("move " + client.getNumOfMatch() + " " + client.getName() + " D;" + dice.getDiceColor() + "," + gridPane.getColumnIndex((Node) event.getSource()) + "," + r + "," + c);
        } else {
            handleAlert(ALERT_DICE_SEL_VALUE);
        }
        stage.close();
        event.consume();
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
