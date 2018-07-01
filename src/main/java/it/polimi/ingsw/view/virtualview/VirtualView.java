package it.polimi.ingsw.view.virtualview;

import it.polimi.ingsw.controller.ClientBox;
import it.polimi.ingsw.controller.VirtualViewParser;
import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.model.gamedata.gametools.WindowPatternCard;

import java.rmi.RemoteException;
import java.util.List;

public class VirtualView extends VirtualViewObserver {
    private static final String TURN="turn ";
    private static final String TIMER="timer ";
    private static final String STATEMOVE="gamestate ";

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
            timeSleep=timeSleep/1000;
            clientBox.updateTurn(TIMER+String.valueOf(timeSleep));
            clientBox.updateTurn(TURN+"E' il turno di : "+whoIsTurn);
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

       String wind= parser.extractedWindows(windows);
        try {
            clientBox.chooseWindow(wind);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
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
            clientBox.update(STATEMOVE+state);
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

    @Override
    public void reconnectingMessage() {
        try {
            clientBox.updateTurn(TIMER + "0");
            clientBox.updateTurn(TURN + "Aspetta un attimo, ti sto riconnettendo...");
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

}
