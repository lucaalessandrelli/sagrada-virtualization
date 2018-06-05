package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.Table;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.gametools.ToolCard;
import it.polimi.ingsw.model.gamelogic.Round;
import it.polimi.ingsw.model.gamelogic.checker.InspectorContext;
import it.polimi.ingsw.model.gamelogic.checker.InspectorContextTool;
import it.polimi.ingsw.model.gamelogic.checker.InspectorPlace;
import it.polimi.ingsw.model.gamelogic.checker.InspectorPlaceTool;
import it.polimi.ingsw.model.gamelogic.ModelModifier;
import it.polimi.ingsw.turn.moveexceptions.WrongMoveException;

import java.lang.reflect.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Turn class: manages the turn of a given player through a state machine
 */
public class Turn {
    private Player player;
    private TurnState state;
    private InspectorContext inspectorContext;
    private InspectorPlace inspectorPlace;
    private InspectorContextTool inspectorContextTool;
    private InspectorPlaceTool inspectorPlaceTool;
    private boolean firstBracket;
    private int roundNumber;
    private ToolCard toolCard;
    private Round round;
    private Table table;

    private ArrayList<String> toolStateList;
    private ArrayList<String> toolAutomatedOperationList;
    private int indexList = 0;
    private TurnState checkPointState;
    private ModelModifier modifier;

    /**
     * Turn constructor
     * @param player The player to whom the turn is assigned
     * @param roundNumber The number of the current round
     * @param firstBracket Boolean value: true if it's the player first turn in the current round
     */
    public Turn(Player player,Round round,int roundNumber, boolean firstBracket,Table table) {
        this.player = player;
        this.inspectorContext = new InspectorContext();
        this.roundNumber = roundNumber;
        this.firstBracket = firstBracket;

        this.inspectorPlace = new InspectorPlace();
        this.table = table;
        this.round = round;
        this.inspectorContextTool = new InspectorContextTool(player.getWindowPatternCard(),player.getDraftPool(), player.getRoundTrack());
        this.inspectorPlaceTool = new InspectorPlaceTool(player.getWindowPatternCard());
        this.modifier = new ModelModifier(table,player);
    }

    /**
     * Called by Round to actually start the turn of a given player
     */
    public void startTurn() {
        this.state = new StartTurn(this);
    }

    /**
     * Called by concrete states to change turn state dynamically
     * @param dice Dice chosen before ToolCard
     * @param pos Position of the dice
     * @param toolDice Dice chosen during ToolCard moves
     * @param toolPos Position of the toolDice
     */
    public void setDynamicState(Dice dice, Pos pos, Dice toolDice, Pos toolPos) {
        String nextStateName = toolStateList.get(indexList);

        if(!nextStateName.equals("CheckPointState")) {
            try {

                String path =("it.polimi.ingsw.turn."+nextStateName).replaceAll("\\s+","");
                Class cls = Class.forName(path);

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
                e.printStackTrace();
            }

            //increase indexList
            indexList++;
        } else {
            this.setState(checkPointState);
        }
    }

    /**
     * Notifies the round that the current turn ended
     */
    public void notifyEndRound() {
        round.interrupt();
    }

    /**
     * Method called every time the turn change state in order to know when the state is EndTurn
     */
    private void checkEndState() {
        state.automaticPass();
    }



    //SETTER METHODS

    /**
     * Called by concrete states in order to change the state of the turn
     * @param state New state to be set
     */
    public void setState(TurnState state) {
        this.state = state;
        this.checkEndState();
    }

    /**
     * Called by concrete states when entering a dynamic machine state in order to know where to return after the end of the toolCard moves
     * @param checkPointState TurnState object
     */
    public void setCheckPointState(TurnState checkPointState) {
        this.checkPointState = checkPointState;
    }

    /**
     * Called by concrete states when the player choose a toolCard in order to save information about that will be used by the next states
     * @param toolCard ToolCard object
     */
    public void setToolCardInfo(ToolCard toolCard) {
        this.setToolCard(toolCard);
        this.setToolStateList(toolCard.getStateList());
        this.setToolAutomatedOperationList(toolCard.getAutomatedoperationlist());
    }

    /**
     * Set the list of the automated operation that need to be done by the given ToolCard
     * @param toolAutomatedOperationList List object
     */
    private void setToolAutomatedOperationList(ArrayList<String> toolAutomatedOperationList) {
        this.toolAutomatedOperationList = toolAutomatedOperationList;
    }

    /**
     * Set the list of the next states that manage the given ToolCard
     * @param toolStateList List object
     */
    private void setToolStateList(ArrayList<String> toolStateList) {
        this.toolStateList = toolStateList;
    }

    /**
     * Set the ToolCard chosen by the player
     * @param toolCard ToolCard object
     */
    public void setToolCard(ToolCard toolCard) {
        this.toolCard = toolCard;
    }


    //GETTER METHODS
    /**
     * Called by concrete states in order to know which round is currently being played by a player
     * @return The round number which is currently being played by a player
     */
    public int getRoundNumber() {
        return this.roundNumber;
    }

    /**
     * Called by concrete states in order to know if it's the player first turn in the current round
     * @return firstBracket
     */
    public boolean isFirstBracket() {
        return this.firstBracket;
    }

    /**
     * Called by concrete states to know which player is currently playing
     * @return The player which is playing the turn
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Called by concrete states in order to call context inspecting methods on a given player standard move
     * @return InspectorContext object
     */
    public InspectorContext getInspectorContext() {
        return inspectorContext;
    }

    /**
     * Called by concrete states in order to call placing inspecting methods on a given player standard move
     * @return InspectorPlace object
     */
    public InspectorPlace getInspectorPlace() {
        return inspectorPlace;
    }

    /**
     * Called by concrete states in order to call context inspecting methods on a given player tool move
     * @return InspectorContextTool object
     */
    public InspectorContextTool getInspectorContextTool() {
        return inspectorContextTool;
    }

    /**
     * Called by concrete states in order to call placing inspecting methods on a given player tool move
     * @return InspectorPlaceTool object
     */
    public InspectorPlaceTool getInspectorPlaceTool() {
        return inspectorPlaceTool;
    }

    /**
     * Called by concrete states in order to call methods that modify the Model objects right after an accepted move
     * @return ModelModifier object
     */
    public ModelModifier getModifier() {
        return modifier;
    }

    /**
     * Called by concrete states in order to let the inspector do his job
     * @return ToolCardObject
     */
    public ToolCard getToolCard() {
        return toolCard;
    }

    /**
     * Called to know the current state
     * @return TurnState state
     */
    public TurnState getState() {
        return state;
    }


    //GETTING MOVE METHODS

    /**
     * Passing dice and position of the dice the payer just chosen to the concrete state
     * @param dice Dice object
     * @param pos Pos object
     */
    public void receiveMove(Dice dice, Pos pos) throws WrongMoveException {
            state.receiveMove(dice, pos);
    }

    /**
     * Passing the ToolCard the player just chosen to the concrete state
     * @param toolCard ToolCard object
     */
    public void receiveMove(ToolCard toolCard) throws WrongMoveException {
            state.receiveMove(toolCard);
    }

    /**
     * Passing the position the payer just chosen to the concrete state
     * @param pos Pos object
     */
    public void receiveMove(Pos pos) throws WrongMoveException {
            state.receiveMove(pos);
    }

    /**
     * Letting know the concrete states that the player would like to pass his turn
     * @param pass String object
     */
    public void receiveMove(String pass) throws WrongMoveException {
            state.receiveMove(pass);
    }
}