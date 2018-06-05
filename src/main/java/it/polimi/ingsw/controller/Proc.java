package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.gamedata.Colour;
import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.gametools.ToolCard;
import it.polimi.ingsw.model.gamelogic.Round;

public class Proc {
    Round round;
    String move;

    public Proc(Round round, String move) {
        this.round = round;
        this.move= move;
    }

    public void process() {
        if(move.startsWith("D;")){
            String res = move.replace("D;","");
            String[] diceToken = res.split(",");
            Dice dice = new Dice(Colour.isIn(diceToken[1].charAt(0)));
            dice.setNumber(Integer.parseInt(diceToken[0]));
            Pos pos = new Pos(Integer.parseInt(diceToken[2]),Integer.parseInt(diceToken[3]));
            round.setTurn(dice,pos);
        }else if(move.startsWith("P")){
            String res = move.replace("P;","");
            String[] posToken = res.split(",");
            Pos pos = new Pos(Integer.parseInt(posToken[0]),Integer.parseInt(posToken[1]));
            round.setTurn(pos);
        }else if(move.startsWith("T;")){
            String res = move.replace("T;","");
            ToolCard t = new ToolCard().exactToolCard(Integer.parseInt(res));
            round.setTurn(t);
        }else if(move.equals("pass")){
            round.setTurn("pass");
        }
    }
}
