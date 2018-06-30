package it.polimi.ingsw.view.gui;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.List;

public final class DrawRoundTrack {
    private DrawRoundTrack() {

    }

    public static void drawDiceRoundTrack(GridPane roundTrackGrid, int row, int col, List<ViewDice> diceList, String cell, MatchViewController controller) {
        Node pane = GeneralFunctionalities.getChildrenByIndex(roundTrackGrid, row, col);
        if (pane == null) {
            System.out.println("Sbagliato");
        } else {
            ImageView image = new ImageView("/dice/" + cell.substring(0, 2) + ".png");
            ((AnchorPane) pane).getChildren().add(image);
            GeneralFunctionalities.fitImageToParent(image, roundTrackGrid);

            /*add the WindowDiceEvent to the dice,plus add the dice to viewDiceList*/
            diceList.add(new ViewDice(image, cell.substring(2, 4)));
            EventAssigner.addRoundTrackEvent(image,controller);
        }
    }
}
