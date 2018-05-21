package it.polimi.ingsw.turn;

public class EndTurn implements TurnState {
    private Turn turn;

    public EndTurn(Turn turn) {
        this.turn = turn;
    }

    @Override
    public void automaticPass() {
        turn.notifyEndRound();
    }
}
