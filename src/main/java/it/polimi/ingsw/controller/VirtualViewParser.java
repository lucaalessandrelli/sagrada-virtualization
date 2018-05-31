package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.gamedata.gametools.Cell;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.gametools.DraftPool;
import it.polimi.ingsw.model.gamedata.gametools.WindowPatternCard;

import java.util.ArrayList;

public class VirtualViewParser {

    String player = "";

    public VirtualViewParser(String player){
        this.player = player;
    }

    public String parseWindowPatternRestrictions(WindowPatternCard window){
        StringBuilder builder = new StringBuilder();
        String passing = "MoveWindow ";
        builder.append(passing);
        builder.append(this.player);
        builder.append(",");

        for (ArrayList<Cell> cells: window.getMatr()){
            this.addProperties(builder,cells);
        }


        return builder.toString();
    }

    public String parseWindowPatternDice(WindowPatternCard window){
        StringBuilder builder = new StringBuilder();
        String passing = "MoveWindow ";
        builder.append(passing);
        builder.append(this.player);
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
        return builder.toString();
    }

    public String parseDraftPool(DraftPool draftPool){
        StringBuilder builder = new StringBuilder();
        String passing = "MoveDraftPool ";
        builder.append(passing);
        builder.append(this.player);
        builder.append(",");
        ArrayList<Dice> dice = draftPool.getDraftPool();

        for(int i = 0; i < dice.size(); i++){
            builder.append(dice.get(i).getColour().toString());
            builder.append(dice.get(i).getNumber());
            builder.append(",");
        }
        return  builder.toString();
    }

    public void setPlayer(String player){
        this.player = player;
    }


    public void addProperties(StringBuilder stringBuilder, ArrayList<Cell> cellArrayList){
        for (Cell cell: cellArrayList){
            stringBuilder.append(cell.getProperty().getColour().toString());
            stringBuilder.append(cell.getProperty().getNumber());
            stringBuilder.append(cell.getPosition().getX());
            stringBuilder.append(cell.getPosition().getY());
            stringBuilder.append(",");

        }
    }

}
