package package1;

public class Player {
    private String username;
    private String password;
    private Match myMatch;
    private Table myTable;
    private ObjectiveCard myObjCard;
    private WindowPatternCard myWindow;
    private int myFavTokens;
    private DraftPool draftPool;
    private DiceBag diceBag;

    public Player(){

    }
    //getter method
    public String getUsername() {
        return username;
    }
    //setter method
    public void setMyWindow(WindowPatternCard myWindow) {
        this.myWindow = myWindow;
    }
    //decreases favTokens
    public void useToken(){

    }
    //create a Move
    public void doYourMove(){

    }
    //show myWindow
    public void showMyWindow(){

    }

    public void setMyObjCard(ObjectiveCard myObjCard) {
        this.myObjCard = myObjCard;
    }

    public void showMyObjCard(){

    }
}

