package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.InputComposer;
import it.polimi.ingsw.view.AbstractView;
import it.polimi.ingsw.view.SceneInterface;

public class CliHandler extends AbstractView {
    private Client client;
    private Printer printer;
    private InputComposer composer;
    private GameData gameData;

    public CliHandler(Client client){
        this.client= client;
        this.printer = new Printer();
        this.scene = new LoginState(printer,client,this);
        this.composer = new InputComposer(client);
        this.gameData = new GameData(client.getName());
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
            String cmd =composer.sanitize(printer.getCommand(),gameData);
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

    void setRoundtrack(String roundTrack){
        gameData.setRoundTrack(roundTrack);
    }

    void setDraftPool(String draftPool){
        gameData.setDraftPool(draftPool);
    }
    void setRestrictions(String restrictions){
        gameData.setRestrictions(restrictions);
    }
}
