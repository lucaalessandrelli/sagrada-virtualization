package it.polimi.ingsw.viewtest;

import it.polimi.ingsw.model.gamedata.Colour;
import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.Table;
import it.polimi.ingsw.model.gamedata.gametools.CardContainer;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.gametools.WindowPatternCard;
import it.polimi.ingsw.controller.VirtualViewParser;
import it.polimi.ingsw.view.cli.Printer;
import org.fusesource.jansi.Ansi;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;
import static java.lang.System.runFinalization;
import static org.fusesource.jansi.Ansi.ansi;
import static org.junit.jupiter.api.Assertions.*;

public class VirtualViewParserTest {
    private static final String BLOCK = "⚀";

    @Test
    public void TestParsingPlayers(){

        Player p1 = new Player("Luca");
        Player p2 = new Player("Giovanni");
        Player p3 = new Player("Andrea");
        Player p4 = new Player("Vincenzo");


        CardContainer container = new CardContainer();
        List<WindowPatternCard> windowPatternCards = container.pullOutPattern(4);
        ArrayList<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        p1.setMyWindow(windowPatternCards.get(0));
        p2.setMyWindow(windowPatternCards.get(1));
        p3.setMyWindow(windowPatternCards.get(2));
        p4.setMyWindow(windowPatternCards.get(3));


        Table table = new Table(players);
        table.initialize();
        table.setPublicObjects();


        VirtualViewParser tester = new VirtualViewParser(p1);

        String parsed = tester.startParsing();

        parsed = parsed.substring(parsed.indexOf("gamePlayers ")+12,parsed.length()-1);
        List<String> pv = Arrays.asList(parsed.split(";"));

        List<String> splitted = Arrays.asList(pv.get(0).split(","));

        assertEquals(4,splitted.size());
        assertTrue(!splitted.get(0).equals(splitted.get(1)) && !splitted.get(0).equals(splitted.get(2)) &&
                !splitted.get(0).equals(splitted.get(3)));
        assertTrue(!splitted.get(1).equals(splitted.get(2)) && !splitted.get(1).equals(splitted.get(3)));
        assertTrue(!splitted.get(2).equals(splitted.get(3)));

        for (String name: splitted) {
            assertTrue(name.equals(p1.getUsername()) || name.equals(p1.getPublicObjects().getPlayers().get(0).getUsername())
                    || name.equals(p1.getPublicObjects().getPlayers().get(1).getUsername())
                    || name.equals(p1.getPublicObjects().getPlayers().get(2).getUsername()));
        }

    }

