package it.polimi.ingsw;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import package1.WindowPatternCard;

public class WindowPatternCardTest extends TestCase{

    WindowPatternCard tester = new WindowPatternCard(0,0,"No name");

    @Test
    public void TestMatrixLenght(){
        assertTrue(tester.getMatr().length == 4);
        assertTrue(tester.getMatr()[0].length == 5);
    }
}
