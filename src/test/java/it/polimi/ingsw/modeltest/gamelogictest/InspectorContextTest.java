package it.polimi.ingsw.modeltest.gamelogictest;

import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.gametools.*;
import it.polimi.ingsw.model.gamelogic.checker.InspectorContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class InspectorContextTest {

    /*@Test
    public void testCheckTools(){
        InspectorContext tester = new InspectorContext();
        CardContainer container = new CardContainer();
        ToolCard toolcard;
        List<ToolCard> toolCardArrayList;
        toolCardArrayList = container.pullOutTools();

        toolcard = toolCardArrayList.get(2);

        //System.out.println("Tool: " + toolcard.getID());
        assertTrue(tester.check(toolcard,toolCardArrayList));
    }*/

    @Test
    public void testCheckPlaceDice(){
        InspectorContext tester = new InspectorContext();
        DiceBag diceBag = new DiceBag();
        diceBag.setNumPlayers(4);
        DraftPool draftPool = new DraftPool();
        draftPool.addNewDices(diceBag.pullOut());

        Dice d = draftPool.chooseDice(5);

        Pos pos = new Pos(5,0);

        tester.check(d,pos,draftPool);
    }

}
