package it.polimi.ingsw.network.client;


import it.polimi.ingsw.view.MessageAnalyzer;

import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageQueue {
    private ConcurrentLinkedQueue<String> messages;
    private MessageAnalyzer messageAnalyzer;

    public MessageQueue(MessageAnalyzer messageAnalyzer){
        this.messageAnalyzer = messageAnalyzer;
        messages = new ConcurrentLinkedQueue<>();
    }

    synchronized void add(String s){
        messages.add(s);
        messageAnalyzer.notifyMessage();
    }
    synchronized public String poll(){
        return messages.poll();
    }

    synchronized public int size(){return messages.size();}
}
