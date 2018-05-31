package it.polimi.ingsw.modeltest.gamelogictest;

import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.gametools.ToolCard;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoveTest {
    @Test
    private void setTest1(){
        Pos pos = new Pos();
       // Move tester = new Move(pos);
       // assertTrue(tester.getChosenPos()!= null);

    }
    @Test
    private void setTest2(){
        Dice dice = new Dice();
      //  Move tester = new Move(dice);
      //  assertTrue(tester.getChosenDice()!=null);

    }
    @Test
    private void setTest3(){
        ToolCard toolc = new ToolCard();
      //  Move tester = new Move(toolc);
      //  assertTrue(tester.getToolCard()!=null);

    }
}
