package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.model.gamedata.gametools.*;

import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.List;

public class VirtualViewParser {
    private StringBuilder builder = new StringBuilder();

    private Player player;
    
    private static final String VIRG = ",";
    private static final String SEP = ";";

    public VirtualViewParser(Player player){
        this.player = player;
    }

    public String startParsing(){
        builder.append("setup ");
        this.parseDraftPool();
        this.parseToolcards();
        this.parseFavorToken();
        this.parseObjectiveCard();
        this.parseRoundTrack();
        this.parseWindowPatternRestrictions(player.getWindowPatternCard());
        this.parseWindowPatternDice(player.getWindowPatternCard());

        for(WindowPatternCard windowPatternCard : this.player.getPublicObjects().getOthersWindows()) {
            this.parseWindowPatternRestrictions(windowPatternCard);
            this.parseWindowPatternDice(windowPatternCard);
        }
        return builder.toString();
    }

    private String parseWindowPatternRestrictions(WindowPatternCard window){
        String passing = "restrictions ";
        builder.append(passing);
        builder.append(window.getPlayer());
        builder.append(VIRG);

        for (ArrayList<Cell> cells: window.getMatr()){
            this.addProperties(cells);
        }

        builder.append(SEP);
        return builder.toString();
    }

    private String parseWindowPatternDice(WindowPatternCard window){
        String passing = "dices ";
        builder.append(passing);
        builder.append(window.getPlayer());
        builder.append(VIRG);
        ArrayList<Cell> topass = new ArrayList<>();

        for(ArrayList<Cell> cells: window.getMatr()){
            for (Cell cell: cells){
                if(cell.isOccupied()) {
                    builder.append(cell.getDice().getColour().toString());
                    builder.append(cell.getDice().getNumber());
                    builder.append(cell.getPosition().getX());
                    builder.append(cell.getPosition().getY());
                    builder.append(VIRG);
                }
            }
        }
        builder.append(SEP);
        return builder.toString();
    }

    public String parseDraftPool(){
        String passing = "draftpool ";
        builder.append(passing);
        ArrayList<Dice> dice = this.player.getDraftPool().getDraftPool();

        for(int i = 0; i < dice.size(); i++){
            builder.append(dice.get(i).getColour().toString());
            builder.append(dice.get(i).getNumber());
            builder.append(VIRG);
        }
        builder.append(SEP);
        return  builder.toString();
    }

    public String parseObjectiveCard(){
        String passing = "publiccards ";
        builder.append(passing);

        for (ObjectiveCard objective: player.getPublicObjects().getObjectiveCards()) {
            builder.append(objective.getID());
            builder.append(VIRG);
        }
        builder.append(SEP);
        builder.append("privatecard ");
        builder.append(player.getPrivateCard().getID());
        builder.append(SEP);
        return builder.toString();
    }

    public String parseToolcards(){
        String passing = "toolcards ";
        builder.append(passing);

        for(ToolCard toolCard : player.getPublicObjects().getToolCards()){
            builder.append(toolCard.getID());
            builder.append(VIRG);
        }
        builder.append(SEP);
        return builder.toString();
    }
    
    public String parseFavorToken(){
        String passing = "favors ";
        builder.append(passing);

        builder.append(player.getWindowPatternCard().getDifficulty());
        builder.append(VIRG);
        builder.append(SEP);
        return builder.toString();
    }
    
    public String parseRoundTrack(){
        String passing = "roundtrack ";
        builder.append(passing);

        List<List<Dice>> roundtrack = player.getPublicObjects().getRoundTrack().getRoundTrack();
        int size = roundtrack.size();

        for(int i = 0; i < size; i++){
            for(int j = 0; j < roundtrack.get(i).size(); j++){
                builder.append(roundtrack.get(i).get(j).getColour().toString());
                builder.append(roundtrack.get(i).get(j).getNumber());
                builder.append(i);
                builder.append(j);
                builder.append(VIRG);
            }
        }
        builder.append(SEP);
        return builder.toString();
    }
    
    public void setPlayer(Player player){
        this.player = player;
    }


    private void addProperties(ArrayList<Cell> cellArrayList){
        for (Cell cell: cellArrayList){
            builder.append(cell.getProperty().getColour().toString());
            builder.append(cell.getProperty().getNumber());
            builder.append(cell.getPosition().getX());
            builder.append(cell.getPosition().getY());
            builder.append(VIRG);
        }
    }

}
