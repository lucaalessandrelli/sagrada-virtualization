package it.polimi.ingsw.view;

import it.polimi.ingsw.network.client.MessageQueue;
import javafx.collections.ObservableList;

import java.util.SplittableRandom;

public interface ViewInterface {
    void handleAlert(String alert);
    void handleConnected(String messageConnection);
    void handleService(String playerlist);
    void handleTimer(String timer);
    void handleMatchId(String idMatch);
    void updateBoard(String setup);
}
