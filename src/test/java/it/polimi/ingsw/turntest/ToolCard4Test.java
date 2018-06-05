package it.polimi.ingsw.turntest;

import it.polimi.ingsw.model.gamedata.*;
import it.polimi.ingsw.model.gamedata.gametools.CardContainer;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.gametools.ToolCard;
import it.polimi.ingsw.model.gamedata.gametools.WindowPatternCard;
import it.polimi.ingsw.model.gamelogic.Round;
import it.polimi.ingsw.turn.Turn;
import it.polimi.ingsw.turn.moveexceptions.WrongMoveException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static it.polimi.ingsw.turntest.TurnTest.lastName;
import static org.junit.jupiter.api.Assertions.*;

public class ToolCard4Test {

    @Test
    void TestingAllowedMoves() {
        ToolCard tester = new ToolCard();
        CardContainer container = new CardContainer();
        ArrayList<ToolCard> toolCardArrayList = container.pullOutTools();

        while (toolCardArrayList.get(0).getID() != 4 && toolCardArrayList.get(1).getID() != 4 && toolCardArrayList.get(2).getID() != 4) {
            container = new CardContainer();
            toolCardArrayList = container.pullOutTools();
        }
        if (toolCardArrayList.get(0).getID() == 4)
            tester = toolCardArrayList.get(0);

        if (toolCardArrayList.get(1).getID() == 4)
            tester = toolCardArrayList.get(1);

        if (toolCardArrayList.get(2).getID() == 4)
            tester = toolCardArrayList.get(2);

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
        table.setPublicObjects();

        while (players.get(0).getToolCards().get(0).getID() != 4 && players.get(0).getToolCards().get(1).getID() != 4 && players.get(0).getToolCards().get(2).getID() != 4) {
            table = new Table(players);
            table.setPublicObjects();
        }

        Round round = new Round(players, 1, table);

        ArrayList<WindowPatternCard> windows = new ArrayList<>();
        WindowPatternCard windowPatternCard = new WindowPatternCard();
        windows.add(windowPatternCard);
        p1.chooseWindow(windows);

        table.getDiceBag().setNumPlayers(4);
        table.getDraftPool().addNewDices(table.getDiceFromBag());

        PublicObjects publicObjects = new PublicObjects();
        publicObjects.setDraftPool(table.getDraftPool());
        publicObjects.setObjectiveCards(table.getObjCard());
        publicObjects.setToolCards(table.getToolCards());

        p1.setPublicObjects(publicObjects);

        Turn turn = new Turn(p1, round, 1, true, table);

        Dice d1 = new Dice(Colour.GREEN);
        d1.setNumber(1);
        Dice d2 = new Dice(Colour.RED);
        d2.setNumber(2);
        Dice d3 = new Dice(Colour.YELLOW);
        d3.setNumber(3);
        Dice d4 = new Dice(Colour.RED);
        d4.setNumber(4);
        Dice d5 = new Dice(Colour.PURPLE);
        d5.setNumber(5);
        Dice d6 = new Dice(Colour.BLUE);
        d6.setNumber(6);
        Dice d7 = new Dice(Colour.PURPLE);
        d7.setNumber(1);
        Dice d8 = new Dice(Colour.YELLOW);
        d8.setNumber(2);
        Dice d9 = new Dice(Colour.YELLOW);
        d9.setNumber(3);



        p1.getWindowPatternCard().placeDice(d9, 0, 0);
        p1.getWindowPatternCard().placeDice(d1, 1, 0);
        p1.getWindowPatternCard().placeDice(d4, 1, 1);
        p1.getWindowPatternCard().placeDice(d5, 1, 2);
        p1.getWindowPatternCard().placeDice(d2, 2, 1);
        p1.getWindowPatternCard().placeDice(d7, 2, 2);
        p1.getWindowPatternCard().placeDice(d8, 2, 3);
        p1.getWindowPatternCard().placeDice(d3, 3, 2);
        p1.getWindowPatternCard().placeDice(d6, 3, 3);

        p1.getWindowPatternCard().show();

        turn.startTurn();

        System.out.println(turn.getState().getClass());

        assertEquals("StartTurn", lastName(turn.getState().toString(), 10));

        try {
            turn.receiveMove(tester);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("SelectingDice", lastName(turn.getState().toString(), 14));

        try {
            turn.receiveMove(d5, new Pos(1, 2));
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("MovingWindowDice", lastName(turn.getState().toString(), 17));

        try {
            turn.receiveMove(new Pos(0, 1));
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("SelectingDice", lastName(turn.getState().toString(), 14));

        try {
            turn.receiveMove(d6, new Pos(3, 3));
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("MovingWindowDice", lastName(turn.getState().toString(), 17));

        try {
            turn.receiveMove(new Pos(3, 1));
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("EndTurn", lastName(turn.getState().toString(), 8));



        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 1)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 2)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 3)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 4)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(1, 3)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(1, 4)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(2, 0)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(2, 4)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(3, 0)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(3, 1)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(3, 4)).isOccupied());


