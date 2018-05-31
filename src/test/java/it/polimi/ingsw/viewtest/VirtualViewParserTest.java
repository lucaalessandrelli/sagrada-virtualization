package it.polimi.ingsw.viewtest;

import it.polimi.ingsw.model.gamedata.gametools.DiceBag;
import it.polimi.ingsw.model.gamedata.gametools.DraftPool;
import it.polimi.ingsw.model.gamedata.gametools.WindowPatternCard;
import it.polimi.ingsw.controller.VirtualViewParser;
import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.modeltest.gamelogictest.InspectorPlaceTest.getWindowPatternCard;
import static org.junit.jupiter.api.Assertions.*;

public class VirtualViewParserTest {

    @Test
    void TestParsingFirstCard(){
        WindowPatternCard windowPatternCard;
        VirtualViewParser tester = new VirtualViewParser("Luca");

        windowPatternCard = getWindowPatternCard(4);

        windowPatternCard.setPlayer("Luca");

        System.out.println(tester.parseWindowPatternRestrictions(windowPatternCard));
    }

    @Test
    void TestParsingDraftPool(){
        String player = "Gionny";
        VirtualViewParser tester = new VirtualViewParser(player);
        DraftPool draftPool = new DraftPool();
        DiceBag diceBag = new DiceBag();
        diceBag.setNumPlayers(4);

        draftPool.addNewDices(diceBag.pullOut());
        
        String parsed = tester.parseDraftPool(draftPool);

        System.out.println(parsed);

        for(int i = 0, k = 0; i < draftPool.getNumOfDices(); i++, k = k+3){

            assertEquals(draftPool.chooseDice(i).getColour().toString().charAt(0), (parsed.charAt(15 + player.length() + k)));

            assertEquals(draftPool.chooseDice(i).getNumber(), (Character.getNumericValue(parsed.charAt(15 + player.length() + (k+1)))));
        }

    }

    @Test
    void TestParsingWindowDices(){
        String player = "Gionny";
        VirtualViewParser tester = new VirtualViewParser(player);
        DraftPool draftPool = new DraftPool();
        DiceBag diceBag = new DiceBag();
        diceBag.setNumPlayers(4);
        WindowPatternCard windowPatternCard;

        draftPool.addNewDices(diceBag.pullOut());

        windowPatternCard = new WindowPatternCard();

        windowPatternCard.placeDice(draftPool.chooseDice(0),0,0);
        windowPatternCard.placeDice(draftPool.chooseDice(1),0,2);
        windowPatternCard.placeDice(draftPool.chooseDice(2),1,1);
        windowPatternCard.placeDice(draftPool.chooseDice(3),2,3);
        windowPatternCard.placeDice(draftPool.chooseDice(4),3,2);


        String parsed = tester.parseWindowPatternDice(windowPatternCard);


        for(int i = 0, k = 0; i < 5; i++, k = k+5){
            assertEquals(draftPool.chooseDice(i).getColour().toString().charAt(0), (parsed.charAt(12 + player.length() +k)));

            assertEquals(draftPool.chooseDice(i).getNumber(), (Character.getNumericValue(parsed.charAt(12 + player.length() + (k+1)))));
        }

    }
}
