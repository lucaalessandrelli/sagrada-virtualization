package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.view.ViewInterface;
import javafx.application.Platform;

public class GuiHandler implements ViewInterface {
    private GuiInterface gui;

    public GuiHandler() {

    }

    public void setGui(GuiInterface gui) {
        this.gui = gui;
    }

    @Override
    public void handleAlert(String alert) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Update UI here.
                gui.handleAlert(alert);
            }
        });
    }

    @Override
    public void handleClientConnected(String messageConnection) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Update UI here.
                gui.handleClientConnected(messageConnection);
            }
        });
    }

    @Override
    public void handleConnectedPlayers(String playerlist) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Update UI here.
                gui.handleConnectedPlayers(playerlist);
            }
        });
    }

    @Override
    public void handleTimer(String timer){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Update UI here.
                gui.handleTimer(timer);
            }
        });
    }

    @Override
    public void handleMatchId(String idMatch) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Update UI here.
                gui.handleMatchId(idMatch);
            }
        });
    }

    @Override
    public void handleTurnMessage(String turnMessage) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Update UI here.
                gui.handleTurnMessage(turnMessage);
            }
        });
    }

    @Override
    public void updateBoard(String setup) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Update UI here.
                gui.updateBoard(setup);
            }
        });
    }

    @Override
    public void setPatternCards(String patternCards) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Update UI here.
                gui.setPatternCards(patternCards);
            }
        });
    }

    @Override
    public void handleGameState(String gameState) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Update UI here.
                gui.handleGameState(gameState);
            }
        });
    }

    @Override
    public void handleScore(String score) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Update UI here.
                gui.handleScore(score);
            }
        });
    }
}
