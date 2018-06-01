package it.polimi.ingsw.view;

import it.polimi.ingsw.network.client.MessageQueue;
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
        }else if(message.startsWith("play")){
            message =message.replace("play ","");
            }else if(message.startsWith("moveWindow")){
            String window= message.replace("moveWindow ","");

        }else if(message.startsWith("moveDraft")){
            String draft =message.replace("moveDraft ","");

        }else if(message.startsWith("service")) {
            view.handleService(FXCollections.observableArrayList(Arrays.asList(message.replace("service", "").split(","))));
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
