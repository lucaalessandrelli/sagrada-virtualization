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

    public void handleAlert(String alert) {
        Platform.runLater(() -> {
            // Update UI here.
            view.handleAlert(alert);
        });
    }

    public void handleClientConnected(String messageConnection) {
        Platform.runLater(() -> {
            // Update UI here.
            view.handleClientConnected(messageConnection);
        });
    }

    public void handleConnectedPlayers(String playerlist) {
        Platform.runLater(() -> {
            // Update UI here.
            view.handleConnectedPlayers(playerlist);
        });
    }

    public void handleTimer(String timer){
        Platform.runLater(() -> {
            // Update UI here.
            view.handleTimer(timer);
        });
    }

    public void handleMatchId(String idMatch) {
        Platform.runLater(() -> {
            // Update UI here.
            view.handleMatchId(idMatch);
        });
    }

    public void handleTurnMessage(String turnMessage) {
        Platform.runLater(() -> {
            // Update UI here.
            view.handleTurnMessage(turnMessage);
        });
    }

    public void updateBoard(String setup) {
        Platform.runLater(() -> {
            // Update UI here.
            view.updateBoard(setup);
        });
    }

    public void setPatternCards(String patternCards) {
        Platform.runLater(() -> {
            // Update UI here.
            view.setPatternCards(patternCards);
        });
    }

    public void handleGameState(String gameState) {
        Platform.runLater(() -> {
            // Update UI here.
            view.handleGameState(gameState);
        });
    }

    public void handleScore(String score) {
        Platform.runLater(() -> {
            // Update UI here.
            view.handleScore(score);
        });
    }
}
