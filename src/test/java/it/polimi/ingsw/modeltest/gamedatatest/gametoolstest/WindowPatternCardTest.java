package it.polimi.ingsw.modeltest.gamedatatest.gametoolstest;

import it.polimi.ingsw.model.gamedata.Colour;
import it.polimi.ingsw.model.gamedata.gametools.CardContainer;
import it.polimi.ingsw.model.gamedata.gametools.Cell;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.gametools.WindowPatternCard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

 class WindowPatternCardTest {


     @Test
     void TestMatrixLength() {
         WindowPatternCard tester = new WindowPatternCard(0, 0, "No name");
         assertEquals(4, tester.getMatr().size());
         assertEquals(5, tester.getMatr().get(0).size());
     }

     //to do this we need to define the rules
    /*@Test
    public void TestPlacement(){

    }
*/
     @Test
     void TestOccupied() {
         WindowPatternCard tester = new WindowPatternCard(0, 0, "No name");
         int x = 0, y = 0;
         Dice d = new Dice();
         assertTrue(tester.placeDice(d, x, y) == true);
         assertTrue(tester.getMatr().get(x).get(y).isOccupied() == true);
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
                 assertTrue(tester.placeDice(d, i, j) == false);
             }
         }
     }

     @Test
     void TestFirstCard() {
         CardContainer container = new CardContainer();
         WindowPatternCard tester;
         ArrayList<WindowPatternCard> windows;
         windows = container.pullOutPattern(4);
         int i;
         for (i = 0; i < windows.size() && windows.get(i).getNum() != 1; i++) {
             if (i + 1 == windows.size()) {
                 container = new CardContainer();
                 windows = container.pullOutPattern(4);
                 i = 0;
             }
         }
         tester = windows.get(i);

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
         CardContainer container = new CardContainer();
         WindowPatternCard tester;
         ArrayList<WindowPatternCard> windows;
         windows = container.pullOutPattern(4);
         int i;
         for (i = 0; i < windows.size() && windows.get(i).getNum() != 2; i++) {
             if (i + 1 == windows.size()) {
                 container = new CardContainer();
                 windows = container.pullOutPattern(4);
                 i = 0;
             }
         }
         tester = windows.get(i);

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
         CardContainer container = new CardContainer();
         WindowPatternCard tester;
         ArrayList<WindowPatternCard> windows;
         windows = container.pullOutPattern(4);
         int i;
         for (i = 0; i < windows.size() && windows.get(i).getNum() != 3; i++) {
             if (i + 1 == windows.size()) {
                 container = new CardContainer();
                 windows = container.pullOutPattern(4);
                 i = 0;
             }
         }
         tester = windows.get(i);

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
         CardContainer container = new CardContainer();
         WindowPatternCard tester;
         ArrayList<WindowPatternCard> windows;
         windows = container.pullOutPattern(4);
         int i;
         for (i = 0; i < windows.size() && windows.get(i).getNum() != 4; i++) {
             if (i + 1 == windows.size()) {
                 container = new CardContainer();
                 windows = container.pullOutPattern(4);
                 i = 0;
             }
         }
         tester = windows.get(i);

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
         CardContainer container = new CardContainer();
         WindowPatternCard tester;
         ArrayList<WindowPatternCard> windows;
         windows = container.pullOutPattern(4);
         int i;
         for (i = 0; i < windows.size() && windows.get(i).getNum() != 5; i++) {
             if (i + 1 == windows.size()) {
                 container = new CardContainer();
                 windows = container.pullOutPattern(4);
                 i = 0;
             }
         }
         tester = windows.get(i);

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
         CardContainer container = new CardContainer();
         WindowPatternCard tester;
         ArrayList<WindowPatternCard> windows;
         windows = container.pullOutPattern(4);
         int i;
         for (i = 0; i < windows.size() && windows.get(i).getNum() != 6; i++) {
             if (i + 1 == windows.size()) {
                 container = new CardContainer();
                 windows = container.pullOutPattern(4);
                 i = 0;
             }
         }
         tester = windows.get(i);

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
         CardContainer container = new CardContainer();
         WindowPatternCard tester;
         ArrayList<WindowPatternCard> windows;
         windows = container.pullOutPattern(4);
         int i;
         for (i = 0; i < windows.size() && windows.get(i).getNum() != 7; i++) {
             if (i + 1 == windows.size()) {
                 container = new CardContainer();
                 windows = container.pullOutPattern(4);
                 i = 0;
             }
         }
         tester = windows.get(i);

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
         CardContainer container = new CardContainer();
         WindowPatternCard tester;
         ArrayList<WindowPatternCard> windows;
         windows = container.pullOutPattern(4);
         int i;
         for (i = 0; i < windows.size() && windows.get(i).getNum() != 8; i++) {
             if (i + 1 == windows.size()) {
                 container = new CardContainer();
                 windows = container.pullOutPattern(4);
                 i = 0;
             }
         }
         tester = windows.get(i);

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
         CardContainer container = new CardContainer();
         WindowPatternCard tester;
         ArrayList<WindowPatternCard> windows;
         windows = container.pullOutPattern(4);
         int i;
         for (i = 0; i < windows.size() && windows.get(i).getNum() != 9; i++) {
             if (i + 1 == windows.size()) {
                 container = new CardContainer();
                 windows = container.pullOutPattern(4);
                 i = 0;
             }
         }
         tester = windows.get(i);

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
         CardContainer container = new CardContainer();
         WindowPatternCard tester;
         ArrayList<WindowPatternCard> windows;
         windows = container.pullOutPattern(4);
         int i;
         for (i = 0; i < windows.size() && windows.get(i).getNum() != 10; i++) {
             if (i + 1 == windows.size()) {
                 container = new CardContainer();
                 windows = container.pullOutPattern(4);
                 i = 0;
             }
         }
         tester = windows.get(i);

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
         CardContainer container = new CardContainer();
         WindowPatternCard tester;
         ArrayList<WindowPatternCard> windows;
         windows = container.pullOutPattern(4);
         int i;
         for (i = 0; i < windows.size() && windows.get(i).getNum() != 11; i++) {
             if (i + 1 == windows.size()) {
                 container = new CardContainer();
                 windows = container.pullOutPattern(4);
                 i = 0;
             }
         }
         tester = windows.get(i);

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
         CardContainer container = new CardContainer();
         WindowPatternCard tester;
         ArrayList<WindowPatternCard> windows;
         windows = container.pullOutPattern(4);
         int i;
         for (i = 0; i < windows.size() && windows.get(i).getNum() != 12; i++) {
             if (i + 1 == windows.size()) {
                 container = new CardContainer();
                 windows = container.pullOutPattern(4);
                 i = 0;
             }
         }
         tester = windows.get(i);

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
         CardContainer container = new CardContainer();
         WindowPatternCard tester;
         ArrayList<WindowPatternCard> windows;
         windows = container.pullOutPattern(4);
         int i;
         for (i = 0; i < windows.size() && windows.get(i).getNum() != 13; i++) {
             if (i + 1 == windows.size()) {
                 container = new CardContainer();
                 windows = container.pullOutPattern(4);
                 i = 0;
             }
         }
         tester = windows.get(i);

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
         CardContainer container = new CardContainer();
         WindowPatternCard tester;
         ArrayList<WindowPatternCard> windows;
         windows = container.pullOutPattern(4);
         int i;
         for (i = 0; i < windows.size() && windows.get(i).getNum() != 14; i++) {
             if (i + 1 == windows.size()) {
                 container = new CardContainer();
                 windows = container.pullOutPattern(4);
                 i = 0;
             }
         }
         tester = windows.get(i);

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
         CardContainer container = new CardContainer();
         WindowPatternCard tester;
         ArrayList<WindowPatternCard> windows;
         windows = container.pullOutPattern(4);
         int i;
         for (i = 0; i < windows.size() && windows.get(i).getNum() != 15; i++) {
             if (i + 1 == windows.size()) {
                 container = new CardContainer();
                 windows = container.pullOutPattern(4);
                 i = 0;
             }
         }
         tester = windows.get(i);

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
         CardContainer container = new CardContainer();
         WindowPatternCard tester;
         ArrayList<WindowPatternCard> windows;
         windows = container.pullOutPattern(4);
         int i;
         for (i = 0; i < windows.size() && windows.get(i).getNum() != 16; i++) {
             if (i + 1 == windows.size()) {
                 container = new CardContainer();
                 windows = container.pullOutPattern(4);
                 i = 0;
             }
         }
         tester = windows.get(i);

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
         CardContainer container = new CardContainer();
         WindowPatternCard tester;
         ArrayList<WindowPatternCard> windows;
         windows = container.pullOutPattern(4);
         int i;
         for (i = 0; i < windows.size() && windows.get(i).getNum() != 17; i++) {
             if (i + 1 == windows.size()) {
                 container = new CardContainer();
                 windows = container.pullOutPattern(4);
                 i = 0;
             }
         }
         tester = windows.get(i);

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
         CardContainer container = new CardContainer();
         WindowPatternCard tester;
         ArrayList<WindowPatternCard> windows;
         windows = container.pullOutPattern(4);
         int i;
         for (i = 0; i < windows.size() && windows.get(i).getNum() != 18; i++) {
             if (i + 1 == windows.size()) {
                 container = new CardContainer();
                 windows = container.pullOutPattern(4);
                 i = 0;
             }
         }
         tester = windows.get(i);

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
         CardContainer container = new CardContainer();
         WindowPatternCard tester;
         ArrayList<WindowPatternCard> windows;
         windows = container.pullOutPattern(4);
         int i;
         for (i = 0; i < windows.size() && windows.get(i).getNum() != 19; i++) {
             if (i + 1 == windows.size()) {
                 container = new CardContainer();
                 windows = container.pullOutPattern(4);
                 i = 0;
             }
         }
         tester = windows.get(i);

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
         CardContainer container = new CardContainer();
         WindowPatternCard tester;
         ArrayList<WindowPatternCard> windows;
         windows = container.pullOutPattern(4);
         int i;
         for (i = 0; i < windows.size() && windows.get(i).getNum() != 20; i++) {
             if (i + 1 == windows.size()) {
                 container = new CardContainer();
                 windows = container.pullOutPattern(4);
                 i = 0;
             }
         }
         tester = windows.get(i);

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
         CardContainer container = new CardContainer();
         WindowPatternCard tester;
         ArrayList<WindowPatternCard> windows;
         windows = container.pullOutPattern(4);
         int i;
         for (i = 0; i < windows.size() && windows.get(i).getNum() != 21; i++) {
             if (i + 1 == windows.size()) {
                 container = new CardContainer();
                 windows = container.pullOutPattern(4);
                 i = 0;
             }
         }
         tester = windows.get(i);

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
         CardContainer container = new CardContainer();
         WindowPatternCard tester;
         ArrayList<WindowPatternCard> windows;
         windows = container.pullOutPattern(4);
         int i;
         for (i = 0; i < windows.size() && windows.get(i).getNum() != 22; i++) {
             if (i + 1 == windows.size()) {
                 container = new CardContainer();
                 windows = container.pullOutPattern(4);
                 i = 0;
             }
         }
         tester = windows.get(i);

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
         CardContainer container = new CardContainer();
         WindowPatternCard tester;
         ArrayList<WindowPatternCard> windows;
         windows = container.pullOutPattern(4);
         int i;
         for (i = 0; i < windows.size() && windows.get(i).getNum() != 23; i++) {
             if (i + 1 == windows.size()) {
                 container = new CardContainer();
                 windows = container.pullOutPattern(4);
                 i = 0;
             }
         }
         tester = windows.get(i);

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
         CardContainer container = new CardContainer();
         WindowPatternCard tester;
         ArrayList<WindowPatternCard> windows;
         windows = container.pullOutPattern(4);
         int i;
         for (i = 0; i < windows.size() && windows.get(i).getNum() != 24; i++) {
             if (i + 1 == windows.size()) {
                 container = new CardContainer();
                 windows = container.pullOutPattern(4);
                 i = 0;
             }
         }
         tester = windows.get(i);

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
 }

