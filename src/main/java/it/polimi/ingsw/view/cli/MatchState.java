package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.view.SceneInterface;

import java.util.Arrays;
import java.util.List;

public class MatchState implements SceneInterface {
    private String setup;
    private CliHandler cliHandler;
    private Printer printer;
    private String timer;
    private List<String> players;
    private String turnState;

    public MatchState(Printer printer, CliHandler cliHandler, String timer) {
        this.printer=printer;
        this.cliHandler=cliHandler;
        this.timer=timer;
    }
    private void printUpdate(){
        printer.printMatch(setup,timer,players,turnState);
    }

    @Override
    public void handleTimer(String timer) {
        this.timer=timer;
    }

    @Override
    public void updateBoard(String setup) {
        this.setup=setup;
        printUpdate();
    }

    @Override
    public void handleConnectedPlayers(String playerlist) {
        players= Arrays.asList(playerlist.split(" "));
        printUpdate();

    }

    @Override
    public void handleTurnMessage(String turnMessage) {
        turnState=turnMessage;
        printUpdate();

    }

    @Override
    public void handleAlert(String alert) {
        printer.printError(alert);
    }

    @Override
    public void handleGameState(String gameState) {

    }

    @Override
    public void handleScore(String score) {
        cliHandler.setState(new ScoreState(score,printer,cliHandler));
    }
}
