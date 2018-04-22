package package1;

public class Move {
    private Pos chosenPos;
    private Dice chosenDice;
    private ToolCard toolCard;

    //move without tool card
    public Move(Pos pos, Dice dice){

        chosenPos = pos;
        chosenDice = dice;
        toolCard = null;

    }
    //move with chosen tool card
    public Move(ToolCard  toolc){

        chosenPos = null;
        chosenDice = null;
        toolCard = toolc;
    }
    //check the move
    protected void check(){

    }
}
