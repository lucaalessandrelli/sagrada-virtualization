package it.polimi.ingsw.view;

public class ModeRep {
    private MatchViewController matchViewController;
    private String draftPool;
    private String restrictions;
    private String diceWindow;

    public ModeRep() {
        this.draftPool = ",Y5,B4,G6,P4,R6,Y2,B4,R5,";
        this.restrictions = ",Y011,W402,";
        this.diceWindow = ",Y523,B233,";
    }


    public void setMatchViewController(MatchViewController matchViewController) {
        this.matchViewController = matchViewController;
    }

    public void notifyController() {
            matchViewController.notifyNewModelRep(draftPool,restrictions,diceWindow);
            System.out.println("ciao");
    }
}
