package it.polimi.ingsw.view.gui;

public interface GuiInterface {

    default void handleAlert(String alert) {

    }

    default void handleClientConnected(String messageConnection) {

    }

    default void handleConnectedPlayers(String playerlist) {

    }

    default void handleTimer(String timer) {

    }

    default void handleMatchId(String idMatch) {

    }

    default void handleTurnMessage(String turnMessage) {

    }

    default void updateBoard(String setup) {

    }

    default void setPatternCards(String patternCards) {

    }

    default void handleGameState(String gameState) {

    }

    default void handleScore(String score) {

    }
}
