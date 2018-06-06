package it.polimi.ingsw.turntest;

import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.PublicObjects;
import it.polimi.ingsw.model.gamedata.Table;
import it.polimi.ingsw.model.gamedata.gametools.CardContainer;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.gametools.ToolCard;
import it.polimi.ingsw.model.gamedata.gametools.WindowPatternCard;
import it.polimi.ingsw.model.gamelogic.Round;
import it.polimi.ingsw.turn.AutomatedOperation;
import it.polimi.ingsw.turn.Turn;
import it.polimi.ingsw.turn.moveexceptions.WrongMoveException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static it.polimi.ingsw.turntest.TurnTest.lastName;
import static org.junit.jupiter.api.Assertions.*;

public class ToolCard5Test {

    @Test
    void TestingAllowedMoves() {
        ToolCard tester = new ToolCard();
        CardContainer container = new CardContainer();
        ArrayList<ToolCard> toolCardArrayList = container.pullOutTools();

        while (toolCardArrayList.get(0).getID() != 5 && toolCardArrayList.get(1).getID() != 5 && toolCardArrayList.get(2).getID() != 5) {
            container = new CardContainer();
            toolCardArrayList = container.pullOutTools();
        }
        if (toolCardArrayList.get(0).getID() == 5)
            tester = toolCardArrayList.get(0);

        if (toolCardArrayList.get(1).getID() == 5)
            tester = toolCardArrayList.get(1);

        if (toolCardArrayList.get(2).getID() == 5)
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
        table.initialize();

        while (players.get(0).getToolCards().get(0).getID() != 5 && players.get(0).getToolCards().get(1).getID() != 5 && players.get(0).getToolCards().get(2).getID() != 5) {
            table = new Table(players);
            table.initialize();
        }

        Round round = new Round(players, 2, table);

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

        p1.getRoundTrack().setDiceOnRoundTrack(1,p1.getDraftPool().getDraftPool());

        Turn turn = new Turn(p1, round, 2, true, table);

        turn.startTurn();

        assertEquals("StartTurn", lastName(turn.getState().toString(), 10));

        try {
            turn.receiveMove(tester);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("SelectingRoundTrackDice",lastName(turn.getState().toString(),18));

        Dice d = new Dice(p1.getRoundTrack().getDice(new Pos(0,0)).getColour());
        d.setNumber(p1.getRoundTrack().getDice(new Pos(0,0)).getNumber());

        try {
            turn.receiveMove(p1.getRoundTrack().getDice(new Pos(0,0)),new Pos(0,0));
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("AutomatedOperation",lastName(turn.getState().toString(),19));

        ((AutomatedOperation) turn.getState()).doAutomatedOperations(tester.getAutomatedoperationlist());

        assertFalse(p1.getRoundTrack().getDice(new Pos(0,0)).equals(d));

        assertEquals("ChooseDice2",lastName(turn.getState().toString(),12));

        try {
            turn.receiveMove(new Pos(2,0));
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("EndTurn",lastName(turn.getState().toString(),8));

    }

    @Test
    void TestingFirstSelectionWrong() {
        ToolCard tester = new ToolCard();
        CardContainer container = new CardContainer();
        ArrayList<ToolCard> toolCardArrayList = container.pullOutTools();

        while (toolCardArrayList.get(0).getID() != 5 && toolCardArrayList.get(1).getID() != 5 && toolCardArrayList.get(2).getID() != 5) {
            container = new CardContainer();
            toolCardArrayList = container.pullOutTools();
        }
        if (toolCardArrayList.get(0).getID() == 5)
            tester = toolCardArrayList.get(0);

        if (toolCardArrayList.get(1).getID() == 5)
            tester = toolCardArrayList.get(1);

        if (toolCardArrayList.get(2).getID() == 5)
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
        table.initialize();

        while (players.get(0).getToolCards().get(0).getID() != 5 && players.get(0).getToolCards().get(1).getID() != 5 && players.get(0).getToolCards().get(2).getID() != 5) {
            table = new Table(players);
            table.initialize();
        }

        Round round = new Round(players, 2, table);

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

        p1.getRoundTrack().setDiceOnRoundTrack(1, p1.getDraftPool().getDraftPool());

        Turn turn = new Turn(p1, round, 2, true, table);

        turn.startTurn();

        assertEquals("StartTurn", lastName(turn.getState().toString(), 10));

        try {
            turn.receiveMove(tester);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("SelectingRoundTrackDice", lastName(turn.getState().toString(), 18));

        Dice d = new Dice(p1.getRoundTrack().getDice(new Pos(0, 0)).getColour());
        d.setNumber(p1.getRoundTrack().getDice(new Pos(0, 0)).getNumber());

        assertThrows(WrongMoveException.class,()->{turn.receiveMove(p1.getRoundTrack().getDice(new Pos(0, 0)), new Pos(0, 3));});

        assertTrue(d.equals(p1.getRoundTrack().getDice(new Pos(0,0))));

    }

    @Test
    void TestingAutomatedOperationListIsRight() {
        ToolCard tester = new ToolCard();
        CardContainer container = new CardContainer();
        ArrayList<ToolCard> toolCardArrayList = container.pullOutTools();

        while (toolCardArrayList.get(0).getID() != 5 && toolCardArrayList.get(1).getID() != 5 && toolCardArrayList.get(2).getID() != 5) {
            container = new CardContainer();
            toolCardArrayList = container.pullOutTools();
        }
        if (toolCardArrayList.get(0).getID() == 5)
            tester = toolCardArrayList.get(0);

        if (toolCardArrayList.get(1).getID() == 5)
            tester = toolCardArrayList.get(1);

        if (toolCardArrayList.get(2).getID() == 5)
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
        table.initialize();

        while (players.get(0).getToolCards().get(0).getID() != 5 && players.get(0).getToolCards().get(1).getID() != 5 && players.get(0).getToolCards().get(2).getID() != 5) {
            table = new Table(players);
            table.initialize();
        }

        Round round = new Round(players, 2, table);

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

        p1.getRoundTrack().setDiceOnRoundTrack(1, p1.getDraftPool().getDraftPool());

        Turn turn = new Turn(p1, round, 2, true, table);

        turn.startTurn();

        assertEquals("StartTurn", lastName(turn.getState().toString(), 10));

        try {
            turn.receiveMove(tester);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("SelectingRoundTrackDice", lastName(turn.getState().toString(), 18));

        Dice d = new Dice(p1.getRoundTrack().getDice(new Pos(0, 0)).getColour());
        d.setNumber(p1.getRoundTrack().getDice(new Pos(0, 0)).getNumber());

        try {
            turn.receiveMove(p1.getRoundTrack().getDice(new Pos(0, 0)), new Pos(0, 0));
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("AutomatedOperation", lastName(turn.getState().toString(), 19));

        ((AutomatedOperation) turn.getState()).doAutomatedOperations(tester.getAutomatedoperationlist());

        ToolCard tmp = new ToolCard();

        if (toolCardArrayList.get(0).getID() == 5)
            tmp = toolCardArrayList.get(0);

        if (toolCardArrayList.get(1).getID() == 5)
            tmp = toolCardArrayList.get(1);

        if (toolCardArrayList.get(2).getID() == 5)
            tmp = toolCardArrayList.get(2);

        for(int i = 0; i < tmp.getAutomatedoperationlist().size(); i++){
            assertEquals(tmp.getAutomatedoperationlist().get(i),turn.getToolCard().getAutomatedoperationlist().get(i));
        }

    }

        @Test
    void TestingNotAllowedInFirstRound() {
        ToolCard tester = new ToolCard();
        CardContainer container = new CardContainer();
        ArrayList<ToolCard> toolCardArrayList = container.pullOutTools();

        while (toolCardArrayList.get(0).getID() != 5 && toolCardArrayList.get(1).getID() != 5 && toolCardArrayList.get(2).getID() != 5) {
            container = new CardContainer();
            toolCardArrayList = container.pullOutTools();
        }
        if (toolCardArrayList.get(0).getID() == 5)
            tester = toolCardArrayList.get(0);

        if (toolCardArrayList.get(1).getID() == 5)
            tester = toolCardArrayList.get(1);

        if (toolCardArrayList.get(2).getID() == 5)
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
        table.initialize();

        while (players.get(0).getToolCards().get(0).getID() != 5 && players.get(0).getToolCards().get(1).getID() != 5 && players.get(0).getToolCards().get(2).getID() != 5) {
            table = new Table(players);
            table.initialize();
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

        turn.startTurn();

        assertEquals("StartTurn", lastName(turn.getState().toString(), 10));

        ToolCard finalTester = tester;
        assertThrows(WrongMoveException.class,()->{turn.receiveMove(finalTester);});
    }

}
