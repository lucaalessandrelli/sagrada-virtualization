package it.polimi.ingsw.view.gui;

public interface GuiInterface {
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
