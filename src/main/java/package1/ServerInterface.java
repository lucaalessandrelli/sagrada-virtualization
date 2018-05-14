package package1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {

    public String login(String name) throws RemoteException;
}
