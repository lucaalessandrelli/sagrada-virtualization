package it.polimi.ingsw.modelTest.gameDataTest;

import it.polimi.ingsw.model.gameData.Colour;
import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.gameTools.DiceBag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiceBagTest {

    @Test
    public void TestCreation(){
        DiceBag tester = new DiceBag();
        assertTrue(tester.remainingDices() == 90);
    }

    @Test
    public void TestEmptyBag(){
        DiceBag tester = new DiceBag();
        tester.setNumPlayers(4);
        for(int i = 0; i < 10; i++){
            tester.pullOut();
        }
        assertEquals(0,tester.remainingDices());
    }

    //tests that we have the exact number of dices in the dicebag: 18 of yellow,green,purple,red,blue and 0 of white.
    @Test
    public void TestExactNumber(){
        DiceBag tester = new DiceBag();
        ArrayList<Dice> tmp;
        int r = 0,g = 0,y = 0,b = 0,p = 0,w = 0;
        tester.setNumPlayers(4);
        for(int i = 0; i < 10; i++) {
            tmp = tester.pullOut();
            for (Dice x: tmp){
                if(x.getColour() == Colour.WHITE)
                    w++;
                else if(x.getColour() == Colour.BLUE)
                    b++;
                else if(x.getColour() == Colour.YELLOW)
                    y++;
                else if(x.getColour() == Colour.GREEN)
                    g++;
                else if(x.getColour() == Colour.RED)
                    r++;
                else if(x.getColour() == Colour.PURPLE)
                    p++;
            }
        }
        assertEquals(18, r);
        assertEquals(18, g);
        assertEquals(18, y);
        assertEquals(18, b);
        assertEquals(18, p);
        assertEquals(0, w);
    }

}
