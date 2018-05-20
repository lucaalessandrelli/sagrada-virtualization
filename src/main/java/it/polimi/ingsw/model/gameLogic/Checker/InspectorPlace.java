package it.polimi.ingsw.model.gameLogic.Checker;

import it.polimi.ingsw.model.gameData.gameTools.Cell;
import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.WindowPatternCard;

public class InspectorPlace {
    private final static int MINCOL=0;
    private final static int MAXCOL=4;
    private final static int MINROW=0;
    private final static int MAXROW=3;

    public boolean checkFirst(Dice dice, Pos pos, WindowPatternCard window){
        if(checkPos(pos, window)){
            if (checkColour(window, pos, dice)){
                if(checkNumber(window, pos, dice)){
                    if(checkFrame(pos)){
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public boolean check(Dice dice, Pos pos, WindowPatternCard window) {
        if(checkPos(pos,window)){
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

    protected boolean checkPos(Pos pos,WindowPatternCard window) {
        if(pos.getX()>= MINCOL && pos.getX()<=MAXCOL)
            if(pos.getY()>=MINROW && pos.getY()<=MAXROW){
                if(!window.getCell(pos).isOccupied())
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

    protected boolean checkFrame(Pos pos){
        int x = pos.getX();
        int y= pos.getY();
        if(x>=MINCOL && x<=MAXCOL){
            if(y==MINROW || y==MAXROW) {
                return true;
            }
        }
        if(y>MINROW && y<MAXROW){
            if(x == MINCOL || (x == MAXCOL)){
                return true;
            }
        }
        return false;
    }

    protected boolean checkCol(Pos pos,WindowPatternCard window, Dice dice){
        Pos p = new Pos(pos.getX(),pos.getY());
        if(p.getX()==MINCOL){
            p.setX(p.getX()+1);
            return checkSide(p,window,dice);
        }else if(p.getX()==MAXCOL){
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
        if(p.getY()==MINROW){
            p.setY(p.getY()+1);
            return checkSide(p,window,dice);
        }else if(p.getY()==MAXROW){
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
