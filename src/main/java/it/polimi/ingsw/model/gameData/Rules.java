package it.polimi.ingsw.model.gameData;

import it.polimi.ingsw.model.gameData.gameTools.Cell;
import it.polimi.ingsw.model.gameData.gameTools.WindowPatternCard;

import java.util.ArrayList;

public class Rules {

    private ArrayList<String> rules;
    private String direction = "";

    public Rules(){
        this.rules = new ArrayList<String>();
    }


    public ArrayList<String> getRules(){
        return this.rules;
    }

    public int verify(String rule, WindowPatternCard window){
        int result = 0;
        switch (rule){
            case "-":
                break;
            case "1,2":
                result = this.couple(window,1,2);
                break;
            case "3,4":
                result = this.couple(window,3,4);
                break;
            case "5,6":
                result = this.couple(window,5,6);
                break;
            case "1,2,3,4,5,6":
                result = this.five(window);
                break;
            case "Y,G,B,P,R":
                result = this.varietycolours(window);
                break;
            case "Y":
                result = this.countcolor(window,Colour.YELLOW);
                break;
            case "G":
                result = this.countcolor(window,Colour.GREEN);
                break;
            case "B":
                result = this.countcolor(window,Colour.BLUE);
                break;
            case "P":
                result = this.countcolor(window,Colour.PURPLE);
                break;
            case "R":
                result = this.countcolor(window,Colour.RED);
                break;
            case "ROW":
                direction = rule;
                break;
            case"COLUMN":
                direction = rule;
                break;
            case "NOCOLOR":
            case "NOVALUE":
                result = this.near(window,direction,rule);
                break;
            case "COLOR":
                if(direction.equals("DIAGONAL"))
                    result = this.diagonal(window);
                break;
            case "DIAGONAL":
                direction = rule;
                break;
        }
        return result;
    }

    private int couple(WindowPatternCard window, int first, int second){
        int countfirst = 0, countsecond = 0;
        int dicenumber;
        ArrayList<ArrayList<Cell>> w = window.getMatr();
        int x = w.size();
        int y = w.get(0).size();
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                if(w.get(i).get(j).isOccupied())
                    dicenumber = w.get(i).get(j).getDice().getNumber();
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

    private int five(WindowPatternCard window){
        int arrayofvalues[] = new int[6];
        int dicenumber;
        int min = 20;
        ArrayList<ArrayList<Cell>> w = window.getMatr();
        int x = w.size();
        int y = w.get(0).size();
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                if(w.get(i).get(j).isOccupied())
                    dicenumber = w.get(i).get(j).getDice().getNumber();
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
        for(int h: arrayofvalues){
            if(h < min)
                min = h;
        }
        if(min == 20)
            return 0;
        else
            return min;
    }

    private int varietycolours(WindowPatternCard window){
        int arrayofvalues[] = new int[5];
        int min = 20;
        String color;
        ArrayList<ArrayList<Cell>> w = window.getMatr();
        int x = w.size();
        int y = w.get(0).size();
        for(int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if(w.get(i).get(j).isOccupied())
                    color = w.get(i).get(j).getDice().getColour().toString();
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
        for(int h: arrayofvalues){
            if(h < min)
                min = h;
        }
        if(min == 20)
            return 0;
        else
            return min;
    }

    private int countcolor (WindowPatternCard window, Colour c){
        ArrayList<ArrayList<Cell>> w = window.getMatr();
        String color = c.toString();
        String k;
        int cont = 0;
        int j;
        int x = w.size();
        int y = w.get(0).size();
        for(int i = 0; i < x; i++) {
            for (j = 0; j < y; j++) {
                if(w.get(i).get(j).isOccupied())
                    k = w.get(i).get(j).getDice().getColour().toString();
                else break;
                if(k.equals(color))
                    cont++;
            }
        }
        return cont;
    }

    private int near(WindowPatternCard window, String direction, String type){
        ArrayList<ArrayList<Cell>> w = window.getMatr();
        int result = 0;
        int j;
        int x;
        int y;
        int sum;
        boolean b = false;
        if (direction.equals("COLUMN")) {
            x = w.get(0).size();
            y = w.size();
            sum = 4;
        }
        else
        {
            x = w.size();
            y = w.get(0).size();
            sum = 5;
        }
        switch (type){
            case "NOVALUE":
                for(int i = 0; i < x; i++){
                    b = false;
                    for(j = 0; j < y; j++){
                        if(w.get(i).get(j).isOccupied() && w.get(i).get(j).getDice().getNumber() ==
                                w.get(i+1).get(j+1).getDice().getNumber()) {
                            b = true;
                            break;
                        }
                    }
                    if(!b)
                        result = result + sum;
                }
                break;
            case "NOCOLOR":
                for(int i = 0; i < x; i++){
                    b = false;
                    for(j = 0; j < y; j++){
                        if(w.get(i).get(j).isOccupied() && w.get(i).get(j).getDice().getColour() ==
                                w.get(i+1).get(j+1).getDice().getColour()) {
                            b = true;
                            break;
                        }
                    }
                    if(!b)
                        result = result + sum;
                }
                break;
        }

        return result;
    }

    private int diagonal(WindowPatternCard window){
        ArrayList<ArrayList<Cell>> w = window.getMatr();
        int result = 0;
        int j;
        int adder = 2;
        for(int i = 0; i < 4; i++){
            for(j = 0; j < 4; j++){
                if(j == 0){
                    if(w.get(i+1).get(j+1).getDice().getColour() == w.get(i).get(j).getDice().getColour()){
                        result = result + adder;
                    }
                    break;
                }
                if(j == 3){
                    if(w.get(i+1).get(j-1).getDice().getColour() == w.get(i).get(j).getDice().getColour()){
                        result = result + adder;
                    }
                    break;
                }
                else{
                    if(w.get(i+1).get(j-1).getDice().getColour() == w.get(i).get(j).getDice().getColour()){
                        result = result + adder;
                    }
                    if(w.get(i+1).get(j+1).getDice().getColour() == w.get(i).get(j).getDice().getColour()){
                        result = result + adder;
                    }
                }
            }
            adder = 1;
        }
        return result;
    }

}
