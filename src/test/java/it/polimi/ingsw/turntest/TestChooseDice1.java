package it.polimi.ingsw.turntest;


import it.polimi.ingsw.Match;
import it.polimi.ingsw.controller.Manager;
import it.polimi.ingsw.model.gamedata.*;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.gametools.WindowPatternCard;
import it.polimi.ingsw.model.gamelogic.Round;
import it.polimi.ingsw.turn.StartTurn;
import it.polimi.ingsw.turn.Turn;
import it.polimi.ingsw.turn.moveexceptions.WrongMoveException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static it.polimi.ingsw.turntest.TurnTest.lastName;
import static org.junit.jupiter.api.Assertions.*;

public class TestChooseDice1 {

    @Test
    void TestFirstTurn(){
        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player("one");
        Player p2 = new Player("two");
        Player p3 = new Player("three");
        Player p4 = new Player("four");
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);



        Table table = new Table(players);
        table.initialize();

        Match match = new Match(players,new Manager(),0);

        Round round = new Round(players, 1, table,match);

        ArrayList<WindowPatternCard> windows = new ArrayList<>();
        WindowPatternCard windowPatternCard = new WindowPatternCard();
        windows.add(windowPatternCard);
        p1.setMyWindow(windows.get(0));

        table.getDiceBag().setNumPlayers(4);
        table.getDraftPool().addNewDices(table.getDiceFromBag());

        PublicObjects publicObjects = new PublicObjects();
        publicObjects.setDraftPool(table.getDraftPool());
        publicObjects.setObjectiveCards(table.getObjCard());
        publicObjects.setToolCards(table.getToolCards());

        p1.setPublicObjects(publicObjects);

        Turn tester = new Turn(p1, round, 1, true, table);

        tester.startTurn();

        Dice d = table.getDraftPool().chooseDice(4);

        Pos pos = new Pos(4,5);

        try {
            tester.receiveMove(d,pos);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        pos.setX(1);
        pos.setY(4);
        try {
            tester.receiveMove(pos);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }


        assertEquals("PositionDice1",lastName(tester.getState().getClass().toString(),14));
    }

    @Test
    void TestNotFirstRound(){
        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player("one");
        Player p2 = new Player("two");
        Player p3 = new Player("three");
        Player p4 = new Player("four");
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        Table table = new Table(players);

        Match match = new Match(players,new Manager(),0);

        Round round = new Round(players, 2, table,match);

        ArrayList<WindowPatternCard> windows = new ArrayList<>();
        WindowPatternCard windowPatternCard = new WindowPatternCard();
        windows.add(windowPatternCard);
        p1.setMyWindow(windows.get(0));

        table.getDiceBag().setNumPlayers(4);
        table.getDraftPool().addNewDices(table.getDiceFromBag());

        PublicObjects publicObjects = new PublicObjects();
        publicObjects.setDraftPool(table.getDraftPool());
        publicObjects.setObjectiveCards(table.getObjCard());
        publicObjects.setToolCards(table.getToolCards());

        p1.setPublicObjects(publicObjects);

        Turn tester = new Turn(p1, round, 1, true, table);

        tester.startTurn();

        Dice d = table.getDraftPool().chooseDice(4);

        Pos pos = new Pos(4,5);

        try {
            tester.receiveMove(d,pos);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        pos.setX(1);
        pos.setY(4);
        try {
            tester.receiveMove(pos);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("PositionDice1",lastName(tester.getState().getClass().toString(),14));
    }

    @Test
    void TestNotOneWay(){

        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player("one");
        Player p2 = new Player("two");
        Player p3 = new Player("three");
        Player p4 = new Player("four");
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        Table table = new Table(players);

        ArrayList<WindowPatternCard> windows = new ArrayList<>();
        WindowPatternCard windowPatternCard = new WindowPatternCard();
        windows.add(windowPatternCard);
        p1.setMyWindow(windows.get(0));

        table.getDiceBag().setNumPlayers(4);
        table.getDraftPool().addNewDices(table.getDiceFromBag());

        PublicObjects publicObjects = new PublicObjects();
        publicObjects.setDraftPool(table.getDraftPool());
        publicObjects.setObjectiveCards(table.getObjCard());
        publicObjects.setToolCards(table.getToolCards());

        p1.setPublicObjects(publicObjects);

        Match match = new Match(players,new Manager(),0);

        Round round = new Round(players, 1, table,match);

        Turn tester = new Turn(p1, round, 1, false, table);

        StartTurn startTurn = new StartTurn(tester);
        tester.setState(startTurn);

        Dice d = new Dice(Colour.GREEN);
        d.setNumber(2);
        p1.getWindowPatternCard().placeDice(d,0,0);


        Dice d1 = new Dice(Colour.BLUE);
        d1.setNumber(3);
        table.getDraftPool().setDice(d1,4);

        Pos pos = new Pos(4,0);

        try {
            tester.receiveMove(d1,pos);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        pos = new Pos(1,0);

        try {
            tester.receiveMove(pos);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("PositionDice1",lastName(tester.getState().getClass().toString(),14));
    }
}
