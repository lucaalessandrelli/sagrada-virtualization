package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.view.AbstractView;
import it.polimi.ingsw.view.SceneInterface;

public class CliHandler extends AbstractView {
    private Client client;
    private Printer printer;

    public CliHandler(Client client){
        this.client= client;
        this.printer = new Printer();
        this.scene = new LoginState(printer,client,this);
    }

    public void initialize() {
        printer.welcome();
        while (!client.connected()) {
            client.setName(printer.getName());
            client.setKindConnection(printer.getConnection());
            client.connect();
        }
    }

    public void receiveCommand(){
        while(client.connected()){
            client.sendCommand(printer.getCommand());
        }
    }

    public void setState(SceneInterface newState) {
        this.scene =newState;
    }
}
