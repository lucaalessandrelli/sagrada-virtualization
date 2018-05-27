package it.polimi.ingsw.view;

import java.util.ArrayList;

public class RiservaFalsa {
    private ArrayList<String> riserva = new ArrayList<String>();
    private String str1 = "red 1";
    private String str2 = "d 3";
    private String str3 = "rd 4";
    private String str4 = "fed 5";
    private String str5 = "hhh 6";
    private String str6 = "nnn 7";
    private String str7 = "cc 8";
    private String str8 = "bb 9";
    private String str9 = "aaa 10";

    public RiservaFalsa() {
        this.riserva.add(str1);
        this.riserva.add(str2);
        this.riserva.add(str3);
        this.riserva.add(str4);
        this.riserva.add(str5);
        this.riserva.add(str6);
        this.riserva.add(str7);
    }

    public ArrayList<String> getRiserva() {
        return riserva;
    }
}