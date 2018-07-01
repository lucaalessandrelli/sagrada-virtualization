package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.view.AbstractView;
import it.polimi.ingsw.view.SceneInterface;
import javafx.application.Platform;

public class GuiHandler extends AbstractView implements SceneInterface {
    private SceneInterface view;

    public GuiHandler() {
        scene=this;
    }

    public void setGui(SceneInterface gui) {
        this.view = gui;
    }

    @Override
    public void handleAlert(String alert) {
        Platform.runLater(() -> view.handleAlert(alert));
    }

    @Override
    public void handleClientConnected(String messageConnection) {
        Platform.runLater(() -> view.handleClientConnected(messageConnection));
    }

    @Override
    public void handleConnectedPlayers(String playerlist) {
        Platform.runLater(() -> view.handleConnectedPlayers(playerlist));
    }

    @Override
    public void handleTimer(String timer){
        Platform.runLater(() -> view.handleTimer(timer));
    }

    @Override
    public void handleMatchId(String idMatch) {
        Platform.runLater(() -> view.handleMatchId(idMatch));
    }

    @Override
    public void handleTurnMessage(String turnMessage) {
        Platform.runLater(() -> view.handleTurnMessage(turnMessage));
    }

    @Override
    public void updateBoard(String setup) {
        Platform.runLater(() -> view.updateBoard(setup));
    }

    @Override
    public void setPatternCards(String patternCards) {
        Platform.runLater(() -> view.setPatternCards(patternCards));
    }

    @Override
    public void handleGameState(String gameState) {
        Platform.runLater(() -> view.handleGameState(gameState));
    }

    @Override
    public void handleScore(String score) {
        Platform.runLater(() -> view.handleScore(score));
    }
}
