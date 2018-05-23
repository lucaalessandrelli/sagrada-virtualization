package it.polimi.ingsw.modeltest.gamedatatest.gametoolstest;

import it.polimi.ingsw.model.gamedata.Property;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DiceTests {

    @Test
    public void TestEverything(){
        Dice tester = new Dice();
        Dice test = new Dice();
        Property prop = new Property();

        assertTrue(tester.getColour() != null);
        assertTrue(tester.getNumber() > 0 && tester.getNumber() < 7);
        while(test.getNumber()!=tester.getNumber()){
            assertFalse(test.equals(tester));
            test = new Dice();
            tester = new Dice();
        }
        assertTrue(test.equals(tester));
    }
}
