package it.polimi.ingsw;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClientMain {

    static int PORT = 1234;
    public static void main(String arg[]){
        try {
            Registry registry=LocateRegistry.getRegistry("127.0.0.1",PORT);
            ServerInterface s = (ServerInterface) registry.lookup("server");
            Scanner in = new Scanner(System.in);
            System.out.println("Insert username");
            String name =in.next();
            String w = s.login(name);
            System.out.println(w);
            String answ = "";
            while(!answ.equals("ok")){
                System.out.println("Insert command");
                String cmd = in.next();
                answ = s.command(cmd);
                System.out.println(answ);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
