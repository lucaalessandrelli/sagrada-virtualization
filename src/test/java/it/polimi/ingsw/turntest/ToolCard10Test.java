package it.polimi.ingsw.turntest;

import it.polimi.ingsw.Match;
import it.polimi.ingsw.controller.Manager;
import it.polimi.ingsw.model.gamedata.*;
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

public class ToolCard10Test {
    long timerCard = 0;
    long timerMove = 0;

    @Test
    void TestAllowedMoves() {
        ToolCard tester = pullOutThatCard(10);

        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player("one");
        Player p2 = new Player("two");
        Player p3 = new Player("three");
        Player p4 = new Player("four");
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        Table table = setThatCard(10,players);

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

        Match match = new Match(players, new Manager(0,0,0), 0, timerCard, timerMove);

        Round round = new Round(players, 1, table, match, timerMove);

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

        Dice d1 = new Dice(Colour.GREEN);
        d1.setNumber(5);
        Dice d2 = new Dice(Colour.YELLOW);
        d2.setNumber(4);
        Dice d3 = new Dice(Colour.PURPLE);
        d3.setNumber(4);
        Dice tmp = new Dice(p1.getDraftPool().chooseDice(6).getColour());
        tmp.setNumber(p1.getDraftPool().chooseDice(6).getNumber());


        Turn turn = new Turn(p1, round, 1, true, table);


        turn.startTurn();

        String state = "StartTurn";

        assertEquals(state, lastName(turn.getState().toString(), state.length()+1));

        try {
            turn.receiveMove(p1.getDraftPool().chooseDice(6),new Pos(6,0));
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        state = "ChooseDice1";

        assertEquals(state, lastName(turn.getState().toString(), state.length()+1));

        try {
            turn.receiveMove(tester);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        if(tmp.getNumber()==1)
            assertEquals(6,p1.getDraftPool().chooseDice(6).getNumber());
        if(tmp.getNumber()==2)
            assertEquals(5,p1.getDraftPool().chooseDice(6).getNumber());
        if(tmp.getNumber()==3)
            assertEquals(4,p1.getDraftPool().chooseDice(6).getNumber());
        if(tmp.getNumber()==4)
            assertEquals(3,p1.getDraftPool().chooseDice(6).getNumber());
        if(tmp.getNumber()==5)
            assertEquals(2,p1.getDraftPool().chooseDice(6).getNumber());
        if(tmp.getNumber()==6)
            assertEquals(1,p1.getDraftPool().chooseDice(6).getNumber());


        state = "ChooseDice2";

        assertEquals(state, lastName(turn.getState().toString(), state.length()+1));

        try {
            turn.receiveMove(new Pos(1,0));
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }


        state = "EndTurn";

        assertEquals(state, lastName(turn.getState().toString(), state.length()+1));
    }

    @Test
    void TestNotFromDraftPool() {
        ToolCard tester = pullOutThatCard(10);

        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player("one");
        Player p2 = new Player("two");
        Player p3 = new Player("three");
        Player p4 = new Player("four");
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        Table table = setThatCard(10,players);

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

        Match match = new Match(players, new Manager(0,0,0), 0, timerCard, timerMove);

        Round round = new Round(players, 1, table, match, timerMove);

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

        Dice d1 = new Dice(Colour.GREEN);
        d1.setNumber(5);
        Dice d2 = new Dice(Colour.YELLOW);
        d2.setNumber(4);
        Dice d3 = new Dice(Colour.PURPLE);
        d3.setNumber(4);

        p1.getWindowPatternCard().placeDice(d1,1,0);
        p1.getWindowPatternCard().placeDice(d2,2,0);
        p1.getWindowPatternCard().placeDice(d3,3,0);

        Dice tmp = new Dice(p1.getDraftPool().chooseDice(6).getColour());
        tmp.setNumber(p1.getDraftPool().chooseDice(6).getNumber());


        Turn turn = new Turn(p1, round, 1, true, table);


        turn.startTurn();

        String state = "StartTurn";

        assertEquals(state, lastName(turn.getState().toString(), state.length()+1));


        assertThrows(WrongMoveException.class,()->{turn.receiveMove(p1.getWindowPatternCard().getDice(new Pos(2,0)),new Pos(2,0));});


    }

}
