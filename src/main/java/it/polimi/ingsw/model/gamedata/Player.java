package it.polimi.ingsw.model.gamedata;

import it.polimi.ingsw.model.gamedata.gametools.*;
import it.polimi.ingsw.model.gamelogic.Move;
import it.polimi.ingsw.view.ViewObserver;

import java.util.ArrayList;
import java.util.List;


public class Player {
    private String username;
    private boolean active;
    private PublicObjects publicObjects;
    private ObjectiveCard myObjCard;
    private WindowPatternCard myWindow;
    private int myFavTokens;
    private ViewObserver observer;


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



    public void setMyObjCard(ObjectiveCard obj){
        myObjCard =obj;

    }
    public void showMyObjCard(){

    }

    //choose between 4 WindowsPatternCard and set MyFavTokens
    public void ChooseWindow(ArrayList<WindowPatternCard> windows) {
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


    public void setPublicObjects(PublicObjects publicObjects) {
        this.publicObjects = publicObjects;
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
    public void notifyPlayer() {
        observer.updatePatternCard();
    }
    public void notifyTurn(String whoIsTurn) {
        observer.updateStateTurn(whoIsTurn);
    }

    public void calculatePoints() {
        int score = myObjCard.finalpoints(myWindow);
        List<ObjectiveCard> cards = publicObjects.getObjectiveCards();
        for(ObjectiveCard c : cards){
            score = c.finalpoints(myWindow);
        }
        score = score + myFavTokens;
        //notifica observer
    }
}

