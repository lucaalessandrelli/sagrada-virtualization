package it.polimi.ingsw.view.gui.drawers;

import it.polimi.ingsw.view.gui.data.ViewDice;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.List;

public final class GeneralFunctionalities {
    private GeneralFunctionalities() {

    }

    public static Node getChildrenByIndex(GridPane gridPane, final int row, final int col) {
        ObservableList<Node> children = gridPane.getChildren();

        for (Node pane: children) {
            if(GridPane.getColumnIndex(pane) == col && GridPane.getRowIndex(pane) == row) {
                return pane;
            }
        }

        throw new IllegalStateException();
    }

    /**
     * Delete the imageView in the given cell of the DraftPoolGrid in order to update the draftpool, also it deletes the reference in the viewDiceList
     * @param pane the pane contained in a cell of the DraftPoolGrid
     */
    public static void deleteDice(AnchorPane pane, List<ViewDice> diceList) {
        ObservableList<Node> children = pane.getChildren();
        if(!children.isEmpty()) {
            ViewDice dice = findDiceInfo(children.get(0),diceList);
            if(dice != null) {
                diceList.remove(dice);
            }
            children.clear();
        }
    }

    public static ViewDice findDiceInfo(Node node,List<ViewDice> diceList) {
        for (ViewDice dice : diceList) {
            if(dice.getDiceImage() == node) {
                return dice;
            }
        }
        return null;
    }

    public static void fitImageToParent(ImageView image, AnchorPane pane) {
        image.fitWidthProperty().bind(pane.widthProperty());
        image.fitHeightProperty().bind(pane.heightProperty());
    }

    public static void fitImageToParent(ImageView image, GridPane grid) {
        image.fitWidthProperty().bind(grid.widthProperty().divide(grid.getColumnConstraints().size()));
        image.fitHeightProperty().bind(grid.heightProperty().divide(grid.getRowConstraints().size()));
    }

    public static void removeDiceFromGrid(GridPane grid, List<ViewDice> diceList) {
        int numRows = grid.getRowConstraints().size();
        int numColumns = grid.getColumnConstraints().size();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                GeneralFunctionalities.deleteDice((AnchorPane)grid.getChildren().get(numRows*i+j), diceList);
            }
        }
    }
}
