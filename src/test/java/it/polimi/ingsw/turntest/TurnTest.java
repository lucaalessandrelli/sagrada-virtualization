package it.polimi.ingsw.turntest;

import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.model.gamedata.PublicObjects;
import it.polimi.ingsw.model.gamedata.Table;
import it.polimi.ingsw.model.gamedata.gametools.WindowPatternCard;
import it.polimi.ingsw.model.gamelogic.Round;
import it.polimi.ingsw.model.gamelogic.checker.InspectorContextTool;
import it.polimi.ingsw.turn.Turn;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TurnTest {

    @Test
    void TestStartTurn(){
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
        p1.ChooseWindow(windows);

        table.getDiceBag().setNumPlayers(4);
        table.getDraftPool().addNewDices(table.getDiceFromBag());

        PublicObjects publicObjects = new PublicObjects();
        publicObjects.setDraftPool(table.getDraftPool());
        publicObjects.setObjectiveCards(table.getObjCard());
        publicObjects.setToolCards(table.getToolCards());

        p1.setPublicObjects(publicObjects);
        p1.getWindowPatternCard().show();

        InspectorContextTool inspectorContextTool = new InspectorContextTool(p1.getWindowPatternCard(),table.getDraftPool(),table.getRoundTrack());
        Turn tester = new Turn(p1,round,1,true,table);

        tester.startTurn();

        assertEquals("StartTurn",lastName(tester.getState().getClass().toString(),10));
    }

    public static String lastName(String type,int x){
        return type.substring((type.lastIndexOf(".")+1),(type.lastIndexOf(".")+x));
    }
}
