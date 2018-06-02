package it.polimi.ingsw.view.virtualview;

import it.polimi.ingsw.controller.ClientBox;
import it.polimi.ingsw.controller.VirtualViewParser;
import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.network.ClientInterface;

import java.rmi.RemoteException;

public class VirtualView extends VirtualViewObserver {
    public VirtualView(ClientBox s, Player player) {
    clientBox=s;
    this.player=player;
    }

    @Override
    public void update() {
        String updateMove;
        VirtualViewParser parser = new VirtualViewParser(player);
        updateMove = parser.startParsing();
        try {
            clientBox.update(updateMove);
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void updateStateTurn(String whoIsTurn) throws RemoteException {
        clientBox.updateTurn("service "+whoIsTurn+" current turn.");
    }


}
