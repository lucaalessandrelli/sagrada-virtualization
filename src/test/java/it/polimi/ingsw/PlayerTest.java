package it.polimi.ingsw;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import package1.Player;
import package1.Table;
import package1.WindowPatternCard;


import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {


    @Test
    public void testLenghtName() {
        String data = "test";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Player tester = new Player();
        assertTrue(tester.getUsername().length() != 0);

    }

    @Test
    public void testSetTable1() {
        Table table = new Table();
        String data = "test";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Player tester = new Player();
        tester.setMyTable(table);
        assertTrue(tester.getMyTable() != null);
    }
    @Test
    public void testSetTable2(){
        Table table = null;
        String data = "test";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Player tester = new Player();
        tester.setMyTable(table);
        assertTrue(tester.getMyTable() == null);
    }
    @Disabled
    @Test
    private void testTokens1(){

        WindowPatternCard windw = new WindowPatternCard(1,1,"test");
        Table table = new Table();
        table.addCard(windw);
        String data = "test";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Player player = new Player();
        player.setMyTable(table);
        player.useToken(true);
        assertTrue(player.getFavTok() == 1);
        }
        @Disabled
        @Test
        private void testTokens2(){

        }

}