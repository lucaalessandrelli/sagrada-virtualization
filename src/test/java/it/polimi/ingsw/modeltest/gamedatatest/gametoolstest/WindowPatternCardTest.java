package it.polimi.ingsw.modeltest.gamedatatest.gametoolstest;

import it.polimi.ingsw.model.gamedata.Colour;
import it.polimi.ingsw.model.gamedata.gametools.Cell;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.gametools.WindowPatternCard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static it.polimi.ingsw.modeltest.gamelogictest.InspectorPlaceTest.getWindowPatternCard;
import static org.junit.jupiter.api.Assertions.*;

class WindowPatternCardTest {


     @Test
     void TestMatrixLength() {
         WindowPatternCard tester = new WindowPatternCard(0, 0, "No name");
         assertEquals(4, tester.getMatr().size());
         assertEquals(5, tester.getMatr().get(0).size());
     }

     @Test
     void TestOccupied() {
         WindowPatternCard tester = new WindowPatternCard(0, 0, "No name");
         int x = 0, y = 0;
         Dice d = new Dice();
         assertTrue(tester.placeDice(d, x, y));
         assertTrue(tester.getMatr().get(x).get(y).isOccupied());
     }

     @Test
     void TestFull() {
         Dice d = new Dice();
         WindowPatternCard tester = new WindowPatternCard(0, 0, "No name");
         for (int i = 0; i < 4; i++) {
             for (int j = 0; j < 5; j++) {
                 tester.placeDice(d, i, j);
             }
         }
         for (int i = 0; i < 4; i++) {
             for (int j = 0; j < 5; j++) {
                 assertFalse(tester.placeDice(d, i, j));
             }
         }
     }

     @Test
     void TestFirstCard() {
         WindowPatternCard tester;
         tester = this.pullOutCard(1);

         System.out.println("Name: " + tester.getName());
         assertEquals("Kaleidoscopic Dream", tester.getName());

         ArrayList<ArrayList<Cell>> arrayLists = tester.getMatr();

         verify(arrayLists,Colour.YELLOW,0,0,0);
         verify(arrayLists,Colour.BLUE,0,0,1);
         verify(arrayLists,Colour.WHITE,0,0,2);
         verify(arrayLists,Colour.WHITE,0,0,3);
         verify(arrayLists,Colour.WHITE,1,0,4);

         verify(arrayLists,Colour.GREEN,0,1,0);
         verify(arrayLists,Colour.WHITE,0,1,1);
         verify(arrayLists,Colour.WHITE,5,1,2);
         verify(arrayLists,Colour.WHITE,0,1,3);
         verify(arrayLists,Colour.WHITE,4,1,4);

         verify(arrayLists,Colour.WHITE,3,2,0);
         verify(arrayLists,Colour.WHITE,0,2,1);
         verify(arrayLists,Colour.RED,0,2,2);
         verify(arrayLists,Colour.WHITE,0,2,3);
         verify(arrayLists,Colour.GREEN,0,2,4);

         verify(arrayLists,Colour.WHITE,2,3,0);
         verify(arrayLists,Colour.WHITE,0,3,1);
         verify(arrayLists,Colour.WHITE,0,3,2);
         verify(arrayLists,Colour.BLUE,0,3,3);
         verify(arrayLists,Colour.YELLOW,0,3,4);


     }

     @Test
     void TestSecondCard() {
         WindowPatternCard tester;
         tester = this.pullOutCard(2);

         System.out.println("Name: " + tester.getName());
         assertEquals("Virtus", tester.getName());

         ArrayList<ArrayList<Cell>> arrayLists = tester.getMatr();

         verify(arrayLists,Colour.WHITE,4,0,0);
         verify(arrayLists,Colour.WHITE,0,0,1);
         verify(arrayLists,Colour.WHITE,2,0,2);
         verify(arrayLists,Colour.WHITE,5,0,3);
         verify(arrayLists,Colour.GREEN,0,0,4);

         verify(arrayLists,Colour.WHITE,0,1,0);
         verify(arrayLists,Colour.WHITE,0,1,1);
         verify(arrayLists,Colour.WHITE,6,1,2);
         verify(arrayLists,Colour.GREEN,0,1,3);
         verify(arrayLists,Colour.WHITE,2,1,4);

         verify(arrayLists,Colour.WHITE,0,2,0);
         verify(arrayLists,Colour.WHITE,3,2,1);
         verify(arrayLists,Colour.GREEN,0,2,2);
         verify(arrayLists,Colour.WHITE,4,2,3);
         verify(arrayLists,Colour.WHITE,0,2,4);

         verify(arrayLists,Colour.WHITE,5,3,0);
         verify(arrayLists,Colour.GREEN,0,3,1);
         verify(arrayLists,Colour.WHITE,1,3,2);
         verify(arrayLists,Colour.WHITE,0,3,3);
         verify(arrayLists,Colour.WHITE,0,3,4);

     }


