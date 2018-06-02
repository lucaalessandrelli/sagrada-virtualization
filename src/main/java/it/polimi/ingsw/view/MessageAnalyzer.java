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
                    view.handleMatchSetup(setup);
                }
            });
        }else if(message.startsWith("match")){
            view.handleMatchId(message.replace("match ",""));
        }else if(message.startsWith("timer")){
            String time = message.replace("timer ","");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    view.handleTimer(time);
                }
            });
        }else if(message.startsWith("service")) {
            String finalMessage = message;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    view.handleService(FXCollections.observableArrayList(Arrays.asList(finalMessage.replace("service ", "").trim().split(" "))));
                }
            });

        }else if(message.startsWith("alert")) {
            view.handleAlert(message.replace("alert ", ""));
        }else if(message.equals("Connected, Welcome!")) {
            view.handleConnected(message);
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
