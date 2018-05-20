package it.polimi.ingsw.turn;


import it.polimi.ingsw.model.gameData.Player;
import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorContext;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorPlace;
import java.lang.reflect.*;

import java.util.ArrayList;


public class Turn {
    private Player player;
    private TurnState state;
    private InspectorContext inspectorContext;
    private InspectorPlace inspectorPlace;
    private boolean firstBracket;
    private int roundNumber;

    private ArrayList<String> toolStateList;
    private ArrayList<String> toolAutomatedOperationList;
    private int indexList = 0;
    private TurnState checkPointState;

    public Turn(Player p,int roundNumber, boolean firstBracket) {
        player = p;
        inspectorContext = new InspectorContext();
        inspectorPlace = new InspectorPlace();
        this.roundNumber = roundNumber;
        this.firstBracket = firstBracket;
    }

    public TurnState getState() {
        return state;
    }

    public void setState(TurnState state) {
        this.state = state;
        System.out.println("cambio stato");
    }

    public void startTurn() {
        this.state = new StartTurn(this);
    }

    public void viewChoice() {
        state.viewChoice();
    }

    //GETTING MOVE METHODS
    public void receiveMove(ToolCard toolCard) {
        state.receiveMove(toolCard);
    }

    public void receiveMove(Dice dice) {
        state.receiveMove(dice);
    }

    public void receiveMove(Pos pos) {
        state.receiveMove(pos);
    }

    public void setDynamicState(Dice dice) {
        String nextStateName = toolStateList.get(indexList);

        if(nextStateName != "CheckPointState") {
            //use java reflection to create an instance of the dynamic state and call method setState(dynamicState);
            try {
                Class cls = Class.forName("it.polimi.ingsw.turn."+ nextStateName);

                Class partypes[] = new Class[2];
                partypes[0] = Turn.class;
                partypes[1] = Dice.class;

                Constructor ct = cls.getConstructor(partypes);

                Object arglist[] = new Object[2];
                arglist[0] = this;
                arglist[1] = dice;

                this.setState((TurnState)ct.newInstance(arglist));
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

    public void setToolStateList(ArrayList<String> toolStateList) {
        this.toolStateList = toolStateList;
    }

    public void setCheckPointState(TurnState checkPointState) {
        this.checkPointState = checkPointState;
    }

    public TurnState getCheckPointState() {
        return checkPointState;
    }

    public void setToolAutomatedOperationList(ArrayList<String> toolAutomatedOperationList) {
        this.toolAutomatedOperationList = toolAutomatedOperationList;
    }
}
