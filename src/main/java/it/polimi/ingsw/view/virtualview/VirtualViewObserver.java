package it.polimi.ingsw.view.virtualview;

import it.polimi.ingsw.controller.ClientBox;
import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.network.ClientInterface;

import java.rmi.RemoteException;

//observer interface, it will be implemented by gui and cli
public abstract class VirtualViewObserver {
    //subject observable
    protected ClientBox clientBox;
    protected Player player;
    //this method will be called when a pattern card is modified
    public abstract void updatePatternCard();
    // this method is called when is the turn passes to the next player
    public abstract void updateStateTurn(String whoIsTurn) throws RemoteException;

}
