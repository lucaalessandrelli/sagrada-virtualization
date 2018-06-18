package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.InputComposer;
import it.polimi.ingsw.view.AbstractView;
import it.polimi.ingsw.view.SceneInterface;

public class CliHandler extends AbstractView {
    private Client client;
    private Printer printer;
    private InputComposer composer;

    public CliHandler(Client client){
        this.client= client;
        this.printer = new Printer();
        this.scene = new LoginState(printer,client,this);
        this.composer = new InputComposer(client);
    }

    public void initialize() {
        while (!client.connected()) {
            client.setName(printer.getName());
            client.setKindConnection(printer.getConnection());
            client.connect();
        }
    }

    public void receiveCommand(){
        while(client.connected()) {
            String cmd =composer.sanitize(printer.getCommand());
            client.sendCommand(cmd);
        }
    }

    public void setState(SceneInterface newState) {
        this.scene =newState;
    }

    public void welcome() {
        printer.welcome();
    }

    public void setIdMatch(Integer num) {
        client.setNumMatch(num);
    }
}
