package it.polimi.ingsw.model.gamedata;

import it.polimi.ingsw.model.gamedata.gametools.*;
import it.polimi.ingsw.view.virtualview.VirtualViewObserver;

import java.util.List;


public class Player {
    private String username;
    private boolean active;
    private PublicObjects publicObjects;
    private ObjectiveCard myObjCard;
    private WindowPatternCard myWindow;
    private int myFavTokens;
    private VirtualViewObserver observer;
    private boolean hasMoved;


    //just get the username from user
    public Player(String username) {
        this.username = username;
        this.active = true;
        this.hasMoved = false;
    }

    public synchronized boolean isActive() {
        return active;
    }

    public void resetMove(){
        hasMoved = false;
    }

    public void moveDone(){
        hasMoved=true;
    }

    public void noMove(){
        if (!this.hasMoved){
            setActivity(false);
        }
    }

    public synchronized void setActivity(boolean active) {
        this.active = active;
        if(active){
            notifyPlayer();
        }
    }


    void setMyObjCard(ObjectiveCard obj){
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

    public int getMyFavTokens() {
        return myFavTokens;
    }

    //choose between 4 WindowsPatternCard and set MyFavTokens
    public void chooseWindow(List<WindowPatternCard> windows) {
        observer.chooseWindow(windows);
    }

    //decreases favTokens if ToolCard is used
    public void useToken(boolean used){
        if(!used) {
            myFavTokens--;
        }else {
            myFavTokens -= 2;
        }
    }
    public void notifyPlayer() {
        observer.update();
    }

    public void notifyTurn(String whoIsTurn, long timeSleep) {
            observer.updateStateTurn(whoIsTurn,timeSleep);
    }

    public int calculatePoints() {
        return myFavTokens + this.calculatePointsPrivate() + this.calculatePointsPublic() - this.calculateMissingCells();

    }

    public int calculatePointsPublic(){
        int score = 0;

        List<ObjectiveCard> cards = publicObjects.getObjectiveCards();
        for(ObjectiveCard c : cards){
            score = score + c.finalpoints(myWindow);
        }

        return score;
    }

    public int calculateMissingCells(){
        int numberOfMissingCells = 0;

        for(List<Cell> cells: myWindow.getMatr()){
            for (Cell c: cells){
                if(!c.isOccupied())
                    numberOfMissingCells++;
            }
        }
        return numberOfMissingCells;
    }

    public int calculatePointsPrivate(){
        return myObjCard.finalpoints(myWindow);
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

    public void reconnectingMessage() {
        observer.reconnectingMessage();
    }

    public boolean canUseTool(boolean used) {
        if(used){
            return myFavTokens >= 2;
        }else{
            return myFavTokens >= 1;
        }
    }
}

