package it.polimi.ingsw.modelTest.gameDataTest;

import it.polimi.ingsw.model.gameData.Dice;
import it.polimi.ingsw.model.gameData.WindowPatternCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WindowPatternCardTest{


    @Test
    public void TestMatrixLength(){
        WindowPatternCard tester = new WindowPatternCard(0,0,"No name");
        assertEquals(5, tester.getMatr().size());
        assertEquals(4, tester.getMatr().get(0).size());
    }

    //to do this we need to define the rules
    /*@Test
    public void TestPlacement(){

    }
*/
    @Test
    public void TestOccupied(){
        WindowPatternCard tester = new WindowPatternCard(0,0,"No name");
        int x = 0,y = 0;
        Dice d = new Dice();
        assertTrue(tester.placeDice(d,x,y) == true);
        assertTrue(tester.getMatr().get(x).get(y).isOccupied() == true);
    }

    @Test
    public void TestFull(){
        Dice d = new Dice();
        WindowPatternCard tester = new WindowPatternCard(0,0,"No name");
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 4; j++){
                tester.placeDice(d,i,j);
            }
        }

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 4; j++){
                assertTrue(tester.placeDice(d,i,j) == false);
            }
        }
    }


}
