package it.polimi.ingsw.model.gamedata;

import it.polimi.ingsw.model.gamedata.gametools.*;

import java.util.ArrayList;
import java.util.List;

//I used ArrayList for data structures but we'll see better in the future which one is the best for this situation
public class Table {
    private List<Player> myplayers;
    private List<ObjectiveCard> objectiveCards;
    private List<ToolCard> toolCards = new ArrayList<>(3);
    private RoundTrack roundTrack;
    private DraftPool draftPool;
    private DiceBag diceBag;
    private CardContainer container;

    public Table(List<Player> players){
        this.diceBag = new DiceBag();
        this.draftPool = new DraftPool();
        this.container = new CardContainer();
        this.myplayers = players;
        this.roundTrack = new RoundTrack();
        this.initialize();
    }

    public void initialize(){
        List<WindowPatternCard> windowPatternCards;
        int i;
        this.toolCards = this.container.pullOutTools();
        this.objectiveCards = this.container.pullOutPublic();
        List<ObjectiveCard> tmp = this.container.pullOutPrivate(this.myplayers.size());
        i = 0;
        for (Player p: this.myplayers){
            p.setMyObjCard(tmp.get(i));
            i++;
        }
        windowPatternCards = this.container.pullOutPattern(this.myplayers.size());
        for (Player p: this.myplayers) {
            p.chooseWindow(windowPatternCards);
            windowPatternCards.remove(p.getWindowPatternCard());
        }

        this.diceBag.setNumPlayers(this.myplayers.size());
        this.draftPool.addNewDices(this.diceBag.pullOut());

        setPublicObjects();
    }

    //Implement this in the other class
    private void showPlayers() {
        for (Player x : this.myplayers) {
            System.out.print("Player: " + x.getUsername() + "\n");
        }
    }

    private void showObjective() {
        for (ObjectiveCard y : this.objectiveCards) {
            y.show();
        }
    }

    private void showTool() {
        for (ToolCard z : this.toolCards) {
            z.show();
        }
    }

    private void showWindow() {
        for(Player p: this.myplayers){
            p.getWindowPatternCard().show();
        }
    }

    public void show(){
        this.showPlayers();
        this.showObjective();
        this.showTool();
        this.showWindow();
    }

    //GETTER METHODS
    public List<ObjectiveCard> getObjCard() {
        return objectiveCards;
    }

    /*public ArrayList<WindowPatternCard> getRandomWindows() {
        return this.container.pullOutPattern(myplayers.size());
    }*/

    public List<Dice> getDiceFromBag() {
        return diceBag.pullOut();
    }

    public List<Dice> getAllDraft() {
        return this.draftPool.getDraftPool();
    }

    public DraftPool getDraftPool(){
        return this.draftPool;
    }

    public RoundTrack getRoundTrack(){
        return this.roundTrack;
    }


    public DiceBag getDiceBag() {
        return this.diceBag;
    }

    public List<ToolCard> getToolCards(){
        return this.toolCards;
    }

    public int getNumPlayers(){
        return this.myplayers.size();
    }

    public void setLastDices(int numRound){
        this.roundTrack.setDiceOnRoundTrack(numRound,this.draftPool.getDraftPool());
    }

    public void fillDraftPool(){
        this.draftPool.addNewDices(diceBag.pullOut());
    }

    private void setPublicObjects(){
        for (Player p: this.myplayers) {
            PublicObjects publicObjects = new PublicObjects();

            publicObjects.setToolCards(this.getToolCards());

            publicObjects.setObjectiveCards(this.getObjCard());

            publicObjects.setDraftPool(this.getDraftPool());

            publicObjects.setRoundTrack(this.roundTrack);

            List<String> playernames = new ArrayList<>();
            List<WindowPatternCard> otherwindows = new ArrayList<>();
            for (Player player: this.myplayers){
                if(!(player.getUsername().equals(p.getUsername()))) {
                    playernames.add(player.getUsername());
                    otherwindows.add(player.getWindowPatternCard());
                }
            }

            publicObjects.setPlayers(playernames);

            publicObjects.setOthersWindows(otherwindows);

            p.setPublicObjects(publicObjects);
        }
    }

    public void resetSelection() {
        myplayers.forEach(player -> player.getWindowPatternCard().resetSelection());
        roundTrack.resetSelection();
        draftPool.resetSelection();
    }
}