package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.view.SceneInterface;

public class LoginState implements SceneInterface {
    private Printer printer;
    private Client client;
    private CliHandler cliHandler;

    public LoginState(Printer printer, Client client, CliHandler cliHandler) {
        this.printer=printer;
        this.client = client;
        this.cliHandler=cliHandler;
    }

    @Override
    public void handleAlert(String alert) {
        printer.printError(alert);
    }

    @Override
    public void handleClientConnected(String messageConnection) {
        printer.printConnection(messageConnection);
    }

    @Override
    public void handleConnectedPlayers(String playerlist) {

    }

    @Override
    public void handleTimer(String timer) {
        cliHandler.setState(new WaitingRoomState(timer,printer,cliHandler));
    }

    @Override
    public void handleMatchId(String idMatch) {

    }

    @Override
    public void handleTurnMessage(String turnMessage) {

    }

    @Override
    public void updateBoard(String setup) {

    }

    @Override
    public void setPatternCards(String patternCards) {

    }

    @Override
    public void handleGameState(String gameState) {

    }

    @Override
    public void handleScore(String score) {

    }
}
