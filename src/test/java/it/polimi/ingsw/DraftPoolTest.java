package it.polimi.ingsw;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import package1.Dice;
import package1.DraftPool;

import java.util.ArrayList;

public class DraftPoolTest extends TestCase{

    //we really need to test this??
    @Test
    public void TestNumDices(){
        DraftPool tester = new DraftPool(1);
        assertTrue(tester.getNumOfDices() == 3);
        tester = new DraftPool(0);
        assertTrue(tester.getNumOfDices() == 1);
    }

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
        tester.chooseDice(1);
        assertTrue(tester.getNumOfDices() == (j - 1));
    }
/*
    @Test
    public void TestNeverEmpty(){

    }

*/
}
