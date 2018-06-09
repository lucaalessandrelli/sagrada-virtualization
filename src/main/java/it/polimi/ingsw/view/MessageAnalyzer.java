package it.polimi.ingsw.view;

import it.polimi.ingsw.network.client.MessageQueue;
import javafx.application.Platform;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageAnalyzer {
    private ViewInterface view;
    private MessageQueue messageQueue;

    public MessageAnalyzer() {
    }

    public void setView(ViewInterface view) {
        this.view = view;
    }

    private void analyze(String message) {
        if(message.equals("service Disconnected form server")){
            message = message.replace("service ", "");
        }else if(message.startsWith("setup")){
            String setup = message.replace("setup ","");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    view.updateBoard(setup);
                }
            });

        }else if(message.startsWith("match")){
            String match = message.replace("match ","");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    view.handleMatchId(match);
                }
            });

        }else if(message.startsWith("turn")){
            String turnMessage = message.replace("turn ","");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    view.handleTurnMessage(turnMessage);
                }
            });

        } else if(message.startsWith("timer")){
            String time = message.replace("timer ","");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    view.handleTimer(time);
                }
            });
        }else if(message.startsWith("connectedPlayers")) {
            String playerList = message.replace("connectedPlayers ","");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    view.handleConnectedPlayers(playerList);
                }
            });

        }else if(message.startsWith("alert")) {
            String alert = message.replace("alert ", "");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    view.handleAlert(alert);
                }
            });

        }else if(message.equals("Connected, Welcome!")) {
            view.handleClientConnected(message);
        }else if(message.startsWith("choseWindow")) {
            String windwos = message.replace("choseWindow ", "");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    view.setPatternCards(windwos);
                }
            });
        }else if(message.startsWith("gamestate")) {
            String gameState = message.replace("gamestate ", "");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    view.handleGameState(gameState);
                }
            });
        }else if(message.startsWith("score")) {
            String score = message.replace("score ", "");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    view.handleScore(score);
                }
            });
        }
    }

    public void setMessageQueue(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    public void notifyMessage() {
        readQueue();
    }

    private void readQueue() {
        //scorre la queue e chiama su ogni stringa il metodo analyze
        while(messageQueue.size()>0) {
            analyze(messageQueue.poll());
        }
    }
}
