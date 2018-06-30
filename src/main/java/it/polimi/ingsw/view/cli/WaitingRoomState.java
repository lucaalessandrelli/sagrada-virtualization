package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.view.SceneInterface;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WaitingRoomState implements SceneInterface {
    private static final String MESSAGE = "Sei in coda. Aspetto altri giocatori...     Timer: ";
    private Printer printer;
    private CliHandler cliHandler;
    private String tmpTimer;
    private List<String> players;
    private ScheduledExecutorService exec;

    WaitingRoomState(Printer printer, CliHandler cliHandler, String timer) {
        this.printer = printer;
        this.cliHandler = cliHandler;
        this.tmpTimer = timer;
    }

    @Override
    public void handleTimer(String timer) {
        cliHandler.setState(new ChooseWindowState(printer, cliHandler, timer, players));
        exec.shutdown();
    }

    @Override
    public void handleConnectedPlayers(String playerlist) {
        players = Arrays.asList(playerlist.split(" "));
        printer.printWaitingRoom(tmpTimer, players, MESSAGE);
        exec = Executors.newSingleThreadScheduledExecutor();
        if (players.size() > 1 && !exec.isShutdown()) {
            startTimer();
        }
        if (players.size() == 1) {
            exec.shutdown();
            printer.printWaitingRoom(tmpTimer, players, MESSAGE);
        }
    }

    private void startTimer() {
        exec.scheduleWithFixedDelay(() -> {
            if (Integer.parseInt(tmpTimer) <= 0) {
                exec.shutdown();
                return;
            }
            printer.printWaitingRoom(tmpTimer, players, MESSAGE);
            int i = Integer.parseInt(tmpTimer) - 1;
            tmpTimer = String.valueOf(i);
        }, 0, 1, TimeUnit.SECONDS);
    }
}
