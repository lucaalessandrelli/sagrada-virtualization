package it.polimi.ingsw.network.client;


import it.polimi.ingsw.view.AbstractView;

import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageQueue {
    private ConcurrentLinkedQueue<String> messages;
    private AbstractView view;


    public MessageQueue(AbstractView view){
        this.view = view;
        messages = new ConcurrentLinkedQueue<>();
    }

    synchronized void add(String s){
        messages.add(s);
        view.notifyMessage();
    }
    synchronized public String poll(){
        return messages.poll();
    }

    synchronized public int size(){return messages.size();}
}
