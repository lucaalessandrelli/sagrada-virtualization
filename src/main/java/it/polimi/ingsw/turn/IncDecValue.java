package it.polimi.ingsw.turn;


import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamelogic.checker.InspectorContextTool;

public class IncDecValue implements TurnState {
    private Turn turn;
    private Dice chosenDice;
    private Dice toolDice;
    private Pos posChosenDice;
    private Pos toolPos;
    private InspectorContextTool inspectContextTool;

    public IncDecValue(Turn turn, Dice chosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos) {
        this.turn = turn;
        this.toolDice = toolDice;
        this.toolPos = toolPos;
        this.posChosenDice = posChosenDice;
        this.chosenDice = chosenDice;
        //this.inspectContextTool = new InspectContextTool();
    }

    //GETTING MOVE METHODS
    @Override
    public void receiveMove(Dice toolDice, Pos toolPos) {
        //chiamare inspector diversi
        //inspectContextTool.checkNumDice(chosenDice,posChosenDice,toolDice,toolPos);
        /*if(inspectorContext.check(toolDice,toolPos,turn.getPlayer().getDraftPool())) {
                //call modifier so the value of the dice will be changed
                turn.getModifier().changeDiceValue(chosenDice,posDiceChosen,toolDice);
                turn.setDynamicState(toolDice,toolPos,new Dice(),new Pos());
        } else {
          //throw wrong Dice exception
        }*/
    }
}