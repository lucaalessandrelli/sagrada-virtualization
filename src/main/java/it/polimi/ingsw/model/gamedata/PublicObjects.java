package it.polimi.ingsw.model.gamedata;



import it.polimi.ingsw.model.gamedata.gametools.*;

import java.util.List;

public class PublicObjects {
    private List<Player> players;
    private List<ObjectiveCard> objectiveCards;
    private List<ToolCard> toolCards;
    private RoundTrack roundTrack;
    private DraftPool draftPool;

    
    public void setDraftPool(DraftPool draftPool) {
        this.draftPool = draftPool;
    }

    public void setObjectiveCards(List<ObjectiveCard> objectiveCards) {
        this.objectiveCards = objectiveCards;
    }

    public void setOthersPlayers(List<Player> players) {
        this.players = players;
    }

    public void setToolCards(List<ToolCard> toolCards) {
        this.toolCards = toolCards;
    }

    public void setRoundTrack(RoundTrack roundTrack){this.roundTrack = roundTrack;}

    public List<ObjectiveCard> getObjectiveCards() {
        return objectiveCards;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<ToolCard> getToolCards() {
        return toolCards;
    }

    public DraftPool getDraftPool() {
        return draftPool;
    }

    public RoundTrack getRoundTrack() {
        return roundTrack;
    }
}
