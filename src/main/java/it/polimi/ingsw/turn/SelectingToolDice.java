package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamelogic.checker.InspectorContext;
import it.polimi.ingsw.model.gamelogic.checker.InspectorContextTool;
import it.polimi.ingsw.turn.moveexceptions.WrongMoveException;

public class SelectingToolDice implements TurnState {
    private Turn turn;
    private Dice chosenDice;
    private Pos posChosenDice;
    private Dice toolDice;
    private Pos toolPos;
    private InspectorContextTool inspectorContextTool;

    public SelectingToolDice(Turn turn, Dice chosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos) {
        this.turn = turn;
        this.chosenDice = chosenDice;
        this.posChosenDice = posChosenDice;
        this.toolDice = toolDice;
        this.toolPos = toolPos;
        this.inspectorContextTool = turn.getInspectorContextTool();
    }

    //GETTING MOVE METHODS
    @Override
    public void receiveMove(Dice toolDice,Pos toolPos) throws WrongMoveException {
        if(inspectorContextTool.check(toolDice,toolPos,turn.getToolCard())) {
            turn.setDynamicState(chosenDice,posChosenDice,toolDice,toolPos);
        } else {
            throw new WrongMoveException("Mossa sbagliata: non è possibile scegliere questo dado.");
        }

        /*
        * Se l'inspector mi dice che ho scelto un dado dalla riserva allora vuol dire che il giocatore vuole andare avanti
        * probema: ho già preso il dado e la sua posizione. Ora dovrei andare nello stato ChoseDice2 ma non posso
        * perchè altrimenti posso far posizionare due dadi se la carta 12 la utilizzo dopo aver scelto e posizionato un dado.
        *
        * Possibile soluzione?
        * */
    }

    @Override
    public void receiveMove(String pass) {
        turn.setState(new EndTurn(turn));
    }
}
