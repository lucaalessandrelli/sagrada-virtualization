package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.view.AbstractView;
import it.polimi.ingsw.view.SceneInterface;
import javafx.application.Platform;

public class GuiHandler extends AbstractView implements SceneInterface {

    SceneInterface view;
    public GuiHandler() {
        scene=this;
    }

    public void setGui(SceneInterface gui) {
        this.view = gui;
    }

    @Override
    public void handleAlert(String alert) {
        Platform.runLater(() -> {
            // Update UI here.
            view.handleAlert(alert);
        });
    }

    @Override
    public void handleClientConnected(String messageConnection) {
        Platform.runLater(() -> {
            // Update UI here.
            view.handleClientConnected(messageConnection);
        });
    }

    @Override
    public void handleConnectedPlayers(String playerlist) {
        Platform.runLater(() -> {
            // Update UI here.
            view.handleConnectedPlayers(playerlist);
        });
    }

    @Override
    public void handleTimer(String timer){
        Platform.runLater(() -> {
            // Update UI here.
            view.handleTimer(timer);
        });
    }

    @Override
    public void handleMatchId(String idMatch) {
        Platform.runLater(() -> {
            // Update UI here.
            view.handleMatchId(idMatch);
        });
    }

    @Override
    public void handleTurnMessage(String turnMessage) {
        Platform.runLater(() -> {
            // Update UI here.
            view.handleTurnMessage(turnMessage);
        });
    }

    @Override
    public void updateBoard(String setup) {
        Platform.runLater(() -> {
            // Update UI here.
            view.updateBoard(setup);
        });
    }

    @Override
    public void setPatternCards(String patternCards) {
        Platform.runLater(() -> {
            // Update UI here.
            view.setPatternCards(patternCards);
        });
    }

    @Override
    public void handleGameState(String gameState) {
        Platform.runLater(() -> {
            // Update UI here.
            view.handleGameState(gameState);
        });
    }

    @Override
    public void handleScore(String score) {
        Platform.runLater(() -> {
            // Update UI here.
            view.handleScore(score);
        });
    }
}
