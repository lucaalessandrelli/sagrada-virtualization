package it.polimi.ingsw.modeltest.gamedatatest.gametoolstest;

import it.polimi.ingsw.model.gamedata.Colour;
import it.polimi.ingsw.model.gamedata.Property;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DiceTests {

    @Test
    public void testGetNumber(){
        Dice tester = new Dice();
        assertTrue(tester.getNumber() > 0 && tester.getNumber() < 7);

    }

    @Test
    public void testGetColour(){
        Dice tester = new Dice();
        assertNotNull(tester.getColour());

    }

    @Test
    public void testEquals(){
        Dice tester = new Dice();
        Dice test = new Dice();
        while(test.getNumber()!=tester.getNumber()){
            assertFalse(test.equals(tester));
            test = new Dice();
            tester = new Dice();
        }
        assertTrue(test.equals(tester));
    }

    @Test
    public void testSetDice(){
        Dice tester = new Dice(Colour.GREEN);
        Dice test = new Dice(Colour.BLUE);
        assertFalse(tester.equals(test));
        tester.setDice(test);
        assertTrue(test.equals(tester));
    }

    @Test
    void testRollDice(){
        Dice tester = new Dice();
        tester.rollDice();
        assertTrue(tester.getNumber() >=1 && tester.getNumber() <=6);
    }
}