     @Test
     void TestThirdCard() {
         WindowPatternCard tester;
         tester = this.pullOutCard(3);

         System.out.println("Name: " + tester.getName());
         assertEquals("Aurorae Magnificus", tester.getName());

         ArrayList<ArrayList<Cell>> arrayLists = tester.getMatr();

         verify(arrayLists,Colour.WHITE,5,0,0);
         verify(arrayLists,Colour.GREEN,0,0,1);
         verify(arrayLists,Colour.BLUE,0,0,2);
         verify(arrayLists,Colour.PURPLE,0,0,3);
         verify(arrayLists,Colour.WHITE,2,0,4);

         verify(arrayLists,Colour.PURPLE,0,1,0);
         verify(arrayLists,Colour.WHITE,0,1,1);
         verify(arrayLists,Colour.WHITE,0,1,2);
         verify(arrayLists,Colour.WHITE,0,1,3);
         verify(arrayLists,Colour.YELLOW,0,1,4);

         verify(arrayLists,Colour.YELLOW,0,2,0);
         verify(arrayLists,Colour.WHITE,0,2,1);
         verify(arrayLists,Colour.WHITE,6,2,2);
         verify(arrayLists,Colour.WHITE,0,2,3);
         verify(arrayLists,Colour.PURPLE,0,2,4);

         verify(arrayLists,Colour.WHITE,1,3,0);
         verify(arrayLists,Colour.WHITE,0,3,1);
         verify(arrayLists,Colour.WHITE,0,3,2);
         verify(arrayLists,Colour.GREEN,0,3,3);
         verify(arrayLists,Colour.WHITE,4,3,4);

     }


     @Test
     void TestFourthCard() {
         WindowPatternCard tester;
         tester = this.pullOutCard(4);

         System.out.println("Name: " + tester.getName());
         assertEquals("Via Lux", tester.getName());

         ArrayList<ArrayList<Cell>> arrayLists = tester.getMatr();

         verify(arrayLists,Colour.YELLOW,0,0,0);
         verify(arrayLists,Colour.WHITE,0,0,1);
         verify(arrayLists,Colour.WHITE,6,0,2);
         verify(arrayLists,Colour.WHITE,0,0,3);
         verify(arrayLists,Colour.WHITE,0,0,4);

         verify(arrayLists,Colour.WHITE,0,1,0);
         verify(arrayLists,Colour.WHITE,1,1,1);
         verify(arrayLists,Colour.WHITE,5,1,2);
         verify(arrayLists,Colour.WHITE,0,1,3);
         verify(arrayLists,Colour.WHITE,2,1,4);

         verify(arrayLists,Colour.WHITE,3,2,0);
         verify(arrayLists,Colour.YELLOW,0,2,1);
         verify(arrayLists,Colour.RED,0,2,2);
         verify(arrayLists,Colour.PURPLE,0,2,3);
         verify(arrayLists,Colour.WHITE,0,2,4);

         verify(arrayLists,Colour.WHITE,0,3,0);
         verify(arrayLists,Colour.WHITE,0,3,1);
         verify(arrayLists,Colour.WHITE,4,3,2);
         verify(arrayLists,Colour.WHITE,3,3,3);
         verify(arrayLists,Colour.RED,0,3,4);

     }

     @Test
     void TestFifthCard() {
         WindowPatternCard tester;
         tester = this.pullOutCard(5);

         System.out.println("Name: " + tester.getName());
         assertEquals("Sun Catcher", tester.getName());

         ArrayList<ArrayList<Cell>> arrayLists = tester.getMatr();

         verify(arrayLists,Colour.WHITE,0,0,0);
         verify(arrayLists,Colour.BLUE,0,0,1);
         verify(arrayLists,Colour.WHITE,2,0,2);
         verify(arrayLists,Colour.WHITE,0,0,3);
         verify(arrayLists,Colour.YELLOW,0,0,4);

         verify(arrayLists,Colour.WHITE,0,1,0);
         verify(arrayLists,Colour.WHITE,4,1,1);
         verify(arrayLists,Colour.WHITE,0,1,2);
         verify(arrayLists,Colour.RED,0,1,3);
         verify(arrayLists,Colour.WHITE,0,1,4);

         verify(arrayLists,Colour.WHITE,0,2,0);
         verify(arrayLists,Colour.WHITE,0,2,1);
         verify(arrayLists,Colour.WHITE,5,2,2);
         verify(arrayLists,Colour.YELLOW,0,2,3);
         verify(arrayLists,Colour.WHITE,0,2,4);

         verify(arrayLists,Colour.GREEN,0,3,0);
         verify(arrayLists,Colour.WHITE,3,3,1);
         verify(arrayLists,Colour.WHITE,0,3,2);
         verify(arrayLists,Colour.WHITE,0,3,3);
         verify(arrayLists,Colour.PURPLE,0,3,4);

     }

