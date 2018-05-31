package it.polimi.ingsw.modeltest.gamelogictest;

import it.polimi.ingsw.model.gamedata.Colour;
import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.gametools.CardContainer;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.gametools.WindowPatternCard;
import it.polimi.ingsw.model.gamelogic.checker.InspectorPlace;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class InspectorPlaceTest {


    /**
     * Verify is you can place the first dice
     */
    @Test
    void TestCheckFirst(){
        InspectorPlace tester = new InspectorPlace();
        WindowPatternCard windowPatternCard;
        windowPatternCard = pullOutCard(1);

        //this should return true because is a white cell
        Dice d = new Dice(Colour.GREEN);
        Pos pos = new Pos(0,3);
        assertTrue(tester.checkFirst(d,pos,windowPatternCard));

    }

    /**
     * Verify if you can place the first dice in a cell that doesn't match the color
     */
    @Test
    void TestCheckFirstRestrictionColour(){
        InspectorPlace tester = new InspectorPlace();
        WindowPatternCard windowPatternCard;
        windowPatternCard = pullOutCard(1);
        Dice d = new Dice(Colour.GREEN);

        //this should return true because is a green cell
        Pos pos = new Pos(2,4);
        assertTrue(tester.checkFirst(d,pos,windowPatternCard));


        //this should return false because is a yellow cell with a green dice
        pos.setX(0);
        pos.setY(0);
        assertFalse(tester.checkFirst(d,pos,windowPatternCard));

    }

    /**
     * Verify if you can place a dice in a central cell, doing a wrong move
     */
    @Test
    void TestCheckFirstBoundaryCell(){
        InspectorPlace tester = new InspectorPlace();
        WindowPatternCard windowPatternCard;
        windowPatternCard = pullOutCard(1);
        Dice d = new Dice(Colour.GREEN);

        //this should return false because is a no-boundary cell
        Pos pos = new Pos(1,3);
        assertFalse(tester.checkFirst(d,pos,windowPatternCard));

        //this should return false because, even if a right move, is a no-boundary cell
        while(d.getNumber()!=5)
            d.rollDice();
        pos.setX(1);
        pos.setY(2);
        assertFalse(tester.checkFirst(d,pos,windowPatternCard));

        //this should return false because is a wrong move and is a no-boundary cell
        pos.setX(2);
        pos.setY(2);
        assertFalse(tester.checkFirst(d,pos,windowPatternCard));

    }

    /**
     * Verify that the second placement, even if is right, is done near a placed dice
     */
    @Test
    void TestCheckSecondPlacement(){
        InspectorPlace tester = new InspectorPlace();
        WindowPatternCard windowPatternCard;

        Pos pos = new Pos(0,3);
        windowPatternCard = pullOutCard(1);

        Dice d = new Dice(Colour.RED);

        assertTrue(tester.checkFirst(d,pos,windowPatternCard));

        windowPatternCard.placeDice(d,pos.getX(),pos.getY());
        d.setNumber(2);
        pos.setX(1);
        pos.setY(1);

        //this should return false because it's not near a dice, even if is a right move
        assertFalse(tester.check(d,pos,windowPatternCard));

        pos.setX(2);
        pos.setY(1);
        d = new Dice(Colour.BLUE);

        //the same as before
        assertFalse(tester.check(d,pos,windowPatternCard));

    }

    /**
     * Verify that the moves goes by
     */
    @Test
    void TestCheckRightMove(){
        InspectorPlace tester = new InspectorPlace();
        WindowPatternCard windowPatternCard;

        Pos pos = new Pos(0,3);
        windowPatternCard = pullOutCard(1);

        Dice d = new Dice(Colour.RED);
        d.setNumber(2);

        assertTrue(tester.checkFirst(d,pos,windowPatternCard));

        windowPatternCard.placeDice(d,pos.getX(),pos.getY());

        d = new Dice(Colour.YELLOW);
        d.setNumber(4);
        pos.setX(0);
        pos.setY(2);
        //this should return true because is a white cell and near a dice
        assertTrue(tester.check(d,pos,windowPatternCard));
        windowPatternCard.placeDice(d,pos.getX(),pos.getY());

        d = new Dice(Colour.GREEN);
        d.setNumber(1);
        pos.setX(0);
        pos.setY(4);
        //this should return true, is okay for both restrictions on the cell
        //and restrictions with near dices
        assertTrue(tester.check(d,pos,windowPatternCard));
        windowPatternCard.placeDice(d,pos.getX(),pos.getY());

        d = new Dice(Colour.YELLOW);
        d.setNumber(1);
        pos.setX(3);
        pos.setY(4);
        //this should return false, is okay for both restrictions on the cell
        //and restrictions with near dices
        assertFalse(tester.check(d,pos,windowPatternCard));
        windowPatternCard.placeDice(d,pos.getX(),pos.getY());
    }

    /**
     * Verify that you can't place a dice out of bound
     */
    /*@Test
    void TestCheckOutOfBound(){
        InspectorPlace tester = new InspectorPlace();
        WindowPatternCard windowPatternCard;

        Pos pos = new Pos(0,3);
        windowPatternCard = pullOutCard(1);

        Dice d = new Dice(Colour.RED);

        assertTrue(tester.checkFirst(d,pos,windowPatternCard));

        windowPatternCard.placeDice(d,pos.getX(),pos.getY());

        //verify if you can go out of bound with positive y
        pos.setY(5);
        assertThrows(IndexOutOfBoundsException.class,()->{tester.check(d,pos,windowPatternCard);});

        //verify if you can go out of bound with negative y
        pos.setY(-2);
        assertThrows(IndexOutOfBoundsException.class,()->{tester.check(d,pos,windowPatternCard);});

        //verify if you can go out of bound with positive x
        pos.setX(4);
        assertThrows(IndexOutOfBoundsException.class,()->{tester.check(d,pos,windowPatternCard);});

        //verify if you can go out of bound with negative x
        pos.setX(-1);
        assertThrows(IndexOutOfBoundsException.class,()->{tester.check(d,pos,windowPatternCard);});

    }
    */
    /**
     * Verify if allows you to place a dice near other dices
     */
    @Test
    void TestCheckNearWithRestrictions(){
        InspectorPlace tester = new InspectorPlace();
        WindowPatternCard windowPatternCard;

        Pos pos = new Pos(0,2);
        windowPatternCard = pullOutCard(1);

        Dice d = new Dice(Colour.RED);
        d.setNumber(2);

        assertTrue(tester.checkFirst(d,pos,windowPatternCard));

        windowPatternCard.placeDice(d,pos.getX(),pos.getY());

        d = new Dice(Colour.GREEN);
        d.setNumber(5);
        pos.setX(1);
        pos.setY(2);
        assertTrue(tester.check(d,pos,windowPatternCard));

        windowPatternCard.placeDice(d,pos.getX(),pos.getY());

        d = new Dice(Colour.RED);
        d.setNumber(2);
        pos.setX(2);
        pos.setY(2);
        assertTrue(tester.check(d,pos,windowPatternCard));
    }

    /**
     * Verify if it doesn't allow you to place a dice near another dice considering
     * the restriction of the color
     */
    @Test
    void TestCheckNearWrongRestrictionColour(){
        InspectorPlace tester = new InspectorPlace();
        WindowPatternCard windowPatternCard;

        Pos pos = new Pos(0,2);
        windowPatternCard = pullOutCard(1);

        Dice d = new Dice(Colour.RED);

        assertTrue(tester.checkFirst(d,pos,windowPatternCard));

        windowPatternCard.placeDice(d,pos.getX(),pos.getY());

        d = new Dice(Colour.RED);
        d.setNumber(5);
        pos.setX(0);
        pos.setY(3);
        assertFalse(tester.check(d,pos,windowPatternCard));

        d = new Dice(Colour.RED);
        d.setNumber(2);
        pos.setX(1);
        pos.setY(3);
        assertTrue(tester.check(d,pos,windowPatternCard));
    }

    /**
     * Verify that you can't place a dice near a dice that has the same number
     */
    @Test
    void TestCheckNearWrongRestrictionNumber(){
        InspectorPlace tester = new InspectorPlace();
        WindowPatternCard windowPatternCard;

        Pos pos = new Pos(0,2);
        windowPatternCard = pullOutCard(1);

        Dice d = new Dice(Colour.RED);

        assertTrue(tester.checkFirst(d,pos,windowPatternCard));

        windowPatternCard.placeDice(d,pos.getX(),pos.getY());

        Dice d1 = new Dice(Colour.GREEN);
        d1.setNumber(5);
        pos.setX(1);
        pos.setY(3);
        assertTrue(tester.check(d,pos,windowPatternCard));

        windowPatternCard.placeDice(d,pos.getX(),pos.getY());

        d = new Dice(Colour.RED);
        d1.setNumber(d.getNumber());
        pos.setX(2);
        pos.setY(3);
        assertFalse(tester.check(d,pos,windowPatternCard));

    }

    /**
     * Verify that, even if you satisfy the cell restrictions, you can't place
     * two near dices that have the same color
     */
    @Test
    void TestCheckRightNumberWrongColour(){
        InspectorPlace tester = new InspectorPlace();
        WindowPatternCard windowPatternCard;

        Pos pos = new Pos(1,4);
        windowPatternCard = pullOutCard(1);

        Dice d = new Dice(Colour.GREEN);
        d.setNumber(4);

        assertTrue(tester.checkFirst(d,pos,windowPatternCard));

        windowPatternCard.placeDice(d,pos.getX(),pos.getY());

        d = new Dice(Colour.GREEN);
        d.setNumber(5);
        pos.setX(2);
        pos.setY(4);

        assertFalse(tester.check(d,pos,windowPatternCard));
    }


    private WindowPatternCard pullOutCard(int value){
        return getWindowPatternCard(value);
    }

    public static WindowPatternCard getWindowPatternCard(int value) {
        CardContainer container = new CardContainer();
        ArrayList<WindowPatternCard> windows;
        windows = container.pullOutPattern(4);
        int i;
        for (i = 0; i < windows.size() && windows.get(i).getNum() != value; i++) {
            if (i + 1 == windows.size()) {
                container = new CardContainer();
                windows = container.pullOutPattern(4);
                i = 0;
            }
        }
        return windows.get(i);
    }
}