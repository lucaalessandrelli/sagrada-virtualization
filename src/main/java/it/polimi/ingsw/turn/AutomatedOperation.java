package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameLogic.ModelModifier;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class AutomatedOperation implements TurnState {
    private Dice chosenDice;
    private Dice toolDice;
    private Pos posChosenDice;
    private Pos toolPos;
    private Turn turn;

    public AutomatedOperation(Turn turn, Dice chosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos) {
        this.turn = turn;
        this.toolDice = toolDice;
        this.toolPos = toolPos;
        this.posChosenDice = posChosenDice;
        this.chosenDice = chosenDice;
    }

    public void doAutomatedOperations(ArrayList<String> toolAutomatedOperationList) {
        ModelModifier modifier = turn.getModifier();

        for (String nextOperationName: toolAutomatedOperationList) {
            if(!nextOperationName.equals("operationEnded")) {
                try {
                    Class cls = Class.forName("it.polimi.ingsw.model.gameLogic.ModelModifier");

                    Class[] parametersType = new Class[4];
                    parametersType[0] = Dice.class;
                    parametersType[1] = Pos.class;
                    parametersType[2] = Dice.class;
                    parametersType[3] = Pos.class;

                    Method method = cls.getMethod(nextOperationName, parametersType);

                    Object[] argumentList = new Object[4];
                    argumentList[0] = chosenDice;
                    argumentList[1] = posChosenDice;
                    argumentList[2] = toolDice;
                    argumentList[3] = toolPos;

                    method.invoke(modifier, argumentList);
                } catch (Throwable e) {
                    System.out.println(e);
                }
            } else {

                turn.setDynamicState(chosenDice,posChosenDice, toolDice, toolPos);
            }
        }
    }

}