     @Test
     void TestSixthCard() {
         WindowPatternCard tester;
         tester = this.pullOutCard(6);

         System.out.println("Name: " + tester.getName());
         assertEquals("Bellesguard", tester.getName());

         ArrayList<ArrayList<Cell>> arrayLists = tester.getMatr();

         verify(arrayLists,Colour.BLUE,0,0,0);
         verify(arrayLists,Colour.WHITE,6,0,1);
         verify(arrayLists,Colour.WHITE,0,0,2);
         verify(arrayLists,Colour.WHITE,0,0,3);
         verify(arrayLists,Colour.YELLOW,0,0,4);

         verify(arrayLists,Colour.WHITE,0,1,0);
         verify(arrayLists,Colour.WHITE,3,1,1);
         verify(arrayLists,Colour.BLUE,0,1,2);
         verify(arrayLists,Colour.WHITE,0,1,3);
         verify(arrayLists,Colour.WHITE,0,1,4);

         verify(arrayLists,Colour.WHITE,0,2,0);
         verify(arrayLists,Colour.WHITE,5,2,1);
         verify(arrayLists,Colour.WHITE,6,2,2);
         verify(arrayLists,Colour.WHITE,2,2,3);
         verify(arrayLists,Colour.WHITE,0,2,4);

         verify(arrayLists,Colour.WHITE,0,3,0);
         verify(arrayLists,Colour.WHITE,4,3,1);
         verify(arrayLists,Colour.WHITE,0,3,2);
         verify(arrayLists,Colour.WHITE,1,3,3);
         verify(arrayLists,Colour.GREEN,0,3,4);

     }

     @Test
     void TestSeventhCard() {
         WindowPatternCard tester;
         tester = this.pullOutCard(7);

         System.out.println("Name: " + tester.getName());
         assertEquals("Firmitas", tester.getName());

         ArrayList<ArrayList<Cell>> arrayLists = tester.getMatr();

         verify(arrayLists,Colour.PURPLE,0,0,0);
         verify(arrayLists,Colour.WHITE,6,0,1);
         verify(arrayLists,Colour.WHITE,0,0,2);
         verify(arrayLists,Colour.WHITE,0,0,3);
         verify(arrayLists,Colour.WHITE,3,0,4);

         verify(arrayLists,Colour.WHITE,5,1,0);
         verify(arrayLists,Colour.PURPLE,0,1,1);
         verify(arrayLists,Colour.WHITE,3,1,2);
         verify(arrayLists,Colour.WHITE,0,1,3);
         verify(arrayLists,Colour.WHITE,0,1,4);

         verify(arrayLists,Colour.WHITE,0,2,0);
         verify(arrayLists,Colour.WHITE,2,2,1);
         verify(arrayLists,Colour.PURPLE,0,2,2);
         verify(arrayLists,Colour.WHITE,1,2,3);
         verify(arrayLists,Colour.WHITE,0,2,4);

         verify(arrayLists,Colour.WHITE,0,3,0);
         verify(arrayLists,Colour.WHITE,1,3,1);
         verify(arrayLists,Colour.WHITE,5,3,2);
         verify(arrayLists,Colour.PURPLE,0,3,3);
         verify(arrayLists,Colour.WHITE,4,3,4);

     }

     @Test
     void TestEightCard() {
         WindowPatternCard tester;
         tester = this.pullOutCard(8);

         ArrayList<ArrayList<Cell>> arrayLists = tester.getMatr();

         verify(arrayLists,Colour.WHITE,2,0,0);
         verify(arrayLists,Colour.WHITE,0,0,1);
         verify(arrayLists,Colour.WHITE,5,0,2);
         verify(arrayLists,Colour.WHITE,0,0,3);
         verify(arrayLists,Colour.WHITE,1,0,4);

         verify(arrayLists,Colour.YELLOW,0,1,0);
         verify(arrayLists,Colour.WHITE,6,1,1);
         verify(arrayLists,Colour.PURPLE,0,1,2);
         verify(arrayLists,Colour.WHITE,2,1,3);
         verify(arrayLists,Colour.RED,0,1,4);

         verify(arrayLists,Colour.WHITE,0,2,0);
         verify(arrayLists,Colour.BLUE,0,2,1);
         verify(arrayLists,Colour.WHITE,4,2,2);
         verify(arrayLists,Colour.GREEN,0,2,3);
         verify(arrayLists,Colour.WHITE,0,2,4);

         verify(arrayLists,Colour.WHITE,0,3,0);
         verify(arrayLists,Colour.WHITE,3,3,1);
         verify(arrayLists,Colour.WHITE,0,3,2);
         verify(arrayLists,Colour.WHITE,5,3,3);
         verify(arrayLists,Colour.WHITE,0,3,4);

     }

