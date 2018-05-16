package it.polimi.ingsw;

import it.polimi.ingsw.model.gameData.Player;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class TimerTest {
    private List<Match> matches;
    Server server = new Server();
    WaitingRoom lobby = server.getLobby();

    public TimerTest() throws RemoteException {
    }

    @Test
    public void resetTimerTest() throws InterruptedException {
        lobby.setServer(server);

        Player player1 = new Player("1");
        Player player2 = new Player("2");

        server.connectPlayer(player1);
        server.connectPlayer(player2);

        System.out.println("----begin sleep1----");
        Thread.sleep(3000);
        System.out.println("----end sleep1----");

        server.disconnectPlayer(player2);
        System.out.println("Now numPlayer is "+ lobby.getPlayerList().size());

        System.out.println("----begin sleep2----");
        Thread.sleep(6000);
        System.out.println("----end sleep2----");

        server.connectPlayer(player2);
        System.out.println("Now numPlayer is "+ lobby.getPlayerList().size());


        System.out.println("----begin sleep3----");
        Thread.sleep(2000);
        System.out.println("----end sleep3----");

        server.disconnectPlayer(player2);
        System.out.println("Now numPlayer is "+lobby.getPlayerList().size());

        System.out.println("----begin sleep4----");
        Thread.sleep(2000);
        System.out.println("----end sleep4----");

        server.connectPlayer(player2);
        System.out.println("Now numPlayer is "+ lobby.getPlayerList().size());

        Thread.sleep(7000);

        matches = server.getMatchList();
        Match match1 = matches.get(0);

        //Test the right state of the match when the timer has been reset
        assertTrue(matches.size() == 1);
        assertTrue(match1.getPlayerList().size() == 2);
        assertTrue(lobby.getPlayerList().size() == 0);
    }

    @Test
    public void noTimerTest() throws InterruptedException {
        lobby.setServer(server);

        Player player1 = new Player("1");

        server.connectPlayer(player1);


        System.out.println("----begin sleep1----");
        Thread.sleep(7000);
        System.out.println("----end sleep1----");

        matches = server.getMatchList();

        assertTrue(matches.size() == 0);
        assertTrue(lobby.getPlayerList().size() == 1);
    }

    @Test
    public void oneTimeTimerTest() throws  InterruptedException {
        lobby.setServer(server);

        Player player1 = new Player("1");
        Player player2 = new Player("2");

        server.connectPlayer(player1);
        server.connectPlayer(player2);

        System.out.println(lobby.getPlayerList().size());

        System.out.println("----begin sleep1----");
        Thread.sleep(7000);
        System.out.println("----end sleep1----");

        matches = server.getMatchList();

        assertTrue(matches.size() == 1);

        assertTrue(lobby.getPlayerList().size() == 0);
    }
}