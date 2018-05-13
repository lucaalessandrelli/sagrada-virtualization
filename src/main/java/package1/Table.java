package package1;

import java.util.ArrayList;
import java.util.List;

//I used ArrayList for data structures but we'll see better in the future which one is the best for this situation
public class Table {
    private RoundTrack roundTrack;
    private ScoreTrack scoreTrack;
    private ArrayList<ObjectiveCard> objectiveCards = new ArrayList<ObjectiveCard>(4);
    private ArrayList<ToolCard> toolCards = new ArrayList<ToolCard>(3);
    private ArrayList<WindowPatternCard> windowPatternCards = new ArrayList<WindowPatternCard>(4);
    private DraftPool draftPool;
    private DiceBag diceBag;


    public Table(){
        this.diceBag = new DiceBag();
        this.draftPool = new DraftPool();
        for(int i = 0; i < 3; i++){
            ToolCard x = new ToolCard();
            //x.getCard()?? To take a casual tool card
            this.toolCards.add(x);
        }
        for(int i = 0; i < 3; i++){
            ObjectiveCard x = new ObjectiveCard();
            //x.getCard()?? To take a casual tool card
            this.objectiveCards.add(x);
        }
    }

    public Table(RoundTrack roundTrack, ScoreTrack scoreTrack, ArrayList<ObjectiveCard> objectiveCards, ArrayList<ToolCard> toolCards, ArrayList<WindowPatternCard> windowPatternCards) {
        this.roundTrack = roundTrack;
        this.scoreTrack = scoreTrack;
        this.objectiveCards = objectiveCards;
        this.toolCards = toolCards;
        this.windowPatternCards = windowPatternCards;
    }

    public void addCard(WindowPatternCard z) throws IndexOutOfBoundsException{
        try{
            if(this.windowPatternCards.size() < 4)
                this.windowPatternCards.add(z);
        } catch (IndexOutOfBoundsException e){
            throw e;
        }
    }
    //Implement this in the other class
    public void showPlayers() {
        for (String x : this.scoreTrack.getPlayers()) {
            System.out.println("Player: " + x + "\n");
        }
    }

    public void showObjective() {
        for (ObjectiveCard y : this.objectiveCards) {
            y.show();
        }
    }

    public void showTool() {
        for (ToolCard z : this.toolCards) {
            z.show();
        }
    }

    public void showWindow() {
        for (WindowPatternCard k : this.windowPatternCards) {
            k.show();
        }
    }

    public void show(){
        this.showPlayers();
        this.showObjective();
        this.showTool();
        this.showWindow();
    }

    public ObjectiveCard getObjCard() {
        return null;
    }

    public ArrayList<WindowPatternCard> getRandomWindows() {
        return null;
    }

    public ArrayList<Dice> getDiceFromBag() {
        return null;
    }

    public List<Dice> getAllDraft() {
        return null;
    }
}