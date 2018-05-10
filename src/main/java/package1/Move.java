package package1;

public class Move {
    private Pos chosenPos;
    private Dice chosenDice;
    private ToolCard toolCard;

    //chosen just pos
    public Move(Pos pos) {
        chosenPos = pos;
    }

    //chosen just toolcard
    public Move(ToolCard toolc) {
        toolCard = toolc;
    }

    //chosen just dice
    public Move (Dice dice){
        chosenDice = dice;
    }

    //getter method
    public Dice getChosenDice() throws NullPointerException {
        if (chosenDice != null) {
            return chosenDice;
        }else{
            throw new NullPointerException();
        }
    }

    //getter method
    public Pos getChosenPos() throws NullPointerException {
        if(chosenPos != null){
            return chosenPos;
        }else{
            throw new NullPointerException();
        }

    }

    //getter method
    public ToolCard getToolCard() throws NullPointerException {
        if (toolCard!= null) {
            return toolCard;
        }else {
            throw new NullPointerException();
        }
    }
}
