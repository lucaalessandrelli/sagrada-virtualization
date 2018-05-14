package package1;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerMain {

    static int PORT = 1234;

    public static void main(String args[]) {
        try {
           Server server = new Server();
            Registry registry = null;
            registry = LocateRegistry.createRegistry(PORT);
            registry.bind("server",server);
            System.out.println("Server is up");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
