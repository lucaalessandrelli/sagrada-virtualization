package it.polimi.ingsw.model.gameData;

import it.polimi.ingsw.model.gameData.gameTools.*;

import java.util.ArrayList;
import java.util.List;

//I used ArrayList for data structures but we'll see better in the future which one is the best for this situation
public class Table {
    private ArrayList<Player> myplayer= new ArrayList<Player>();
    private ArrayList<ObjectiveCard> objectiveCards = new ArrayList<ObjectiveCard>(4);
    private ArrayList<ToolCard> toolCards = new ArrayList<ToolCard>(3);
    private ArrayList<WindowPatternCard> windowPatternCards = new ArrayList<WindowPatternCard>();
    private DraftPool draftPool;
    private DiceBag diceBag;
    private CardContainer container;


    public Table(ArrayList<Player> players){
        this.diceBag = new DiceBag();
        this.draftPool = new DraftPool();
        this.container = new CardContainer();
        this.myplayer = players;
        int i;
        for(i = 0; i < 3; i++){
            ToolCard x = new ToolCard();
            //x.getCard()?? To take a casual tool card
            this.toolCards.add(x);
        }
            this.objectiveCards = this.container.pullOutPublic();
            ArrayList<ObjectiveCard> tmp = this.container.pullOutPrivate(this.myplayer.size());
            i = 0;
        for (Player p: this.myplayer){
            p.setMyObjCard(tmp.get(i));
            i++;
        }
        this.windowPatternCards = this.container.pullOutPattern(this.myplayer.size());
    }

    public Table(ArrayList<Player> myplayer, ArrayList<ObjectiveCard> objectiveCards, ArrayList<ToolCard> toolCards, ArrayList<WindowPatternCard> windowPatternCards) {
        this.myplayer = myplayer;
        this.objectiveCards = objectiveCards;
        this.toolCards = toolCards;
        this.windowPatternCards = windowPatternCards;
    }

    public boolean addCard(WindowPatternCard z) throws IndexOutOfBoundsException{
            if(this.windowPatternCards.size() < 4) {
                this.windowPatternCards.add(z);
                return true;
            }
            else
                return false;
    }
    //Implement this in the other class
    public void showPlayers() {
        for (Player x : this.myplayer) {
            System.out.print("Player: " + x.getUsername() + "\n");
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

    //method not ready to use
    public ObjectiveCard getObjCard() {
        return null;
    }

    //NB!!!! This method is not ready to use because I need to write pullOutCards in the right way
    public ArrayList<WindowPatternCard> getRandomWindows() {
        return null;
        //return this.container.pullOutCards();
    }

    public ArrayList<Dice> getDiceFromBag() {
        return diceBag.pullOut();
    }

    public List<Dice> getAllDraft() {
        return this.draftPool.getDraftPool();
    }
}