package it.polimi.ingsw.network.client;

import java.io.Serializable;

public interface ConnectionHandler {
     void connect();
     /*void disconnect();*/
     void sendCommand(String cmd);
}
