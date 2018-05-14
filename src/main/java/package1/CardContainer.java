package package1;

import java.util.ArrayList;
import java.util.Random;

public class CardContainer {

    private ArrayList<Integer> pattern = new ArrayList<Integer>(12);
    private ArrayList<Integer> objectiveprivate = new ArrayList<Integer>(4);
    private ArrayList<Integer> objectivepublic = new ArrayList<Integer>(11);

    public CardContainer(){
        for(int i = 0; i < 12; i++) {
            pattern.add((i + 1));
            objectiveprivate.add((i+1));
            objectivepublic.add((i+1));
        }
    }
    //Pull out 4 WindowPatternCard given to the player to select one of them
    //!!!!Misses one part of the code!!!!
      public ArrayList<WindowPatternCard> pullOutPattern(){
        int dimension = 4;
        ArrayList<WindowPatternCard> tmp = new ArrayList<WindowPatternCard>(dimension);
        int randomNum;
        Random rand = new Random();
        for(int k = 0; k < dimension; k++) {
            randomNum = rand.nextInt((pattern.size()));
           // Here I need to take from the configuration file the randomNum card
            pattern.remove(randomNum);
            //tmp.add(z); Supposing that z is the selected card from this "turn"
        }
        return tmp;
    }

    //Pull out x Object Private Card given to the player to select one of them
    //!!!!Misses one part of the code!!!!
    public ArrayList<WindowPatternCard> pullOutPrivate(int numPlayers){
        int dimension = numPlayers;
        ArrayList<WindowPatternCard> tmp = new ArrayList<WindowPatternCard>(dimension);
        int randomNum;
        Random rand = new Random();
        for(int k = 0; k < dimension; k++) {
            randomNum = rand.nextInt((objectiveprivate.size()));
            // Here I need to take from the configuration file the randomNum card
            objectiveprivate.remove(randomNum);
            //tmp.add(z); Supposing that z is the selected card from this "turn"
        }
        return tmp;
    }

    //Pull out 3 Object Public Card given to the player to select one of them
    //!!!!Misses one part of the code!!!!
    public ArrayList<WindowPatternCard> pullOutPublic(){
        int dimension = 3;
        ArrayList<WindowPatternCard> tmp = new ArrayList<WindowPatternCard>(dimension);
        int randomNum;
        Random rand = new Random();
        for(int k = 0; k < dimension; k++) {
            randomNum = rand.nextInt((objectivepublic.size()));
            // Here I need to take from the configuration file the randomNum card
            objectivepublic.remove(randomNum);
            //tmp.add(z); Supposing that z is the selected card from this "turn"
        }
        return tmp;
    }



}
