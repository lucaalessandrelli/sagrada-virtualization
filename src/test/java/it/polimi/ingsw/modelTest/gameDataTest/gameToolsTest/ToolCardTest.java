package it.polimi.ingsw.modelTest.gameDataTest.gameToolsTest;

import it.polimi.ingsw.model.gameData.gameTools.ToolCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class ToolCardTest {

    //testing the increasing cost of a ToolCard after it's been used
    @Test
    public void increasingCostTest() {
        ToolCard toolCard1 = new ToolCard();
        ToolCard toolCard2 = new ToolCard();

        toolCard1.setUsed();

        assertTrue(toolCard1.getCost() == 2);
        assertTrue(toolCard2.getCost() == 1);
    }
}
