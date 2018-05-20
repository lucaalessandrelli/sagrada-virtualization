package it.polimi.ingsw.model.gameData;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class CardContainer {

    private ArrayList<Integer> pattern = new ArrayList<Integer>(24);
    private ArrayList<Integer> objectiveprivate = new ArrayList<Integer>(5);
    private ArrayList<Integer> objectivepublic = new ArrayList<Integer>(10);
    private boolean privateused = false;
    private boolean publicused = false;
    private boolean patternused = false;

    public CardContainer(){
        for(int i = 0; i < 24; i++) {
            pattern.add((i));
            if (i < 5)
            objectiveprivate.add(i);
            if( i < 10)
            objectivepublic.add(i);
        }
    }

    //Pull out 4 WindowPatternCard given to the player to select one of them
    //!!!!Misses one part of the code!!!!
    public ArrayList<WindowPatternCard> pullOutPattern(int numPlayers) {
        int dimension = numPlayers*4;
        if(numPlayers < 1 || numPlayers > 4)
            throw new IndexOutOfBoundsException();
        ArrayList<WindowPatternCard> tmp = new ArrayList<WindowPatternCard>(dimension);
        try {
            if(this.patternused == true)
                throw new AlreadyBeenCalledException();
            this.patternused = true;
            int randomNum;
            int cont = 23;
            int next = 0;
            Random rand = new Random();
            for (int k = 0; k < dimension; k=k+2) {
                randomNum = rand.nextInt(cont - (k+1));
                if(randomNum%2 == 0)
                    if((randomNum - 1) >= 0)
                        next = randomNum - 1;
                    else
                        next = pattern.size()-1;
                else
                    next = randomNum + 1;
                tmp.add(this.readPatterns("src/main/resources/window_pattern_cards_formalization.xml", pattern.get(randomNum))); //Supposing that z is the selected card from this "turn"
                tmp.add(this.readPatterns("src/main/resources/window_pattern_cards_formalization.xml", pattern.get(next))); //Supposing that z is the selected card from this "turn"
                pattern.remove(randomNum);
                pattern.remove(next-1);
            }
        } catch (AlreadyBeenCalledException e){
            System.out.println(e.message);
        }
        return tmp;
    }

    //Pull out x Object Private Card given to the player to select one of them
    //!!!!Misses one part of the code!!!!
    public ArrayList<ObjectiveCard> pullOutPrivate(int numPlayers) {
        int dimension = numPlayers;
        if(numPlayers < 1 || numPlayers > 4)
            throw new IndexOutOfBoundsException();
        ArrayList<ObjectiveCard> tmp = new ArrayList<ObjectiveCard>(dimension);
        try {
            if(this.privateused == true)
                throw new AlreadyBeenCalledException();
            this.privateused = true;
            int randomNum;
            int cont = objectiveprivate.size();
            Random rand = new Random();
            for (int k = 0; k < dimension; k++) {
                randomNum = rand.nextInt(cont - k);
                tmp.add(this.readValues("src/main/resources/private_cards_formalization.xml", objectiveprivate.get(randomNum))); //Supposing that z is the selected card from this "turn"
                objectiveprivate.remove(randomNum);
            }
        } catch (AlreadyBeenCalledException e){
            System.out.println(e.message);
        }
        return tmp;
    }

    //Pull out 3 Object Public Card given to the table
    //!!!!Misses one part of the code!!!!
    public ArrayList<ObjectiveCard> pullOutPublic(){
        int dimension = 3;
        ArrayList<ObjectiveCard> tmp = new ArrayList<ObjectiveCard>(dimension);
        try {
            if(this.publicused == true)
                throw new AlreadyBeenCalledException();
            this.publicused = true;
            int randomNum;
            int cont = objectivepublic.size();
            Random rand = new Random();
            for (int k = 0; k < dimension; k++) {
                randomNum = rand.nextInt(cont - k);
                tmp.add(this.readValues("src/main/resources/public_cards_formalization.xml", objectivepublic.get(randomNum))); //Supposing that z is the selected card from this "turn"
                objectivepublic.remove(randomNum);
            }
        } catch (AlreadyBeenCalledException e){
            System.out.println(e.message);
        }
        return tmp;
    }


    //read the values in the xml file and create a objective card to return
    private ObjectiveCard readValues(String namefile, int cont) {
        ObjectiveCard myobj = new ObjectiveCard();
        int result;
        String path = new File(namefile).getAbsolutePath();
        File file = new File(path);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = null;
        try {
            document = documentBuilder.parse(file);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String x = document.getElementsByTagName("NAME").item(cont).getTextContent();
        myobj.setName(document.getElementsByTagName("NAME").item(cont).getTextContent());
        myobj.setDescription(document.getElementsByTagName("DESCRIPTION").item(cont).getTextContent());
        myobj.setType(document.getElementsByTagName("TYPE").item(cont).getTextContent());
        String input = document.getElementsByTagName("POINTS").item(cont).getTextContent();
        if(!(input.equals("V"))) {
            if(input.equals("#"))
                myobj.setPoints(1);
            else{
                result = Integer.valueOf(input);
                myobj.setPoints(result);
            }
            myobj.setID(Integer.valueOf(document.getElementsByTagName("NUMBER").item(cont).getTextContent()));
            this.readRulesPublic(document,myobj.getRules().getRules(),cont);
        }
        else {
            myobj.setID(Integer.valueOf(document.getElementsByTagName("NUMBER").item(cont).getTextContent()) + 10);
            myobj.setPoints(1);
            myobj.getRules().getRules().add(document.getElementsByTagName("COLOR").item(cont).getTextContent());
        }
        return myobj;
    }

    //read values that are relevant only for public cards and not for private ones
    private void readRulesPublic(Document document, ArrayList<String> rules, int cont){
        rules.add(document.getElementsByTagName("WHERE").item(cont).getTextContent());
        rules.add(document.getElementsByTagName("PROP").item(cont).getTextContent());
        rules.add(document.getElementsByTagName("VALUES").item(cont).getTextContent());
        rules.add(document.getElementsByTagName("COLOR").item(cont).getTextContent());
    }

    private WindowPatternCard readPatterns(String namefile, int cont){
        WindowPatternCard mypattern = new WindowPatternCard();
        String all;
        String path = new File(namefile).getAbsolutePath();
        File file = new File(path);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = null;
        try {
            document = documentBuilder.parse(file);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mypattern.setNum(Integer.valueOf(document.getElementsByTagName("NUMBER").item(cont).getTextContent()));
        mypattern.setName(document.getElementsByTagName("NAME").item(cont).getTextContent());
        mypattern.setDifficulty(Integer.valueOf(document.getElementsByTagName("DIFFICULTY").item(cont).getTextContent()));
        all = document.getElementsByTagName("RULES").item(cont).getTextContent();
        System.out.println("\n" + mypattern.getName());
        final int len = all.length();
        for(int i = 0; i < len; i=i+4){
            if(all.charAt(i) == ',')
                continue;
            System.out.print("\n" + i);
            mypattern.addRestr(all.charAt(i),Integer.valueOf(all.charAt(i+1)),Integer.valueOf(all.charAt(i+2)));
        }
        return mypattern;
    }

}
