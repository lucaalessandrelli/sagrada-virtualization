package package1;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private WaitingRoom lobby;
    private List<Match> matchList;
    private List<Client> clientList;
    private ClientHandler clientHandler;
    private int idMatch;
    private long timerWaitingRoom;


    public Server() {
        this.matchList = new ArrayList<Match>();
        this.clientList = new ArrayList<Client>();
        this.clientHandler = new ClientHandler();
        this.idMatch = 0;
        this.timerWaitingRoom = 5000;
        this.lobby = new WaitingRoom(timerWaitingRoom, this);
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
}
