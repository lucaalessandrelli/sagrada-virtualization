package it.polimi.ingsw.modelTest.gameDataTest.gameToolsTest;

import it.polimi.ingsw.model.gameData.Property;
import it.polimi.ingsw.model.gameData.gameTools.Dice;
import org.junit.jupiter.api.Test;

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
