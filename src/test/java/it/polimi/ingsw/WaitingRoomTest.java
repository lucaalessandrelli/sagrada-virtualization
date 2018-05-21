package it.polimi.ingsw;

import it.polimi.ingsw.model.gameData.Player;
import it.polimi.ingsw.network.server.Server;
import org.junit.jupiter.api.Test;
import java.rmi.RemoteException;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WaitingRoomTest {
    Server server = new Server();
    WaitingRoom lobby = server.getLobby();

    public WaitingRoomTest() throws RemoteException {
    }

    @Test
    public void addPlayerTest() {
        lobby.setServer(server);

        Player player1 = new Player("1");
        Player player2 = new Player("2");

        server.connectPlayer(player1);
        server.connectPlayer(player2);

        // Testing addPlayer function in Waiting Room
        assertTrue(lobby.getPlayerList().size() == 2);
        assertTrue(lobby.getPlayerList().contains(player1));
        assertTrue(lobby.getPlayerList().contains(player2));
    }

    @Test
    public void removePlayerTest() {
        lobby.setServer(server);

        Player player1 = new Player("2");
        Player player2 = new Player("2");
        Player player3 = new Player("3");

        server.connectPlayer(player1);
        server.connectPlayer(player2);
        server.connectPlayer(player3);

        server.disconnectPlayer(player1);

        // Testing the remove function when match state is WaitingRoom
        assertTrue(lobby.getPlayerList().size() == 2);
        assertTrue(lobby.getPlayerList().contains(player2));
        assertTrue(lobby.getPlayerList().contains(player3));
    }


    @Test
    public void equalsTest() {
        lobby.setServer(server);

        Player player1 = new Player("1");
        Player player2 = new Player("1");

        server.connectPlayer(player1);

        assertTrue(lobby.getPlayerList().size() == 1);
        assertTrue(lobby.getPlayerList().contains(player1));
        assertFalse(lobby.getPlayerList().contains(player2));
    }
}
