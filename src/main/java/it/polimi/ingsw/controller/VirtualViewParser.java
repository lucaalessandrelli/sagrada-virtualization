package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.model.gamedata.gametools.*;

import java.util.List;

public class VirtualViewParser {
    private StringBuilder builder = new StringBuilder();

    private Player player;

    private static final String SPACE = " ";
    private static final String VIRG = ",";
    private static final String SEP = ";";
    private static final String PLAYERS = "gamePlayers ";
    private static final String DRAFT = "draftpool ";
    private static final String TOOL = "toolcards ";
    private static final String FAV = "favors ";
    private static final String STATE = "state ";
    private static final String PUBLIC = "publiccards ";
    private static final String PRIV = "privatecard ";
    private static final String TRACK = "roundtrack ";
    private static final String RESTR = "restrictions ";
    private static final String DICES = "dices ";
    private static final String SETUP = "setup ";
    private static final String CHOSE = "choseWindow ";
    private static final String WINDOW = "window";



    public VirtualViewParser(Player player){
        this.player = player;
    }

    public String startParsing(){
        builder.append(SETUP);
        this.parsePlayers();
        this.parseDraftPool();
        this.parseToolcards();
        this.parseFavorToken();
        this.parseStatePlayers();
        this.parseObjectiveCard();
        this.parseRoundTrack();
        this.parseWindowPatternRestrictions(player.getWindowPatternCard());
        this.parseWindowPatternDice(player.getWindowPatternCard());

        for(Player p : this.player.getPublicObjects().getPlayers()) {
            this.parseWindowPatternRestrictions(p.getWindowPatternCard());
            this.parseWindowPatternDice(p.getWindowPatternCard());
        }
        return builder.toString();
    }

    private String parsePlayers(){
        builder.append(PLAYERS);

        builder.append(this.player.getUsername());
        for (Player p: this.player.getPublicObjects().getPlayers()) {
            builder.append(VIRG);
            builder.append(p.getUsername());
        }
        builder.append(SEP);
        return builder.toString();
    }

    public String parseDraftPool(){
        builder.append(DRAFT);
        List<Dice> dice = this.player.getDraftPool().getDraftPool();

        for(int i = 0; i < dice.size(); i++){
            builder.append(dice.get(i).getColour().toString());
            builder.append(dice.get(i).getNumber());
            builder.append(VIRG);
        }

        if(builder.charAt(builder.length()-1) == VIRG.charAt(0)){
            builder.deleteCharAt(builder.length()-1);
        }
        builder.append(SEP);
        return  builder.toString();
    }

    private String parseToolcards(){
        builder.append(TOOL);

        for(ToolCard toolCard : player.getPublicObjects().getToolCards()){
            builder.append(toolCard.getID());
            builder.append(VIRG);
        }
        if(builder.charAt(builder.length()-1) == VIRG.charAt(0)){
            builder.deleteCharAt(builder.length()-1);
        }
        builder.append(SEP);
        return builder.toString();
    }

    private String parseFavorToken(){
        builder.append(FAV);

        builder.append(this.player.getUsername());
        builder.append(VIRG);
        builder.append(player.getWindowPatternCard().getDifficulty());
        builder.append(SEP);
        for(Player p :player.getPublicObjects().getPlayers()){
            builder.append(FAV);

            builder.append(p.getUsername());
            builder.append(VIRG);
            builder.append(p.getWindowPatternCard().getDifficulty());
            builder.append(SEP);
        }

        return builder.toString();
    }

    private String parseStatePlayers() {
        builder.append(STATE);
        builder.append(this.player.getUsername());
        builder.append(VIRG);
        builder.append(this.player.isActive());
        builder.append(SEP);

        for (Player p : this.player.getPublicObjects().getPlayers()) {
            builder.append(STATE);
            builder.append(p.getUsername());
            builder.append(VIRG);
            builder.append(p.isActive());
            builder.append(SEP);
        }

        return builder.toString();
    }

    private String parseObjectiveCard(){
        builder.append(PUBLIC);

        for (ObjectiveCard objective: player.getPublicObjects().getObjectiveCards()) {
            builder.append(objective.getID());
            builder.append(VIRG);
        }
        if(builder.charAt(builder.length()-1) == VIRG.charAt(0)){
            builder.deleteCharAt(builder.length()-1);
        }

        builder.append(SEP);
        builder.append(PRIV);
        builder.append(player.getPrivateCard().getID());
        builder.append(SEP);
        return builder.toString();
    }

    private String parseRoundTrack(){
        String passing = TRACK;
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

        if(builder.charAt(builder.length()-1) == VIRG.charAt(0)){
            builder.deleteCharAt(builder.length()-1);
        }
        builder.append(SEP);
        return builder.toString();
    }

    private String parseWindowPatternRestrictions(WindowPatternCard window){
        builder.append(RESTR);
        builder.append(window.getPlayer());
        builder.append(VIRG);

        builder.append(this.restrictions(window));

        builder.append(SEP);
        return builder.toString();
    }

    private String parseWindowPatternDice(WindowPatternCard window){
        builder.append(DICES);
        builder.append(window.getPlayer());
        builder.append(VIRG);

        for(List<Cell> cells: window.getMatr()){
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

        if(builder.charAt(builder.length()-1) == VIRG.charAt(0)){
            builder.deleteCharAt(builder.length()-1);
        }

        builder.append(SEP);
        return builder.toString();
    }

    public String extractedWindows(List<WindowPatternCard> windows){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CHOSE);

        for(int i = 0; i < windows.size(); i++){
            stringBuilder.append(WINDOW);
            stringBuilder.append(i+1);
            stringBuilder.append(SPACE);
            stringBuilder.append(this.restrictions(windows.get(i)));
            stringBuilder.append(SEP);
        }
        return stringBuilder.toString();
    }
    
    public void setPlayer(Player player){
        this.player = player;
    }


    private StringBuilder addProperties(List<Cell> cellArrayList, StringBuilder builder){
        for (Cell cell: cellArrayList){
            builder.append(cell.getProperty().getColour().toString());
            builder.append(cell.getProperty().getNumber());
            builder.append(cell.getPosition().getX());
            builder.append(cell.getPosition().getY());
            builder.append(VIRG);
        }
        return builder;
    }

    private StringBuilder restrictions(WindowPatternCard window){
        StringBuilder topass = new StringBuilder();
        for (List<Cell> cells: window.getMatr()){
            this.addProperties(cells,topass);
        }
        return topass;
    }

}
