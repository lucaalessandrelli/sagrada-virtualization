package package1;

public class WindowPatternCard {
    private int num;
    private Cell[][] matr = new Cell[4][5];
    private int difficulty;
    private String name;

    public WindowPatternCard(int num, int difficulty, String name){
        this.num = num;
        this.difficulty = difficulty;
        this.name = name;
    }

    public int getNum(){
        return this.num;
    }

    //getter
    public int getDifficulty(){
        return this.difficulty;
    }

    public Cell[][] getMatr(){ return this.matr;}

    //shows every attribute of the Card, also the scheme
    public void show(){
        System.out.println("ID number :" + num + "\n" + "Difficulty :" + difficulty + "\n" + "Name :" + name + "Scheme :\n" + "\n");
        for(int i = 0; i <= matr.length; i++){
            System.out.println(" " + i + " ");
            for (int j = 0; j <= matr[i].length ; j++) {
                if (i == 0) {
                    System.out.println(" " + j + " ");
                }
                else
                    System.out.println(" " + "matr[i][j]" + " ");
                if( j == matr[i].length) {
                    System.out.println("\n");
                }
            }
        }
    }

    //place the dice
    public Boolean placeDice(Dice d, int x, int y){
        /*Here we need the validation of the rules*/
        if((matr[x][y] != null) && matr[x][y].isOccupied())
            return false;
        else{
            matr[x][y] = new Cell();
            matr[x][y].setOccupation(true);
            matr[x][y].getProperty().setColour(d.getColour());
            matr[x][y].getProperty().setNumber(d.getNumber());
            return true;
        }
    }
}
