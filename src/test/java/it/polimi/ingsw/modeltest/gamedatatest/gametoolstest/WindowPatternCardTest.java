package it.polimi.ingsw.modeltest.gamedatatest.gametoolstest;

import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.gametools.WindowPatternCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WindowPatternCardTest{


    @Test
    public void TestMatrixLength(){
        WindowPatternCard tester = new WindowPatternCard(0,0,"No name");
        assertEquals(4, tester.getMatr().size());
        assertEquals(5, tester.getMatr().get(0).size());
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
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 5; j++){
                tester.placeDice(d,i,j);
            }
        }
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 5; j++){
                assertTrue(tester.placeDice(d,i,j) == false);
            }
        }
    }


}
