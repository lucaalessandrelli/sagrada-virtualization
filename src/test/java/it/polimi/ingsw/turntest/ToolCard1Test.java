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

public class ToolCard1Test {


    @Test
    void TestingCardWrongMoves(){
        ToolCard tester = new ToolCard();
        CardContainer container = new CardContainer();
        ArrayList<ToolCard> toolCardArrayList = new ArrayList<>();
        toolCardArrayList = container.pullOutTools();
        while(toolCardArrayList.get(0).getID()!=1 && toolCardArrayList.get(1).getID()!= 1 && toolCardArrayList.get(2).getID()!=1 ){
            container = new CardContainer();
            toolCardArrayList = container.pullOutTools();
        }
        if(toolCardArrayList.get(0).getID()==1)
            tester = toolCardArrayList.get(0);

        if(toolCardArrayList.get(1).getID()==1)
            tester = toolCardArrayList.get(1);

        if(toolCardArrayList.get(2).getID()==1)
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

        Round round = new Round(players,1,table);

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

        Turn turn = new Turn(p1,round,1,true,table);

        turn.startTurn();

        ToolCard finalTester = tester;
        assertThrows(WrongMoveException.class,()->{turn.receiveMove(finalTester);});

    }

    @Test
    void TestingCard(){
        ToolCard tester = new ToolCard();
        CardContainer container = new CardContainer();
        ArrayList<ToolCard> toolCardArrayList = container.pullOutTools();

        while(toolCardArrayList.get(0).getID()!=1 && toolCardArrayList.get(1).getID()!= 1 && toolCardArrayList.get(2).getID()!=1 ){
            container = new CardContainer();
            toolCardArrayList = container.pullOutTools();
        }
        if(toolCardArrayList.get(0).getID()==1)
            tester = toolCardArrayList.get(0);

        if(toolCardArrayList.get(1).getID()==1)
            tester = toolCardArrayList.get(1);

        if(toolCardArrayList.get(2).getID()==1)
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

        while(players.get(0).getToolCards().get(0).getID() != 1 && players.get(0).getToolCards().get(1).getID() != 1 && players.get(0).getToolCards().get(2).getID() != 1){
            table = new Table(players);
            table.initialize();
        }

        Round round = new Round(players,1,table);

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

    }
}
