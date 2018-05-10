package it.polimi.ingsw;

import org.junit.jupiter.api.Test;
import package1.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class MatchTest {
    private List<Match> matches;
    //private List<Player> players = new ArrayList<Player>();


    @Test
    public void maxPlayersMatchTest() {
        Server server = new Server();

        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();
        Player player5 = new Player();

        server.connectPlayer(player1);
        server.connectPlayer(player2);
        server.connectPlayer(player3);
        server.connectPlayer(player4);
        server.connectPlayer(player5);

        matches = server.getMatchList();
        Match match1 = matches.get(0);
        WaitingRoom lobby = server.getLobby();

        // Testing max 4 players in a Match
        assertTrue(matches.size() == 1);
        assertTrue(server.getLobby().getPlayerList().size() == 1);
        assertTrue(match1.getPlayerList().size() == 4);
        assertTrue(match1.getPlayerList().contains(player1));
        assertTrue(match1.getPlayerList().contains(player2));
        assertTrue(match1.getPlayerList().contains(player3));
        assertTrue(match1.getPlayerList().contains(player4));
    }

    @Test
    public void addPlayerWaitingRoomTest() {
        Server server = new Server();

        Player player1 = new Player();
        Player player2 = new Player();

        server.connectPlayer(player1);
        server.connectPlayer(player2);

        // Testing addPlayer function in Waiting Room
        assertTrue(server.getLobby().getPlayerList().size() == 2);
        assertTrue(server.getLobby().getPlayerList().contains(player1));
        assertTrue(server.getLobby().getPlayerList().contains(player2));
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
    }

    @Test
    public void removePlayerWaitingRoomTest() {
        Match match = new Match(1); // Match is tested
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();

        match.addPlayer(player1);
        match.addPlayer(player2);
        match.addPlayer(player3);

        match.removePlayer(player1);

        // Testing the remove function when match state is WaitingRoom
        assertTrue(match.getPlayerList().size() == 2);
        assertTrue(match.getPlayerList().contains(player2));
        assertTrue(match.getPlayerList().contains(player3));
    }

    /* constructor of other class still not implemented
    //Testing numbers of players in waiting room going to 0
    @Test
    public void emptyWaitingRoomTest() {
        Server server = new Server();
        Match match1 = new Match(1);
        Match match2 = new Match(2);
        Match match3 = new Match(3);
        Match match4 = new Match(4);

        Player player1 = new Player();
        Player player2 = new Player();

        server.addMatch(match1);
        server.addMatch(match2);
        server.addMatch(match3);
        server.addMatch(match4);

        match1.addPlayer(player1);
        match1.addPlayer(player2);

        match1.removePlayer(player1);
        match1.removePlayer(player2);

        assertTrue(server.getMatchList().size() == 3);
        assertTrue(server.getMatchList().contains(match2));
        assertTrue(server.getMatchList().contains(match3));
        assertTrue(server.getMatchList().contains(match4));
    } */
/*
    @Test
    public void initializeRoundTest() {
        int i = 1;
        Match match = new Match(1);

        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();

        match.addPlayer(player1);
        match.addPlayer(player2);
        match.addPlayer(player3);
        match.addPlayer(player4);

        assertTrue(match.getRoundList().size() == 10);

        for (Round round: match.getRoundList()) {
            assertTrue(round.getRoundNumber() == i);
            i++;
        }

    }
    */
}