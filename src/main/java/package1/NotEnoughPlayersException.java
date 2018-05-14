package package1;

public class NotEnoughPlayersException extends Exception {
    String message;
    public  NotEnoughPlayersException(){
        message = "Not enough players in current match";
    }
}
