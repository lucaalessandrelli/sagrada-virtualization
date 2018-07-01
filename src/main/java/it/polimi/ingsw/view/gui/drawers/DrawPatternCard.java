package it.polimi.ingsw.view.gui.drawers;

import it.polimi.ingsw.view.gui.data.ViewDice;
import it.polimi.ingsw.view.gui.guicontrollers.MatchViewController;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.List;

public final class DrawPatternCard {

    private DrawPatternCard() {

    }

    public static void drawDicePatternCard(GridPane grid, int row, int col, boolean isMyWindow, List<ViewDice> diceList, String cell, MatchViewController controller) {
        Node pane = GeneralFunctionalities.getChildrenByIndex(grid,row,col);
        ImageView image = new ImageView("/dice/" + cell.substring(0,2) + ".png");
        ((AnchorPane)pane).getChildren().add(image);
        GeneralFunctionalities.fitImageToParent(image,grid);

        /*If the current window is the player's one then add the WindowDiceEvent to the dice,plus add the dice to viewDiceList*/
        if(isMyWindow) {
            diceList.add(new ViewDice(image,cell.substring(2,4)));
            EventAssigner.addWindowEvent(image,controller);
        }
    }

    public static void drawRestrictions(String restriction, Node anchorPane) {
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


    public static void drawFavTokens(ObservableList<Node> tokenList,List<String> favorsList, GridPane favorGrid) {
        for(int i = 0; i < tokenList.size();i++) {
            if(i < Integer.parseInt(favorsList.get(1))) {
                ImageView favimg = new ImageView("/viewLogo/favortoken.png");
                ((AnchorPane)tokenList.get(i)).getChildren().add(favimg);
                GeneralFunctionalities.fitImageToParent(favimg,favorGrid);
                favimg.setPreserveRatio(true);
            }
        }
    }

    public static void removeFavTokens(ObservableList<Node> tokenList) {
        for(int i = 0; i < tokenList.size();i++) {
            AnchorPane pane = (AnchorPane)tokenList.get(i);
            if(pane.getChildren().size() > 0) {
                pane.getChildren().remove(0);
            }
        }
    }
}
