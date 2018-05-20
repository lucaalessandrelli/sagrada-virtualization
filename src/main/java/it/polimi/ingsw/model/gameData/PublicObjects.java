package it.polimi.ingsw.model.gameData;



import it.polimi.ingsw.model.gameData.gameTools.*;

import java.util.ArrayList;

public class PublicObjects {
    private ArrayList<WindowPatternCard> othersWindows;
    private ArrayList<ObjectiveCard> objectiveCards;
    private ArrayList<ToolCard> toolCards;
    private RoundTrack roundTrack;
    private DraftPool draftPool;
    private ArrayList<String> players;

    public PublicObjects(){
        roundTrack = new RoundTrack();
    }

    public void addDice(int numRound,ArrayList<Dice> d){
        roundTrack.setDiceOnRoundTrack(numRound,d);
    }

    public void setDraftPool(DraftPool draftPool) {
        this.draftPool = draftPool;
    }

    public void setObjectiveCards(ArrayList<ObjectiveCard> objectiveCards) {
        this.objectiveCards = objectiveCards;
    }

    public void setOthersWindows(ArrayList<WindowPatternCard> othersWindows) {
        this.othersWindows = othersWindows;
    }

    public void setPlayers(ArrayList<String> players) {
        this.players = players;
    }

    public void setToolCards(ArrayList<ToolCard> toolCards) {
        this.toolCards = toolCards;
    }

    public ArrayList<ObjectiveCard> getObjectiveCards() {
        return objectiveCards;
    }

    public ArrayList<String> getPlayers() {
        return players;
    }

    public ArrayList<ToolCard> getToolCards() {
        return toolCards;
    }

    public ArrayList<WindowPatternCard> getOthersWindows() {
        return othersWindows;
    }

    public DraftPool getDraftPool() {
        return draftPool;
    }

    public RoundTrack getRoundTrack() {
        return roundTrack;
    }
}