     @Test
     void TestNinthCard() {
         WindowPatternCard tester;
         tester = this.pullOutCard(9);

         System.out.println("Name: " + tester.getName());
         assertEquals("Aurora Sagradis", tester.getName());

         ArrayList<ArrayList<Cell>> arrayLists = tester.getMatr();

         verify(arrayLists,Colour.RED,0,0,0);
         verify(arrayLists,Colour.WHITE,0,0,1);
         verify(arrayLists,Colour.BLUE,0,0,2);
         verify(arrayLists,Colour.WHITE,0,0,3);
         verify(arrayLists,Colour.YELLOW,0,0,4);

         verify(arrayLists,Colour.WHITE,4,1,0);
         verify(arrayLists,Colour.PURPLE,0,1,1);
         verify(arrayLists,Colour.WHITE,3,1,2);
         verify(arrayLists,Colour.GREEN,0,1,3);
         verify(arrayLists,Colour.WHITE,2,1,4);

         verify(arrayLists,Colour.WHITE,0,2,0);
         verify(arrayLists,Colour.WHITE,1,2,1);
         verify(arrayLists,Colour.WHITE,0,2,2);
         verify(arrayLists,Colour.WHITE,5,2,3);
         verify(arrayLists,Colour.WHITE,0,2,4);

         verify(arrayLists,Colour.WHITE,0,3,0);
         verify(arrayLists,Colour.WHITE,0,3,1);
         verify(arrayLists,Colour.WHITE,6,3,2);
         verify(arrayLists,Colour.WHITE,0,3,3);
         verify(arrayLists,Colour.WHITE,0,3,4);

     }

     @Test
     void TestTenthCard() {
         WindowPatternCard tester;
         tester = this.pullOutCard(10);

         System.out.println("Name: " + tester.getName());
         assertEquals("Industria", tester.getName());

         ArrayList<ArrayList<Cell>> arrayLists = tester.getMatr();

         verify(arrayLists,Colour.WHITE,1,0,0);
         verify(arrayLists,Colour.RED,0,0,1);
         verify(arrayLists,Colour.WHITE,3,0,2);
         verify(arrayLists,Colour.WHITE,0,0,3);
         verify(arrayLists,Colour.WHITE,6,0,4);

         verify(arrayLists,Colour.WHITE,5,1,0);
         verify(arrayLists,Colour.WHITE,4,1,1);
         verify(arrayLists,Colour.RED,0,1,2);
         verify(arrayLists,Colour.WHITE,2,1,3);
         verify(arrayLists,Colour.WHITE,0,1,4);

         verify(arrayLists,Colour.WHITE,0,2,0);
         verify(arrayLists,Colour.WHITE,0,2,1);
         verify(arrayLists,Colour.WHITE,5,2,2);
         verify(arrayLists,Colour.RED,0,2,3);
         verify(arrayLists,Colour.WHITE,1,2,4);

         verify(arrayLists,Colour.WHITE,0,3,0);
         verify(arrayLists,Colour.WHITE,0,3,1);
         verify(arrayLists,Colour.WHITE,0,3,2);
         verify(arrayLists,Colour.WHITE,3,3,3);
         verify(arrayLists,Colour.RED,0,3,4);

     }

     @Test
     void TestEleventhCard() {
        WindowPatternCard tester;
         tester = this.pullOutCard(11);

         System.out.println("Name: " + tester.getName());
         assertEquals("Shadow Thief", tester.getName());

         ArrayList<ArrayList<Cell>> arrayLists = tester.getMatr();

         verify(arrayLists,Colour.WHITE,6,0,0);
         verify(arrayLists,Colour.PURPLE,0,0,1);
         verify(arrayLists,Colour.WHITE,0,0,2);
         verify(arrayLists,Colour.WHITE,0,0,3);
         verify(arrayLists,Colour.WHITE,5,0,4);

         verify(arrayLists,Colour.WHITE,5,1,0);
         verify(arrayLists,Colour.WHITE,0,1,1);
         verify(arrayLists,Colour.PURPLE,0,1,2);
         verify(arrayLists,Colour.WHITE,0,1,3);
         verify(arrayLists,Colour.WHITE,0,1,4);

         verify(arrayLists,Colour.RED,0,2,0);
         verify(arrayLists,Colour.WHITE,6,2,1);
         verify(arrayLists,Colour.WHITE,0,2,2);
         verify(arrayLists,Colour.PURPLE,0,2,3);
         verify(arrayLists,Colour.WHITE,0,2,4);

         verify(arrayLists,Colour.YELLOW,0,3,0);
         verify(arrayLists,Colour.RED,0,3,1);
         verify(arrayLists,Colour.WHITE,5,3,2);
         verify(arrayLists,Colour.WHITE,4,3,3);
         verify(arrayLists,Colour.WHITE,3,3,4);

     }

     @Test
     void TestTwelthCard() {
         WindowPatternCard tester;
         tester = this.pullOutCard(12);

         System.out.println("Name: " + tester.getName());
         assertEquals("Batllo", tester.getName());

         ArrayList<ArrayList<Cell>> arrayLists = tester.getMatr();

         verify(arrayLists,Colour.WHITE,0,0,0);
         verify(arrayLists,Colour.WHITE,0,0,1);
         verify(arrayLists,Colour.WHITE,6,0,2);
         verify(arrayLists,Colour.WHITE,0,0,3);
         verify(arrayLists,Colour.WHITE,0,0,4);

         verify(arrayLists,Colour.WHITE,0,1,0);
         verify(arrayLists,Colour.WHITE,5,1,1);
         verify(arrayLists,Colour.BLUE,0,1,2);
         verify(arrayLists,Colour.WHITE,4,1,3);
         verify(arrayLists,Colour.WHITE,0,1,4);

         verify(arrayLists,Colour.WHITE,3,2,0);
         verify(arrayLists,Colour.GREEN,0,2,1);
         verify(arrayLists,Colour.YELLOW,0,2,2);
         verify(arrayLists,Colour.PURPLE,0,2,3);
         verify(arrayLists,Colour.WHITE,2,2,4);

         verify(arrayLists,Colour.WHITE,1,3,0);
         verify(arrayLists,Colour.WHITE,4,3,1);
         verify(arrayLists,Colour.RED,0,3,2);
         verify(arrayLists,Colour.WHITE,5,3,3);
         verify(arrayLists,Colour.WHITE,3,3,4);

     }

