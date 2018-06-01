package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.model.gamedata.gametools.Cell;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.gametools.DraftPool;
import it.polimi.ingsw.model.gamedata.gametools.WindowPatternCard;

import java.util.ArrayList;

public class VirtualViewParser {
    private StringBuilder builder = new StringBuilder();

    private Player player;

    public VirtualViewParser(Player player){
        this.player = player;
    }

    public String startParsing(){
        this.parseWindowPatternRestrictions(player.getWindowPatternCard());
        this.parseWindowPatternDice(player.getWindowPatternCard());
        this.parseDraftPool(player.getDraftPool());

        for(WindowPatternCard windowPatternCard : this.player.getPublicObjects().getOthersWindows()) {
            this.parseWindowPatternRestrictions(windowPatternCard);
            this.parseWindowPatternDice(windowPatternCard);
        }
        return builder.toString();
    }

    private String parseWindowPatternRestrictions(WindowPatternCard window){
        String passing = "MoveRestrictions ";
        builder.append(passing);
        builder.append(window.getPlayer());
        builder.append(",");

        for (ArrayList<Cell> cells: window.getMatr()){
            this.addProperties(cells);
        }

        builder.append(";");
        return builder.toString();
    }

    private String parseWindowPatternDice(WindowPatternCard window){
        String passing = "MoveDices ";
        builder.append(passing);
        builder.append(window.getPlayer());
        builder.append(",");
        ArrayList<Cell> topass = new ArrayList<>();

        for(ArrayList<Cell> cells: window.getMatr()){
            for (Cell cell: cells){
                if(cell.isOccupied()) {
                    builder.append(cell.getDice().getColour().toString());
                    builder.append(cell.getDice().getNumber());
                    builder.append(cell.getPosition().getX());
                    builder.append(cell.getPosition().getY());
                    builder.append(",");
                }
            }
        }
        builder.append(";");
        return builder.toString();
    }

    private String parseDraftPool(DraftPool draftPool){
        String passing = "MoveDraftPool ";
        builder.append(passing);
        builder.append(this.player.getUsername());
        builder.append(",");
        ArrayList<Dice> dice = draftPool.getDraftPool();

        for(int i = 0; i < dice.size(); i++){
            builder.append(dice.get(i).getColour().toString());
            builder.append(dice.get(i).getNumber());
            builder.append(",");
        }
        builder.append(";");
        return  builder.toString();
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
            builder.append(",");
        }
    }

}
