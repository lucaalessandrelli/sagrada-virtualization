package it.polimi.ingsw.turntest;

import it.polimi.ingsw.Match;
import it.polimi.ingsw.controller.Manager;
import it.polimi.ingsw.model.gamedata.*;
import it.polimi.ingsw.model.gamedata.gametools.*;
import it.polimi.ingsw.model.gamelogic.Round;
import it.polimi.ingsw.turn.AutomatedOperation;
import it.polimi.ingsw.turn.ChooseDice1;
import it.polimi.ingsw.turn.Turn;
import it.polimi.ingsw.turn.TurnState;
import it.polimi.ingsw.turn.moveexceptions.WrongMoveException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        table.setPublicObjects();


        while (players.get(0).getToolCards().get(0).getID() != 5 && players.get(0).getToolCards().get(1).getID() != 5 && players.get(0).getToolCards().get(2).getID() != 5) {
            table = new Table(players);
            table.initialize();
            table.setPublicObjects();
        }


        Match match = new Match(players,new Manager(),0);

        Round round = new Round(players, 2, table,match);

        ArrayList<WindowPatternCard> windows = new ArrayList<>();
        WindowPatternCard windowPatternCard = new WindowPatternCard();
        windows.add(windowPatternCard);
        p1.setMyWindow(windows.get(0));

        table.getDiceBag().setNumPlayers(4);
        table.getDraftPool().addNewDices(table.getDiceFromBag());

        table.getRoundTrack().setDiceOnRoundTrack(1,table.getDraftPool().getDraftPool());

        table.getDraftPool().showDraftPool();System.out.println("=:");

        Turn turn = new Turn(p1, round, 2, true, table);

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

        List<List<Dice>> tmp = new ArrayList<>();
        for(int i = 0; i < table.getRoundTrack().getRoundTrack().size(); i++){
            tmp.add(new ArrayList<>());
        }

        for (int i = 0; i < table.getRoundTrack().getRoundTrack().size()-1;i++){
            for(int j = 0; j < table.getRoundTrack().getRoundTrack().get(i).size()-1;i++){
                Dice d = new Dice(table.getRoundTrack().getRoundTrack().get(i).get(j).getColour());
                d.setNumber(table.getRoundTrack().getRoundTrack().get(i).get(j).getNumber());
                table.getRoundTrack().getRoundTrack().get(i).get(j).show();
                tmp.get(i).add(j,d);
            }
        }

        TurnState turnstate = new ChooseDice1(turn,table.getDraftPool().chooseDice(2),new Pos(2,0));

        turn.setState(turnstate);

        String state = "ChooseDice1";

        assertEquals(state, lastName(turn.getState().toString(), state.length()+1));

        try {
            turn.receiveMove(tester);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        state = "SelectingRoundTrackDice";
        assertEquals(state,lastName(turn.getState().toString(),state.length()+1));

        Dice d = new Dice(p1.getRoundTrack().getDice(new Pos(0,0)).getColour());
        d.setNumber(p1.getRoundTrack().getDice(new Pos(0,0)).getNumber());

        try {
            turn.receiveMove(p1.getRoundTrack().getDice(new Pos(0,0)),new Pos(0,0));
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        /*state = "AutomatedOperation";
        assertEquals(state,lastName(turn.getState().toString(),state.length()+1));

        //((AutomatedOperation) turn.getState()).doAutomatedOperations(tester.getAutomatedoperationlist());*/



        p1.getDraftPool().showDraftPool();System.out.println("__1__");
        table.getDraftPool().showDraftPool();System.out.println("__d__");
        d.show();
        p1.getDraftPool().chooseDice(2).show();System.out.println("__p1draftchosedice__");
        table.getDraftPool().chooseDice(2).show();System.out.println("__tabledraftchosedice__");

        assertTrue(p1.getDraftPool().chooseDice(2).equals(d));

        for(int i = 0; i < p1.getRoundTrack().getRoundTrack().size(); i++){
            for(int j = 0; j < p1.getRoundTrack().getRoundTrack().get(i).size(); j++){
                if(i != 0 && j != 0)
                assertTrue(p1.getRoundTrack().getRoundTrack().get(i).get(j).equals(tmp.get(i).get(j)));
            }
        }

        state = "ChooseDice2";
        assertEquals(state,lastName(turn.getState().toString(),state.length()+1));

        try {
            turn.receiveMove(new Pos(2, 0));
        } catch (WrongMoveException e)  {
            e.printStackTrace();
        } catch (NullPointerException e){
            System.out.println("If this is printed we have a NullPointerException that is right");
        }

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
        table.setPublicObjects();

        while (players.get(0).getToolCards().get(0).getID() != 5 && players.get(0).getToolCards().get(1).getID() != 5 && players.get(0).getToolCards().get(2).getID() != 5) {
            table = new Table(players);
            table.initialize();
            table.setPublicObjects();
        }

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


        Dice d4 = new Dice(Colour.YELLOW);
        p1.getWindowPatternCard().placeDice(d4, 1, 1);

        table.getRoundTrack().setDiceOnRoundTrack(1, table.getDraftPool().getDraftPool());

        Turn turn = new Turn(p1, round, 2, true, table);

        TurnState turnstate = new ChooseDice1(turn,d4,new Pos(1,1));

        turn.setState(turnstate);

        String state = "ChooseDice1";

        assertEquals(state, lastName(turn.getState().toString(), state.length()+1));

        try {
            turn.receiveMove(tester);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        state = "SelectingRoundTrackDice";
        assertEquals(state, lastName(turn.getState().toString(), state.length()+1));

        Dice d = new Dice(p1.getRoundTrack().getDice(new Pos(0, 0)).getColour());
        d.setNumber(p1.getRoundTrack().getDice(new Pos(0, 0)).getNumber() + 1);

        assertThrows(WrongMoveException.class,()->{turn.receiveMove(p1.getRoundTrack().getDice(new Pos(0, 0)), new Pos(0, 3));});

        assertFalse(d.equals(table.getRoundTrack().getDice(new Pos(0,0))));

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
        table.setPublicObjects();

        while (players.get(0).getToolCards().get(0).getID() != 5 && players.get(0).getToolCards().get(1).getID() != 5 && players.get(0).getToolCards().get(2).getID() != 5) {
            table = new Table(players);
            table.initialize();
            table.setPublicObjects();
        }

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

        Dice d4 = new Dice(Colour.YELLOW);
        p1.getWindowPatternCard().placeDice(d4, 1, 1);

        table.getRoundTrack().setDiceOnRoundTrack(1, table.getDraftPool().getDraftPool());

        Turn turn = new Turn(p1, round, 2, true, table);

        TurnState turnstate = new ChooseDice1(turn,d4,new Pos(1,1));

        turn.setState(turnstate);

        String state = "ChooseDice1";

        assertEquals(state, lastName(turn.getState().toString(), state.length()+1));

        try {
            turn.receiveMove(tester);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        state = "SelectingRoundTrackDice";
        assertEquals(state, lastName(turn.getState().toString(), state.length()+1));

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
        table.setPublicObjects();

            while (players.get(0).getToolCards().get(0).getID() != 5 && players.get(0).getToolCards().get(1).getID() != 5 && players.get(0).getToolCards().get(2).getID() != 5) {
            table = new Table(players);
            table.initialize();
            table.setPublicObjects();
            }

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


        Turn turn = new Turn(p1, round, 1, true, table);

        turn.startTurn();

        assertEquals("StartTurn", lastName(turn.getState().toString(), 10));

        ToolCard finalTester = tester;
        assertThrows(WrongMoveException.class,()->{turn.receiveMove(finalTester);});
    }

}