     @Test
     void TestThirteenthCard() {
         WindowPatternCard tester;
         tester = this.pullOutCard(13);

         System.out.println("Name: " + tester.getName());
         assertEquals("Gravitas", tester.getName());

         ArrayList<ArrayList<Cell>> arrayLists = tester.getMatr();

         verify(arrayLists,Colour.WHITE,1,0,0);
         verify(arrayLists,Colour.WHITE,0,0,1);
         verify(arrayLists,Colour.WHITE,3,0,2);
         verify(arrayLists,Colour.BLUE,0,0,3);
         verify(arrayLists,Colour.WHITE,0,0,4);

         verify(arrayLists,Colour.WHITE,0,1,0);
         verify(arrayLists,Colour.WHITE,2,1,1);
         verify(arrayLists,Colour.BLUE,0,1,2);
         verify(arrayLists,Colour.WHITE,0,1,3);
         verify(arrayLists,Colour.WHITE,0,1,4);

         verify(arrayLists,Colour.WHITE,6,2,0);
         verify(arrayLists,Colour.BLUE,0,2,1);
         verify(arrayLists,Colour.WHITE,0,2,2);
         verify(arrayLists,Colour.WHITE,4,2,3);
         verify(arrayLists,Colour.WHITE,0,2,4);

         verify(arrayLists,Colour.BLUE,0,3,0);
         verify(arrayLists,Colour.WHITE,5,3,1);
         verify(arrayLists,Colour.WHITE,2,3,2);
         verify(arrayLists,Colour.WHITE,0,3,3);
         verify(arrayLists,Colour.WHITE,1,3,4);

     }

     @Test
     void TestFourteenthCard() {
         WindowPatternCard tester;
         tester = this.pullOutCard(14);

         System.out.println("Name: " + tester.getName());
         assertEquals("Fractal Drops", tester.getName());

         ArrayList<ArrayList<Cell>> arrayLists = tester.getMatr();

         verify(arrayLists,Colour.WHITE,0,0,0);
         verify(arrayLists,Colour.WHITE,4,0,1);
         verify(arrayLists,Colour.WHITE,0,0,2);
         verify(arrayLists,Colour.YELLOW,0,0,3);
         verify(arrayLists,Colour.WHITE,6,0,4);

         verify(arrayLists,Colour.RED,0,1,0);
         verify(arrayLists,Colour.WHITE,0,1,1);
         verify(arrayLists,Colour.WHITE,2,1,2);
         verify(arrayLists,Colour.WHITE,0,1,3);
         verify(arrayLists,Colour.WHITE,0,1,4);

         verify(arrayLists,Colour.WHITE,0,2,0);
         verify(arrayLists,Colour.WHITE,0,2,1);
         verify(arrayLists,Colour.RED,0,2,2);
         verify(arrayLists,Colour.PURPLE,0,2,3);
         verify(arrayLists,Colour.WHITE,1,2,4);

         verify(arrayLists,Colour.BLUE,0,3,0);
         verify(arrayLists,Colour.YELLOW,0,3,1);
         verify(arrayLists,Colour.WHITE,0,3,2);
         verify(arrayLists,Colour.WHITE,0,3,3);
         verify(arrayLists,Colour.WHITE,0,3,4);


     }

     @Test
     void TestFifthteenCard() {
         WindowPatternCard tester;
         tester = this.pullOutCard(15);

         System.out.println("Name: " + tester.getName());
         assertEquals("Lux Astram", tester.getName());

         ArrayList<ArrayList<Cell>> arrayLists = tester.getMatr();

         verify(arrayLists,Colour.WHITE,0,0,0);
         verify(arrayLists,Colour.WHITE,1,0,1);
         verify(arrayLists,Colour.GREEN,0,0,2);
         verify(arrayLists,Colour.PURPLE,0,0,3);
         verify(arrayLists,Colour.WHITE,4,0,4);

         verify(arrayLists,Colour.WHITE,6,1,0);
         verify(arrayLists,Colour.PURPLE,0,1,1);
         verify(arrayLists,Colour.WHITE,2,1,2);
         verify(arrayLists,Colour.WHITE,5,1,3);
         verify(arrayLists,Colour.GREEN,0,1,4);

         verify(arrayLists,Colour.WHITE,1,2,0);
         verify(arrayLists,Colour.GREEN,0,2,1);
         verify(arrayLists,Colour.WHITE,5,2,2);
         verify(arrayLists,Colour.WHITE,3,2,3);
         verify(arrayLists,Colour.PURPLE,0,2,4);

         verify(arrayLists,Colour.WHITE,0,3,0);
         verify(arrayLists,Colour.WHITE,0,3,1);
         verify(arrayLists,Colour.WHITE,0,3,2);
         verify(arrayLists,Colour.WHITE,0,3,3);
         verify(arrayLists,Colour.WHITE,0,3,4);


     }

