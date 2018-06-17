package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.view.AbstractView;
import it.polimi.ingsw.view.SceneInterface;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CliHandler extends AbstractView {
    private Client client;
    private Printer printer;
    //private CommandAnalyzer analyzer;

    public CliHandler(Client client){
        this.client= client;
        this.printer = new Printer();
        this.scene = new LoginState(printer,client,this);
        //this.analyzer = new CommandAnalyzer();
    }

    public void initialize() {
        while (!client.connected()) {
            client.setName(printer.getName());
            client.setKindConnection(printer.getConnection());
            client.connect();
        }
    }

    public void receiveCommand(){
        /*while(client.connected()) {
            client.sendCommand(printer.getCommand());
        }*/
        ExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.execute(new Runnable() {
            @Override
            public void run() {
                Scanner in = new Scanner(System.in);
                while (client.connected()){
                    client.sendCommand(in.nextLine());
                }
            }
        });

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
