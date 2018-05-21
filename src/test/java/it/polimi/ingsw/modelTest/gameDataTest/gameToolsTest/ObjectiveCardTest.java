package it.polimi.ingsw.modelTest.gameDataTest.gameToolsTest;

import it.polimi.ingsw.model.gameData.Colour;
import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.Property;
import it.polimi.ingsw.model.gameData.gameTools.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObjectiveCardTest {

    @Test
    public void TestCardOne(){
        ObjectiveCard tester = new ObjectiveCard();
        CardContainer container = new CardContainer();
        ArrayList<ObjectiveCard> objectiveCards = new ArrayList<>();
        ArrayList<ArrayList<Cell>> matr = new ArrayList<>(4);
        WindowPatternCard windowPatternCard = new WindowPatternCard();
        Dice d = new Dice(Colour.BLUE);
        objectiveCards = container.pullOutPublic();
        int i = 0, j = 1, k = 2;
        boolean x = true;
        while(x) {
            container = new CardContainer();
            objectiveCards = container.pullOutPublic();
            if ((objectiveCards.get(i).getID() == 1)) {
                x = false;
            }
            if ((objectiveCards.get(j).getID() == 1)) {
                i = j;
                x = false;
            }
            if ((objectiveCards.get(k).getID() == 1)) {
                i = k;
                x = false;
            }
        }
        tester = objectiveCards.get(i);
        tester.show();
        windowPatternCard.placeDice(d,0,0);
        d = new Dice(Colour.YELLOW);
        windowPatternCard.placeDice(d,0,1);
        d = new Dice(Colour.RED);
        windowPatternCard.placeDice(d,0,2);
        d = new Dice(Colour.PURPLE);
        windowPatternCard.placeDice(d,0,3);
        d = new Dice(Colour.GREEN);
        windowPatternCard.placeDice(d,0,4);
        windowPatternCard.show();
        assertEquals(6,tester.finalpoints(windowPatternCard));
        int s1,s2;
        s1 = windowPatternCard.getMatr().size();
        for(i = 1; i < s1; i++){
            d = new Dice(Colour.BLUE);
            windowPatternCard.placeDice(d,i,0);
            d = new Dice(Colour.YELLOW);
            windowPatternCard.placeDice(d,i,1);
            d = new Dice(Colour.RED);
            windowPatternCard.placeDice(d,i,2);
            d = new Dice(Colour.PURPLE);
            windowPatternCard.placeDice(d,i,3);
            d = new Dice(Colour.GREEN);
            windowPatternCard.placeDice(d,i,4);
            windowPatternCard.show();
            assertEquals((i+1)*6,tester.finalpoints(windowPatternCard));
        }
    }

     /*How to extract random dices
    int randomNum;
    Random rand = new Random();
    for(int i = 0; i < 4; i++){
            for(int j = 0; j < 5; j++) {
                randomNum = rand.nextInt(4);
                p = new Property(coll[randomNum]);
                d = new Dice(p);
                windowPatternCard.placeDice(d,i,j);
            }
        }
     */
}

