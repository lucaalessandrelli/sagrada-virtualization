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

public class ToolCard1Test {


    @Test
    void TestingCardWrongMoves(){
        ToolCard tester = pullOutThatCard(1);

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

        turn.startTurn();

        ToolCard finalTester = tester;
        assertThrows(WrongMoveException.class,()->{turn.receiveMove(finalTester);});

    }

    @Test
    void TestingCard(){
        ToolCard tester = pullOutThatCard(1);

        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player("one");
        Player p2 = new Player("two");
        Player p3 = new Player("three");
        Player p4 = new Player("four");
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        Table table = setThatCard(1,players);

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

        turn.startTurn();

        System.out.println(turn.getState().getClass());

        assertEquals("StartTurn", lastName(turn.getState().toString(),10));

        Dice dice = p1.getDraftPool().chooseDice(3);

        Pos pos = new Pos(3,2);

        try {
            turn.receiveMove(dice,pos);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        System.out.println(turn.getState().getClass());

        assertEquals("ChooseDice1", lastName(turn.getState().toString(),12));

        try {
            turn.receiveMove(tester);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("IncDecValue", lastName(turn.getState().toString(),12));

        System.out.println(turn.getState().getClass());

        Dice d = new Dice(Colour.GREEN);
        if(dice.getNumber()==6)
            d.setNumber(dice.getNumber()-1);
        else if(dice.getNumber()==1)
            d.setNumber(dice.getNumber()+1);
        else
            d.setNumber(dice.getNumber()+1);

        try {
            turn.receiveMove(d,pos);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        assertEquals("ChooseDice2", lastName(turn.getState().toString(),12));

        System.out.println(turn.getState().getClass());
        
        try {
            turn.receiveMove("pass");
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        String state = "EndTurn";

        assertEquals(state, lastName(turn.getState().toString(),state.length()+1));

    }
}
