package it.polimi.ingsw.model.gamedata;


import it.polimi.ingsw.model.gamedata.gametools.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//I used ArrayList for data structures but we'll see better in the future which one is the best for this situation
public class Table {
    private List<Player> myplayers;
    private List<ObjectiveCard> objectiveCards;
    private List<ToolCard> toolCards = new ArrayList<>(3);
    private RoundTrack roundTrack;
    private DraftPool draftPool;
    private DiceBag diceBag;
    private CardContainer container;
    private List<WindowPatternCard> windowPatternCards;
    private List<Integer> temporaryCards = new ArrayList<>();

    public Table(List<Player> players){
        this.diceBag = new DiceBag();
        this.draftPool = new DraftPool();
        this.container = new CardContainer();
        this.myplayers = players;
        this.roundTrack = new RoundTrack();
    }

    public void initialize(){
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

        this.diceBag.setNumPlayers(this.myplayers.size());
        this.draftPool.addNewDices(this.diceBag.pullOut());

    }


    public void selectWindowCards(){
        List<WindowPatternCard> patterns = new ArrayList<>();
        for (int i = 0; i < myplayers.size();i++) {
            patterns.add(windowPatternCards.get((i*4)));
            temporaryCards.add(windowPatternCards.get(i*4).getNum());
            patterns.add(windowPatternCards.get((i*4)+1));
            temporaryCards.add(windowPatternCards.get((i*4)+1).getNum());
            patterns.add(windowPatternCards.get((i*4)+2));
            temporaryCards.add(windowPatternCards.get((i*4)+2).getNum());
            patterns.add(windowPatternCards.get((i*4)+3));
            temporaryCards.add(windowPatternCards.get((i*4)+3).getNum());
            myplayers.get(i).chooseWindow(patterns);
            patterns.clear();
        }
        setPublicObjects();
    }

    //GETTER METHODS
    public List<ObjectiveCard> getObjCard() {
        return objectiveCards;
    }

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

    public void setWindow(String p){
        Random random = new Random();
        int i = 0;
        while (i < this.myplayers.size() && !(p.equals(this.myplayers.get(i).getUsername()))) {
            i++;
        }
        Player player = this.myplayers.get(i);
        player.setMyWindow(windowPatternCards.get(random.nextInt(4) + 4*i));
    }

    public void setWindow(Player p, int id){
        int i = 0;

        while (i < this.myplayers.size() && !(p.getUsername().equals(this.myplayers.get(i).getUsername()))) {
            i++;
        }

        if(temporaryCards.contains(id)){
            if(windowPatternCards.get(i*4).getNum() == id)
                this.myplayers.get(i).setMyWindow(windowPatternCards.get(i*4));
            if(windowPatternCards.get((i*4)+1).getNum() == id)
                this.myplayers.get(i).setMyWindow(windowPatternCards.get((i*4)+1));
            if(windowPatternCards.get((i*4)+2).getNum() == id)
                this.myplayers.get(i).setMyWindow(windowPatternCards.get((i*4)+2));
            if(windowPatternCards.get((i*4)+3).getNum() == id)
                this.myplayers.get(i).setMyWindow(windowPatternCards.get((i*4)+3));
            }
        }


    public void fillDraftPool(){
        this.draftPool.addNewDices(diceBag.pullOut());
    }

    public void setPublicObjects(){
        for (Player p: this.myplayers) {
            PublicObjects publicObjects = new PublicObjects();

            publicObjects.setToolCards(this.getToolCards());

            publicObjects.setObjectiveCards(this.getObjCard());

            publicObjects.setDraftPool(this.getDraftPool());

            publicObjects.setRoundTrack(this.roundTrack);

            List<Player> players = new ArrayList<>();
            for (Player player: this.myplayers){
                if(!(player.getUsername().equals(p.getUsername()))) {
                    players.add(player);
                }
            }

            publicObjects.setOthersPlayers(players);

            p.setPublicObjects(publicObjects);
        }
    }

    public void resetSelection() {
        myplayers.forEach(player -> player.getWindowPatternCard().resetSelection());
        roundTrack.resetSelection();
        draftPool.resetSelection();
    }
}