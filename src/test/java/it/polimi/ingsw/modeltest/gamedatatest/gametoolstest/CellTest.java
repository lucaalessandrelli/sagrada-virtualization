package it.polimi.ingsw.modeltest.gamedatatest.gametoolstest;

import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.Property;
import it.polimi.ingsw.model.gamedata.gametools.Cell;
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
