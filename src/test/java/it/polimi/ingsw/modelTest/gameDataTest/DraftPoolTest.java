package it.polimi.ingsw.modelTest.gameDataTest;

import it.polimi.ingsw.model.gameData.Dice;
import it.polimi.ingsw.model.gameData.DraftPool;
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
        tester.chooseDice(1);
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
            tester.chooseDice(i);
        }
        assertTrue(tester.getNumOfDices() == 1);
        assertThrows(IndexOutOfBoundsException.class,()->{tester.chooseDice(0);});
    }



}
