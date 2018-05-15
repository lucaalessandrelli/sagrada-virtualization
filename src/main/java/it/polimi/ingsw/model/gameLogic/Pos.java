package it.polimi.ingsw.model.gameLogic;

public class Pos {
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

    public Pos clone() throws CloneNotSupportedException {
        Pos x = (Pos) super.clone();
        return x;
    }
    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

}