     @Test
     void TestSixteenthCard() {
         WindowPatternCard tester;
         tester = this.pullOutCard(16);

         System.out.println("Name: " + tester.getName());
         assertEquals("Chromatic Splendor", tester.getName());

         ArrayList<ArrayList<Cell>> arrayLists = tester.getMatr();

         verify(arrayLists,Colour.WHITE,0,0,0);
         verify(arrayLists,Colour.WHITE,0,0,1);
         verify(arrayLists,Colour.GREEN,0,0,2);
         verify(arrayLists,Colour.WHITE,0,0,3);
         verify(arrayLists,Colour.WHITE,0,0,4);

         verify(arrayLists,Colour.WHITE,2,1,0);
         verify(arrayLists,Colour.YELLOW,0,1,1);
         verify(arrayLists,Colour.WHITE,5,1,2);
         verify(arrayLists,Colour.BLUE,0,1,3);
         verify(arrayLists,Colour.WHITE,1,1,4);

         verify(arrayLists,Colour.WHITE,0,2,0);
         verify(arrayLists,Colour.RED,0,2,1);
         verify(arrayLists,Colour.WHITE,3,2,2);
         verify(arrayLists,Colour.PURPLE,0,2,3);
         verify(arrayLists,Colour.WHITE,0,2,4);

         verify(arrayLists,Colour.WHITE,1,3,0);
         verify(arrayLists,Colour.WHITE,0,3,1);
         verify(arrayLists,Colour.WHITE,6,3,2);
         verify(arrayLists,Colour.WHITE,0,3,3);
         verify(arrayLists,Colour.WHITE,4,3,4);


     }

     @Test
     void TestSeventeenthCard() {
         WindowPatternCard tester;
         tester = this.pullOutCard(17);

         System.out.println("Name: " + tester.getName());
         assertEquals("Firelight", tester.getName());

         ArrayList<ArrayList<Cell>> arrayLists = tester.getMatr();

         verify(arrayLists,Colour.WHITE,3,0,0);
         verify(arrayLists,Colour.WHITE,4,0,1);
         verify(arrayLists,Colour.WHITE,1,0,2);
         verify(arrayLists,Colour.WHITE,5,0,3);
         verify(arrayLists,Colour.WHITE,0,0,4);

         verify(arrayLists,Colour.WHITE,0,1,0);
         verify(arrayLists,Colour.WHITE,6,1,1);
         verify(arrayLists,Colour.WHITE,2,1,2);
         verify(arrayLists,Colour.WHITE,0,1,3);
         verify(arrayLists,Colour.YELLOW,0,1,4);

         verify(arrayLists,Colour.WHITE,0,2,0);
         verify(arrayLists,Colour.WHITE,0,2,1);
         verify(arrayLists,Colour.WHITE,0,2,2);
         verify(arrayLists,Colour.YELLOW,0,2,3);
         verify(arrayLists,Colour.RED,0,2,4);

         verify(arrayLists,Colour.WHITE,5,3,0);
         verify(arrayLists,Colour.WHITE,0,3,1);
         verify(arrayLists,Colour.YELLOW,0,3,2);
         verify(arrayLists,Colour.RED,0,3,3);
         verify(arrayLists,Colour.WHITE,6,3,4);


     }

     @Test
     void TestEighteenthCard() {
         WindowPatternCard tester;
         tester = this.pullOutCard(18);

         System.out.println("Name: " + tester.getName());
         assertEquals("Luz Celestial", tester.getName());

         ArrayList<ArrayList<Cell>> arrayLists = tester.getMatr();

         verify(arrayLists,Colour.WHITE,0,0,0);
         verify(arrayLists,Colour.WHITE,0,0,1);
         verify(arrayLists,Colour.RED,0,0,2);
         verify(arrayLists,Colour.WHITE,5,0,3);
         verify(arrayLists,Colour.WHITE,0,0,4);

         verify(arrayLists,Colour.PURPLE,0,1,0);
         verify(arrayLists,Colour.WHITE,4,1,1);
         verify(arrayLists,Colour.WHITE,0,1,2);
         verify(arrayLists,Colour.GREEN,0,1,3);
         verify(arrayLists,Colour.WHITE,3,1,4);

         verify(arrayLists,Colour.WHITE,6,2,0);
         verify(arrayLists,Colour.WHITE,0,2,1);
         verify(arrayLists,Colour.WHITE,0,2,2);
         verify(arrayLists,Colour.BLUE,0,2,3);
         verify(arrayLists,Colour.WHITE,0,2,4);

         verify(arrayLists,Colour.WHITE,0,3,0);
         verify(arrayLists,Colour.YELLOW,0,3,1);
         verify(arrayLists,Colour.WHITE,2,3,2);
         verify(arrayLists,Colour.WHITE,0,3,3);
         verify(arrayLists,Colour.WHITE,0,3,4);


     }

