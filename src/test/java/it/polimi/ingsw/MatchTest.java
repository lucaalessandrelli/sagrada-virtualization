package it.polimi.ingsw;

import org.junit.jupiter.api.Test;
import package1.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MatchTest {
    private List<Match> matches;
    Server server = new Server();
    WaitingRoom lobby = server.getLobby();
    //private List<Player> players = new ArrayList<Player>();


    @Test
    public void maxPlayersMatchTest() {
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
    public void equalsTest() {
        lobby.setServer(server);

        Player player1 = new Player("1");
        Player player2 = new Player("1");

        server.connectPlayer(player1);

        assertTrue(lobby.getPlayerList().size() == 1);
        assertTrue(lobby.getPlayerList().contains(player1));
        assertTrue(lobby.getPlayerList().contains(player2));
    }

    @Test
    public void addPlayerWaitingRoomTest() {
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

/*
    @Test
    public void removePlayerStartedMatchTest() {
        Match match = new Match(1); // Match is tested
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();

        match.addPlayer(player1);
        match.addPlayer(player2);
        match.addPlayer(player3);
        match.addPlayer(player4);

        match.removePlayer(player1);

        // Testing the remove function when match state is StartedMatch
        assertTrue(match.getPlayerList().size() == 4);
        assertTrue(match.getPlayerList().contains(player1));
        assertTrue(match.getPlayerList().contains(player2));
        assertTrue(match.getPlayerList().contains(player3));
        assertTrue(match.getPlayerList().contains(player4));
    }*/

    @Test
    public void removePlayerWaitingRoomTest() {
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

        List<Round> roundList = match.getRoundList();

        assertTrue(roundList.size() == 1);

        match.startNextRound();

        assertTrue(roundList.size() == 2);

        for (Round round: roundList) {
            assertTrue(round.getRoundNumber() == i);
            i++;
        }

    }
}