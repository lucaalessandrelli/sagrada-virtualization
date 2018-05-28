package it.polimi.ingsw.controller;

public class InputAnalyzer {


    public String analyse(String in){
        if (in.startsWith("login")){
            return "login";
        }else if (in.startsWith("//exit")){
            return "exit";
        }else if (in.startsWith("//disconnect")){
            return "disconnect";
        }
        return "Command Not Valid";
    }

    public  String getData(String in){
        String tmp = in.replace("<User>","");
                tmp=tmp.replace("login","");
                tmp=tmp.trim();
        return tmp;
    }

}
