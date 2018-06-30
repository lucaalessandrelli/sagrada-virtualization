package it.polimi.ingsw.view.gui;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.List;

public final class DrawDraftPool {

    private DrawDraftPool() {

    }

    public static void drawDiceIntoDraft(GridPane draftPool,int i,List<String> draftList, List<ViewDice> diceList, MatchViewController controller) {
        AnchorPane pane = ((AnchorPane)(draftPool.getChildren().get(i)));
        GeneralFunctionalities.deleteDice(pane,diceList);
        if(i < draftList.size()) {
            ImageView image = new ImageView("/dice/" + draftList.get(i) + ".png");
            pane.getChildren().add(image);
            diceList.add(new ViewDice(image,draftList.get(i)));
            GeneralFunctionalities.fitImageToParent(image,draftPool);

            EventAssigner.addDraftEvent(image,controller);
        }
    }
}
