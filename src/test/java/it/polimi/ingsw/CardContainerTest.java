package it.polimi.ingsw;

import it.polimi.ingsw.model.gameData.AlreadyBeenCalledException;
import it.polimi.ingsw.model.gameData.CardContainer;
import it.polimi.ingsw.model.gameData.ObjectiveCard;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardContainerTest {

    @Test
    public void TestPrivatePullout(){
        CardContainer tester = new CardContainer();
        ArrayList<ObjectiveCard> test = null;
        try {
            test = tester.pullOutPrivate(2);
            for (ObjectiveCard x: test) {
                System.out.println("Id number: " + x.getID() +
                                    "\nName: " + x.getName() +
                                    "\nDescription: " + x.getDescription() +
                                    "\nType: " + x.getType() +
                                    "\nPoints: " + x.getPoints() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        assertEquals(2, test.size());
    }

    @Test
    public void TestPublicPullout() throws SAXException, ParserConfigurationException, AlreadyBeenCalledException, IOException {
        CardContainer tester = new CardContainer();
        ArrayList<ObjectiveCard> test = null;
        try {
            test = tester.pullOutPublic();
            for (ObjectiveCard x: test) {
                System.out.println("Id number: " + x.getID() +
                        "\nName: " + x.getName() +
                        "\nDescription: " + x.getDescription() +
                        "\nType: " + x.getType() +
                        "\nPoints: " + x.getPoints() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (AlreadyBeenCalledException e) {
            e.printStackTrace();
        }
        assertEquals(3, test.size());
    }

    @Test
    public void TestPublicRules() throws SAXException, ParserConfigurationException, AlreadyBeenCalledException, IOException {
        CardContainer tester = new CardContainer();
        ArrayList<ObjectiveCard> test = new ArrayList<ObjectiveCard>();
        test = tester.pullOutPublic();
        for (ObjectiveCard x: test) {
            for (String k: x.getRules()){
             System.out.println("Regola: " + k + "\n");
            }
            assertTrue(!(x.getRules().isEmpty()));
        }
    }
}
