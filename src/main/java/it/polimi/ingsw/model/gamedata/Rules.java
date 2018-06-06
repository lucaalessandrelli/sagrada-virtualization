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
            case "3,4":
            case "5,6":
                result = this.couple(window,(rule.charAt(0)-'0'),(rule.charAt(2)-'0'));
                break;
            case "1,2,3,4,5,6":
                result = this.five(window);
                break;
            case "Y,G,B,P,R":
                result = this.varietycolours(window);
                break;
            case "Y":
            case "G":
            case "B":
            case "P":
            case "R":
                result = this.countcolor(window,Colour.isIn(rule.charAt(0)));
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
        int countfirst = 0;
        int countsecond = 0;
        int dicenumber;
        List<List<Cell>> w = window.getMatr();
        //int x = w.size();
        //int y = w.get(0).size();


        for(List<Cell> cells: w){
            for (Cell c: cells){
                if(c.isOccupied()){
                    dicenumber = c.getDice().getNumber();
                    if(dicenumber==first)
                        countfirst++;
                    else if(dicenumber == second)
                        countsecond++;
                }
            }
        }
        if(countfirst < countsecond)
            return countfirst;
        else
            return countsecond;
    }

    private int five(WindowPatternCard window){
        int[] arrayofvalues = new int[5];
        int min = 20;
        List<List<Cell>> w = window.getMatr();
        for(List<Cell> cells: w){
            for(Cell c: cells){
                if(c.isOccupied()) {
                    arrayofvalues[(c.getDice().getNumber()-1)]++;
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
        int arrayofvalues[] = new int[4];
        int min = 20;
        String color;
        List<List<Cell>> w = window.getMatr();
        for(List<Cell> cells: w){
            for(Cell cell: cells){
                if(cell.isOccupied()) {
                    color = cell.getDice().getColour().toString();
                    switch (color) {
                        //scale all the numbers in the array with +1 to find the real number you're searching for
                        case "Y":
                            arrayofvalues[0]++;
                            break;
                        case "G":
                            arrayofvalues[1]++;
                            break;
                        case "P":
                            arrayofvalues[2]++;
                            break;
                        case "R":
                            arrayofvalues[3]++;
                            break;
                        case "B":
                            arrayofvalues[4]++;
                            break;
                        default:
                    }
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
        List<List<Cell>> w = window.getMatr();
        String color = c.toString();
        String k;
        int cont = 0;
        for(List<Cell> cells: w){
            for(Cell cell: cells){
                if(cell.isOccupied() && cell.getDice().getColour().equals(c))
                    cont = cont + cell.getDice().getNumber();
            }
        }
        return cont;
    }

    private int near(WindowPatternCard window, String direction, String type){
        List<List<Cell>> w = window.getMatr();
        List<Colour> colours;
        List<Integer> numbers;
        int result = 0;
        int j,i,x,y,sum;
        boolean column = direction.equals("COLUMN");
        boolean find = false;
        x = w.size();
        y = w.get(0).size();
        if (type.equals("NOVALUE")) {
            if (!column) {
                for (i = 0; i < x; i++) {
                    if (allDifferent(w.get(i), false, 5))
                        result++;
                }
            } else {
                for (i = 0; i < x; i++) {
                    for (j = 0; j < y - 1 && !find; j++) {
                        if (w.get(j).get(i).getDice().getNumber() == w.get(j + 1).get(x).getDice().getNumber())
                            find = true;
                    }
                    if (!find)
                        result++;
                    else
                        find = false;
                }
            }
        }
        else if (type.equals("NOCOLOR")) {
                if (!column) {
                    for (i = 0; i < x; i++) {
                        if (allDifferent(w.get(i), true, 5))
                            result++;
                    }
                } else {
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
            }

        return result;
    }

    private int diagonal(WindowPatternCard window){
        List<List<Cell>> w = window.getMatr();
        int result = 0;
        int j;
        int adder = 2;
        for(int i = 0; i < 3; i++){
            for(j = 0; j < 5; j++){
                if(j != 4 && verifySameColour(w,i+1,j+1,i,j)){
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

    private boolean verifySameColour(List<List<Cell>> matrix, int i1, int i2, int i3, int i4){
        return (matrix.get(i1).get(i2).isOccupied() && matrix.get(i3).get(i4).isOccupied() &&
                matrix.get(i1).get(i2).getDice().getColour().equals(matrix.get(i3).get(i4).getDice().getColour()));
    }

    private boolean allDifferent(List<Cell> cells,boolean colour,int value){
        if(colour)
            return(cells.parallelStream().filter(Cell::isOccupied).map(Cell::getDice).map(Dice::getColour).distinct().collect(Collectors.toList()).size() == value);
        else
            return(cells.parallelStream().filter(Cell::isOccupied).map(Cell::getDice).map(Dice::getNumber).distinct().collect(Collectors.toList()).size() == value);
    }

}
