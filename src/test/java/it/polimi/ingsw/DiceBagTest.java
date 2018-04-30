package it.polimi.ingsw;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import package1.DiceBag;

public class DiceBagTest extends TestCase {

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


}
