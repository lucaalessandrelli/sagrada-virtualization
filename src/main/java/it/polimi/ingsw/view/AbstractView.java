package it.polimi.ingsw.view;

import it.polimi.ingsw.network.client.MessageQueue;

public abstract class AbstractView  {
    public SceneInterface scene;
    public MessageQueue queue;

    public void notifyMessage() {
        readQueue();
    }

    public void readQueue() {
        while(queue.size()>0) {
            analyze(queue.poll());
        }
    }

    public void analyze(String message){
        if(message.equals("service Disconnected form server")){
            message = message.replace("service ", "");
        }else if(message.startsWith("setup")){
            String setup = message.replace("setup ","");
            scene.updateBoard(setup);

        }else if(message.startsWith("match")){
            String match = message.replace("match ","");
            scene.handleMatchId(match);

        }else if(message.startsWith("turn")){
            String turnMessage = message.replace("turn ","");
            scene.handleTurnMessage(turnMessage);

        } else if(message.startsWith("timer")){
            String time = message.replace("timer ","");
            scene.handleTimer(time);

        }else if(message.startsWith("connectedPlayers")) {
            String playerList = message.replace("connectedPlayers ","");
            scene.handleConnectedPlayers(playerList);

        }else if(message.startsWith("alert")) {
            String alert = message.replace("alert ", "");
            scene.handleAlert(alert);

        }else if(message.equals("Connected, Welcome!")) {
            scene.handleClientConnected(message);
        }else if(message.startsWith("choseWindow")) {
            String windows = message.replace("choseWindow ", "");
            scene.setPatternCards(windows);

        }else if(message.startsWith("gamestate")) {
            String gameState = message.replace("gamestate ", "");
            scene.handleGameState(gameState);
        }else if(message.startsWith("score ")) {
            String score = message.replace("score ", "");
            scene.handleScore(score);

        }
    }

    public void setQueue(MessageQueue queue) {
        this.queue=queue;
    }
}
