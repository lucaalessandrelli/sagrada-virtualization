package it.polimi.ingsw.network.client;


import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageQueue {
    private ConcurrentLinkedQueue<String> messages;
    //viewInterface to notify

    MessageQueue(){
        messages = new ConcurrentLinkedQueue<>();
    }

    synchronized void add(String s){
        messages.add(s);
        //notify for view
    }
    synchronized String poll(){
        return messages.poll();
    }
    synchronized int size(){return messages.size();}
}
