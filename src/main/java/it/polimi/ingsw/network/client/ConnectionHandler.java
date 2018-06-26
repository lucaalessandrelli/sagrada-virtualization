package it.polimi.ingsw.network.client;

public interface ConnectionHandler {
     void connect();
     void sendCommand(String cmd);
}
