package it.polimi.ingsw.turntest;

import it.polimi.ingsw.Match;
import it.polimi.ingsw.controller.Manager;
import it.polimi.ingsw.model.gamedata.*;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.gametools.ToolCard;
import it.polimi.ingsw.model.gamedata.gametools.WindowPatternCard;
import it.polimi.ingsw.model.gamelogic.Round;
import it.polimi.ingsw.turn.ChooseDice1;
import it.polimi.ingsw.turn.Turn;
import it.polimi.ingsw.turn.TurnState;
import it.polimi.ingsw.turn.moveexceptions.WrongMoveException;
import it.polimi.ingsw.view.virtualview.VirtualViewObserver;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.turntest.TurnTest.lastName;
import static it.polimi.ingsw.turntest.TurnTest.pullOutThatCard;
import static it.polimi.ingsw.turntest.TurnTest.setThatCard;
import static org.junit.jupiter.api.Assertions.*;

public class ToolCard6Test {
    long timerCard = 0;
    long timerMove = 0;

    @Test
    void TestingAllowedMoves() {
        ToolCard tester = pullOutThatCard(6);

        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player("one");
        Player p2 = new Player("two");
        Player p3 = new Player("three");
        Player p4 = new Player("four");
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        Table table = setThatCard(6,players);

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

        Match match = new Match(players,new Manager(0,0,0),0, timerCard, timerMove);

        Round round = new Round(players, 2, table,match, timerMove);

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


        p1.getRoundTrack().setDiceOnRoundTrack(1, p1.getDraftPool().getDraftPool());

        Dice d = new Dice(Colour.WHITE);
        p1.getWindowPatternCard().placeDice(d,2,0);

        Turn turn = new Turn(p1, round, 2, true, table);

        Dice chosenone = p1.getDraftPool().chooseDice(3);
        Dice tmp = new Dice(chosenone.getColour());
        tmp.setNumber(chosenone.getNumber());

        TurnState turnState = new ChooseDice1(turn,chosenone,new Pos(3,3));

        turn.setState(turnState);

        String state = "ChooseDice1";

        assertEquals(state, lastName(turn.getState().toString(), state.length()+1));

        try {
            turn.receiveMove(tester);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertTrue(chosenone.getNumber()<=6 || chosenone.getNumber()>=1);

        state = "ChooseDice2";

        assertEquals(state, lastName(turn.getState().toString(),state.length()+1));

        if (d.getNumber() == chosenone.getNumber())
            if(d.getNumber()==6) {
                chosenone.setNumber(5);
            }
            else if(d.getNumber()==1){
            chosenone.setNumber(1);
            }
            else
                chosenone.setNumber(d.getNumber()+1);


        try {
            turn.receiveMove(new Pos(2,1));
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }
    }

    @Test
    void TestingPassingAfterCard() {
        ToolCard tester = pullOutThatCard(6);

        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player("one");
        Player p2 = new Player("two");
        Player p3 = new Player("three");
        Player p4 = new Player("four");
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        Table table = setThatCard(6,players);

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

        Match match = new Match(players,new Manager(0,0,0),0, timerCard, timerMove);

        Round round = new Round(players, 2, table,match, timerMove);

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

        p1.getRoundTrack().setDiceOnRoundTrack(1, p1.getDraftPool().getDraftPool());

        Turn turn = new Turn(p1, round, 2, true, table);

        Dice chosenone = p1.getDraftPool().chooseDice(3);
        Dice tmp = new Dice(chosenone.getColour());
        tmp.setNumber(chosenone.getNumber());

        TurnState turnState = new ChooseDice1(turn,chosenone,new Pos(3,3));

        turn.setState(turnState);

        String state = "ChooseDice1";

        assertEquals(state, lastName(turn.getState().toString(), state.length()+1));

        try {
            turn.receiveMove(tester);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertTrue(chosenone.getNumber()<=6 || chosenone.getNumber()>=1);

        state = "ChooseDice2";

        assertEquals(state, lastName(turn.getState().toString(),state.length()+1));

        try {
            turn.receiveMove("pass");
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertSame(chosenone,p1.getDraftPool().chooseDice(3));
        assertTrue(chosenone.equals(p1.getDraftPool().chooseDice(3)));

        state = "EndTurn";

        assertEquals(state, lastName(turn.getState().toString(),state.length()+1));
    }
}