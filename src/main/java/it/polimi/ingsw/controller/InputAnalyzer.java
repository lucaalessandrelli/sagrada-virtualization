package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.gamedata.Colour;
import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.gametools.Dice;

public class InputAnalyzer {
    Manager manager;

    public  InputAnalyzer(){manager=null;}
    public InputAnalyzer(Manager manager) {
        this.manager = manager;
    }

    public void analyse(String in){
        if (in.startsWith("login")){
            //return "login";
        }else if (in.startsWith("move ")){
            String res =in.replace("move ","");
            String[] token = res.split(" ");
            int num = Integer.parseInt(token[0]);
            String name = token[1];
            String move = token[2];
            manager.move(num,name,move);
        }else if (in.startsWith("//disconnect")){
            //return "disconnect";
        }
        //return "Command Not Valid";
    }

    public  String getData(String in){
        String tmp = in.replace("<User>","");
                tmp=tmp.replace("login","");
                tmp=tmp.trim();
        return tmp;
    }

}
