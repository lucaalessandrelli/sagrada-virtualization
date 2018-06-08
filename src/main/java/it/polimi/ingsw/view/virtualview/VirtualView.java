package it.polimi.ingsw.view.virtualview;

import it.polimi.ingsw.controller.ClientBox;
import it.polimi.ingsw.controller.VirtualViewParser;
import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.model.gamedata.gametools.WindowPatternCard;

import java.rmi.RemoteException;
import java.util.List;

public class VirtualView extends VirtualViewObserver {
    final static private String turn="turn ";
    final static private String timer="timer ";
    final static private String stateMove="gamestate ";

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
    public void updateStateTurn(String whoIsTurn,long timeSleep){
        try {
            clientBox.updateTurn(turn+whoIsTurn+" current turn.");
            timeSleep=timeSleep/1000;
            clientBox.updateTurn(timer+String.valueOf(timeSleep));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void wrongMove(String s){
        try {
            clientBox.wrongMove(s);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void chooseWindow(List<WindowPatternCard> windows) {
        VirtualViewParser parser = new VirtualViewParser(player);

       //String windows;
       //clientBox.chooseWindow(windows);
    }

    @Override
    public void timerChoose(long timerWindows) {
        try {
            clientBox.setTimer(timerWindows);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyState(String state) {
        try {
            clientBox.update(stateMove+state);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyScore(String s) {
        try {
            clientBox.update(s);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


}
