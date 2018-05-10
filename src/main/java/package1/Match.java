package package1;

import java.util.List;

public class Match {
    public int id;
    public List<Player> playerList;
    public Server server;

    public Match(List<Player> playerList, Server server, int id) {
        //this need to be passed by copy from the server
        this.playerList = playerList;
        this.server = server;
        this.id = id;
    }

    //Getter methods
    public List<Player> getPlayerList() {
        return playerList;
    }
}
