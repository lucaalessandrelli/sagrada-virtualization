package it.polimi.ingsw.network.server;


import it.polimi.ingsw.controller.Manager;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

public class ServerMain {

    public static void main(String[] args) {
        OptionParser parser = new OptionParser();
        parser.accepts("timerRoom").withOptionalArg().ofType(Integer.class).defaultsTo(30);
        parser.accepts("timerCard").withOptionalArg().ofType(Integer.class).defaultsTo(10);
        parser.accepts("timerMove").withOptionalArg().ofType(Integer.class).defaultsTo(30);

        OptionSet options = parser.parse(args);

        try {
            Server server = new Server();
            Manager manager = new Manager((Integer) options.valueOf("timerRoom"),(Integer)options.valueOf("timerCard"),
                    (Integer)options.valueOf("timerMove"));
            server.setManager(manager);
            manager.checkEndGame();
            NetworkManager networkManager = new NetworkManager(server);
            networkManager.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
