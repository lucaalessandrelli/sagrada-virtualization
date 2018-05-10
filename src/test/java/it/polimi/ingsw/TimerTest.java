package it.polimi.ingsw;

import org.junit.jupiter.api.Test;
import package1.Player;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class TimerTest {
    /*
    @Test
    public void resetTimerTest() throws InterruptedException {
        Match match1 = new Match(1);
        Player player1 = new Player();
        Player player2 = new Player();

        match1.addPlayer(player1);
        match1.addPlayer(player2);

        System.out.println("----begin sleep1----");
        Thread.sleep(3000);
        System.out.println("----end sleep1----");

        match1.removePlayer(player2);
        System.out.println("Now numPlayer is 1");

        System.out.println("----begin sleep2----");
        Thread.sleep(3000);
        System.out.println("----end sleep2----");

        match1.addPlayer(player2);
        System.out.println("Now numPlayer is 2");


        System.out.println("----begin sleep3----");
        Thread.sleep(2000);
        System.out.println("----end sleep3----");

        match1.removePlayer(player2);
        System.out.println("Now numPlayer is 1");

        System.out.println("----begin sleep4----");
        Thread.sleep(2000);
        System.out.println("----end sleep4----");

        match1.addPlayer(player2);
        System.out.println("Now numPlayer is 2");

        Thread.sleep(6000);

        //Test the right state of the match when the timer has been reset
        assertTrue(match1.getPlayerList().size() == 2);
        assertTrue(match1.getState() instanceof StartedMatch);
    }

    @Test
    public void noTimerTest() throws InterruptedException {
        Match match1 = new Match(1);
        Player player1 = new Player();

        match1.addPlayer(player1);


        System.out.println("----begin sleep1----");
        Thread.sleep(7000);
        System.out.println("----end sleep1----");

        assertTrue(match1.getState() instanceof WaitingRoom);
    }*/
}