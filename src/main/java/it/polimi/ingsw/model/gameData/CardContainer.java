package it.polimi.ingsw.model.gameData;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CardContainer {

    private ArrayList<Integer> pattern = new ArrayList<Integer>(12);
    private ArrayList<Integer> objectiveprivate = new ArrayList<Integer>(5);
    private ArrayList<Integer> objectivepublic = new ArrayList<Integer>(10);
    public boolean privateused = false;
    public boolean publicused = false;
    public boolean patternused = false;

    //String usr = document.getElementsByTagName("user").item(0).getTextContent();
    //String pwd = document.getElementsByTagName("password").item(0).getTextContent();

    public CardContainer(){
        for(int i = 0; i < 12; i++) {
            pattern.add((i));
            if (i < 5)
            objectiveprivate.add(i);
            if( i < 10)
            objectivepublic.add(i);
        }
    }

    //Pull out 4 WindowPatternCard given to the player to select one of them
    //!!!!Misses one part of the code!!!!
    public ArrayList<WindowPatternCard> pullOutPattern(int numPlayers) throws IOException, SAXException, ParserConfigurationException {
        int dimension = numPlayers;
        ArrayList<WindowPatternCard> tmp = new ArrayList<WindowPatternCard>(dimension);
        try {
            if(this.patternused == true)
                throw new AlreadyBeenCalledException();
            this.patternused = true;
            int randomNum;
            int cont = pattern.size();
            Random rand = new Random();
            for (int k = 0; k < dimension; k++) {
                randomNum = rand.nextInt(cont - k);
                //   ---->  Devi aggiungere il metodo per caricare i dati dal nuovo file
                // tmp.add(this.readValues("C:/Users/Vincenzo/IdeaProjects/ProgettoIngSw/src/main/resources/public_cards_formalization.xml", pattern.get(randomNum))); //Supposing that z is the selected card from this "turn"
                pattern.remove(randomNum);
            }
        } catch (AlreadyBeenCalledException e){
            System.out.println(e.message);
        }
        return tmp;
    }

    //Pull out x Object Private Card given to the player to select one of them
    //!!!!Misses one part of the code!!!!
    public ArrayList<ObjectiveCard> pullOutPrivate(int numPlayers) throws IOException, SAXException, ParserConfigurationException {
        int dimension = numPlayers;
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
    public ArrayList<ObjectiveCard> pullOutPublic() throws IOException, SAXException, ParserConfigurationException, AlreadyBeenCalledException {
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

    private ObjectiveCard readValues(String namefile, int cont) throws ParserConfigurationException, IOException, SAXException {
        ObjectiveCard myobj = new ObjectiveCard();
        int result;
        String path = new File(namefile).getAbsolutePath();
        File file = new File(path);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);
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

    private void readRulesPublic(Document document, ArrayList<String> rules, int cont){
        rules.add(document.getElementsByTagName("WHERE").item(cont).getTextContent());
        rules.add(document.getElementsByTagName("PROP").item(cont).getTextContent());
        rules.add(document.getElementsByTagName("VALUES").item(cont).getTextContent());
        rules.add(document.getElementsByTagName("COLOR").item(cont).getTextContent());
    }

}
