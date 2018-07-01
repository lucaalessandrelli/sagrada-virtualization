package it.polimi.ingsw.model.gamelogic;

import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.Table;
import it.polimi.ingsw.model.gamedata.gametools.*;

import java.util.List;

public class ModelModifier {
    private static final int NOT_USED = 1;
    private RoundTrack roundTrack;
    private DraftPool draftPool;
    private WindowPatternCard windowPatternCard;
    private DiceBag diceBag;
    private List<ToolCard> toolCards;

    public ModelModifier(Table table, Player player) {
        this.roundTrack = table.getRoundTrack();
        this.draftPool = table.getDraftPool();
        this.windowPatternCard = player.getWindowPatternCard();
        this.diceBag = table.getDiceBag();
        this.toolCards = table.getToolCards();
    }

    //methods that modify the model

    public void updateToolCardPrice(ToolCard toolCard,Player player) {
        for (ToolCard card: toolCards) {
            if(toolCard.getID() == card.getID()) {
                updatePlayerTokens(player,card.getCost());
                card.setUsed();
            }
        }
    }

    private void updatePlayerTokens(Player player,int toolCardCost) {
        if(toolCardCost == NOT_USED) {
            player.useToken(false);
        } else {
            player.useToken(true);
        }
    }

    //parameters needed: used all 4
    public void switchDice(Dice chosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos) {
        Dice draftDice = new Dice(draftPool.chooseDice(posChosenDice.getX()).getColour());
        draftDice.setNumber(draftPool.chooseDice(posChosenDice.getX()).getNumber());
        Dice roundDice = new Dice(roundTrack.getDice(toolPos).getColour());
        roundDice.setNumber(roundTrack.getDice(toolPos).getNumber());
        //draftPool.removeDice(posChosenDice.getX());

        draftPool.setDice(roundDice,posChosenDice.getX());
        roundTrack.setDice(draftDice,toolPos);

        //switch also the copy of the dice
        chosenDice = toolDice;
        posChosenDice = toolPos;
    }

    //parameters needed: used first 2
    public void launchDice(Dice chosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos) {
        chosenDice.rollDice();
        draftPool.chooseDice(posChosenDice.getX()).setNumber(chosenDice.getNumber());
    }

    /**
     * Automatic move that move dices from the DraftPool to the DiceBag and then pulls out randomly the same amount of dices
     * @param chosenDice Dice chosen during normal turn
     * @param posChosenDice Position of the dice chosen during normal turn
     * @param toolDice Dice chosen during toolCard
     * @param toolPos Position of the dice chosen during toolCard
     */
    /*public void resetDraftPool(Dice chosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos) {
        for (Dice dice: draftPool.getDraftPool()) {
            diceBag.addDice(dice);
        }

        draftPool.addNewDices(diceBag.pullOut(draftPool.getNumOfDices()));
    }*/

    public void resetDraftPool(Dice chosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos){
        for(Dice dice: draftPool.getDraftPool()){
            dice.rollDice();
        }
    }

    //parameter needed: used first 2
    public void spinDice(Dice chosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos) {
        switch (chosenDice.getNumber()) {
            case 1: chosenDice.setNumber(6);
                break;
            case 2: chosenDice.setNumber(5);
                break;
            case 3: chosenDice.setNumber(4);
                break;
            case 4: chosenDice.setNumber(3);
                break;
            case 5: chosenDice.setNumber(2);
                break;
            case 6: chosenDice.setNumber(1);
                break;
                default:
        }

        draftPool.chooseDice(posChosenDice.getX()).setNumber(chosenDice.getNumber());
    }

    //parameter needed: used first 2
    public void newDice(Dice chosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos) {
        diceBag.addDice(chosenDice);
        draftPool.setDice(diceBag.pullOut(1).get(0),posChosenDice.getX());
    }

    //parameters: Dice previously chosen and Pos
    public void positionDiceFromDraft(Dice chosenDice, Pos posChosenDice, Pos pos) {
        windowPatternCard.placeDice(draftPool.chooseDice(posChosenDice.getX()), pos.getX(),pos.getY());
        draftPool.removeDice(posChosenDice.getX());
    }
    public void positionDiceFromWindow(Dice chosenDice, Pos posChosenDice, Pos pos) {
        Dice d = windowPatternCard.getDice(posChosenDice);
        windowPatternCard.removeDice(posChosenDice);
        windowPatternCard.placeDice(d, pos.getX(),pos.getY());
    }

    public void changeDiceValue(Dice chosenDice, Pos posChosenDice, Dice toolDice) {
        //set the value of chosenDice to the toolDice one
        chosenDice.setNumber(toolDice.getNumber());
        draftPool.chooseDice(posChosenDice.getX()).setNumber(toolDice.getNumber());
    }
}
