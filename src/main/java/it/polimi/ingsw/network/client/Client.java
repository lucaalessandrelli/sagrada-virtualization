package it.polimi.ingsw.network.client;

import java.rmi.RemoteException;

import static java.lang.System.*;

public class Client  {
    private String name;
    private boolean connected;
    private int kindConnection;
    private ConnectionHandler connectionHandler;
    private int numOfMatch;
    private MessageQueue messages;

    public Client(String name,int conn, String addr) throws RemoteException{
        this.name=name;
        connected=false;
        kindConnection=conn;
        messages = new MessageQueue();
        if(kindConnection==1){
            connectionHandler = new SocketConnection(this,addr);
        }else if(kindConnection==2){
            connectionHandler = new RmiConnection(this,addr);
        }else{
            out.println("Not valid choice, default connection : RMI");
            connectionHandler = new RmiConnection(this,addr);
        }
    }

    synchronized void setConnected(boolean b){
        connected = b;
    }


    synchronized public void connect(){
        connectionHandler.connect();
    }

    synchronized public boolean ping(){
        return connectionHandler.ping();
    }


   /* @Override
    synchronized public void disconnect() {
        connectionHandler.disconnect();

    }*/


    synchronized public String getName() {
        return name;
    }



    /*synchronized public String getTypeConnection() {
        return "(RMI)";
    }*/


    synchronized public void updateWindows(String allWindows) throws RemoteException {

    }


    synchronized public void updateDraftPool(String draftPool) throws RemoteException {

    }

    synchronized public void updatePlayers(String playersIn) throws RemoteException {

    }

    synchronized public void updateTurn(String whoIsTurn) {

    }

    public void sendCommand(String cmd) {
         connectionHandler.sendCommand(cmd);
    }

    public boolean connected() {
        return connected;
    }

    public void setName(String n) {
        name=n;
        setAddress("127.0.0.1");
    }

    public void setAddress(String addr) {
        if(kindConnection==1){
            connectionHandler = new SocketConnection(this,addr);
        }else if(kindConnection==2){
            connectionHandler = new RmiConnection(this,addr);
        }else{
            connectionHandler = new RmiConnection(this,addr);
        }
    }

    public void setServiceMessage(String serviceMessage) {
        System.out.println(serviceMessage);
    }

    public void setNumMatch(int i) {
        numOfMatch=i;
    }
    public synchronized void viewMessage(String s){
        messages.add(s);
    }
}
