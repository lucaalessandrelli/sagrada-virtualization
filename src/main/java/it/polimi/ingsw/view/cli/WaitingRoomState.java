package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.view.SceneInterface;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WaitingRoomState implements SceneInterface {
    final static String message = "You are currently in queue. Waiting for players...     Timer: ";
    String timer;
    Printer printer;
    CliHandler cliHandler;
    String tmpTimer;
    List<String> players;
    ScheduledExecutorService exec;

    public WaitingRoomState(Printer printer, CliHandler cliHandler,String timer) {
        this.timer=timer;
        this.printer=printer;
        this.cliHandler=cliHandler;
        this.tmpTimer=timer;
    }

    @Override
    public void handleTimer(String timer) {
        cliHandler.setState(new ChooseWindowState(printer,cliHandler,timer,players));
        exec.shutdown();
    }

    @Override
    public void handleConnectedPlayers(String playerlist) {
        players = Arrays.asList(playerlist.split(" "));
        printer.printWaitingRoom(tmpTimer,players,message);
        if(players.size()==2){
            exec = Executors.newSingleThreadScheduledExecutor();
            exec.scheduleWithFixedDelay(() -> {
                if(Integer.valueOf(tmpTimer)==0){
                    exec.shutdown();
                }
                printer.printWaitingRoom(tmpTimer,players,message);
                int i = Integer.parseInt(tmpTimer)-1;
                tmpTimer= String.valueOf(i);
            }, 0, 1, TimeUnit.SECONDS);
        }
    }
}
