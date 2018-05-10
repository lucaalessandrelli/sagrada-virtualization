package it.polimi.ingsw;

import org.junit.jupiter.api.Test;
import package1.Dice;
import package1.Move;
import package1.Pos;
import package1.ToolCard;



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
