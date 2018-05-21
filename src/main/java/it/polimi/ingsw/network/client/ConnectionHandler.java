package it.polimi.ingsw.network.client;

public interface ConnectionHandler {
     void connect();
     void disconnect();
     String sendCommand(String cmd);
     String ping();
}
