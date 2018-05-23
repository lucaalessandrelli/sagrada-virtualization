package it.polimi.ingsw.modeltest.gamedatatest.gametoolstest;

import it.polimi.ingsw.model.gamedata.Colour;
import it.polimi.ingsw.model.gamedata.gametools.CardContainer;
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

         assertEquals(Colour.YELLOW, tester.getMatr().get(0).get(0).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(0).get(0).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.YELLOW, tester.getMatr().get(0).get(0).getProperty().getColour());
         assertEquals(0, tester.getMatr().get(0).get(0).getProperty().getNumber());

         assertEquals(Colour.BLUE, tester.getMatr().get(0).get(1).getProperty().getColour());
         assertEquals(0, tester.getMatr().get(0).get(1).getProperty().getNumber());

         assertEquals(1, tester.getMatr().get(0).get(4).getProperty().getNumber());
         assertEquals(Colour.WHITE, tester.getMatr().get(0).get(4).getProperty().getColour()); //COLOUR

         assertEquals(Colour.GREEN, tester.getMatr().get(1).get(0).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(1).get(0).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(1).get(2).getProperty().getColour()); //COLOUR
         assertEquals(5, tester.getMatr().get(1).get(2).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(1).get(4).getProperty().getColour()); //COLOUR
         assertEquals(4, tester.getMatr().get(1).get(4).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(2).get(0).getProperty().getColour()); //COLOUR
         assertEquals(3, tester.getMatr().get(2).get(0).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.RED, tester.getMatr().get(2).get(2).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(2).get(2).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.GREEN, tester.getMatr().get(2).get(4).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(2).get(4).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(3).get(0).getProperty().getColour()); //COLOUR
         assertEquals(2, tester.getMatr().get(3).get(0).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.BLUE, tester.getMatr().get(3).get(3).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(3).get(3).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.YELLOW, tester.getMatr().get(3).get(4).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(3).get(4).getProperty().getNumber()); //NUMBER

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

         assertEquals(Colour.WHITE, tester.getMatr().get(0).get(0).getProperty().getColour()); //COLOUR
         assertEquals(4, tester.getMatr().get(0).get(0).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(0).get(2).getProperty().getColour());
         assertEquals(2, tester.getMatr().get(0).get(2).getProperty().getNumber());

         assertEquals(Colour.WHITE, tester.getMatr().get(0).get(3).getProperty().getColour());
         assertEquals(5, tester.getMatr().get(0).get(3).getProperty().getNumber());

         assertEquals(Colour.GREEN, tester.getMatr().get(0).get(4).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(0).get(4).getProperty().getNumber());

         assertEquals(Colour.WHITE, tester.getMatr().get(1).get(2).getProperty().getColour()); //COLOUR
         assertEquals(6, tester.getMatr().get(1).get(2).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.GREEN, tester.getMatr().get(1).get(3).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(1).get(3).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(1).get(4).getProperty().getColour()); //COLOUR
         assertEquals(2, tester.getMatr().get(1).get(4).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(2).get(1).getProperty().getColour()); //COLOUR
         assertEquals(3, tester.getMatr().get(2).get(1).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.GREEN, tester.getMatr().get(2).get(2).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(2).get(2).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(2).get(3).getProperty().getColour()); //COLOUR
         assertEquals(4, tester.getMatr().get(2).get(3).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(3).get(0).getProperty().getColour()); //COLOUR
         assertEquals(5, tester.getMatr().get(3).get(0).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.GREEN, tester.getMatr().get(3).get(1).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(3).get(1).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(3).get(2).getProperty().getColour()); //COLOUR
         assertEquals(1, tester.getMatr().get(3).get(2).getProperty().getNumber()); //NUMBER

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

         assertEquals(Colour.WHITE, tester.getMatr().get(0).get(0).getProperty().getColour()); //COLOUR
         assertEquals(5, tester.getMatr().get(0).get(0).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.GREEN, tester.getMatr().get(0).get(1).getProperty().getColour());
         assertEquals(0, tester.getMatr().get(0).get(1).getProperty().getNumber());

         assertEquals(Colour.BLUE, tester.getMatr().get(0).get(2).getProperty().getColour());
         assertEquals(0, tester.getMatr().get(0).get(2).getProperty().getNumber());

         assertEquals(Colour.PURPLE, tester.getMatr().get(0).get(3).getProperty().getColour());
         assertEquals(0, tester.getMatr().get(0).get(3).getProperty().getNumber());

         assertEquals(Colour.WHITE, tester.getMatr().get(0).get(4).getProperty().getColour()); //COLOUR
         assertEquals(2, tester.getMatr().get(0).get(4).getProperty().getNumber());

         assertEquals(Colour.PURPLE, tester.getMatr().get(1).get(0).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(1).get(0).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.YELLOW, tester.getMatr().get(1).get(4).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(1).get(4).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.YELLOW, tester.getMatr().get(2).get(0).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(2).get(0).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(2).get(2).getProperty().getColour()); //COLOUR
         assertEquals(6, tester.getMatr().get(2).get(2).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.PURPLE, tester.getMatr().get(2).get(4).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(2).get(4).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(3).get(0).getProperty().getColour()); //COLOUR
         assertEquals(1, tester.getMatr().get(3).get(0).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.GREEN, tester.getMatr().get(3).get(3).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(3).get(3).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(3).get(4).getProperty().getColour()); //COLOUR
         assertEquals(4, tester.getMatr().get(3).get(4).getProperty().getNumber()); //NUMBER

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

         assertEquals(Colour.YELLOW, tester.getMatr().get(0).get(0).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(0).get(0).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(0).get(2).getProperty().getColour());
         assertEquals(6, tester.getMatr().get(0).get(2).getProperty().getNumber());

         assertEquals(Colour.WHITE, tester.getMatr().get(1).get(1).getProperty().getColour());
         assertEquals(1, tester.getMatr().get(1).get(1).getProperty().getNumber());

         assertEquals(Colour.WHITE, tester.getMatr().get(1).get(2).getProperty().getColour());
         assertEquals(5, tester.getMatr().get(1).get(2).getProperty().getNumber());

         assertEquals(Colour.WHITE, tester.getMatr().get(1).get(4).getProperty().getColour()); //COLOUR
         assertEquals(2, tester.getMatr().get(1).get(4).getProperty().getNumber());

         assertEquals(Colour.WHITE, tester.getMatr().get(2).get(0).getProperty().getColour()); //COLOUR
         assertEquals(3, tester.getMatr().get(2).get(0).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.YELLOW, tester.getMatr().get(2).get(1).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(2).get(1).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.RED, tester.getMatr().get(2).get(2).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(2).get(2).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.PURPLE, tester.getMatr().get(2).get(3).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(2).get(3).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(3).get(2).getProperty().getColour()); //COLOUR
         assertEquals(4, tester.getMatr().get(3).get(2).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(3).get(3).getProperty().getColour()); //COLOUR
         assertEquals(3, tester.getMatr().get(3).get(3).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.RED, tester.getMatr().get(3).get(4).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(3).get(4).getProperty().getNumber()); //NUMBER
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

         assertEquals(Colour.BLUE, tester.getMatr().get(0).get(1).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(0).get(1).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(0).get(2).getProperty().getColour());
         assertEquals(2, tester.getMatr().get(0).get(2).getProperty().getNumber());

         assertEquals(Colour.YELLOW, tester.getMatr().get(0).get(4).getProperty().getColour());
         assertEquals(0, tester.getMatr().get(0).get(4).getProperty().getNumber());

         assertEquals(Colour.WHITE, tester.getMatr().get(1).get(1).getProperty().getColour());
         assertEquals(4, tester.getMatr().get(1).get(1).getProperty().getNumber());

         assertEquals(Colour.RED, tester.getMatr().get(1).get(3).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(1).get(3).getProperty().getNumber());

         assertEquals(Colour.WHITE, tester.getMatr().get(2).get(2).getProperty().getColour()); //COLOUR
         assertEquals(5, tester.getMatr().get(2).get(2).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.YELLOW, tester.getMatr().get(2).get(3).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(2).get(3).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.GREEN, tester.getMatr().get(3).get(0).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(3).get(0).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(3).get(1).getProperty().getColour()); //COLOUR
         assertEquals(3, tester.getMatr().get(3).get(1).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.PURPLE, tester.getMatr().get(3).get(4).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(3).get(4).getProperty().getNumber()); //NUMBER
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

         assertEquals(Colour.BLUE, tester.getMatr().get(0).get(0).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(0).get(0).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(0).get(1).getProperty().getColour());
         assertEquals(6, tester.getMatr().get(0).get(1).getProperty().getNumber());

         assertEquals(Colour.YELLOW, tester.getMatr().get(0).get(4).getProperty().getColour());
         assertEquals(0, tester.getMatr().get(0).get(4).getProperty().getNumber());

         assertEquals(Colour.WHITE, tester.getMatr().get(1).get(1).getProperty().getColour());
         assertEquals(3, tester.getMatr().get(1).get(1).getProperty().getNumber());

         assertEquals(Colour.BLUE, tester.getMatr().get(1).get(2).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(1).get(2).getProperty().getNumber());

         assertEquals(Colour.WHITE, tester.getMatr().get(2).get(1).getProperty().getColour()); //COLOUR
         assertEquals(5, tester.getMatr().get(2).get(1).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(2).get(2).getProperty().getColour()); //COLOUR
         assertEquals(6, tester.getMatr().get(2).get(2).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(2).get(3).getProperty().getColour()); //COLOUR
         assertEquals(2, tester.getMatr().get(2).get(3).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(3).get(1).getProperty().getColour()); //COLOUR
         assertEquals(4, tester.getMatr().get(3).get(1).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(3).get(3).getProperty().getColour()); //COLOUR
         assertEquals(1, tester.getMatr().get(3).get(3).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.GREEN, tester.getMatr().get(3).get(4).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(3).get(4).getProperty().getNumber()); //NUMBER
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

         assertEquals(Colour.PURPLE, tester.getMatr().get(0).get(0).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(0).get(0).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(0).get(1).getProperty().getColour());
         assertEquals(6, tester.getMatr().get(0).get(1).getProperty().getNumber());

         assertEquals(Colour.WHITE, tester.getMatr().get(0).get(4).getProperty().getColour());
         assertEquals(3, tester.getMatr().get(0).get(4).getProperty().getNumber());

         assertEquals(Colour.WHITE, tester.getMatr().get(1).get(0).getProperty().getColour());
         assertEquals(5, tester.getMatr().get(1).get(0).getProperty().getNumber());

         assertEquals(Colour.PURPLE, tester.getMatr().get(1).get(1).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(1).get(1).getProperty().getNumber());

         assertEquals(Colour.WHITE, tester.getMatr().get(1).get(2).getProperty().getColour()); //COLOUR
         assertEquals(3, tester.getMatr().get(1).get(2).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(2).get(1).getProperty().getColour()); //COLOUR
         assertEquals(2, tester.getMatr().get(2).get(1).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.PURPLE, tester.getMatr().get(2).get(2).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(2).get(2).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(2).get(3).getProperty().getColour()); //COLOUR
         assertEquals(1, tester.getMatr().get(2).get(3).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(3).get(1).getProperty().getColour()); //COLOUR
         assertEquals(1, tester.getMatr().get(3).get(1).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(3).get(2).getProperty().getColour()); //COLOUR
         assertEquals(5, tester.getMatr().get(3).get(2).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.PURPLE, tester.getMatr().get(3).get(3).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(3).get(3).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(3).get(4).getProperty().getColour()); //COLOUR
         assertEquals(4, tester.getMatr().get(3).get(4).getProperty().getNumber()); //NUMBER

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

         System.out.println("Name: " + tester.getName());
         assertEquals("Symphony of Light", tester.getName());

         assertEquals(Colour.WHITE, tester.getMatr().get(0).get(0).getProperty().getColour()); //COLOUR
         assertEquals(2, tester.getMatr().get(0).get(0).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(0).get(2).getProperty().getColour());
         assertEquals(5, tester.getMatr().get(0).get(2).getProperty().getNumber());

         assertEquals(Colour.WHITE, tester.getMatr().get(0).get(4).getProperty().getColour());
         assertEquals(1, tester.getMatr().get(0).get(4).getProperty().getNumber());

         assertEquals(Colour.YELLOW, tester.getMatr().get(1).get(0).getProperty().getColour());
         assertEquals(0, tester.getMatr().get(1).get(0).getProperty().getNumber());

         assertEquals(Colour.WHITE, tester.getMatr().get(1).get(1).getProperty().getColour()); //COLOUR
         assertEquals(6, tester.getMatr().get(1).get(1).getProperty().getNumber());

         assertEquals(Colour.PURPLE, tester.getMatr().get(1).get(2).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(1).get(2).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(1).get(3).getProperty().getColour()); //COLOUR
         assertEquals(2, tester.getMatr().get(1).get(3).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.RED, tester.getMatr().get(1).get(4).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(1).get(4).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.BLUE, tester.getMatr().get(2).get(1).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(2).get(1).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(2).get(2).getProperty().getColour()); //COLOUR
         assertEquals(4, tester.getMatr().get(2).get(2).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.GREEN, tester.getMatr().get(2).get(3).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(2).get(3).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(3).get(1).getProperty().getColour()); //COLOUR
         assertEquals(3, tester.getMatr().get(3).get(1).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(3).get(3).getProperty().getColour()); //COLOUR
         assertEquals(5, tester.getMatr().get(3).get(3).getProperty().getNumber()); //NUMBER

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

         assertEquals(Colour.RED, tester.getMatr().get(0).get(0).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(0).get(0).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.BLUE, tester.getMatr().get(0).get(2).getProperty().getColour());
         assertEquals(0, tester.getMatr().get(0).get(2).getProperty().getNumber());

         assertEquals(Colour.YELLOW, tester.getMatr().get(0).get(4).getProperty().getColour());
         assertEquals(0, tester.getMatr().get(0).get(4).getProperty().getNumber());

         assertEquals(Colour.WHITE, tester.getMatr().get(1).get(0).getProperty().getColour());
         assertEquals(4, tester.getMatr().get(1).get(0).getProperty().getNumber());

         assertEquals(Colour.PURPLE, tester.getMatr().get(1).get(1).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(1).get(1).getProperty().getNumber());

         assertEquals(Colour.WHITE, tester.getMatr().get(1).get(2).getProperty().getColour()); //COLOUR
         assertEquals(3, tester.getMatr().get(1).get(2).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.GREEN, tester.getMatr().get(1).get(3).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(1).get(3).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(1).get(4).getProperty().getColour()); //COLOUR
         assertEquals(2, tester.getMatr().get(1).get(4).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(2).get(1).getProperty().getColour()); //COLOUR
         assertEquals(1, tester.getMatr().get(2).get(1).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(2).get(3).getProperty().getColour()); //COLOUR
         assertEquals(5, tester.getMatr().get(2).get(3).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(3).get(2).getProperty().getColour()); //COLOUR
         assertEquals(6, tester.getMatr().get(3).get(2).getProperty().getNumber()); //NUMBER
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

         assertEquals(Colour.WHITE, tester.getMatr().get(0).get(0).getProperty().getColour()); //COLOUR
         assertEquals(1, tester.getMatr().get(0).get(0).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.RED, tester.getMatr().get(0).get(1).getProperty().getColour());
         assertEquals(0, tester.getMatr().get(0).get(1).getProperty().getNumber());

         assertEquals(Colour.WHITE, tester.getMatr().get(0).get(2).getProperty().getColour());
         assertEquals(3, tester.getMatr().get(0).get(2).getProperty().getNumber());

         assertEquals(Colour.WHITE, tester.getMatr().get(0).get(4).getProperty().getColour());
         assertEquals(6, tester.getMatr().get(0).get(4).getProperty().getNumber());

         assertEquals(Colour.WHITE, tester.getMatr().get(1).get(0).getProperty().getColour()); //COLOUR
         assertEquals(5, tester.getMatr().get(1).get(0).getProperty().getNumber());

         assertEquals(Colour.WHITE, tester.getMatr().get(1).get(1).getProperty().getColour()); //COLOUR
         assertEquals(4, tester.getMatr().get(1).get(1).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.RED, tester.getMatr().get(1).get(2).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(1).get(2).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(1).get(3).getProperty().getColour()); //COLOUR
         assertEquals(2, tester.getMatr().get(1).get(3).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(2).get(2).getProperty().getColour()); //COLOUR
         assertEquals(5, tester.getMatr().get(2).get(2).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.RED, tester.getMatr().get(2).get(3).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(2).get(3).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(2).get(4).getProperty().getColour()); //COLOUR
         assertEquals(1, tester.getMatr().get(2).get(4).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(3).get(3).getProperty().getColour()); //COLOUR
         assertEquals(3, tester.getMatr().get(3).get(3).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.RED, tester.getMatr().get(3).get(4).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(3).get(4).getProperty().getNumber()); //NUMBER

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

         assertEquals(Colour.WHITE, tester.getMatr().get(0).get(0).getProperty().getColour()); //COLOUR
         assertEquals(6, tester.getMatr().get(0).get(0).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.PURPLE, tester.getMatr().get(0).get(1).getProperty().getColour());
         assertEquals(0, tester.getMatr().get(0).get(1).getProperty().getNumber());

         assertEquals(Colour.WHITE, tester.getMatr().get(0).get(4).getProperty().getColour());
         assertEquals(5, tester.getMatr().get(0).get(4).getProperty().getNumber());

         assertEquals(Colour.WHITE, tester.getMatr().get(1).get(0).getProperty().getColour());
         assertEquals(5, tester.getMatr().get(1).get(0).getProperty().getNumber());

         assertEquals(Colour.PURPLE, tester.getMatr().get(1).get(2).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(1).get(2).getProperty().getNumber());

         assertEquals(Colour.RED, tester.getMatr().get(2).get(0).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(2).get(0).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(2).get(1).getProperty().getColour()); //COLOUR
         assertEquals(6, tester.getMatr().get(2).get(1).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.PURPLE, tester.getMatr().get(2).get(3).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(2).get(3).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.YELLOW, tester.getMatr().get(3).get(0).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(3).get(0).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.RED, tester.getMatr().get(3).get(1).getProperty().getColour()); //COLOUR
         assertEquals(0, tester.getMatr().get(3).get(1).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(3).get(2).getProperty().getColour()); //COLOUR
         assertEquals(5, tester.getMatr().get(3).get(2).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(3).get(3).getProperty().getColour()); //COLOUR
         assertEquals(4, tester.getMatr().get(3).get(3).getProperty().getNumber()); //NUMBER

         assertEquals(Colour.WHITE, tester.getMatr().get(3).get(4).getProperty().getColour()); //COLOUR
         assertEquals(3, tester.getMatr().get(3).get(4).getProperty().getNumber()); //NUMBER

     }

 }