     @Test
     void TestNineteenthCard() {
         WindowPatternCard tester;
         tester = this.pullOutCard(19);

         System.out.println("Name: " + tester.getName());
         assertEquals("Water of Life", tester.getName());

         ArrayList<ArrayList<Cell>> arrayLists = tester.getMatr();

         verify(arrayLists,Colour.WHITE,6,0,0);
         verify(arrayLists,Colour.BLUE,0,0,1);
         verify(arrayLists,Colour.WHITE,0,0,2);
         verify(arrayLists,Colour.WHITE,0,0,3);
         verify(arrayLists,Colour.WHITE,1,0,4);

         verify(arrayLists,Colour.WHITE,0,1,0);
         verify(arrayLists,Colour.WHITE,5,1,1);
         verify(arrayLists,Colour.BLUE,0,1,2);
         verify(arrayLists,Colour.WHITE,0,1,3);
         verify(arrayLists,Colour.WHITE,0,1,4);

         verify(arrayLists,Colour.WHITE,4,2,0);
         verify(arrayLists,Colour.RED,0,2,1);
         verify(arrayLists,Colour.WHITE,2,2,2);
         verify(arrayLists,Colour.BLUE,0,2,3);
         verify(arrayLists,Colour.WHITE,0,2,4);

         verify(arrayLists,Colour.GREEN,0,3,0);
         verify(arrayLists,Colour.WHITE,6,3,1);
         verify(arrayLists,Colour.YELLOW,0,3,2);
         verify(arrayLists,Colour.WHITE,3,3,3);
         verify(arrayLists,Colour.PURPLE,0,3,4);


     }

     @Test
     void TestTwenteenthCard() {
         WindowPatternCard tester;
         tester = this.pullOutCard(20);

         System.out.println("Name: " + tester.getName());
         assertEquals("Ripples of Light", tester.getName());

         ArrayList<ArrayList<Cell>> arrayLists = tester.getMatr();

         verify(arrayLists,Colour.WHITE,0,0,0);
         verify(arrayLists,Colour.WHITE,0,0,1);
         verify(arrayLists,Colour.WHITE,0,0,2);
         verify(arrayLists,Colour.RED,0,0,3);
         verify(arrayLists,Colour.WHITE,5,0,4);

         verify(arrayLists,Colour.WHITE,0,1,0);
         verify(arrayLists,Colour.WHITE,0,1,1);
         verify(arrayLists,Colour.PURPLE,0,1,2);
         verify(arrayLists,Colour.WHITE,4,1,3);
         verify(arrayLists,Colour.BLUE,0,1,4);

         verify(arrayLists,Colour.WHITE,0,2,0);
         verify(arrayLists,Colour.BLUE,0,2,1);
         verify(arrayLists,Colour.WHITE,3,2,2);
         verify(arrayLists,Colour.YELLOW,0,2,3);
         verify(arrayLists,Colour.WHITE,6,2,4);

         verify(arrayLists,Colour.YELLOW,0,3,0);
         verify(arrayLists,Colour.WHITE,2,3,1);
         verify(arrayLists,Colour.GREEN,0,3,2);
         verify(arrayLists,Colour.WHITE,1,3,3);
         verify(arrayLists,Colour.RED,0,3,4);


     }

     @Test
     void TestTwentyfirstCard() {
         WindowPatternCard tester;
         tester = this.pullOutCard(21);

         System.out.println("Name: " + tester.getName());
         assertEquals("Lux Mundi", tester.getName());

         ArrayList<ArrayList<Cell>> arrayLists = tester.getMatr();

         verify(arrayLists,Colour.WHITE,0,0,0);
         verify(arrayLists,Colour.WHITE,0,0,1);
         verify(arrayLists,Colour.WHITE,1,0,2);
         verify(arrayLists,Colour.WHITE,0,0,3);
         verify(arrayLists,Colour.WHITE,0,0,4);

         verify(arrayLists,Colour.WHITE,1,1,0);
         verify(arrayLists,Colour.GREEN,0,1,1);
         verify(arrayLists,Colour.WHITE,3,1,2);
         verify(arrayLists,Colour.BLUE,0,1,3);
         verify(arrayLists,Colour.WHITE,2,1,4);

         verify(arrayLists,Colour.BLUE,0,2,0);
         verify(arrayLists,Colour.WHITE,5,2,1);
         verify(arrayLists,Colour.WHITE,4,2,2);
         verify(arrayLists,Colour.WHITE,6,2,3);
         verify(arrayLists,Colour.GREEN,0,2,4);

         verify(arrayLists,Colour.WHITE,0,3,0);
         verify(arrayLists,Colour.BLUE,0,3,1);
         verify(arrayLists,Colour.WHITE,5,3,2);
         verify(arrayLists,Colour.GREEN,0,3,3);
         verify(arrayLists,Colour.WHITE,0,3,4);


     }

