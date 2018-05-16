package it.polimi.ingsw.modelTest.gameLogicTest;

import it.polimi.ingsw.model.gameData.Dice;
import it.polimi.ingsw.model.gameLogic.Move;
import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.ToolCard;
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
