package it.polimi.ingsw.modeltest.gamedatatest;

import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.model.gamedata.Table;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TableTest {

    @Test
    void TestConstructorAndPublicObjects(){
        Player p1 = new Player("Gionny");
        Player p2 = new Player("Luca");
        Player p3 = new Player("Andrea");
        Player p4 = new Player("Giuseppe");

        List<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        Table tester = new Table(players);

        //tester.initialize();

        assertFalse(tester.getDraftPool().getDraftPool().isEmpty());

        assertFalse(tester.getObjCard().isEmpty());

        assertFalse(tester.getToolCards().isEmpty());

        assertEquals(90 - ((tester.getNumPlayers()*2) + 1),tester.getDiceBag().remainingDices());

        assertTrue(tester.getRoundTrack().getRoundTrack().isEmpty());

        for (Player p: players){
            assertEquals(tester.getDraftPool(),p.getDraftPool());
            assertEquals(tester.getRoundTrack(),p.getRoundTrack());
            assertEquals(tester.getToolCards(),p.getToolCards());

        }
    }

    @Test
    void TestinGetters(){
        Player p1 = new Player("Gionny");
        Player p2 = new Player("Luca");
        Player p3 = new Player("Andrea");
        Player p4 = new Player("Giuseppe");

        List<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        Table tester = new Table(players);
        //tester.initialize();


        assertNotNull(tester.getToolCards());
        assertNotNull(tester.getObjCard());
        assertNotNull(tester.getRoundTrack().getRoundTrack());
        assertNotNull(tester.getDiceBag());
        assertNotNull(tester.getDraftPool().getDraftPool());

    }

    @Test
    void TestDraftPool(){
        Player p1 = new Player("Gionny");
        Player p2 = new Player("Luca");
        Player p3 = new Player("Andrea");
        Player p4 = new Player("Giuseppe");

        List<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        Table tester = new Table(players);
        //tester.initialize();


        tester.fillDraftPool();

        for(int i = 0; i < tester.getDraftPool().getNumOfDices();i++){
            assertTrue(tester.getDraftPool().getDraftPool().get(i).equals(p1.getDraftPool().getDraftPool().get(i)));
            assertTrue(tester.getDraftPool().getDraftPool().get(i).equals(p2.getDraftPool().getDraftPool().get(i)));
            assertTrue(tester.getDraftPool().getDraftPool().get(i).equals(p3.getDraftPool().getDraftPool().get(i)));
            assertTrue(tester.getDraftPool().getDraftPool().get(i).equals(p4.getDraftPool().getDraftPool().get(i)));
        }
    }

    @Test
    void TestRoundTrack(){

        Player p1 = new Player("Gionny");
        Player p2 = new Player("Luca");
        Player p3 = new Player("Andrea");
        Player p4 = new Player("Giuseppe");

        List<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        Table tester = new Table(players);

        ////tester.initialize();

        tester.fillDraftPool();

        tester.setLastDices(1);

        tester.fillDraftPool();

        tester.setLastDices(2);

        for(int i = 0; i < tester.getRoundTrack().getRoundTrack().size();i++){
            if(tester.getRoundTrack().getRoundTrack().get(i)!=null){
                for(int j = 0; j < tester.getRoundTrack().getRoundTrack().get(i).size(); j++){
                    assertTrue(tester.getRoundTrack().getRoundTrack().get(i).get(j).equals(p1.getRoundTrack().getRoundTrack().get(i).get(j)));
                }
            }
        }

    }
}