     @Test
     void TestTwentysecondCard() {
         WindowPatternCard tester;
         tester = this.pullOutCard(22);

         System.out.println("Name: " + tester.getName());
         assertEquals("Comitas", tester.getName());

         ArrayList<ArrayList<Cell>> arrayLists = tester.getMatr();

         verify(arrayLists,Colour.YELLOW,0,0,0);
         verify(arrayLists,Colour.WHITE,0,0,1);
         verify(arrayLists,Colour.WHITE,2,0,2);
         verify(arrayLists,Colour.WHITE,0,0,3);
         verify(arrayLists,Colour.WHITE,6,0,4);

         verify(arrayLists,Colour.WHITE,0,1,0);
         verify(arrayLists,Colour.WHITE,4,1,1);
         verify(arrayLists,Colour.WHITE,0,1,2);
         verify(arrayLists,Colour.WHITE,5,1,3);
         verify(arrayLists,Colour.YELLOW,0,1,4);

         verify(arrayLists,Colour.WHITE,0,2,0);
         verify(arrayLists,Colour.WHITE,0,2,1);
         verify(arrayLists,Colour.WHITE,0,2,2);
         verify(arrayLists,Colour.YELLOW,0,2,3);
         verify(arrayLists,Colour.WHITE,5,2,4);

         verify(arrayLists,Colour.WHITE,1,3,0);
         verify(arrayLists,Colour.WHITE,2,3,1);
         verify(arrayLists,Colour.YELLOW,0,3,2);
         verify(arrayLists,Colour.WHITE,3,3,3);
         verify(arrayLists,Colour.WHITE,0,3,4);


     }

     @Test
     void TestTwentythirdCard() {
         WindowPatternCard tester;
         tester = this.pullOutCard(23);

         System.out.println("Name: " + tester.getName());
         assertEquals("Sun's Glory", tester.getName());

         ArrayList<ArrayList<Cell>> arrayLists = tester.getMatr();

         verify(arrayLists,Colour.WHITE,1,0,0);
         verify(arrayLists,Colour.PURPLE,0,0,1);
         verify(arrayLists,Colour.YELLOW,0,0,2);
         verify(arrayLists,Colour.WHITE,0,0,3);
         verify(arrayLists,Colour.WHITE,4,0,4);

         verify(arrayLists,Colour.PURPLE,0,1,0);
         verify(arrayLists,Colour.YELLOW,0,1,1);
         verify(arrayLists,Colour.WHITE,0,1,2);
         verify(arrayLists,Colour.WHITE,0,1,3);
         verify(arrayLists,Colour.WHITE,6,1,4);

         verify(arrayLists,Colour.YELLOW,0,2,0);
         verify(arrayLists,Colour.WHITE,0,2,1);
         verify(arrayLists,Colour.WHITE,0,2,2);
         verify(arrayLists,Colour.WHITE,5,2,3);
         verify(arrayLists,Colour.WHITE,3,2,4);

         verify(arrayLists,Colour.WHITE,0,3,0);
         verify(arrayLists,Colour.WHITE,5,3,1);
         verify(arrayLists,Colour.WHITE,4,3,2);
         verify(arrayLists,Colour.WHITE,2,3,3);
         verify(arrayLists,Colour.WHITE,1,3,4);


     }

     @Test
     void TestTwentyfourthCard() {
         WindowPatternCard tester;
         tester = this.pullOutCard(24);

         System.out.println("Name: " + tester.getName());
         assertEquals("Fulgor del Cielo", tester.getName());

         ArrayList<ArrayList<Cell>> arrayLists = tester.getMatr();

         verify(arrayLists,Colour.WHITE,0,0,0);
         verify(arrayLists,Colour.BLUE,0,0,1);
         verify(arrayLists,Colour.RED,0,0,2);
         verify(arrayLists,Colour.WHITE,0,0,3);
         verify(arrayLists,Colour.WHITE,0,0,4);

         verify(arrayLists,Colour.WHITE,0,1,0);
         verify(arrayLists,Colour.WHITE,4,1,1);
         verify(arrayLists,Colour.WHITE,5,1,2);
         verify(arrayLists,Colour.WHITE,0,1,3);
         verify(arrayLists,Colour.BLUE,0,1,4);

         verify(arrayLists,Colour.BLUE,0,2,0);
         verify(arrayLists,Colour.WHITE,2,2,1);
         verify(arrayLists,Colour.WHITE,0,2,2);
         verify(arrayLists,Colour.RED,0,2,3);
         verify(arrayLists,Colour.WHITE,5,2,4);

         verify(arrayLists,Colour.WHITE,6,3,0);
         verify(arrayLists,Colour.RED,0,3,1);
         verify(arrayLists,Colour.WHITE,3,3,2);
         verify(arrayLists,Colour.WHITE,1,3,3);
         verify(arrayLists,Colour.WHITE,0,3,4);


     }

     private void verify(ArrayList<ArrayList<Cell>> matr, Colour colour, int number, int x, int y){
         assertEquals(colour, matr.get(x).get(y).getProperty().getColour()); //COLOUR
         assertEquals(number, matr.get(x).get(y).getProperty().getNumber()); //NUMBER
     }

     public WindowPatternCard pullOutCard(int value){
         return getWindowPatternCard(value);
     }
 }

