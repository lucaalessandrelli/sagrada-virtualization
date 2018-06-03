package it.polimi.ingsw.viewtest;

import it.polimi.ingsw.view.Deparser;
import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.Table;
import it.polimi.ingsw.model.gamedata.gametools.WindowPatternCard;
import it.polimi.ingsw.controller.VirtualViewParser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VirtualViewParserTest {

    @Test
    void TestParsingRestrictions(){
        ArrayList<WindowPatternCard> windowPatternCards = new ArrayList<>();
        WindowPatternCard windowPatternCard = new WindowPatternCard();
        WindowPatternCard windowPatternCard1 = new WindowPatternCard();
        WindowPatternCard windowPatternCard2 = new WindowPatternCard();
        WindowPatternCard windowPatternCard3 = new WindowPatternCard();

        Player p1 = new Player("Luca");

        Player p2 = new Player("Giovanni");

        Player p3 = new Player("Andrea");

        Player p4 = new Player("Vincenzo");

        ArrayList<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        Table table = new Table(players);


        windowPatternCards.add(windowPatternCard);
        p1.chooseWindow(windowPatternCards);
        windowPatternCards.remove(windowPatternCard);


        windowPatternCards.add(windowPatternCard1);
        p2.chooseWindow(windowPatternCards);
        windowPatternCards.remove(windowPatternCard1);


        windowPatternCards.add(windowPatternCard2);
        p3.chooseWindow(windowPatternCards);
        windowPatternCards.remove(windowPatternCard2);


        windowPatternCards.add(windowPatternCard3);
        p4.chooseWindow(windowPatternCards);
        windowPatternCards.remove(windowPatternCard3);

        table.setPublicObjects();

        VirtualViewParser tester = new VirtualViewParser(p1);

        String parsed = tester.startParsing();

        System.out.println(parsed);

        int k = 0;
        Pos pos = new Pos();
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 5; j++, k = k + 5){
                pos.setX(i);
                pos.setY(j);

                assertEquals(p1.getWindowPatternCard().getCell(pos).getProperty().getColour().toString().charAt(0), (parsed.charAt(18 + p1.getUsername().length() + k)));

                assertEquals(p1.getWindowPatternCard().getCell(pos).getProperty().getNumber(),(Character.getNumericValue(parsed.charAt(18 + p1.getUsername().length() + (k+1)))));
            }
        }
    }

    @Test
    void TestParsingDraftPool(){
        ArrayList<WindowPatternCard> windowPatternCards = new ArrayList<>();
        WindowPatternCard windowPatternCard = new WindowPatternCard();
        WindowPatternCard windowPatternCard1 = new WindowPatternCard();
        WindowPatternCard windowPatternCard2 = new WindowPatternCard();
        WindowPatternCard windowPatternCard3 = new WindowPatternCard();

        Player p1 = new Player("Luca");
        windowPatternCards.add(windowPatternCard);
        p1.chooseWindow(windowPatternCards);
        windowPatternCards.remove(windowPatternCard);

        Player p2 = new Player("Giovanni");
        windowPatternCards.add(windowPatternCard);
        p2.chooseWindow(windowPatternCards);
        windowPatternCards.remove(windowPatternCard1);

        Player p3 = new Player("Andrea");
        windowPatternCards.add(windowPatternCard);
        p3.chooseWindow(windowPatternCards);
        windowPatternCards.remove(windowPatternCard2);

        Player p4 = new Player("Vincenzo");
        windowPatternCards.add(windowPatternCard);
        p4.chooseWindow(windowPatternCards);
        windowPatternCards.remove(windowPatternCard3);

        ArrayList<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        Table table = new Table(players);
        table.setPublicObjects();


        p1.getWindowPatternCard().placeDice(table.getDraftPool().chooseDice(0),0,0);
        p1.getWindowPatternCard().placeDice(table.getDraftPool().chooseDice(1),0,2);
        p1.getWindowPatternCard().placeDice(table.getDraftPool().chooseDice(2),1,1);
        p1.getWindowPatternCard().placeDice(table.getDraftPool().chooseDice(3),2,3);
        p1.getWindowPatternCard().placeDice(table.getDraftPool().chooseDice(4),3,2);

        VirtualViewParser tester = new VirtualViewParser(p1);

        String parsed = tester.parseDraftPool();

        System.out.println(parsed);

        for(int i = 0, k = 0; i < table.getDraftPool().getNumOfDices(); i++, k = k+3){
            assertEquals(table.getDraftPool().chooseDice(i).getColour().toString().charAt(0), (parsed.charAt(15 + p1.getUsername().length() + k)));

            assertEquals(table.getDraftPool().chooseDice(i).getNumber(), (Character.getNumericValue(parsed.charAt(15 + p1.getUsername().length() + (k+1)))));
        }

    }

    @Test
    void TestParsingWindowDices(){

        ArrayList<WindowPatternCard> windowPatternCards = new ArrayList<>();
        WindowPatternCard windowPatternCard = new WindowPatternCard();
        WindowPatternCard windowPatternCard1 = new WindowPatternCard();
        WindowPatternCard windowPatternCard2 = new WindowPatternCard();
        WindowPatternCard windowPatternCard3 = new WindowPatternCard();

        Player p1 = new Player("Luca");

        Player p2 = new Player("Giovanni");

        Player p3 = new Player("Andrea");

        Player p4 = new Player("Vincenzo");

        ArrayList<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        Table table = new Table(players);


        windowPatternCards.add(windowPatternCard);
        p1.chooseWindow(windowPatternCards);
        windowPatternCards.remove(windowPatternCard);
        windowPatternCards.add(windowPatternCard1);
        p2.chooseWindow(windowPatternCards);
        windowPatternCards.remove(windowPatternCard1);
        windowPatternCards.add(windowPatternCard2);
        p3.chooseWindow(windowPatternCards);
        windowPatternCards.remove(windowPatternCard2);
        windowPatternCards.add(windowPatternCard3);
        p4.chooseWindow(windowPatternCards);
        windowPatternCards.remove(windowPatternCard3);

        table.setPublicObjects();

        p1.getWindowPatternCard().placeDice(table.getDraftPool().chooseDice(0),0,0);
        p1.getWindowPatternCard().placeDice(table.getDraftPool().chooseDice(1),0,2);
        p1.getWindowPatternCard().placeDice(table.getDraftPool().chooseDice(2),1,1);
        p1.getWindowPatternCard().placeDice(table.getDraftPool().chooseDice(3),2,3);
        p1.getWindowPatternCard().placeDice(table.getDraftPool().chooseDice(4),3,2);



        p2.getWindowPatternCard().placeDice(table.getDraftPool().chooseDice(5),0,0);
        p2.getWindowPatternCard().placeDice(table.getDraftPool().chooseDice(6),0,2);
        p2.getWindowPatternCard().placeDice(table.getDraftPool().chooseDice(7),1,1);
        p2.getWindowPatternCard().placeDice(table.getDraftPool().chooseDice(8),2,3);

        VirtualViewParser tester = new VirtualViewParser(p1);

        String parsed = tester.startParsing();

        for(int i = 0, k = 0; i < 5 ; i++, k = k+5){

            assertEquals(table.getDraftPool().chooseDice(i).getColour().toString().charAt(0), (parsed.charAt(134 + p1.getUsername().length() +k)));

            assertEquals(table.getDraftPool().chooseDice(i).getNumber(), (Character.getNumericValue(parsed.charAt(134 + p1.getUsername().length() + (k+1)))));
        }

    }

    @Test
    public void TestHowParse(){

        ArrayList<WindowPatternCard> windowPatternCards = new ArrayList<>();
        WindowPatternCard windowPatternCard = new WindowPatternCard();
        WindowPatternCard windowPatternCard1 = new WindowPatternCard();
        WindowPatternCard windowPatternCard2 = new WindowPatternCard();
        WindowPatternCard windowPatternCard3 = new WindowPatternCard();

        Player p1 = new Player("Luca");

        Player p2 = new Player("Giovanni");

        Player p3 = new Player("Andrea");

        Player p4 = new Player("Vincenzo");

        ArrayList<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        Table table = new Table(players);


        windowPatternCards.add(windowPatternCard);
        p1.chooseWindow(windowPatternCards);
        windowPatternCards.remove(windowPatternCard);


        windowPatternCards.add(windowPatternCard1);
        p2.chooseWindow(windowPatternCards);
        windowPatternCards.remove(windowPatternCard1);


        windowPatternCards.add(windowPatternCard2);
        p3.chooseWindow(windowPatternCards);
        windowPatternCards.remove(windowPatternCard2);


        windowPatternCards.add(windowPatternCard3);
        p4.chooseWindow(windowPatternCards);
        windowPatternCards.remove(windowPatternCard3);

        table.setPublicObjects();

        VirtualViewParser tester = new VirtualViewParser(p1);

        String parsed = tester.startParsing();

        System.out.println(parsed);

        /*Deparser deparser = new Deparser();
        System.out.println(deparser.DivideinStrings(tester.startParsing()));
        List<String> strings = deparser.DivideinStrings(tester.startParsing());
        for (String string: strings){
            List <String> deparsed = deparser.Deparse(string);
            for (String x: deparsed){
                System.out.println(x);
            }
        }*/
    }
}
