package it.polimi.ingsw.model.gamedata;



import it.polimi.ingsw.model.gamedata.gametools.*;

import java.util.ArrayList;
import java.util.List;

public class PublicObjects {
    private List<WindowPatternCard> othersWindows;
    private List<ObjectiveCard> objectiveCards;
    private List<ToolCard> toolCards;
    private RoundTrack roundTrack;
    private DraftPool draftPool;
    private List<String> players;

    public PublicObjects(){
        roundTrack = new RoundTrack();
    }

    public void addDice(int numRound,List<Dice> d){
        roundTrack.setDiceOnRoundTrack(numRound,d);
    }

    public void setDraftPool(DraftPool draftPool) {
        this.draftPool = draftPool;
    }

    public void setObjectiveCards(List<ObjectiveCard> objectiveCards) {
        this.objectiveCards = objectiveCards;
    }

    public void setOthersWindows(List<WindowPatternCard> othersWindows) {
        this.othersWindows = othersWindows;
    }

    public void setPlayers(
            List<String> players) {
        this.players = players;
    }

    public void setToolCards(List<ToolCard> toolCards) {
        this.toolCards = toolCards;
    }

    public List<ObjectiveCard> getObjectiveCards() {
        return objectiveCards;
    }

    public List<String> getPlayers() {
        return players;
    }

    public List<ToolCard> getToolCards() {
        return toolCards;
    }

    public List<WindowPatternCard> getOthersWindows() {
        return othersWindows;
    }

    public DraftPool getDraftPool() {
        return draftPool;
    }

    public RoundTrack getRoundTrack() {
        return roundTrack;
    }
}
