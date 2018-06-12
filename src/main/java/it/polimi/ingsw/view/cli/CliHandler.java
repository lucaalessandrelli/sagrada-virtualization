package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.view.ViewInterface;

public class CliHandler implements ViewInterface {
    private Client client;
    private Printer printer;

    public CliHandler(Client client){
        this.client=client;
        this.printer = new Printer();
    }

    @Override
    public void handleAlert(String alert) {
        printer.printError(alert);
    }

    @Override
    public void handleClientConnected(String messageConnection) {
        printer.print(messageConnection);

    }

    @Override
    public void handleConnectedPlayers(String playerlist) {

    }

    @Override
    public void handleTimer(String timer) {

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

    public void initialize() {
        printer.welcome();
        while (!client.connected()) {
            client.setName(printer.getName());
            client.setKindConnection(printer.getConnection());
            client.connect();
        }
    }

    public void reciveCommand(){
        while(client.connected()){
            client.sendCommand(printer.getCommand());
        }
    }
}
