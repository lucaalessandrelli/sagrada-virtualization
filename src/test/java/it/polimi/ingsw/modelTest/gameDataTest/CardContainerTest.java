package it.polimi.ingsw.modelTest.gameDataTest;

import it.polimi.ingsw.model.gameData.CardContainer;
import it.polimi.ingsw.model.gameData.ObjectiveCard;
import it.polimi.ingsw.model.gameData.WindowPatternCard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardContainerTest {

    @Test
    public void TestPrivatePullout(){
        CardContainer tester = new CardContainer();
        ArrayList<ObjectiveCard> test = null;
        test = tester.pullOutPrivate(2);
        for (ObjectiveCard x: test) {
            System.out.println("Id number: " + x.getID() +
                                "\nName: " + x.getName() +
                                "\nDescription: " + x.getDescription() +
                                "\nType: " + x.getType() +
                                "\nPoints: " + x.getPoints() + "\n");
        }
        assertEquals(2, test.size());
    }

    @Test
    public void TestPublicPullout(){
        CardContainer tester = new CardContainer();
        ArrayList<ObjectiveCard> test = null;
        test = tester.pullOutPublic();
        for (ObjectiveCard x: test) {
            System.out.println("Id number: " + x.getID() +
                    "\nName: " + x.getName() +
                    "\nDescription: " + x.getDescription() +
                    "\nType: " + x.getType() +
                    "\nPoints: " + x.getPoints() + "\n");
        }
        assertEquals(3, test.size());
    }

    @Test
    public void TestPublicRules() {
        CardContainer tester = new CardContainer();
        ArrayList<ObjectiveCard> test = new ArrayList<ObjectiveCard>();
        test = tester.pullOutPublic();
        for (ObjectiveCard x: test) {
            for (String k: x.getRules().getRules()){
             System.out.println("Regola: " + k + "\n");
            }
            assertTrue(!(x.getRules().getRules().isEmpty()));
        }
    }

    @Test
    public void TestPatternCards(){
        CardContainer tester = new CardContainer();
        ArrayList<WindowPatternCard> test = new ArrayList<>();
        test = tester.pullOutPattern(2);
        assertEquals(8,test.size());
        for(WindowPatternCard x:test){
            x.show();
        }
    }

    @Test
    public void TestNeverOutofBound(){
        CardContainer tester = new CardContainer();
        for(int i = 0; i < 200; i++){
            tester.pullOutPattern(4);
            tester = new CardContainer();
        }
    }

    @Test
    public void TestNeverCallTwoTimes(){
        CardContainer tester = new CardContainer();
        tester.pullOutPattern(2);
        tester.pullOutPublic();
        tester.pullOutPrivate(2);
        tester.pullOutPrivate(3);
        tester.pullOutPublic();
        tester.pullOutPattern(4);
    }

    @Test
    public void TestTooManyPlayers(){
        CardContainer tester = new CardContainer();
        assertThrows(IndexOutOfBoundsException.class,()->{tester.pullOutPattern(6);});
        assertThrows(IndexOutOfBoundsException.class,()->{tester.pullOutPrivate(8);});
        assertThrows(IndexOutOfBoundsException.class,()->{tester.pullOutPattern(-4);});
        assertThrows(IndexOutOfBoundsException.class,()->{tester.pullOutPrivate(-2);});
    }
}
