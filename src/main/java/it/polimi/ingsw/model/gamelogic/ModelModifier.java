package it.polimi.ingsw.model.gamelogic;

import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.gametools.DraftPool;
import it.polimi.ingsw.model.gamedata.gametools.RoundTrack;
import it.polimi.ingsw.model.gamedata.gametools.WindowPatternCard;

public class ModelModifier {
    private DraftPool draftPool;
    private WindowPatternCard windowPatternCard;
    private RoundTrack roundTrack;

    public ModelModifier(DraftPool draftPool, WindowPatternCard windowPatternCard, RoundTrack roundTrack) {
        this.draftPool = draftPool;
        this.windowPatternCard = windowPatternCard;
        this.roundTrack = roundTrack;
    }

    //methods that modify the model

    //parameters needed: used all 4
    public void switchDice(Dice chosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos) {
        //switch dice
        //switch also the copy of the dice
    }

    //parameters needed: used first 2
    public void launchDice(Dice choosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos) {

    }

    //parameters needed: used 0
    public void resetDraftPool(Dice choosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos) {

    }

    //parameter needed: used first 2
    public void spinDice(Dice choosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos) {

    }

    //parameter needed: used first 2
    public void newDice(Dice choosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos) {

    }

    //parameters: Dice previously chosen and Pos
    public void positionDice(Dice chosenDice, Pos posChosenDice, Pos pos) {
        //position dice
    }

    public void changeDiceValue(Dice chosenDice, Pos posChosenDice, Dice toolDice) {
        //set the value of chosenDice to the toolDice one
    }
}
