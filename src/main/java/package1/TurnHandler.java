package package1;

import java.util.ArrayList;

public class TurnHandler {
    private ArrayList<Player> players;
    private DiceBag diceBag;
    private Move playerMove;
    private DraftPool draftPool;

    protected TurnHandler(ArrayList<Player> players, DiceBag diceBag ){
        this.players= players;
        this.diceBag=diceBag;
    }

    //start turn for every player in each round one by one
    protected void start(){
    }
    //get dice to put on RoundTrack
   // public Dice getLastDice(){    }

}
