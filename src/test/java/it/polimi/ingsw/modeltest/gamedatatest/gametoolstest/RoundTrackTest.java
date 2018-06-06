package it.polimi.ingsw.modeltest.gamedatatest.gametoolstest;

import it.polimi.ingsw.model.gamedata.Colour;
import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.Property;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.gametools.RoundTrack;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoundTrackTest {


    /**
     * Testing getter and setter
     */
    @Test
    public void TestGet_And_Set(){
        RoundTrack roundTrack = new RoundTrack();
        List<List<Dice>> arrayList = roundTrack.getRoundTrack(); //Testing the getter method
        List<Dice> dices = new ArrayList<>();
        Dice dice = new Dice(Colour.GREEN);
        dices.add(dice);
        roundTrack.setDiceOnRoundTrack(1,dices); //Testing the setter method
        dices = new ArrayList<>();
        addRandomDices(dices,10); //Using method to generate random dices with random colours and random numbers
        roundTrack.setDiceOnRoundTrack(2,dices); //Adding dices to the round
        assertFalse(arrayList.isEmpty()); //Assuring that the dices have been added
        assertTrue(arrayList.contains(roundTrack.getDiceOnRoundtrack(0)));
    }

    /**
     * Testing Find method and Switch method
     */
    @Test
    public void TestFind_And_Switch(){
        RoundTrack roundTrack = new RoundTrack();
        List<Dice> dices = new ArrayList<>();
        Dice dice = new Dice(Colour.GREEN);
        dices.add(dice);
        roundTrack.setDiceOnRoundTrack(1,dices); //Testing the setter method
        dices = new ArrayList<>();
        addRandomDices(dices,10); //Using method to generate random dices with random colours and random numbers
        roundTrack.setDiceOnRoundTrack(2,dices); //Adding dices to the round
        assertTrue(roundTrack.findDice(dice,new Pos(1,1)));
        assertTrue(dice.equals(roundTrack.switchDice(dice)));
    }

    private void addRandomDices(List<Dice> dices,int num){
        Dice dice;
        int randomNum;
        Random rand = new Random();
        Colour coll[] = new Colour[5];
        coll[0] = Colour.BLUE;
        coll[1] = Colour.GREEN;
        coll[2] = Colour.PURPLE;
        coll[3] = Colour.RED;
        coll[4] = Colour.YELLOW;
        Property p;
        for(int j = 0; j < num; j++) {
            randomNum = rand.nextInt(4);
            p = new Property(coll[randomNum],true);
            dice = new Dice(p);
            dices.add(dice);
        }
    }
}
