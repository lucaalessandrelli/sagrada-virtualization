package it.polimi.ingsw.network.client;

import it.polimi.ingsw.view.AbstractView;

import java.rmi.RemoteException;

public class Client  {
    private String name;
    private boolean connected;
    private int kindConnection;
    private String addr;
    private ConnectionHandler connectionHandler;
    private int numOfMatch;
    private MessageQueue messages;

    public Client(String addr) throws RemoteException{
        connected=false;
        this.addr = addr;
        //messages = new MessageQueue(view);
    }

    public void setQueue(AbstractView view){
        messages = new MessageQueue(view);
    }


    synchronized public void setConnected(boolean b){
        connected = b;
    }


    synchronized public void connect(){
        connectionHandler.connect();
    }



   /* @Override
    synchronized public void disconnect() {
        connectionHandler.disconnect();

    }*/


    synchronized public String getName() {
        return name;
    }




    public void sendCommand(String cmd) {
         connectionHandler.sendCommand(cmd);
    }

    public synchronized boolean connected() {
        return connected;
    }

    public void setName(String n) {
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

    public void setServiceMessage(String serviceMessage) {
        messages.add(serviceMessage);
    }

    public void setNumMatch(int i) {
        numOfMatch=i;
    }
    public int getNumOfMatch(){return numOfMatch;}
    public synchronized void viewMessage(String s){
        messages.add(s);
    }

    public void retryConnection(String command) {

    }
}
