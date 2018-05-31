package it.polimi.ingsw.model.gamedata;

import it.polimi.ingsw.model.gamelogic.Move;

public class Pos implements Move {
    private int x;
    private int y;

    public Pos(){
        this.x = -1;
        this.y = -1;
    }

    public Pos(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }
}
