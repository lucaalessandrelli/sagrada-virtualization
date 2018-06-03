package it.polimi.ingsw.view;

import it.polimi.ingsw.network.client.MessageQueue;
import javafx.collections.ObservableList;

import java.util.SplittableRandom;

public interface ViewInterface {
    void handleAlert(String message);
    void handleConnected(String message);
    void handleService(ObservableList<String> list);
    void handleTimer(String string);
    void handleMatchId(String idMatch);
    void updateBoard(String setup);
}
