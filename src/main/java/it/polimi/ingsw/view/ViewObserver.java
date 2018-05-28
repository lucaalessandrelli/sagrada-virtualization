package it.polimi.ingsw.view;

import it.polimi.ingsw.model.gamedata.Player;

//observer interface, it will be implemented by gui and cli
public abstract class ViewObserver {
    //subject observable
    protected Player player;
    //this method will be called when a pattern card is modified
    public abstract void updatePatternCard();
    // this method is called when is the turn passes to the next player
    public abstract void updateStateTurn(String whoIsTurn);

}
