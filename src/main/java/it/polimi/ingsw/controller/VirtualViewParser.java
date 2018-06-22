package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.model.gamedata.gametools.*;

import java.util.Arrays;
import java.util.List;

public class VirtualViewParser {
    private StringBuilder builder = new StringBuilder();

    private Player player;

    private static final String SPACE = " ";
    private static final String VIRG = ",";
    private static final String SEP = ";";
    private static final String SLASH = "/";
    private static final String BACKSLASH = "\\";
    private static final String ACTIVE = "Active";
    private static final String INACTIVE = "Inactive";
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
        this.parseWindowPatternRestrictions(player);
        this.parseWindowPatternDice(player);

        for(Player p : this.player.getPublicObjects().getPlayers()) {
            this.parseWindowPatternRestrictions(p);
            this.parseWindowPatternDice(p);
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
            builder.append(SLASH);
            builder.append(toolCard.getCost());
            builder.append(SLASH);
            builder.append(toolCard.getDescription());
            builder.append(BACKSLASH);
        }
        if(builder.charAt(builder.length()-1) == BACKSLASH.charAt(0)){
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
        builder.append(SPACE);
        builder.append(this.active(this.player));
        builder.append(VIRG);

        for (Player p : this.player.getPublicObjects().getPlayers()) {
            builder.append(p.getUsername());
            builder.append(SPACE);
            builder.append(this.active(p));
            builder.append(VIRG);
        }
        builder.append(SEP);

        return builder.toString();
    }

    private String parseObjectiveCard(){
        builder.append(PUBLIC);

        for (ObjectiveCard objective: player.getPublicObjects().getObjectiveCards()) {
            builder.append(objective.getID());
            builder.append(SLASH);
            builder.append(objective.getDescription());
            builder.append(BACKSLASH);
        }
        if(builder.charAt(builder.length()-1) == BACKSLASH.charAt(0)){
            builder.deleteCharAt(builder.length()-1);
        }

        builder.append(SEP);
        builder.append(PRIV);
        builder.append(player.getPrivateCard().getID());
        builder.append(VIRG);
        builder.append(player.getPrivateCard().getDescription().substring(player.getPrivateCard().getDescription().lastIndexOf(' ')+1,player.getPrivateCard().getDescription().length()));
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

    public String parseWindowPatternRestrictions(Player player){
        builder.append(RESTR);
        builder.append(player.getUsername());
        builder.append(VIRG);

        builder.append(this.restrictions(player.getWindowPatternCard()));

        if(builder.charAt(builder.length()-1) == VIRG.charAt(0)){
            builder.deleteCharAt(builder.length()-1);
        }

        builder.append(SEP);
        return builder.toString();
    }

    private String parseWindowPatternDice(Player player){
        builder.append(DICES);
        builder.append(player.getUsername());
        builder.append(VIRG);

        for(List<Cell> cells: player.getWindowPatternCard().getMatr()){
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
        WindowPatternCard window;

        for(int i = 0; i < windows.size(); i++){
            window = windows.get(i);
            stringBuilder.append(window.getNum());
            stringBuilder.append(SPACE);
            stringBuilder.append(window.getDifficulty());
            stringBuilder.append(SPACE);
            stringBuilder.append(this.restrictions(window));
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

    private StringBuilder active(Player p){
        StringBuilder topass = new StringBuilder();
        if(p.isActive()){
            topass.append(ACTIVE);
        }
        else
            topass.append(INACTIVE);
        return topass;
    }


}
