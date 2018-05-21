package it.polimi.ingsw.turn;


import it.polimi.ingsw.model.gameData.Player;
import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorContext;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorPlace;
import it.polimi.ingsw.model.gameLogic.ModelModifier;

import java.lang.reflect.*;

import java.util.ArrayList;


public class Turn {
    private Player player;
    private TurnState state;
    private InspectorContext inspectorContext;
    private InspectorPlace inspectorPlace;
    private boolean firstBracket;
    private int roundNumber;

    private ToolCard toolCard;
    private ArrayList<String> toolStateList;
    private ArrayList<String> toolAutomatedOperationList;
    private int indexList = 0;
    private TurnState checkPointState;
    private ModelModifier modifier;

    public Turn(Player player,int roundNumber, boolean firstBracket) {
        this.player = player;
        inspectorContext = new InspectorContext();
        inspectorPlace = new InspectorPlace();
        this.roundNumber = roundNumber;
        this.firstBracket = firstBracket;
        //this.modifier = new ModelModifier(player.getDraftPool(), player.getWindowPatternCard(),player.getRoundTrack());
    }

    public TurnState getState() {
        return state;
    }

    public void setState(TurnState state) {
        this.state = state;
        this.checkEndState();
    }

    //GETTING MOVE METHODS
    public void receiveMove(ToolCard toolCard) {
        state.receiveMove(toolCard);
    }

    public void receiveMove(Dice dice, Pos pos) {
        state.receiveMove(dice, pos);
    }

    public void receiveMove(Pos pos) {
        state.receiveMove(pos);
    }

    public void setDynamicState(Dice dice, Pos pos, Dice toolDice, Pos toolPos) {
        String nextStateName = toolStateList.get(indexList);

        if(!nextStateName.equals("CheckPointState")) {
            //use java reflection to create an instance of the dynamic state and call method setState(dynamicState);
            try {
                Class cls = Class.forName("it.polimi.ingsw.turn."+ nextStateName);

                Class partypes[] = new Class[5];
                partypes[0] = Turn.class;
                partypes[1] = Dice.class;
                partypes[2] = Pos.class;
                partypes[3] = Dice.class;
                partypes[4] = Pos.class;


                Constructor ct = cls.getConstructor(partypes);

                Object arglist[] = new Object[5];
                arglist[0] = this;
                arglist[1] = dice;
                arglist[2] = pos;
                arglist[3] = toolDice;
                arglist[4] = toolPos;

                this.setState((TurnState)ct.newInstance(arglist));

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

    public boolean getFirstBracket() {
        return this.firstBracket;
    }
    public int getRoundNumber() {
        return this.roundNumber;
    }

    public void setCheckPointState(TurnState checkPointState) {
        this.checkPointState = checkPointState;
    }

    public TurnState getCheckPointState() {
        return checkPointState;
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
        this.toolCard = toolCard;
        this.setToolStateList(toolCard.getStateList());
        this.setToolAutomatedOperationList(toolCard.getAutomatedoperationlist());
    }

    private void setToolAutomatedOperationList(ArrayList<String> toolAutomatedOperationList) {
        this.toolAutomatedOperationList = toolAutomatedOperationList;
    }

    private void setToolStateList(ArrayList<String> toolStateList) {
        this.toolStateList = toolStateList;
    }
}
