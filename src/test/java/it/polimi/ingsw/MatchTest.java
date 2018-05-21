package it.polimi.ingsw;

import it.polimi.ingsw.model.gameData.Player;
import it.polimi.ingsw.model.gameLogic.Round;
import it.polimi.ingsw.network.server.Server;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MatchTest {
    private ArrayList<Match> matches;
    Server server = new Server();
    WaitingRoom lobby = server.getLobby();

    public MatchTest() throws RemoteException {
    }


    @Test
    public void maxPlayersTest() {
        lobby.setServer(server);

        Player player1 = new Player("1");
        Player player2 = new Player("2");
        Player player3 = new Player("3");
        Player player4 = new Player("4");
        Player player5 = new Player("5");

        server.connectPlayer(player1);
        server.connectPlayer(player2);
        server.connectPlayer(player3);
        server.connectPlayer(player4);
        server.connectPlayer(player5);

        matches = server.getMatchList();
        Match match1 = matches.get(0);

        // Testing max 4 players in a Match
        assertTrue(lobby.getPlayerList().size() == 1);
        assertTrue(lobby.getPlayerList().contains(player5));

        assertTrue(matches.size() == 1);
        assertTrue(match1.getPlayerList().size() == 4);
        assertTrue(match1.getPlayerList().contains(player1));
        assertTrue(match1.getPlayerList().contains(player2));
        assertTrue(match1.getPlayerList().contains(player3));
        assertTrue(match1.getPlayerList().contains(player4));
        assertFalse(match1.getPlayerList().contains(player5));
    }

    @Test
    public void initializeRoundTest() {
        int i = 1;
        lobby.setServer(server);

        Player player1 = new Player("1");
        Player player2 = new Player("2");
        Player player3 = new Player("3");
        Player player4 = new Player("4");

        server.connectPlayer(player1);
        server.connectPlayer(player2);
        server.connectPlayer(player3);
        server.connectPlayer(player4);

        Match match = server.getMatchList().get(0);

        assertTrue(match.getPlayerList().size() == 4);
        assertTrue(match.getRoundList().size() == 10);

        for (Round round: match.getRoundList()) {
            assertTrue(round.getRoundNumber() == i);
            i++;
        }

    }
}