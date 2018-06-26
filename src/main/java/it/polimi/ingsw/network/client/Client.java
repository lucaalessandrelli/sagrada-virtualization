package it.polimi.ingsw.network.client;

import it.polimi.ingsw.view.AbstractView;

public class Client  {
    private String name;
    private boolean connected;
    private int kindConnection;
    private String addr;
    private ConnectionHandler connectionHandler;
    private int numOfMatch;
    private MessageQueue messages;

    public Client(String addr) {
        connected=false;
        this.addr = addr;
    }

    public void setQueue(AbstractView view){
        messages = new MessageQueue(view);
    }


    public synchronized void setConnected(boolean b){
        connected = b;
    }


    public synchronized void connect(){
        connectionHandler.connect();
    }

    public synchronized String getName() {
        return name;
    }

    public void sendCommand(String cmd) {
         connectionHandler.sendCommand(cmd);
    }

    public synchronized boolean connected() {
        return connected;
    }

    public synchronized void setName(String n) {
        name=n;

    }

    private void setAddress(String addr) {
        if(kindConnection==1){
            connectionHandler = new SocketConnection(this,addr);
        }else if(kindConnection==2){
            connectionHandler = new RmiConnection(this,addr);
        }else{
            connectionHandler = new RmiConnection(this,addr);
        }
    }

    public void setKindConnection(int kindConnection) {
        this.kindConnection = kindConnection;
        setAddress(addr);
    }

    public MessageQueue getQueue() {
        return messages;
    }

    void setServiceMessage(String serviceMessage) {
        messages.add(serviceMessage);
    }

    public void setNumMatch(int i) {
        numOfMatch=i;
    }
    public int getNumOfMatch(){return numOfMatch;}
    public synchronized void viewMessage(String s){
        messages.add(s);
    }

}
