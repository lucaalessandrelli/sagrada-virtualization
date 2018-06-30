package it.polimi.ingsw.view.virtualview;

import it.polimi.ingsw.controller.ClientBox;
import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.model.gamedata.gametools.WindowPatternCard;

import java.util.List;

//observer interface, it will be implemented by gui and cli
public abstract class VirtualViewObserver {
    //subject observable
    ClientBox clientBox;
    protected Player player;
    //this method will be called when a pattern card is modified
    public abstract void update();
    // this method is called when is the turn passes to the next player
    public abstract void updateStateTurn(String whoIsTurn, long timeSleep);

    public abstract void wrongMove(String s);
    
    public abstract void chooseWindow(List<WindowPatternCard> windows);

    public abstract void timerChoose(long timerWindows);

    public abstract void notifyState(String state);

    public abstract void notifyScore(String s);

    public abstract void reconnectingMessage();
}
