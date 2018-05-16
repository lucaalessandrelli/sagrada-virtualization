package it.polimi.ingsw.model.gameData;

import it.polimi.ingsw.model.gameLogic.Move;

import java.util.ArrayList;
import java.util.List;


public class Player {
    private String username;
    private boolean active;
    private Table myTable;
    private ObjectiveCard myObjCard;
    private WindowPatternCard myWindow;
    private int myFavTokens;


    //just get the username from user
    public Player(String username) {
        this.username = username;
        active = true;
    }

    public boolean isActive() {
        return active;
    }


    public void setActivity(boolean active) {
        this.active = active;
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
     //   windows.forEach(i -> System.out.println(i.getNum())); player view
     //   Scanner in = new Scanner(System.in);  player choose
        int chosenOne = 0;
        while (myWindow==null) {
            for (WindowPatternCard w : windows) {
                if (w.getNum() == chosenOne) {
                    setMyWindow(w);
                    setmyFavTokens();
                }
            }
        }

    }

    private void setMyWindow(WindowPatternCard w) {
        myWindow = w;
    }

    private void setmyFavTokens() {
        myFavTokens = myWindow.getDifficulty();
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
        return myFavTokens;
    }
    //pull out dice at the beginning of round
    public ArrayList<Dice> pullOutFirst(){
        ArrayList<Dice> dice = myTable.getDiceFromBag();
        return dice;
    }
    //modify windowsPatternCard
    public void placeMove(Move move){
      //  myWindow.placeDice(move.getChosenDice(),move.getChosenPos().getX(),move.getChosenPos().getY());
    }

    //create a Move
    public void doYourMove(){
    }

    //show myWindow
    public void showMyWindow(){
    }


    public List<Dice> getLastDice() {
        List<Dice> remainingDice = myTable.getAllDraft();
        return remainingDice;
    }
}

