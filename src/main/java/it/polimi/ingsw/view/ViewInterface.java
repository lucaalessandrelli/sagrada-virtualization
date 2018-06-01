package it.polimi.ingsw.view;

import it.polimi.ingsw.network.client.MessageQueue;
import javafx.collections.ObservableList;

public interface ViewInterface {
    void handleAlert(String message);
    void handleConnected(String message);
    void handleService(ObservableList<String> list);
}