    @Test
    public void TestParsingRestrictions(){

        Player p1 = new Player("Luca");

        Player p2 = new Player("Giovanni");

        Player p3 = new Player("Andrea");

        Player p4 = new Player("Vincenzo");

        CardContainer container = new CardContainer();
        List<WindowPatternCard> windowPatternCards = container.pullOutPattern(4);
        ArrayList<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        p1.setMyWindow(windowPatternCards.get(0));
        p2.setMyWindow(windowPatternCards.get(1));
        p3.setMyWindow(windowPatternCards.get(2));
        p4.setMyWindow(windowPatternCards.get(3));

        Table table = new Table(players);

        table.initialize();

        table.setPublicObjects();

        VirtualViewParser tester = new VirtualViewParser(p1);

        String parsed = tester.startParsing();

        parsed = parsed.substring(parsed.indexOf("restrictions Luca,")+18,parsed.length());

        int k = 0;
        Pos pos = new Pos();
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 5; j++, k = k + 5){
                pos.setX(i);
                pos.setY(j);

                assertEquals(p1.getWindowPatternCard().getCell(pos).getProperty().getColour().toString().charAt(0), (parsed.charAt(k)));

                assertEquals(p1.getWindowPatternCard().getCell(pos).getProperty().getNumber(),(Character.getNumericValue(parsed.charAt((k+1)))));
            }
        }
    }

    @Test
    void TestParsingDraftPool(){

        Player p1 = new Player("Luca");
        Player p2 = new Player("Giovanni");
        Player p3 = new Player("Andrea");
        Player p4 = new Player("Vincenzo");

        CardContainer container = new CardContainer();
        List<WindowPatternCard> windowPatternCards = container.pullOutPattern(4);
        ArrayList<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        p1.setMyWindow(windowPatternCards.get(0));
        p2.setMyWindow(windowPatternCards.get(1));
        p3.setMyWindow(windowPatternCards.get(2));
        p4.setMyWindow(windowPatternCards.get(3));

        Table table = new Table(players);
        table.initialize();
        table.setPublicObjects();


        p1.getWindowPatternCard().placeDice(table.getDraftPool().chooseDice(0),0,0);
        p1.getWindowPatternCard().placeDice(table.getDraftPool().chooseDice(1),0,2);
        p1.getWindowPatternCard().placeDice(table.getDraftPool().chooseDice(2),1,1);
        p1.getWindowPatternCard().placeDice(table.getDraftPool().chooseDice(3),2,3);
        p1.getWindowPatternCard().placeDice(table.getDraftPool().chooseDice(4),3,2);

        VirtualViewParser tester = new VirtualViewParser(p1);

        String parsed = tester.parseDraftPool();

        parsed = parsed.substring(parsed.indexOf("draftpool ")+10,parsed.length());

        for(int i = 0, k = 0; i < table.getDraftPool().getNumOfDices(); i++, k = k+3){
            assertEquals(table.getDraftPool().chooseDice(i).getColour().toString().charAt(0), (parsed.charAt(k)));

            assertEquals(table.getDraftPool().chooseDice(i).getNumber(), (Character.getNumericValue(parsed.charAt((k+1)))));
        }

    }

    @Test
    void TestParsingWindowDices(){

        Player p1 = new Player("Luca");

        Player p2 = new Player("Giovanni");

        Player p3 = new Player("Andrea");

        Player p4 = new Player("Vincenzo");

        CardContainer container = new CardContainer();
        List<WindowPatternCard> windowPatternCards = container.pullOutPattern(4);
        ArrayList<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        p1.setMyWindow(windowPatternCards.get(0));
        p2.setMyWindow(windowPatternCards.get(1));
        p3.setMyWindow(windowPatternCards.get(2));
        p4.setMyWindow(windowPatternCards.get(3));

        Table table = new Table(players);

        table.initialize();

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

        parsed = parsed.substring(parsed.indexOf("dices Luca,")+11,parsed.length());

        for(int i = 0, k = 0; i < 5 ; i++, k = k+5){

            assertEquals(table.getDraftPool().chooseDice(i).getColour().toString().charAt(0), (parsed.charAt(k)));

            assertEquals(table.getDraftPool().chooseDice(i).getNumber(), (Character.getNumericValue(parsed.charAt((k+1)))));
        }

    }

    @Test
    public void testHowParse(){

        Player p1 = new Player("Luca");

        Player p2 = new Player("Giovanni");

        Player p3 = new Player("Andrea");

        Player p4 = new Player("Vincenzo");

        CardContainer container = new CardContainer();
        List<WindowPatternCard> windowPatternCards = container.pullOutPattern(4);
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<String> namePlayers = new ArrayList<>();
        namePlayers.add(p1.getUsername());
        namePlayers.add(p2.getUsername());
        namePlayers.add(p3.getUsername());
        namePlayers.add(p4.getUsername());

        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        p1.setMyWindow(windowPatternCards.get(0));
        p2.setMyWindow(windowPatternCards.get(1));
        p3.setMyWindow(windowPatternCards.get(2));
        p4.setMyWindow(windowPatternCards.get(3));


        Dice d1 = new Dice(Colour.GREEN);
        d1.setNumber(5);
        Dice d2 = new Dice(Colour.YELLOW);
        d2.setNumber(4);
        Dice d3 = new Dice(Colour.PURPLE);
        d3.setNumber(4);

        p1.getWindowPatternCard().placeDice(d1,2,3);
        p1.getWindowPatternCard().placeDice(d2,1,3);
        p1.getWindowPatternCard().placeDice(d3,2,4);


        Table table = new Table(players);

        table.getRoundTrack().setDiceOnRoundTrack(1,table.getDiceBag().pullOut(4));
        table.getRoundTrack().setDiceOnRoundTrack(2,table.getDiceBag().pullOut(6));


        table.initialize();

        table.setPublicObjects();

        VirtualViewParser tester = new VirtualViewParser(p1);

        String parsed = tester.startParsing();

        Printer printer = new Printer();

        /*System.out.println(parsed);

        List<String> x = Arrays.asList(parsed.split(";"));
        for (String s: x){
            List<String> k = Arrays.asList(s.split(","));
            for (String z: k){
                //System.out.println(z);
            }
        }

        String lu = "restrictions Luca,";
        String tmp = parsed.substring(parsed.indexOf(lu) + lu.length(), parsed.length());
        tmp = tmp.substring(0,tmp.indexOf(";"));
        //System.out.println(tmp);*/

        String turnState = "Is the turn of:Andrea";

        printer.printMatch(parsed,"10",namePlayers,turnState);

        //printer.welcome();
        //printer.timer("30");
        //printer.printPatternCard(Arrays.asList(tmp.split(",")));
        //printer.startTimer("30");
        //container = new CardContainer();
        //String k = tester.extractedWindows(container.pullOutPattern(1));
        //k = k.replace("choseWindow ", "");
        //printer.choosePatternCard(k);

        //lu = "dices Luca,";
        //String tmp2 = parsed.substring(parsed.indexOf(lu)+lu.length(),parsed.length());
        //tmp2 = tmp2.substring(0,tmp2.indexOf(";"));

        //
        //printer.printCoordinates(0,3,0,4);
        //printer.printPatternCard(Arrays.asList(tmp.split(",")));
        //printer.printPlacedDices(Arrays.asList(tmp2.split(",")));

        /*ArrayList<String> playersconnected = new ArrayList<>();
        playersconnected.add(p1.getUsername());
        playersconnected.add(p2.getUsername());
        playersconnected.add(p3.getUsername());
        playersconnected.add(p4.getUsername());

        printer.printWaitingRoom("30",playersconnected,"The match will start in..");
        out.println();
        out.println();
        out.println();
        out.println();
        out.println();
        out.println();
        out.println();*/

        /*container = new CardContainer();
        String z = tester.extractedWindows(container.pullOutPattern(1));
        z = z.replace("choseWindow ", "");
        List<String> space = Arrays.asList(z.split(";"));
        System.out.println("space " +space);
        for(int k = 0; k < space.size(); k++){
            List <String> list = Arrays.asList(space.get(k).split(" "));
            printer.printchoosePatternCard(list.get(0),list.get(1));
            List<String> restr = Arrays.asList(list.get(2).split(","));
            printer.printPatternCard(restr);
        }*/
        //printer.printtimer("30","Remaining time:");
        //printer.rePrintChoseCard("7","Remaining time:");

        /*String testForChoseCard = "14 3 R000,W001,B002,W003,Y004,W410,P011,W312,G013,W214,W020,W121,W022,W523,W024,W030,W031,W632,W033,W034,;" +
        /*String testForChoseCard = "14 3 R000,W001,B002,W003,Y004,W410,P011,W312,G013,W214,W020,W121,W022,W523,W024,W030,W031,W632,W033,W034,;" +
                "2 5 W200,W001,W502,W003,W104,Y010,W611,P012,W213,R014,W020,B021,W422,G023,W024,W030,W331,W032,W533,W034,;";

        //printer.printChooseCardRoom(testForChoseCard,"20");

        List<String> names = new ArrayList<>();

        for (Player p: players){
            names.add(p.getUsername());
        }


        String turnstate = "Is the turn of:" + p1.getUsername();

        printer.printMatch(parsed,"30",names,turnstate);

        String score = "Luca 19,Andrea 4,Giovanni 16,Vincenzo 30";

        //printer.printScore(score);

        String score2 = "Luca 19,Andrea 4,Giovanni 16,Vincenzo 19";

        //printer.printScore(score2);

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.print(parsed);*/

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
