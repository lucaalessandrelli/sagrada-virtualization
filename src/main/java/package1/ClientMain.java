package package1;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientMain {

    static int PORT = 1234;
    public static void main(String arg[]){
        try {
            Registry registry=LocateRegistry.getRegistry("127.0.0.1",PORT);
            ServerInterface s = (ServerInterface) registry.lookup("server");
            String w = s.login("test1");
            System.out.println(w);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
