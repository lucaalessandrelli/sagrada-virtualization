package it.polimi.ingsw;

import it.polimi.ingsw.model.gameData.Dice;
import it.polimi.ingsw.model.gameLogic.Move;
import it.polimi.ingsw.model.gameLogic.Pos;
import it.polimi.ingsw.model.gameData.ToolCard;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoveTest {
    @Test
    public void setTest1(){
        Pos pos = new Pos();
        Move tester = new Move(pos);
        assertTrue(tester.getChosenPos()!= null);

    }
    @Test
    public void setTest2(){
        Dice dice = new Dice();
        Move tester = new Move(dice);
        assertTrue(tester.getChosenDice()!=null);

    }
    @Test
    public void setTest3(){
        ToolCard toolc = new ToolCard();
        Move tester = new Move(toolc);
        assertTrue(tester.getToolCard()!=null);

    }
}
