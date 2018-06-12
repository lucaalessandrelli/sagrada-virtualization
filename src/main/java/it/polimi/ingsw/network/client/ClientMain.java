package it.polimi.ingsw.network.client;

import it.polimi.ingsw.view.MessageAnalyzer;
import it.polimi.ingsw.view.gui.GuiHandler;
import it.polimi.ingsw.view.gui.GuiView;

import java.rmi.RemoteException;
import java.util.Scanner;

public class ClientMain {
    private static String addr;

    public static void main(String[] arg) throws RemoteException {
        /*if(arg!=null){
            addr=arg[0];
        }else{
            addr="127.0.0.1";
        }*/
        Scanner in = new Scanner(System.in);
        addr=in.nextLine();

        MessageAnalyzer messageAnalyzer = new MessageAnalyzer();
        Client client = new Client(addr,messageAnalyzer);

        //User chose GUI, so we need to create a GuiHandler object and set the the attribute ViewInterface in the MessageAnalyzer object
        GuiHandler guiHandler = new GuiHandler();
        messageAnalyzer.setView(guiHandler);
        //Add the guiHandler to the guiView so we can set the right controller object into the attribute GuiInterface in the GuiHandler object
        GuiView.setGuiHandler(guiHandler);

        messageAnalyzer.setMessageQueue(client.getQueue());

        GuiView.setClient(client);
        GuiView.launching();

        /*CliHandler cliHandler = new CliHandler(client);
        messageAnalyzer.setView(cliHandler);
        messageAnalyzer.setMessageQueue(client.getQueue());
        cliHandler.initialize();
        cliHandler.reciveCommand();
        */

    }
}

