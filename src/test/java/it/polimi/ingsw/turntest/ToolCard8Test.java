package it.polimi.ingsw.turntest;

import it.polimi.ingsw.Match;
import it.polimi.ingsw.controller.Manager;
import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.PublicObjects;
import it.polimi.ingsw.model.gamedata.Table;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToolCard8Test {

    @Test
    void TestAllowedMoves() {
        ToolCard tester = pullOutThatCard(8);

        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player("one");
        Player p2 = new Player("two");
        Player p3 = new Player("three");
        Player p4 = new Player("four");
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        Table table = setThatCard(8,players);

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

        Match match = new Match(players, new Manager(), 0);

        Round round = new Round(players, 1, table, match);

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

        String state = "StartTurn";

        assertEquals(state, lastName(turn.getState().getClass().toString(), state.length()+1));

        Dice d = table.getDraftPool().chooseDice(4);

        Pos pos = new Pos(4, 5);

        try {
            turn.receiveMove(d, pos);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        state = "ChooseDice1";

        assertEquals(state, lastName(turn.getState().getClass().toString(), state.length()+1));


        pos.setX(1);
        pos.setY(4);
        try {
            turn.receiveMove(pos);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        state = "PositionDice1";

        assertEquals(state, lastName(turn.getState().getClass().toString(), state.length()+1));

        try {
            turn.receiveMove(tester);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }

        state = "SelectingDraftDice";

        assertEquals(state, lastName(turn.getState().getClass().toString(), state.length()+1));

        try {
            turn.receiveMove(p1.getDraftPool().chooseDice(3),new Pos(3,5));
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }



    }


}
