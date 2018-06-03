package it.polimi.ingsw.model.gamedata;

import it.polimi.ingsw.model.gamedata.gametools.Cell;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.gametools.WindowPatternCard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Rules {

    private ArrayList<String> myrules;
    private String direction;

    public Rules(){
        myrules = new ArrayList<>();
        direction = "";
    }


    public ArrayList<String> getRules(){
        return this.myrules;
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
                default:
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
                        default:
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
                        default:
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
        List<Colour> colours;
        List<Integer> numbers;
        int result = 0;
        int j,i,x,y,sum;
        boolean column = direction.equals("COLUMN");
        boolean find = false;
        x = w.size();
        y = w.get(0).size();
        //misses the part for control on column, now controls only rows
        switch (type) {
            case "NOVALUE":
                if(!column){
                    for(i = 0; i < x; i++){
                        if (allDifferent(w.get(i),false,5))
                            result++;
                    }
                }
                else {
                    for (i = 0; i < x; i++) {
                        for (j = 0; j < y - 1; j++) {
                            if (w.get(j).get(i).getDice().getNumber() == w.get(j + 1).get(x).getDice().getNumber())
                                find = true;
                        }
                        if (!find)
                            result++;
                        else
                            find = false;
                    }
                }
                break;

            case "NOCOLOR":
                if(!column){
                    for(i = 0; i < x; i++){
                        if(allDifferent(w.get(i),true,5))
                            result++;
                    }
                }
                else{
                    for (i = 0; i < x; i++) {
                        for (j = 0; j < y - 1; j++) {
                            if (w.get(j).get(i).getDice().getColour().equals(w.get(j + 1).get(x).getDice().getColour()))
                                find = true;
                        }
                        if (!find)
                            result++;
                        else
                            find = false;
                    }
                }

                break;
                default:
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
                if(j != 3 && verifySameColour(w,i+1,j+1,i,j)){
                    result = result + adder;
                }
                if(j != 0 && verifySameColour(w,i+1,j-1,i,j)){
                    result = result + adder;
                }
            }
            adder = 1;
        }
        return result;
    }

    private boolean verifySameColour(ArrayList<ArrayList<Cell>> matrix, int i1, int i2, int i3, int i4){
        return matrix.get(i1).get(i2).isOccupied() && matrix.get(i3).get(i4).isOccupied() &&
                matrix.get(i1).get(i2).getDice().getColour().toString().equals(matrix.get(i3).get(i4).getDice().getColour().toString());
    }

    private boolean allDifferent(ArrayList<Cell> cells,boolean colour,int value){
        if(colour)
            return(cells.parallelStream().filter(Cell::isOccupied).map(Cell::getDice).map(Dice::getColour).distinct().collect(Collectors.toList()).size() == value);
        else
            return(cells.parallelStream().filter(Cell::isOccupied).map(Cell::getDice).map(Dice::getNumber).distinct().collect(Collectors.toList()).size() == value);
    }

}
