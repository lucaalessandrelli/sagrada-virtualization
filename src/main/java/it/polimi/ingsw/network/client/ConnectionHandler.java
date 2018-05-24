package it.polimi.ingsw.network.client;

import java.io.Serializable;

public interface ConnectionHandler extends Serializable {
     void connect();
     void disconnect();
     String sendCommand(String cmd);
     String ping();
}
