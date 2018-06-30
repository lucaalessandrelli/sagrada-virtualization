package it.polimi.ingsw.view.gui;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public final class EventAssigner {
    private EventAssigner() {

    }

    public static void addDraftEvent(ImageView image, MatchViewController controller) {
        image.addEventHandler(MouseEvent.MOUSE_PRESSED, controller::handleDraftDiceClicked);
    }

    public static void addWindowEvent(ImageView image,MatchViewController controller) {
        image.addEventHandler(MouseEvent.MOUSE_PRESSED, controller::handleWindowDiceClicked);
    }

    public static void addRoundTrackEvent(ImageView image,MatchViewController controller) {
        image.addEventHandler(MouseEvent.MOUSE_PRESSED, controller::handleRoundTrackDiceClicked);
    }

}
