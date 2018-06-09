package it.polimi.ingsw.view;

import it.polimi.ingsw.network.client.MessageQueue;
import javafx.collections.ObservableList;

import java.util.SplittableRandom;

public interface ViewInterface {
    void handleAlert(String alert);
    void handleClientConnected(String messageConnection);
    void handleConnectedPlayers(String playerlist);
    void handleTimer(String timer);
    void handleMatchId(String idMatch);
    void handleTurnMessage(String turnMessage);
    void updateBoard(String setup);
    void setPatternCards(String patternCards);
    void handleGameState(String gameState);
    void handleScore(String score);
}
