package it.polimi.ingsw;

import it.polimi.ingsw.model.gameData.Player;
import it.polimi.ingsw.model.gameData.ToolCard;
import it.polimi.ingsw.model.gameLogic.Move;
import it.polimi.ingsw.turn.Turn;
import org.junit.jupiter.api.Test;

public class TurnTest {
    @Test
    public void instanceTest() {
        Move move = new ToolCard();
        Player player = new Player("1");

        Turn turn = new Turn(player);
        turn.startTurn();

    }
}