package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gameData.Player;
import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;
import it.polimi.ingsw.model.gameLogic.Checker.InspectContextTool;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorContext;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorPlace;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorPlaceTool;
import it.polimi.ingsw.model.gameLogic.ModelModifier;
import it.polimi.ingsw.turn.moveexceptions.WrongMoveException;

import java.lang.reflect.*;
import java.util.ArrayList;

/**
 * Turn class: manages the turn of a given player through a state machine
 */
public class Turn {
    private Player player;
    private TurnState state;
    private InspectorContext inspectorContext;
    private InspectorPlace inspectorPlace;
    private InspectContextTool inspectContextTool;
    private InspectorPlaceTool inspectorPlaceTool;
    private boolean firstBracket;
    private int roundNumber;
    private ToolCard toolCard;

    private ArrayList<String> toolStateList;
    private ArrayList<String> toolAutomatedOperationList;
    private int indexList = 0;
    private TurnState checkPointState;
    private ModelModifier modifier;

    /**
     * Turn constructor
     *
     * @param player The player to whom the turn is assigned
     * @param roundNumber The number of the current round
     * @param firstBracket Boolean value: true if it's the player first turn in the current round
     */
    public Turn(Player player,int roundNumber, boolean firstBracket) {
        this.player = player;
        this.inspectorContext = new InspectorContext();
        this.roundNumber = roundNumber;
        this.firstBracket = firstBracket;

        this.inspectorPlace = new InspectorPlace();
        //this.inspectContextTool = new InspectContextTool(player.getWindowPatternCard(),player.getDraftPool(), player.getRoundTrack());
        //this.inspectorPlaceTool = new InspectorPlaceTool(player.getWindowPatternCard());
        this.modifier = new ModelModifier(player.getDraftPool(), player.getWindowPatternCard(),player.getRoundTrack());
    }

    /**
     * Called by Round to actually start the turn of a given player
     */
    public void startTurn() {
        this.state = new StartTurn(this);
    }

    /**
     * Called by concrete states in order to change the state of the turn
     * @param state New state to be set
     */
    public void setState(TurnState state) {
        this.state = state;
        this.checkEndState();
    }

    /**
     * Called by concrete states in order to know which round is currently being played by a player
     * @return The round number which is currently being played by a player
     */
    public int getRoundNumber() {
        return this.roundNumber;
    }

    public boolean isFirstBracket() {
        return this.firstBracket;
    }

    //GETTING MOVE METHODS
    public void receiveMove(ToolCard toolCard) {
        try {
            state.receiveMove(toolCard);
        } catch (WrongMoveException e) {

        }
    }

    public void receiveMove(Dice dice, Pos pos) {
        try {
            state.receiveMove(dice, pos);
        } catch (WrongMoveException e) {

        }
    }

    public void receiveMove(Pos pos) {
        try {
            state.receiveMove(pos);
        } catch (WrongMoveException e) {

        }
    }

    public void setDynamicState(Dice dice, Pos pos, Dice toolDice, Pos toolPos) {
        String nextStateName = toolStateList.get(indexList);

        if(!nextStateName.equals("CheckPointState")) {
            try {
                Class cls = Class.forName("it.polimi.ingsw.turn."+ nextStateName);

                Class[] parametersTypes = new Class[5];
                parametersTypes [0] = Turn.class;
                parametersTypes [1] = Dice.class;
                parametersTypes [2] = Pos.class;
                parametersTypes [3] = Dice.class;
                parametersTypes [4] = Pos.class;


                Constructor ct = cls.getConstructor(parametersTypes );

                Object[] argumentList = new Object[5];
                argumentList[0] = this;
                argumentList[1] = dice;
                argumentList[2] = pos;
                argumentList[3] = toolDice;
                argumentList[4] = toolPos;

                this.setState((TurnState)ct.newInstance(argumentList));

                if(nextStateName.equals("AutomatedOperation")) {
                    ((AutomatedOperation)state).doAutomatedOperations(toolAutomatedOperationList);
                }
            } catch (Throwable e) {
                System.out.println(e);
            }

            //increase indexList
            indexList++;
        } else {
            this.setState(checkPointState);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public InspectorContext getInspectorContext() {
        return inspectorContext;
    }

    public InspectorPlace getInspectorPlace() {
        return inspectorPlace;
    }

    public InspectorPlaceTool getInspectorPlaceTool() {
        return inspectorPlaceTool;
    }

    public InspectContextTool getInspectContextTool() {
        return inspectContextTool;
    }

    public void setCheckPointState(TurnState checkPointState) {
        this.checkPointState = checkPointState;
    }

    public ModelModifier getModifier() {
        return modifier;
    }

    public void notifyEndRound() {
        //call a method on round instance
    }

    private void checkEndState() {
        state.automaticPass();
    }

    public void setToolCardInfo(ToolCard toolCard) {
        this.setToolCard(toolCard);
        this.setToolStateList(toolCard.getStateList());
        this.setToolAutomatedOperationList(toolCard.getAutomatedoperationlist());
    }

    private void setToolAutomatedOperationList(ArrayList<String> toolAutomatedOperationList) {
        this.toolAutomatedOperationList = toolAutomatedOperationList;
    }

    private void setToolStateList(ArrayList<String> toolStateList) {
        this.toolStateList = toolStateList;
    }

    public void setToolCard(ToolCard toolCard) {
        this.toolCard = toolCard;
    }

    public ToolCard getToolCard() {
        return toolCard;
    }
}
