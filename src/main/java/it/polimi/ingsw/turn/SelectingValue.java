package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamelogic.checker.InspectorContextTool;
import it.polimi.ingsw.model.gamelogic.checker.InspectorContext;

public class SelectingValue implements TurnState {
    private Dice chosenDice;
    private Dice toolDice;
    private Pos posChosenDice;
    private Pos toolPos;
    private Turn turn;
    InspectorContext inspectorContext;
    private InspectorContextTool inspectorContextTool;

    public SelectingValue(Turn turn, Dice chosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos) {
        this.turn = turn;
        this.toolDice = toolDice;
        this.toolPos = toolPos;
        this.posChosenDice = posChosenDice;
        this.chosenDice = chosenDice;
        inspectorContext = turn.getInspectorContext();
        this.inspectorContextTool = turn.getInspectorContextTool();
    }

    //GETTING MOVE METHODS
    @Override
    public void receiveMove(Dice toolDice,Pos toolPos) {
        //chiamare inspector diversi
        //inspectContextTool.checkColourDice(chosenDice,posChosenDice,toolDice,toolPos);
        if(inspectorContext.check(toolDice,toolPos,turn.getPlayer().getDraftPool())) {
                //call modifier
                turn.getModifier().changeDiceValue(chosenDice,posChosenDice,toolDice);
                turn.setDynamicState(toolDice,toolPos,new Dice(), new Pos());
        } else {
          //throw wrong Dice exception
        }
    }
}
