package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.ClientInterface;
import it.polimi.ingsw.network.ServerInterface;

import java.io.Serializable;

public class Client implements ClientInterface,Serializable {
    private String name;
    private ServerInterface server;
    private boolean connected;
    private int kindConnection;
    private ConnectionHandler connectionHandler;

    public Client(String name,int conn){
        this.name=name;
        connected=false;
        kindConnection=conn;
        if(kindConnection==1){
            connectionHandler = new SocketConnection(this);
        }else if(kindConnection==2){
            connectionHandler = new RmiConnection(this);
        }else{
            System.out.println("Not valid choice, default connection : RMI");
            connectionHandler = new RmiConnection(this);
        }
    }

    protected void setConnected(boolean b){
        connected = b;
    }


    public void connect(){
        connectionHandler.connect();
    }

    public String ping(){
        return connectionHandler.ping();
    }


    @Override
    public void disconnect() {
        connectionHandler.disconnect();

    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public String getTypeConnection() {
        return "(RMI)";
    }

    public String sendCommand(String cmd) {
        return connectionHandler.sendCommand(cmd);
    }

    public boolean connected() {
        return connected;
    }

    public void setName(String n) {
        name=n;
    }
}
