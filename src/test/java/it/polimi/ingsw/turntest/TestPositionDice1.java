package it.polimi.ingsw.turntest;

import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.PublicObjects;
import it.polimi.ingsw.model.gamedata.Table;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.gametools.WindowPatternCard;
import it.polimi.ingsw.model.gamelogic.Round;
import it.polimi.ingsw.turn.StartTurn;
import it.polimi.ingsw.turn.Turn;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static it.polimi.ingsw.turntest.TurnTest.lastName;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPositionDice1 {

    @Test
    void TestGoingEndTurn(){ArrayList<Player> players = new ArrayList<>();
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
        p1.ChooseWindow(windows);

        table.getDiceBag().setNumPlayers(4);
        table.getDraftPool().addNewDices(table.getDiceFromBag());

        PublicObjects publicObjects = new PublicObjects();
        publicObjects.setDraftPool(table.getDraftPool());
        publicObjects.setObjectiveCards(table.getObjCard());
        publicObjects.setToolCards(table.getToolCards());

        p1.setPublicObjects(publicObjects);

        Turn tester = new Turn(p1,round,1,true,table);

        StartTurn startTurn = new StartTurn(tester);

        tester.setState(startTurn);

        Dice d = table.getDraftPool().chooseDice(4);

        Pos pos = new Pos(4,5);

        tester.receiveMove(d,pos);

        String pass = "";

        tester.receiveMove(d,pos);

        pos.setX(1);
        pos.setY(2);
        tester.receiveMove(pos);

        tester.receiveMove(pass);

        assertEquals("EndTurn", lastName(tester.getState().toString(),8));
    }
}