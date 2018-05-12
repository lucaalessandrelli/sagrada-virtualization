package package1;

import java.util.ArrayList;
import java.util.Scanner;


public class Player {
    private String username;
    private Table myTable;
    private ObjectiveCard myObjCard;
    private WindowPatternCard myWindow;
    private int myFavTokens;

    //just take username from user
    public Player(){

        /*Scanner in = new Scanner(System.in);
        String input = in.next();
        while (input.length() == 0) {
            System.out.println("Username not valid!");
            input = in.next();
        }
        username = input;
        */
    }

    //I created another constructor (should be the right one with a little bit of adjustment)
    public Player(String username) {
        this.username = username;
        //adjustment
    }

    //getter method
    public String getUsername() {
        return username;
    }
    //setter method
    public void setMyTable(Table table){
        myTable= table;

    }
    //getter method
    public Table getMyTable() {
        return myTable;
    }

    public void setMyObjCard(){
        myObjCard = myTable.getObjCard();

    }
    public void showMyObjCard(){

    }

    //choose between 4 WindowsPatternCard and set MyFavTokens
    public void ChooseWindow() {
        ArrayList<WindowPatternCard> windows = myTable.getRandomWindows();
        windows.forEach(i -> System.out.println(i.getNum()));
        Scanner in = new Scanner(System.in);
        int chosenOne = in.nextInt();
        while (myWindow==null) {
            for (WindowPatternCard w : windows) {
                if (w.getNum() == chosenOne) {
                    myWindow = w;
                    myFavTokens = myWindow.getDifficulty();
                }
            }
        }

    }

    //decreases favTokens if ToolCard is used
    public void useToken(boolean used){
        if(!used){
            if(myFavTokens>=1) {
                myFavTokens--;
            }
        }else{
            if(myFavTokens>=2) {
                myFavTokens -= 2;
            }
        }
    }
    //getter method
    public int getFavTok(){
        int tok = myFavTokens;
        return tok;
    }
    //pull out dice at the beginning of round
    public ArrayList<Dice> pullOutFirst(){
        ArrayList<Dice> dice = myTable.getDiceFromBag();
        return dice;
    }
    //modify windowsPatternCard
    public void placeMove(){

    }

    //create a Move
    public void doYourMove(){
    }

    //show myWindow
    public void showMyWindow(){
    }


    public Player clonePlayer() {
        String clonedString = new String(this.getUsername());
        Player clonedPlayer = new Player(clonedString);

        return clonedPlayer;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Player)) {
            return false;
        }

        Player player = (Player) o;

        if(!(this.username.equals(player.getUsername()))) {
            return false;
        }

        return true;
    }
}

