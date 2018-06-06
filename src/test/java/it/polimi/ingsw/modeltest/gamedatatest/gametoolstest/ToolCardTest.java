package it.polimi.ingsw.modeltest.gamedatatest.gametoolstest;

import it.polimi.ingsw.model.gamedata.gametools.CardContainer;
import it.polimi.ingsw.model.gamedata.gametools.ToolCard;
import org.junit.jupiter.api.Test;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class ToolCardTest {

    //testing the increasing cost of a ToolCard after it's been used
    @Test
    public void TestIncreasingCost() {
        ToolCard toolCard1 = new ToolCard();

        assertEquals(1,toolCard1.getCost());
        toolCard1.setUsed();
        assertEquals(2,toolCard1.getCost());

    }

    @Test
    public void TestGetter(){
        ToolCard tester = new ToolCard();
        CardContainer container = new CardContainer();
        List<ToolCard> toolCards = container.pullOutTools();
        tester = toolCards.get(0);
        for (ToolCard x: toolCards){
            assertFalse(tester.getAutomatedoperationlist().isEmpty());
            assertFalse(tester.getStateList().isEmpty());
        }




    }
}
