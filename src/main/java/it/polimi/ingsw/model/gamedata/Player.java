package it.polimi.ingsw.model.gamedata;

import it.polimi.ingsw.model.gamedata.gametools.*;
import it.polimi.ingsw.view.virtualview.VirtualViewObserver;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;


public class Player {
    private String username;
    private boolean active;
    private PublicObjects publicObjects;
    private ObjectiveCard myObjCard;
    private WindowPatternCard myWindow;
    private int myFavTokens;
    private VirtualViewObserver observer;


    //just get the username from user
    public Player(String username) {
        this.username = username;
        active = true;
    }

    public boolean isActive() {
        return active;
    }


    //SETTER

    public void setActivity(boolean active) {
        this.active = active;
        if(active){
            notifyPlayer();
        }
    }


    public void setMyObjCard(ObjectiveCard obj){
        myObjCard =obj;
    }

    public void setMyWindow(WindowPatternCard w) {
        myWindow = w;
        myFavTokens = myWindow.getDifficulty();
    }

    public void setPublicObjects(PublicObjects publicObjects) {
        this.publicObjects = publicObjects;
    }


    //getter method
    public String getUsername() {
        return username;
    }

    public List<ToolCard> getToolCards(){
        return publicObjects.getToolCards();
    }

    public DraftPool getDraftPool(){
        return publicObjects.getDraftPool();
    }

    public WindowPatternCard getWindowPatternCard(){
        return myWindow;
    }

    public RoundTrack getRoundTrack(){
        return publicObjects.getRoundTrack();
    }

    public PublicObjects getPublicObjects(){return this.publicObjects;}

    public ObjectiveCard getPrivateCard(){return this.myObjCard;}


    public void showMyObjCard(){

    }

    //choose between 4 WindowsPatternCard and set MyFavTokens
    public void chooseWindow(List<WindowPatternCard> windows) {
        observer.chooseWindow(windows);
        /*int chosenOne = 0;
        // while (myWindow==null) {
        setMyWindow(windows.get(chosenOne));
        setmyFavTokens();
        windows.get(chosenOne).setPlayer(this.username);
        */

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
    public void notifyPlayer() {
        observer.update();
    }

    public void notifyTurn(String whoIsTurn, long timeSleep) {
            observer.updateStateTurn(whoIsTurn,timeSleep);
    }

    public int calculatePoints() {
        int score = myObjCard.finalpoints(myWindow);
        List<ObjectiveCard> cards = publicObjects.getObjectiveCards();
        for(ObjectiveCard c : cards){
            score = score + c.finalpoints(myWindow);
        }
        return score + myFavTokens;

    }
    public void addObserver(VirtualViewObserver o){
        observer=o;
    }

    public void wrongMove(String s) {
        observer.wrongMove(s);
    }

    public void timerChoose(long timerWindows) {
        observer.timerChoose(timerWindows);
    }

    public void notifyState(String state) {
        observer.notifyState(state);
    }

    public void notifyScore(String s) {
        observer.notifyScore(s);
    }
}

