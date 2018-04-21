package package1;


import java.util.Random;

public class ScoreMarker {
    private Player assignedplayer;

    public ScoreMarker(Player aplayer){
        this.assignedplayer = aplayer;
    }

    public Player getPlayer(){
        return assignedplayer;
    }

    /*I use this to return a random char from the string above to select a color for a player
    public Character getColor(){
        int i = 4;
        int randomNum = rand.nextInt(i);
        char x = 'X';
        //if there are no more letters
        if( i == 0){
            return 'X';
        }
        //with this for cycle I search for a letter in the string that is different from X
        //X is the character with I'll replace the letter already taken
        while( x == 'X' && i > 0) {
            x = this.color.charAt(rand.nextInt(i));
        }
        this.color.replace(x,'X');
        i--;
        return x;
    }*/
}