        assertTrue(p1.getWindowPatternCard().getCell(new Pos(0, 1)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(1, 2)).isOccupied());

        assertTrue(p1.getWindowPatternCard().getCell(new Pos(3, 1)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(3, 3)).isOccupied());

        assertTrue(p1.getWindowPatternCard().getCell(new Pos(0, 0)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(1, 0)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(1, 1)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(1, 2)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(2, 1)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(2, 2)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(3, 2)).isOccupied());

    }

    @Test
    void TestingWrongFirstSelection() {
        ToolCard tester = new ToolCard();
        CardContainer container = new CardContainer();
        ArrayList<ToolCard> toolCardArrayList = container.pullOutTools();

        while (toolCardArrayList.get(0).getID() != 4 && toolCardArrayList.get(1).getID() != 4 && toolCardArrayList.get(2).getID() != 4) {
            container = new CardContainer();
            toolCardArrayList = container.pullOutTools();
        }
        if (toolCardArrayList.get(0).getID() == 4)
            tester = toolCardArrayList.get(0);

        if (toolCardArrayList.get(1).getID() == 4)
            tester = toolCardArrayList.get(1);

        if (toolCardArrayList.get(2).getID() == 4)
            tester = toolCardArrayList.get(2);

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
        table.setPublicObjects();

        while (players.get(0).getToolCards().get(0).getID() != 4 && players.get(0).getToolCards().get(1).getID() != 4 && players.get(0).getToolCards().get(2).getID() != 4) {
            table = new Table(players);
            table.setPublicObjects();
        }

        Round round = new Round(players, 1, table);

        ArrayList<WindowPatternCard> windows = new ArrayList<>();
        WindowPatternCard windowPatternCard = new WindowPatternCard();
        windows.add(windowPatternCard);
        p1.chooseWindow(windows);

        table.getDiceBag().setNumPlayers(4);
        table.getDraftPool().addNewDices(table.getDiceFromBag());

        PublicObjects publicObjects = new PublicObjects();
        publicObjects.setDraftPool(table.getDraftPool());
        publicObjects.setObjectiveCards(table.getObjCard());
        publicObjects.setToolCards(table.getToolCards());

        p1.setPublicObjects(publicObjects);

        Turn turn = new Turn(p1, round, 1, true, table);

        Dice d1 = new Dice(Colour.GREEN);
        d1.setNumber(1);
        Dice d2 = new Dice(Colour.RED);
        d2.setNumber(2);
        Dice d3 = new Dice(Colour.YELLOW);
        d3.setNumber(3);
        Dice d4 = new Dice(Colour.RED);
        d4.setNumber(4);
        Dice d5 = new Dice(Colour.PURPLE);
        d5.setNumber(5);
        Dice d6 = new Dice(Colour.BLUE);
        d6.setNumber(6);
        Dice d7 = new Dice(Colour.PURPLE);
        d7.setNumber(1);
        Dice d8 = new Dice(Colour.YELLOW);
        d8.setNumber(2);
        Dice d9 = new Dice(Colour.YELLOW);
        d9.setNumber(3);


        p1.getWindowPatternCard().placeDice(d9, 0, 0);
        p1.getWindowPatternCard().placeDice(d1, 1, 0);
        p1.getWindowPatternCard().placeDice(d4, 1, 1);
        p1.getWindowPatternCard().placeDice(d5, 1, 2);
        p1.getWindowPatternCard().placeDice(d2, 2, 1);
        p1.getWindowPatternCard().placeDice(d7, 2, 2);
        p1.getWindowPatternCard().placeDice(d8, 2, 3);
        p1.getWindowPatternCard().placeDice(d3, 3, 2);
        p1.getWindowPatternCard().placeDice(d6, 3, 3);


        p1.getWindowPatternCard().show();

        turn.startTurn();

        System.out.println(turn.getState().getClass());

        assertEquals("StartTurn", lastName(turn.getState().toString(), 10));

        try {
            turn.receiveMove(tester);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("SelectingDice", lastName(turn.getState().toString(), 14));

        assertThrows(WrongMoveException.class,()->{turn.receiveMove(d8, new Pos(2, 2));});

        assertEquals("EndTurn", lastName(turn.getState().toString(), 8));

        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 1)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 2)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 3)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 4)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(1, 3)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(1, 4)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(2, 0)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(2, 4)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(3, 0)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(3, 1)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(3, 4)).isOccupied());


        assertTrue(p1.getWindowPatternCard().getCell(new Pos(0, 0)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(1, 0)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(1, 1)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(1, 2)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(2, 1)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(2, 2)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(3, 2)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(3, 3)).isOccupied());

    }

    @Test
    void TestingWrongPositioning() {
        ToolCard tester = new ToolCard();
        CardContainer container = new CardContainer();
        ArrayList<ToolCard> toolCardArrayList = container.pullOutTools();

        while (toolCardArrayList.get(0).getID() != 4 && toolCardArrayList.get(1).getID() != 4 && toolCardArrayList.get(2).getID() != 4) {
            container = new CardContainer();
            toolCardArrayList = container.pullOutTools();
        }
        if (toolCardArrayList.get(0).getID() == 4)
            tester = toolCardArrayList.get(0);

        if (toolCardArrayList.get(1).getID() == 4)
            tester = toolCardArrayList.get(1);

        if (toolCardArrayList.get(2).getID() == 4)
            tester = toolCardArrayList.get(2);

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
        table.setPublicObjects();

        while (players.get(0).getToolCards().get(0).getID() != 4 && players.get(0).getToolCards().get(1).getID() != 4 && players.get(0).getToolCards().get(2).getID() != 4) {
            table = new Table(players);
            table.setPublicObjects();
        }

        Round round = new Round(players, 1, table);

        ArrayList<WindowPatternCard> windows = new ArrayList<>();
        WindowPatternCard windowPatternCard = new WindowPatternCard();
        windows.add(windowPatternCard);
        p1.chooseWindow(windows);

        table.getDiceBag().setNumPlayers(4);
        table.getDraftPool().addNewDices(table.getDiceFromBag());

        PublicObjects publicObjects = new PublicObjects();
        publicObjects.setDraftPool(table.getDraftPool());
        publicObjects.setObjectiveCards(table.getObjCard());
        publicObjects.setToolCards(table.getToolCards());

        p1.setPublicObjects(publicObjects);

        Turn turn = new Turn(p1, round, 1, true, table);

        Dice d1 = new Dice(Colour.GREEN);
        d1.setNumber(1);
        Dice d2 = new Dice(Colour.RED);
        d2.setNumber(2);
        Dice d3 = new Dice(Colour.YELLOW);
        d3.setNumber(3);
        Dice d4 = new Dice(Colour.RED);
        d4.setNumber(4);
        Dice d5 = new Dice(Colour.PURPLE);
        d5.setNumber(5);
        Dice d6 = new Dice(Colour.BLUE);
        d6.setNumber(6);
        Dice d7 = new Dice(Colour.PURPLE);
        d7.setNumber(1);
        Dice d8 = new Dice(Colour.YELLOW);
        d8.setNumber(2);
        Dice d9 = new Dice(Colour.YELLOW);
        d9.setNumber(3);


        p1.getWindowPatternCard().placeDice(d9, 0, 0);
        p1.getWindowPatternCard().placeDice(d1, 1, 0);
        p1.getWindowPatternCard().placeDice(d4, 1, 1);
        p1.getWindowPatternCard().placeDice(d5, 1, 2);
        p1.getWindowPatternCard().placeDice(d2, 2, 1);
        p1.getWindowPatternCard().placeDice(d7, 2, 2);
        p1.getWindowPatternCard().placeDice(d8, 2, 3);
        p1.getWindowPatternCard().placeDice(d3, 3, 2);
        p1.getWindowPatternCard().placeDice(d6, 3, 3);


        p1.getWindowPatternCard().show();

        turn.startTurn();

        System.out.println(turn.getState().getClass());

        assertEquals("StartTurn", lastName(turn.getState().toString(), 10));

        try {
            turn.receiveMove(tester);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("SelectingDice", lastName(turn.getState().toString(), 14));

        try {
            turn.receiveMove(d5, new Pos(1, 2));
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("MovingWindowDice", lastName(turn.getState().toString(), 17));

        assertThrows(WrongMoveException.class,()->{turn.receiveMove(new Pos(2, 2));});


        assertEquals("EndTurn", lastName(turn.getState().toString(), 8));


        assertTrue(p1.getWindowPatternCard().getCell(new Pos(0, 0)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(1, 0)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(1, 1)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(1, 2)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(2, 1)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(2, 2)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(2, 3)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(3, 2)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(3, 3)).isOccupied());

        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 1)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 2)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 3)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 4)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(1, 3)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(1, 4)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(2, 0)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(2, 4)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(3, 0)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(3, 1)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(3, 4)).isOccupied());

    }

    @Test
    void TestingWrongSecondSelection() {
        ToolCard tester = new ToolCard();
        CardContainer container = new CardContainer();
        ArrayList<ToolCard> toolCardArrayList = container.pullOutTools();

        while (toolCardArrayList.get(0).getID() != 4 && toolCardArrayList.get(1).getID() != 4 && toolCardArrayList.get(2).getID() != 4) {
            container = new CardContainer();
            toolCardArrayList = container.pullOutTools();
        }
        if (toolCardArrayList.get(0).getID() == 4)
            tester = toolCardArrayList.get(0);

        if (toolCardArrayList.get(1).getID() == 4)
            tester = toolCardArrayList.get(1);

        if (toolCardArrayList.get(2).getID() == 4)
            tester = toolCardArrayList.get(2);

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
        table.setPublicObjects();

        while (players.get(0).getToolCards().get(0).getID() != 4 && players.get(0).getToolCards().get(1).getID() != 4 && players.get(0).getToolCards().get(2).getID() != 4) {
            table = new Table(players);
            table.setPublicObjects();
        }

        Round round = new Round(players, 1, table);

        ArrayList<WindowPatternCard> windows = new ArrayList<>();
        WindowPatternCard windowPatternCard = new WindowPatternCard();
        windows.add(windowPatternCard);
        p1.chooseWindow(windows);

        table.getDiceBag().setNumPlayers(4);
        table.getDraftPool().addNewDices(table.getDiceFromBag());

        PublicObjects publicObjects = new PublicObjects();
        publicObjects.setDraftPool(table.getDraftPool());
        publicObjects.setObjectiveCards(table.getObjCard());
        publicObjects.setToolCards(table.getToolCards());

        p1.setPublicObjects(publicObjects);

        Turn turn = new Turn(p1, round, 1, true, table);

        Dice d1 = new Dice(Colour.GREEN);
        d1.setNumber(1);
        Dice d2 = new Dice(Colour.RED);
        d2.setNumber(2);
        Dice d3 = new Dice(Colour.YELLOW);
        d3.setNumber(3);
        Dice d4 = new Dice(Colour.RED);
        d4.setNumber(4);
        Dice d5 = new Dice(Colour.PURPLE);
        d5.setNumber(5);
        Dice d6 = new Dice(Colour.BLUE);
        d6.setNumber(6);
        Dice d7 = new Dice(Colour.PURPLE);
        d7.setNumber(1);
        Dice d8 = new Dice(Colour.YELLOW);
        d8.setNumber(2);
        Dice d9 = new Dice(Colour.YELLOW);
        d9.setNumber(3);


        p1.getWindowPatternCard().placeDice(d9, 0, 0);
        p1.getWindowPatternCard().placeDice(d1, 1, 0);
        p1.getWindowPatternCard().placeDice(d4, 1, 1);
        p1.getWindowPatternCard().placeDice(d5, 1, 2);
        p1.getWindowPatternCard().placeDice(d2, 2, 1);
        p1.getWindowPatternCard().placeDice(d7, 2, 2);
        p1.getWindowPatternCard().placeDice(d8, 2, 3);
        p1.getWindowPatternCard().placeDice(d3, 3, 2);
        p1.getWindowPatternCard().placeDice(d6, 3, 3);


        p1.getWindowPatternCard().show();

        turn.startTurn();

        System.out.println(turn.getState().getClass());

        assertEquals("StartTurn", lastName(turn.getState().toString(), 10));

        try {
            turn.receiveMove(tester);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("SelectingDice", lastName(turn.getState().toString(), 14));

        try {
            turn.receiveMove(d6, new Pos(3, 3));
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("MovingWindowDice", lastName(turn.getState().toString(), 17));

        try {
            turn.receiveMove(new Pos(2, 0));
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("SelectingDice", lastName(turn.getState().toString(), 14));

        assertThrows(WrongMoveException.class,()->{turn.receiveMove(d5,new Pos(2, 2));});

        assertEquals("EndTurn", lastName(turn.getState().toString(), 8));


        assertTrue(p1.getWindowPatternCard().getCell(new Pos(3, 1)).isOccupied());

        assertTrue(p1.getWindowPatternCard().getCell(new Pos(0, 0)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(1, 0)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(1, 1)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(1, 2)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(2, 1)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(2, 2)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(3, 2)).isOccupied());

        assertTrue(p1.getWindowPatternCard().getCell(new Pos(2, 0)).isOccupied());


        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 1)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 2)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 3)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 4)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(1, 3)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(1, 4)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(2, 0)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(2, 4)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(3, 0)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(3, 1)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(3, 3)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(3, 4)).isOccupied());

    }

    @Test
    void TestingWrongSecondPlacement() {
        ToolCard tester = new ToolCard();
        CardContainer container = new CardContainer();
        ArrayList<ToolCard> toolCardArrayList = container.pullOutTools();

        while (toolCardArrayList.get(0).getID() != 4 && toolCardArrayList.get(1).getID() != 4 && toolCardArrayList.get(2).getID() != 4) {
            container = new CardContainer();
            toolCardArrayList = container.pullOutTools();
        }
        if (toolCardArrayList.get(0).getID() == 4)
            tester = toolCardArrayList.get(0);

        if (toolCardArrayList.get(1).getID() == 4)
            tester = toolCardArrayList.get(1);

        if (toolCardArrayList.get(2).getID() == 4)
            tester = toolCardArrayList.get(2);

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
        table.setPublicObjects();

        while (players.get(0).getToolCards().get(0).getID() != 4 && players.get(0).getToolCards().get(1).getID() != 4 && players.get(0).getToolCards().get(2).getID() != 4) {
            table = new Table(players);
            table.setPublicObjects();
        }

        Round round = new Round(players, 1, table);

        ArrayList<WindowPatternCard> windows = new ArrayList<>();
        WindowPatternCard windowPatternCard = new WindowPatternCard();
        windows.add(windowPatternCard);
        p1.chooseWindow(windows);

        table.getDiceBag().setNumPlayers(4);
        table.getDraftPool().addNewDices(table.getDiceFromBag());

        PublicObjects publicObjects = new PublicObjects();
        publicObjects.setDraftPool(table.getDraftPool());
        publicObjects.setObjectiveCards(table.getObjCard());
        publicObjects.setToolCards(table.getToolCards());

        p1.setPublicObjects(publicObjects);

        Turn turn = new Turn(p1, round, 1, true, table);

        Dice d1 = new Dice(Colour.GREEN);
        d1.setNumber(1);
        Dice d2 = new Dice(Colour.RED);
        d2.setNumber(2);
        Dice d3 = new Dice(Colour.YELLOW);
        d3.setNumber(3);
        Dice d4 = new Dice(Colour.RED);
        d4.setNumber(4);
        Dice d5 = new Dice(Colour.PURPLE);
        d5.setNumber(5);
        Dice d6 = new Dice(Colour.BLUE);
        d6.setNumber(6);
        Dice d7 = new Dice(Colour.PURPLE);
        d7.setNumber(1);
        Dice d8 = new Dice(Colour.YELLOW);
        d8.setNumber(2);
        Dice d9 = new Dice(Colour.YELLOW);
        d9.setNumber(3);



        p1.getWindowPatternCard().placeDice(d9, 0, 0);
        p1.getWindowPatternCard().placeDice(d1, 1, 0);
        p1.getWindowPatternCard().placeDice(d4, 1, 1);
        p1.getWindowPatternCard().placeDice(d5, 1, 2);
        p1.getWindowPatternCard().placeDice(d2, 2, 1);
        p1.getWindowPatternCard().placeDice(d7, 2, 2);
        p1.getWindowPatternCard().placeDice(d8, 2, 3);
        p1.getWindowPatternCard().placeDice(d3, 3, 2);
        p1.getWindowPatternCard().placeDice(d6, 3, 3);

        p1.getWindowPatternCard().show();

        turn.startTurn();

        System.out.println(turn.getState().getClass());

        assertEquals("StartTurn", lastName(turn.getState().toString(), 10));

        try {
            turn.receiveMove(tester);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("SelectingDice", lastName(turn.getState().toString(), 14));

        try {
            turn.receiveMove(d5, new Pos(1, 2));
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("MovingWindowDice", lastName(turn.getState().toString(), 17));

        try {
            turn.receiveMove(new Pos(0, 1));
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("SelectingDice", lastName(turn.getState().toString(), 14));

        try {
            turn.receiveMove(d7, new Pos(2, 2));
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("MovingWindowDice", lastName(turn.getState().toString(), 17));

        assertThrows(WrongMoveException.class,()->{turn.receiveMove(d7,new Pos(2, 0));});

        assertEquals("EndTurn", lastName(turn.getState().toString(), 8));



        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 2)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 3)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 4)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(1, 2)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(1, 3)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(1, 4)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(2, 0)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(2, 4)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(3, 0)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(3, 1)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(3, 4)).isOccupied());


        assertTrue(p1.getWindowPatternCard().getCell(new Pos(0, 0)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(0, 1)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(1, 0)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(1, 1)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(1, 2)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(2, 1)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(2, 2)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(3, 2)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(3, 3)).isOccupied());


    }
}