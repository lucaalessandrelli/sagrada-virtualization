package it.polimi.ingsw.network.client;

import it.polimi.ingsw.view.MessageAnalyzer;
import it.polimi.ingsw.view.cli.CliHandler;
import it.polimi.ingsw.view.gui.GuiHandler;
import it.polimi.ingsw.view.gui.GuiView;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

import java.rmi.RemoteException;

public class ClientMain {
    private static String addr = "127.0.0.1";

    public static void main(String[] args) throws RemoteException {
        OptionParser parser = new OptionParser();
        parser.accepts("a","server address").withRequiredArg().ofType(String.class).defaultsTo(addr);
        parser.accepts("gui","gui interface");
        parser.accepts("cli","cli interface");
        OptionSet options = parser.parse(args);
        addr = (String) options.valueOf("a");
        int ui;
        if(options.has("gui")){
            ui=1;
        }else if(options.has("cli")){
            ui=2;
        }else{
            ui=1;
        }
        MessageAnalyzer messageAnalyzer = new MessageAnalyzer();
        Client client = new Client(addr,messageAnalyzer);

        if(ui==1) {
            //User chose GUI, so we need to create a GuiHandler object and set the the attribute ViewInterface in the MessageAnalyzer object
            GuiHandler guiHandler = new GuiHandler();
            messageAnalyzer.setView(guiHandler);
            //Add the guiHandler to the guiView so we can set the right controller object into the attribute GuiInterface in the GuiHandler object
            GuiView.setGuiHandler(guiHandler);

            messageAnalyzer.setMessageQueue(client.getQueue());

            GuiView.setClient(client);
            GuiView.launching();
        }else if(ui==2) {
            CliHandler cliHandler = new CliHandler(client);
            messageAnalyzer.setView(cliHandler);
            messageAnalyzer.setMessageQueue(client.getQueue());
            cliHandler.initialize();
            cliHandler.receiveCommand();
        }

    }
}

