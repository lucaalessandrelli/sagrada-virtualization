package it.polimi.ingsw.network;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.view.gui.GuiHandler;
import it.polimi.ingsw.view.gui.GuiView;
import it.polimi.ingsw.view.MessageAnalyzer;

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
        //User chose GUI, so we need to create a GuiHandler object and set the the attribute ViewInterface in the MessageAnalyzer object
        GuiHandler guiHandler = new GuiHandler();
        messageAnalyzer.setView(guiHandler);
        //Add the guiHandler to the guiView so we can set the right controller object into the attribute GuiInterface in the GuiHandler object
        GuiView.setGuiHandler(guiHandler);

        Client client = new Client(addr,messageAnalyzer);
        messageAnalyzer.setMessageQueue(client.getQueue());

        GuiView.setClient(client);
        GuiView.launching();

       /* while (!client.connected()) {
            out.println("Insert valid username");
            client.setName(in.next());
            client.connect();
        }
        while (client.connected()) {
            client.viewMessage("Insert command:");
            String cmd = in.next();
            client.sendCommand(cmd);
            //System.out.println(asw);
            //if (asw.equals("ok")) {
             //   client.disconnect();
            }*/
    }
}

