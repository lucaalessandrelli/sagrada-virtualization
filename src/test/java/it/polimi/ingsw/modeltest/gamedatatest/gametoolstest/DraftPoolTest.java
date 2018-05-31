package it.polimi.ingsw.modeltest.gamedatatest.gametoolstest;

import it.polimi.ingsw.model.gamedata.Colour;
import it.polimi.ingsw.model.gamedata.Property;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.gametools.DiceBag;
import it.polimi.ingsw.model.gamedata.gametools.DraftPool;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DraftPoolTest{


    @Test
    public void TestRemoveDice(){
        DraftPool tester = new DraftPool(4);
        Dice y = new Dice();
        int j;
        ArrayList<Dice> x = new ArrayList<Dice>(9);
        for(int i = 0; i < 9; i++){
            x.add(y);
        }
        tester.addNewDices(x);
        j = tester.getNumOfDices();
        tester.removeDice(1);
        assertTrue(tester.getNumOfDices() == (j - 1));
    }

    @Test
    public void TestNeverEmpty(){
        int i;
        DraftPool tester = new DraftPool();
        Dice y = new Dice();
        ArrayList<Dice> x = new ArrayList<Dice>(9);
        for(i = 0; i < 9; i++){
            x.add(y);
        }
        tester.addNewDices(x);
        for(i = 0; tester.getNumOfDices() > 1;){
            tester.removeDice(i);
        }
        assertTrue(tester.getNumOfDices() == 1);
        assertThrows(IndexOutOfBoundsException.class,()->{tester.chooseDice(0);});
    }

    @Test
    public void TestAddDices(){
        DraftPool tester = new DraftPool();
        DiceBag test = new DiceBag();
        tester.addNewDices(test.pullOut());
    }

    @Test
    public void TestFindDice(){
        DraftPool tester = new DraftPool();
        Property prop = new Property(Colour.RED,true);
        Dice d;
        prop.setNumber(5);
        for(int i = 0; i < 6; i++) {
            d = new Dice();
            assertTrue(tester.getDraftPool().add(d));
        }
        d = new Dice(prop);
        assertFalse(tester.findDice(d));
        assertTrue(tester.getDraftPool().add(d));
        assertTrue(tester.findDice(d));
    }

    @Test
    public void TestFindinPosition(){
        DraftPool tester = new DraftPool();
        Property prop = new Property(Colour.RED,true);
        Dice d;
        prop.setNumber(5);
        for(int i = 0; i < 6; i++) {
            d = new Dice();
            assertTrue(tester.getDraftPool().add(d));
        }
        d = new Dice(prop);
        tester.getDraftPool().add(d);
        prop = new Property(Colour.GREEN,true);
        assertTrue(tester.findDice(d,tester.getNumOfDices()-1));
    }

    @Test
    public void removeDice(){
        DraftPool tester = new DraftPool();
        Dice d1 = new Dice(Colour.BLUE);
        tester.getDraftPool().add(d1);
        Dice d;
        for(int i = 0; i < 6; i++) {
            d = new Dice();
            assertTrue(tester.getDraftPool().add(d));
        }
        assertTrue(tester.findDice(d1));
        int numdices = tester.getNumOfDices();
        tester.removeDice(tester.getNumOfDices()-1);
        assertEquals(numdices-1, tester.getNumOfDices());
        tester.findDice(d1);
    }




}
