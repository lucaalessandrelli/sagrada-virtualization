package it.polimi.ingsw.turntest;

import it.polimi.ingsw.Match;
import it.polimi.ingsw.controller.Manager;
import it.polimi.ingsw.model.gamedata.*;
import it.polimi.ingsw.model.gamedata.gametools.CardContainer;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.gametools.ToolCard;
import it.polimi.ingsw.model.gamedata.gametools.WindowPatternCard;
import it.polimi.ingsw.model.gamelogic.Round;
import it.polimi.ingsw.turn.Turn;
import it.polimi.ingsw.turn.moveexceptions.WrongMoveException;
import it.polimi.ingsw.view.virtualview.VirtualViewObserver;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.turntest.TurnTest.lastName;
import static it.polimi.ingsw.turntest.TurnTest.pullOutThatCard;
import static it.polimi.ingsw.turntest.TurnTest.setThatCard;
import static org.junit.jupiter.api.Assertions.*;

public class ToolCard3Test {

    @Test
    void TestingAllowedMoves(){
        ToolCard tester = pullOutThatCard(3);

        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player("one");
        Player p2 = new Player("two");
        Player p3 = new Player("three");
        Player p4 = new Player("four");
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        Table table = setThatCard(3,players);

        VirtualViewObserver virtualViewObserver = new VirtualViewObserver() {
            @Override
            public void update() {

            }

            @Override
            public void updateStateTurn(String whoIsTurn, long timeSleep) {

            }

            @Override
            public void wrongMove(String s) {

            }

            @Override
            public void chooseWindow(List<WindowPatternCard> windows) {

            }

            @Override
            public void timerChoose(long timerWindows) {

            }

            @Override
            public void notifyState(String state) {

            }

            @Override
            public void notifyScore(String s) {

            }
        };

        p1.addObserver(virtualViewObserver);
        p2.addObserver(virtualViewObserver);
        p3.addObserver(virtualViewObserver);
        p4.addObserver(virtualViewObserver);

        Match match = new Match(players,new Manager(),0);

        Round round = new Round(players,1,table,match);

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
        publicObjects.setRoundTrack(table.getRoundTrack());
        List<Player> playerList = new ArrayList<>();
        playerList.add(p2);
        playerList.add(p3);
        playerList.add(p4);
        publicObjects.setOthersPlayers(playerList);

        p1.setPublicObjects(publicObjects);

        Turn turn = new Turn(p1,round,1,true,table);

        Dice d1 = new Dice(Colour.GREEN);
        Dice d2 = new Dice(Colour.RED);
        Dice d3 = new Dice(Colour.YELLOW);

        p1.getWindowPatternCard().placeDice(d1,1,0);
        p1.getWindowPatternCard().placeDice(d2,2,1);
        p1.getWindowPatternCard().placeDice(d3,3,2);

        //p1.getWindowPatternCard().show();

        turn.startTurn();

        assertEquals("StartTurn", lastName(turn.getState().toString(),10));

        try {
            turn.receiveMove(tester);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("SelectingWindowDice", lastName(turn.getState().toString(),20));
        if(d1.getNumber()==1)
            d2.setNumber(2);
        else if(d1.getNumber()==6)
            d2.setNumber(5);
        else
            d2.setNumber(d1.getNumber()+1);

        try {
            turn.receiveMove(d2,new Pos(2,1));
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("MovingWindowDice", lastName(turn.getState().toString(),17));

        try {
            turn.receiveMove(new Pos(1, 1));
        } catch (WrongMoveException e)  {
            e.printStackTrace();
        } catch (NullPointerException e){
            System.out.println("If this is printed we have a NullPointerException that is right");
        }

        assertEquals("ToolBeforeDice", lastName(turn.getState().toString(),"ToolBeforeDice".length()+1));

        assertTrue(p1.getWindowPatternCard().getCell(new Pos(1,1)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(2,1)).isOccupied());

        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 0)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 1)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 2)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 3)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 4)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(1, 2)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(1, 3)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(1, 4)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(2, 0)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(2, 2)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(2, 3)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(2, 4)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(3, 0)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(3, 1)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(3, 3)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(3, 4)).isOccupied());


        assertTrue(p1.getWindowPatternCard().getCell(new Pos(1, 0)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(3, 2)).isOccupied());



        try {
            turn.receiveMove("pass");
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }


        String state = "EndTurn";

        assertEquals(state, lastName(turn.getState().toString(),state.length()+1));
    }

    @Test
    void TestingRightMovesWithRestrictions(){
        ToolCard tester = pullOutThatCard(3);

        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player("one");
        Player p2 = new Player("two");
        Player p3 = new Player("three");
        Player p4 = new Player("four");
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        Table table = setThatCard(3,players);

        VirtualViewObserver virtualViewObserver = new VirtualViewObserver() {
            @Override
            public void update() {

            }

            @Override
            public void updateStateTurn(String whoIsTurn, long timeSleep) {

            }

            @Override
            public void wrongMove(String s) {

            }

            @Override
            public void chooseWindow(List<WindowPatternCard> windows) {

            }

            @Override
            public void timerChoose(long timerWindows) {

            }

            @Override
            public void notifyState(String state) {

            }

            @Override
            public void notifyScore(String s) {

            }
        };

        p1.addObserver(virtualViewObserver);
        p2.addObserver(virtualViewObserver);
        p3.addObserver(virtualViewObserver);
        p4.addObserver(virtualViewObserver);

        Match match = new Match(players,new Manager(),0);

        Round round = new Round(players,1,table,match);

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
        publicObjects.setRoundTrack(table.getRoundTrack());
        List<Player> playerList = new ArrayList<>();
        playerList.add(p2);
        playerList.add(p3);
        playerList.add(p4);
        publicObjects.setOthersPlayers(playerList);

        p1.setPublicObjects(publicObjects);

        Turn turn = new Turn(p1,round,1,true,table);

        Dice d1 = new Dice(Colour.GREEN);
        Dice d2 = new Dice(Colour.RED);
        Dice d3 = new Dice(Colour.YELLOW);
        d2.setNumber(3);

        p1.getWindowPatternCard().placeDice(d1,1,0);
        p1.getWindowPatternCard().placeDice(d2,2,3);
        p1.getWindowPatternCard().placeDice(d3,3,3);

        p1.getWindowPatternCard().addRestr('1',2,2);

        //p1.getWindowPatternCard().show();

        turn.startTurn();

        assertEquals("StartTurn", lastName(turn.getState().toString(),10));

        try {
            turn.receiveMove(tester);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("SelectingWindowDice", lastName(turn.getState().toString(),20));

        try {
            turn.receiveMove(d2,new Pos(2,3));
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("MovingWindowDice", lastName(turn.getState().toString(),17));

        try {
            turn.receiveMove(new Pos(2, 2));
        } catch (WrongMoveException e)  {
            e.printStackTrace();
        } catch (NullPointerException e){
            System.out.println("If this is printed we have a NullPointerException that is right");
        }

        assertEquals("ToolBeforeDice", lastName(turn.getState().toString(),"ToolBeforeDice".length()+1));

        assertTrue(p1.getWindowPatternCard().getCell(new Pos(2,2)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(2,3)).isOccupied());

        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 0)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 1)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 2)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 3)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 4)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(1, 1)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(1, 2)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(1, 3)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(1, 4)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(2, 0)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(2, 1)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(2, 4)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(3, 0)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(3, 1)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(3, 4)).isOccupied());


        assertTrue(p1.getWindowPatternCard().getCell(new Pos(1, 0)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(3, 3)).isOccupied());



        try {
            turn.receiveMove("pass");
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }


        String state = "EndTurn";

        assertEquals(state, lastName(turn.getState().toString(),state.length()+1));

    }


    @Test
    void TestingWrongMove(){
        ToolCard tester = pullOutThatCard(3);

        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player("one");
        Player p2 = new Player("two");
        Player p3 = new Player("three");
        Player p4 = new Player("four");
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        Table table = setThatCard(3,players);

        VirtualViewObserver virtualViewObserver = new VirtualViewObserver() {
            @Override
            public void update() {

            }

            @Override
            public void updateStateTurn(String whoIsTurn, long timeSleep) {

            }

            @Override
            public void wrongMove(String s) {

            }

            @Override
            public void chooseWindow(List<WindowPatternCard> windows) {

            }

            @Override
            public void timerChoose(long timerWindows) {

            }

            @Override
            public void notifyState(String state) {

            }

            @Override
            public void notifyScore(String s) {

            }
        };

        p1.addObserver(virtualViewObserver);
        p2.addObserver(virtualViewObserver);
        p3.addObserver(virtualViewObserver);
        p4.addObserver(virtualViewObserver);

        Match match = new Match(players,new Manager(),0);

        Round round = new Round(players,1,table, match);

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
        publicObjects.setRoundTrack(table.getRoundTrack());
        List<Player> playerList = new ArrayList<>();
        playerList.add(p2);
        playerList.add(p3);
        playerList.add(p4);
        publicObjects.setOthersPlayers(playerList);

        p1.setPublicObjects(publicObjects);

        Turn turn = new Turn(p1,round,1,true,table);

        Dice d1 = new Dice(Colour.GREEN);
        Dice d2 = new Dice(Colour.RED);
        Dice d3 = new Dice(Colour.YELLOW);
        d2.setNumber(3);

        p1.getWindowPatternCard().placeDice(d1,1,0);
        p1.getWindowPatternCard().placeDice(d2,2,1);
        p1.getWindowPatternCard().placeDice(d3,3,2);

        p1.getWindowPatternCard().addRestr('P',2,2);

        //p1.getWindowPatternCard().show();

        turn.startTurn();


        assertEquals("StartTurn", lastName(turn.getState().toString(),10));

        try {
            turn.receiveMove(tester);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("SelectingWindowDice", lastName(turn.getState().toString(),20));

        assertThrows(WrongMoveException.class,()->{turn.receiveMove(d3, new Pos(2, 3));});


        assertTrue(p1.getWindowPatternCard().getCell(new Pos(2,1)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(2,3)).isOccupied());

        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 0)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 1)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 2)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 3)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(0, 4)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(1, 1)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(1, 2)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(1, 3)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(1, 4)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(2, 0)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(2, 2)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(2, 4)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(3, 0)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(3, 1)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(3, 3)).isOccupied());
        assertFalse(p1.getWindowPatternCard().getCell(new Pos(3, 4)).isOccupied());


        assertTrue(p1.getWindowPatternCard().getCell(new Pos(1, 0)).isOccupied());
        assertTrue(p1.getWindowPatternCard().getCell(new Pos(3, 2)).isOccupied());

    }
}
