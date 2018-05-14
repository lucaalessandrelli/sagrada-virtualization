package package1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Server extends UnicastRemoteObject implements ServerInterface {
    private WaitingRoom lobby;
    private List<Match> matchList;
    private List<Client> clientList;
    private ClientHandler clientHandler;
    private int idMatch;
    private long timerWaitingRoom;


    public Server() throws RemoteException {
        super();
        this.matchList = new ArrayList<Match>();
        this.clientList = new ArrayList<Client>();
        this.clientHandler = new ClientHandler();
        this.idMatch = 0;
        this.timerWaitingRoom = 5000;
        this.lobby = new WaitingRoom(timerWaitingRoom);
    }

    //Modifier methods

    public void connectPlayer(Player player) {
        lobby.addPlayer(player);
    }

    public void disconnectPlayer(Player player) {
        lobby.removePlayer(player);
    }

    public void createMatch(WaitingRoom lobby) {
        matchList.add(new Match(lobby.getPlayerList(), this, idMatch));
        /*START MATCH*/
        matchList.get(idMatch).start();
        idMatch++;
        lobby.restore();
    }

    public boolean connect() {
        return true;
    }

    //Getter methods
    public List<Match> getMatchList() {
        return this.matchList;
    }

    public WaitingRoom getLobby() {
        return lobby;
    }

    @Override
    public String login(String name) throws RemoteException {
        System.out.println(name + " is connecting");
        return new String("welcome");
    }
}
