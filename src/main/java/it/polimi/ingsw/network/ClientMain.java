package it.polimi.ingsw.network;

import it.polimi.ingsw.network.client.Client;

import java.util.Scanner;

public class ClientMain {

    static int PORT_RMI = 56789;

    public static void main(String arg[]) {
        Scanner in = new Scanner(System.in);
        System.out.println("Choose kind of connection:\n 1)Socket\n 2)RMI");
        int conn = in.nextInt();
        System.out.println("Insert username");
        String name = in.next();
        Client client = new Client(name, conn);
        client.connect();
        while (!client.connected()) {
            System.out.println("Insert valid username");
            client.setName(in.next());
            client.connect();
        }
        while (client.connected()) {
            System.out.println("Insert command");
            String cmd = in.next();
            String asw = client.sendCommand(cmd);
            System.out.println(asw);
            if (asw.equals("ok")) {
                client.disconnect();
            }
        }
    }
}

