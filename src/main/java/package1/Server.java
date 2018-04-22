package package1;

import java.util.ArrayList;

public class Server {
    private ArrayList<Match> matchList;
    private ArrayList<Client> clientList;
    private ClientHandler clientHandler;


    public Server() {

    }

    public boolean connect() {
        return true;
    }
}
