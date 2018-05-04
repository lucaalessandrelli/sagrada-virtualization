package it.polimi.ingsw;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import package1.Match;
import package1.Player;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class PlayerListTest {
    @Test
    public void addTest() {
        Match match = new Match(1); // Match is tested
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
    }

    @Test
    public void removeTest() {
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

        // Testing the remove function
        assertTrue(match.getPlayerList().size() == 4);
    }
}