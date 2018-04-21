package package1;

public class WindowPatternCard {
    private int num;
    private Cell[][] matr;
    private int difficulty;
    private String name;

    public WindowPatternCard(int num, int ncol, int nrow, int difficulty, String name){
        this.num = num;
        this.matr = new Cell[nrow][ncol];
        this.difficulty = difficulty;
        this.name = name;
    }

    public int getNum(){
        return this.num;
    }

    public int getDifficulty(){
        return this.difficulty;
    }
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
}
