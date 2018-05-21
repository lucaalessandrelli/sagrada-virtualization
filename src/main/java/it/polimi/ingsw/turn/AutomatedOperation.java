package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;
import it.polimi.ingsw.model.gameLogic.ModelModifier;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class AutomatedOperation implements TurnState {
    private Dice chosenDice;
    private Dice toolDice;
    private Pos posChosenDice;
    private Pos toolPos;
    private Turn turn;

    /* problema: aggiungere la lista di automated operation in turno, così posso chiamare il metodo dalla classe AutomatedOperation
        che uno alla volta mi scorre la lista e mi chaima i metodi della classe CambiaModel.
        Problema: io creo lo stato di AutomatedOperation ma come faccio a far partire i metodi?? utilizzo un metodo implementato sull'interfaccia
        e lo chiamo sullo stato quando la stringa presa con la reflection è "automatedOperation?"
        problema: che parametri devo passare al metodo che mi modifica la view??? Devo passarglieli il più possibili generali così lui ha tutto ciò
        che gli serve per modificare il model
    * */
    public AutomatedOperation(Turn turn, Dice chosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos) {
        this.turn = turn;
        this.toolDice = toolDice;
        this.toolPos = toolPos;
        this.posChosenDice = posChosenDice;
        this.chosenDice = chosenDice;
    }

    public void doAutomatedOperations(ArrayList<String> toolAutomatedOperationList) {
        String nextOperationName;
        ModelModifier modifier = turn.getModifier();

        for(int i = 0; i < toolAutomatedOperationList.size();i++) {
            nextOperationName = toolAutomatedOperationList.get(i);

            if(nextOperationName != "operationEnded") {
                //use java reflection to create an instance of the dynamic state and call method setState(dynamicState);
                try {
                    Class cls = Class.forName("it.polimi.ingsw.model.gameLogic.ModelModifier");

                    Class partypes[] = new Class[4];
                    partypes[0] = Dice.class;
                    partypes[1] = Pos.class;
                    partypes[2] = Dice.class;
                    partypes[3] = Pos.class;

                    Method method = cls.getMethod(nextOperationName, partypes);

                    Object arglist[] = new Object[4];
                    arglist[0] = chosenDice;
                    arglist[1] = posChosenDice;
                    arglist[2] = toolDice;
                    arglist[3] = toolPos;

                    method.invoke(modifier, arglist);
                } catch (Throwable e) {
                    System.out.println(e);
                }

                //increase indexList
                i++;
            } else {

                turn.setDynamicState(chosenDice,posChosenDice, toolDice, toolPos);
            }
        }
    }

}
