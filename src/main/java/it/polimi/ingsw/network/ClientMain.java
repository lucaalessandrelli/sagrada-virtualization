package it.polimi.ingsw.network;

import it.polimi.ingsw.network.client.Client;

import java.rmi.RemoteException;
import java.util.Scanner;

import static java.lang.System.*;

public class ClientMain {
    private static String addr;

    public static void main(String[] arg) throws RemoteException {
        /*if(arg!=null){
            addr=arg[0];
        }else{
            addr="127.0.0.1";
        }*/
        addr="127.0.0.1";
        Scanner in = new Scanner(System.in);
        out.println("Choose kind of connection:\n 1)Socket\n 2)RMI");
        int conn = in.nextInt();
        out.println("Insert username:");
        String name = in.next();
        Client client = new Client(name,conn,"addr");
        client.connect();
        while (!client.connected()) {
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
            }
    }
}

