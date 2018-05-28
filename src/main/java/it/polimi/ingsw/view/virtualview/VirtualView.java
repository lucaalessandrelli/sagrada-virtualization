package it.polimi.ingsw.view.virtualview;

import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.network.ClientInterface;

public class VirtualView extends VirtualViewObserver {
    public VirtualView(ClientInterface s, Player player) {
    clientInterface=s;
    this.player=player;
    }

    @Override
    public void updatePatternCard() {
        String allWindows;
        String draftPool;
        //call class compose string windows
        //call class compose string draftpool
        //clientInterface.updateWindows(allWindows);
        //clientInterface.updateDraftPool(draftPool);

    }

    @Override
    public void updateStateTurn(String whoIsTurn) {
        //call class compose string
        clientInterface.updateTurn(whoIsTurn);
    }

    @Override
    public void updatePlayers() {
        String allPlayers;
        //player.getOtherPlayers-->
       // clientInterface.updatePlayers(allPlayers);
    }
}
