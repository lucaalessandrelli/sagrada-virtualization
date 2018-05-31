package it.polimi.ingsw.modeltest.gamedatatest.gametoolstest;

import it.polimi.ingsw.model.gamedata.Colour;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.gametools.DiceBag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DiceBagTest {

    @Test
    public void TestCreation(){
        DiceBag tester = new DiceBag();
        assertEquals( 90,tester.remainingDices());
    }

    @Test
    public void TestEmptyBag(){
        DiceBag tester = new DiceBag();
        ArrayList<Dice> d = new ArrayList<>();
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

    @Test
    public void TestSetNumPlayers(){
        DiceBag tester = new DiceBag();
        tester.setNumPlayers(5);
        tester.setNumPlayers(2);
    }

    @Test
    public void TestAddDice(){
        DiceBag tester = new DiceBag();
        Dice dice = new Dice();
        int remaining = tester.remainingDices();
        boolean ended = false;
        tester.addDice(dice);
        assertEquals(remaining+1,tester.remainingDices());
        ArrayList<Dice> test = tester.pullOut(91);
        for (Dice x: test){
            if(x.equals(dice)){
                if(x == dice) {
                    assertTrue(true);
                    ended = true;
                }
            }
        }
        if(!ended)
            fail("Test failed");
    }

    @Test
    public void TestPullOut(){
        DiceBag tester = new DiceBag();
        ArrayList<Dice> dices;
        tester.setNumPlayers(3);
        dices = tester.pullOut();
        assertEquals(7,dices.size());
    }

    @Test
    public void TestNumPullOut(){
        DiceBag tester = new DiceBag();
        ArrayList<Dice> dices;
        dices = tester.pullOut(24);
        assertEquals(24,dices.size());
    }
}
