package it.polimi.ingsw.modelTest.gameDataTest.gameToolsTest;

import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.Property;
import it.polimi.ingsw.model.gameData.gameTools.Cell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CellTest {

    @Test
    public void TestGetterandOccupation(){
        Cell tester = new Cell();
        Property p = new Property();
        Pos pos = new Pos();
        assertTrue(tester.getDice() != null);
        assertTrue(tester.getPosition() != null);
        assertTrue(tester.getProperty() != null);
        assertTrue(tester.isOccupied()==false);
    }
}
