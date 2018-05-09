package it.polimi.ingsw;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import package1.Match;
import package1.Player;
import package1.Round;
import package1.StartedMatch;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class MatchTest {
    //private List<Match> matches = new ArrayList<Match>();
    //private List<Player> players = new ArrayList<Player>();

    @Test
    public void maxPlayersMatchTest() {
        Match match = new Match(1);

        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();
        Player player5 = new Player();
        Player player6 = new Player();
        Player player7 = new Player();


        match.addPlayer(player1);
        match.addPlayer(player2);
        match.addPlayer(player3);
        match.addPlayer(player4);
        match.addPlayer(player5);
        match.addPlayer(player6);
        match.addPlayer(player7);

        // Testing max 4 players in a Match
        assertTrue(match.getPlayerList().size() == 4);
        assertTrue(match.getPlayerList().contains(player1));
        assertTrue(match.getPlayerList().contains(player2));
        assertTrue(match.getPlayerList().contains(player3));
        assertTrue(match.getPlayerList().contains(player4));
    }

    @Test
    public void addPlayerWaitingRoomTest() {
        Match match = new Match(1); // Match is tested
        Player player1 = new Player();
        Player player2 = new Player();

        match.addPlayer(player1);
        match.addPlayer(player2);

        // Testing addPlayer function in Waiting Room
        assertTrue(match.getPlayerList().size() == 2);
        assertTrue(match.getPlayerList().contains(player1));
        assertTrue(match.getPlayerList().contains(player2));
    }

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
}