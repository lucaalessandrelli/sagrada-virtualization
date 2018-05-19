package it.polimi.ingsw.model.gameLogic.Checker;

import it.polimi.ingsw.model.gameData.Cell;
import it.polimi.ingsw.model.gameData.Dice;
import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.WindowPatternCard;

public class InspectorPlace {



    public boolean check(Dice dice, Pos pos, WindowPatternCard window) {
        if(checkPos(pos)){
            if(checkColour(window,pos,dice)) {
                if (checkNumber(window,pos,dice)) {
                    if (checkCol(pos,window,dice)) {
                        if (checkRow(pos,window,dice)) {
                            return true;
                        }
                    }

                }
            }
        }
        return false;
    }

    protected boolean checkPos(Pos pos) {
        if(pos.getX()>= 0 && pos.getX()<=4)
            if(pos.getY()>=0 && pos.getY()<=3){
                return true;
            }
        return false;
    }

    protected boolean checkColour(WindowPatternCard window, Pos pos, Dice dice){
        Cell c =window.getCell(pos);
        if(c.getProperty().getColour().toString()=="WHITE" || c.getProperty().getColour()==dice.getColour()){
            return true;
        }else{
            return false;
        }
    }

    protected boolean checkNumber(WindowPatternCard window, Pos pos, Dice dice){
        Cell c= window.getCell(pos);
        if(c.getProperty().getNumber()== 0 || c.getProperty().getNumber()==dice.getNumber()){
            return true;
        }else{
            return false;
        }
    }

    protected boolean checkCol(Pos pos,WindowPatternCard window, Dice dice){
        Pos p = new Pos(pos.getX(),pos.getY());
        if(p.getX()==0){
            p.setX(p.getX()+1);
            return checkSide(p,window,dice);
        }else if(p.getX()==4){
            p.setX(p.getX()-1);
            return checkSide(p,window,dice);
        }else{
            p.setX(p.getX()+1);
            if(checkSide(p,window,dice)){
                p.setX(p.getX()-2);
                if(checkSide(p,window,dice)){
                    return true;
                }
            }
            return false;
        }
    }
    //0<=R<=3
    protected boolean checkRow(Pos pos,WindowPatternCard window, Dice dice){
        Pos p = new Pos(pos.getX(),pos.getY());
        if(p.getY()==0){
            p.setY(p.getY()+1);
            return checkSide(p,window,dice);
        }else if(p.getY()==3){
            p.setY(p.getY()-1);
            return checkSide(p,window,dice);
        }else{
            p.setY(p.getY()+1);
                if(checkSide(p,window,dice)){
                    p.setY(p.getY()-2);
                    if(checkSide(p,window,dice)){
                        return true;
                    }
                }
            return false;
        }

    }

    private boolean checkSide(Pos p,WindowPatternCard window,Dice dice) {
        if(window.isIn(p)){
            Dice d = window.getDice(p);
            if(d.getNumber()==dice.getNumber() || d.getColour()==dice.getColour()){
                return false;
            }else{
                return true;
            }
        }else{
            return true;
        }
    }
}
