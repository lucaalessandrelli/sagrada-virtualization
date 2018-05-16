package it.polimi.ingsw.model.gameData;

import java.util.ArrayList;

public class Rules {

    private ArrayList<String> rules = new ArrayList<String>();


    public ArrayList<String> getRules(){
        return this.rules;
    }

    public int verify(String rule, WindowPatternCard window){
        int result = 0;
        switch (rule){
            case "1,2":
                this.couple(window,1,2);
                break;
            case "3,4":
                this.couple(window,3,4);
                break;
            case "5,6":
                this.couple(window,5,6);
                break;
            case "1,2,3,4,5,6":
                this.five(window);
                break;
            case "Y,G,B,P,R":
                this.varietycolours(window);
                break;
            case "YELLOW":
                this.countcolor(window,Colour.YELLOW);
                break;
            case "GREEN":
                this.countcolor(window,Colour.GREEN);
                break;
            case "BLUE":
                this.countcolor(window,Colour.BLUE);
                break;
            case "PURPLE":
                this.countcolor(window,Colour.PURPLE);
                break;
            case "RED":
                this.countcolor(window,Colour.RED);
                break;
        }
        return result;
    }

    public int couple(WindowPatternCard window, int first, int second){
        int countfirst = 0, countsecond = 0;
        int dicenumber;
        Cell[][] w = window.getMatr();
        for(int i = 0; i < w.length; i++){
            for(int j = 0; j < w[i].length; j++){
                if(w[i][j].isOccupied())
                    dicenumber = w[i][j].getDice().getNumber();
                else break;
                if ( dicenumber == first )
                    countfirst++;
                if (dicenumber == second)
                    countsecond++;
            }
        }
        if(countfirst < countsecond)
            return countfirst;
        else
            return countsecond;
    }

    public int five(WindowPatternCard window){
        int arrayofvalues[] = new int[6];
        int dicenumber;
        int min = 20;
        Cell[][] w = window.getMatr();
        for(int i = 0; i < w.length; i++){
            for(int j = 0; j < w[i].length; j++){
                if(w[i][j].isOccupied())
                    dicenumber = w[i][j].getDice().getNumber();
                else break;
                switch (dicenumber){
                    //scale all the numbers in the array with +1 to find the real number you're searching for
                    case 1: arrayofvalues[0]++;
                        break;
                    case 2: arrayofvalues[1]++;
                        break;
                    case 3: arrayofvalues[2]++;
                        break;
                    case 4: arrayofvalues[3]++;
                        break;
                    case 5: arrayofvalues[4]++;
                        break;
                    case 6: arrayofvalues[5]++;
                        break;
                }
            }
        }
        for(int x: arrayofvalues){
            if(x < min)
                min = x;
        }
        if(min == 20)
            return 0;
        else
            return min;
    }

    public int varietycolours(WindowPatternCard window){
        int arrayofvalues[] = new int[5];
        int min = 20;
        String color;
        Cell[][] w = window.getMatr();
        for(int i = 0; i < w.length; i++) {
            for (int j = 0; j < w[i].length; j++) {
                if(w[i][j].isOccupied())
                    color = w[i][j].getDice().getColour().toString();
                else break;
                switch (color){
                    //scale all the numbers in the array with +1 to find the real number you're searching for
                    case "YELLOW": arrayofvalues[0]++;
                        break;
                    case "GREEN": arrayofvalues[1]++;
                        break;
                    case "PURPLE": arrayofvalues[2]++;
                        break;
                    case "RED": arrayofvalues[3]++;
                        break;
                    case "BLUE": arrayofvalues[4]++;
                        break;
                }
            }
        }
        for(int x: arrayofvalues){
            if(x < min)
                min = x;
        }
        if(min == 20)
            return 0;
        else
            return min;
    }

    public int countcolor (WindowPatternCard window, Colour c){
        Cell [][] w = window.getMatr();
        String color = c.toString();
        String k;
        int cont = 0;
        for(int i = 0; i < w.length; i++) {
            for (int j = 0; j < w[i].length; j++) {
                if(w[i][j].isOccupied())
                    k = w[i][j].getDice().getColour().toString();
                else break;
                if(k.equals(color))
                    cont++;
            }
        }
        return cont;
    }
}
