package it.polimi.ingsw.modeltest.gamelogictest;

import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.gametools.*;
import it.polimi.ingsw.model.gamelogic.checker.InspectorContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class InspectorContextTest {

    @Test
    public void TestCheckTools(){
        InspectorContext tester = new InspectorContext();
        CardContainer container = new CardContainer();
        ToolCard toolcard;
        ArrayList<ToolCard> toolCardArrayList;
        toolCardArrayList = container.pullOutTools();

        toolcard = toolCardArrayList.get(2);

        System.out.println("Tool: " + toolcard.getID());
        assertTrue(tester.check(toolcard,toolCardArrayList));
    }

    @Test
    void TestCheckPlaceDice(){
        InspectorContext tester = new InspectorContext();
        CardContainer container = new CardContainer();
        DiceBag diceBag = new DiceBag();
        diceBag.setNumPlayers(4);
        DraftPool draftPool = new DraftPool();
        draftPool.addNewDices(diceBag.pullOut());

        Dice d = draftPool.chooseDice(5);

        Pos pos = new Pos(5,0);

        tester.check(d,pos,draftPool);
    }

}