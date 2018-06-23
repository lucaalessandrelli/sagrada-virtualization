package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.network.client.Client;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SelValueWindow {
    private static final int NUM_ROWS = 0;
    private static final int NUM_COL = 6;
    private static Stage stage;
    private static double OFFSET = 10;

    public static void display(Client client, ViewDice dice, int diceChosenRow, int diceChosenColumn) {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Select dice value");

        AnchorPane anchorPane = new AnchorPane();

        GridPane gridPane = new GridPane();

        for(int i = 0; i < NUM_COL;i++) {
            ImageView image = new ImageView("/dice/"+dice.getDiceColor()+(i+1)+".png");
            gridPane.setConstraints(image,i,NUM_ROWS);
            image.fitHeightProperty().bind(gridPane.heightProperty());
            image.fitWidthProperty().bind(gridPane.widthProperty().divide(NUM_COL));
            gridPane.getChildren().add(image);
            image.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent mouseEvent) -> handleValueChosen(mouseEvent,client,dice,diceChosenRow,diceChosenColumn));
        }

        anchorPane.getChildren().add(gridPane);

        anchorPane.setTopAnchor(gridPane,OFFSET);
        anchorPane.setLeftAnchor(gridPane,OFFSET);
        anchorPane.setBottomAnchor(gridPane,OFFSET);
        anchorPane.setRightAnchor(gridPane,OFFSET);

        Scene scene = new Scene(anchorPane,500,150);
        stage.setScene(scene);
        stage.showAndWait();
    }

    private static void handleValueChosen(MouseEvent event,Client client,ViewDice dice,int r,int c) {
        client.sendCommand("move "+client.getNumOfMatch()+" "+client.getName()+" D;"+dice.getDiceColor()+","+dice.getDiceNumber()+","+r+","+c);
        stage.close();
        event.consume();
    }
}
