package it.polimi.ingsw.view.cli;


import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

import static java.lang.System.out;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class Printer {
    private static final String ONE = "2680";
    private static final String TWO = "2681";
    private static final String THREE = "2682";
    private static final String FOUR = "2683";
    private static final String FIVE = "2684";
    private static final String SIX = "2685";
    private static final String CIRCLE = "25CF";
    private static final String IDNUMBER = "Numero id: ";
    private static final String DIFFICULTY = "     Difficoltà: ";
    private static final String ZERO = " 0 ";
    private static final String SETTEDTO = "Il timer è settato a: ";
    private static final String TURNOF = "E' il turno di: ";
    private static final String WAITING = "In attesa di una mossa..";
    private static final String TOOLCARDS = "Carte utensile";
    private static final String COSTO = "Costo";
    private static final String OBJECTIVE = "Carte obiettivo";
    private static final String DESCRIZIONE = "Descrizione";
    private static final String RESTRICTIONS = "restrictions";



    private Scanner in = new Scanner(System.in);

    public Printer(){
        //AnsiConsole.systemInstall();
    }


    public void welcome() {
        out.print(ansi().saveCursorPosition());
        out.print(ansi().a("Benvenuto in "));
        out.print( ansi().render("@|red S|@"));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        out.print( ansi().render("@|green A|@"));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        out.print( ansi().render("@|magenta G|@"));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        out.print( ansi().render("@|yellow R|@"));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        out.print( ansi().render("@|blue A|@"));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        out.print( ansi().render("@|red D|@"));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        out.print( ansi().render("@|green A|@"));
        out.print(ansi().a("    "));
        out.print(ansi().restoreCursorPosition().cursorDown(3));

    }

    public String getName() {
        out.println("Inserisci l'username:");
        return in.nextLine();
    }

    public int getConnection() {
        out.println("Inserisci quale tipo di connessione vuoi utilizzare: \n1)SOCKET \n2)RMI");
        return in.nextInt();
    }

    public void printConnection(String messageConnection) {
        out.print(ansi().saveCursorPosition());
        out.print(ansi().fg(GREEN).a(messageConnection).reset()); //add a colour maybe
        out.print(ansi().restoreCursorPosition().cursorDown(3));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void printError(String alert) {
        out.print(ansi().saveCursorPosition());
        out.println(ansi().fg(RED).a(alert).reset());
        out.print(ansi().restoreCursorPosition().cursorDown(3));
    }

    public void connectedPlayers(String players){
        List<String> connectedplayers = Arrays.asList(players.split(" "));
        out.print(ansi().a("Giocatori connessi:"));
        for(int i = 0; i < connectedplayers.size(); i++){
            out.print("  " + ansi().a(connectedplayers.get(i)));
        }
        out.println();
    }

    public void matchnumber(String match){
        out.println(ansi().fg(MAGENTA).a("You are connected to the match number ").reset().a(match).reset());
    }


    public void printtimer(String timer, String output){
        out.print(ansi().saveCursorPosition());
        out.print(ansi().a(output + timer));
        out.print(ansi().restoreCursorPosition());
    }

    public void printplayersConnected(List<String> players){
        //out.print(ansi().saveCursorPosition());

        out.print(ansi().a("Giocatori connessi:").cursorLeft("Giocatori connessi:".length()));

        out.print(ansi().cursorDown(1));

        for (String player: players) {
            out.print(ansi().a(player));
            out.print(ansi().cursorLeft(player.length()).cursorDown(1));
        }

        out.print(ansi().cursorUp(players.size()));
        //out.print(ansi().restoreCursorPosition());

    }


    public void printchoosePatternCard(String id, String difficulty, List<String> restr){
        out.print(ansi().cursorDown(2));

        out.print(ansi().saveCursorPosition());

        out.print(ansi().a(IDNUMBER + id + DIFFICULTY +difficulty));

        out.print(ansi().restoreCursorPosition());
        out.print(ansi().saveCursorPosition());

        out.print(ansi().cursorDown(2));

        this.printPatternCard(restr);

        out.print(ansi().cursorDown(2));
    }

    public String getCommand() {
        return in.nextLine();
    }

    public void printCoordinates(){
        out.print(ansi().saveCursorPosition());
        out.println(ansi().a("   j0 j1 j2 j3 j4"));
        for(int i = 0; i < 4; i++){
            out.println("i"+i);
        }
        out.print(ansi().restoreCursorPosition());
        out.print(ansi().cursorDown(1).cursorRight(3));
    }

    public void printPatternCard(List<String> restr){
        for(int i = 0; i < restr.size(); i++){
            if((restr.get(i).charAt(1)-'0')!=0){
                out.print(ansi().bg(WHITE).fg(BLACK).a(" " + restr.get(i).charAt(1) + " ").reset());
            }
            else
                printBackground(restr.get(i).charAt(0));
            out.print(ansi().reset());
            if((restr.get(i).charAt(3)-'0')==4) {
                out.print(ansi().cursorDown(1).cursorLeft(15));
            }
        }
    }

    public void printPlacedDices(List<String> dices){
        int updown;
        int leftright;
        out.print(ansi().cursorUp(4));
        out.print(ansi().saveCursorPosition());
        for(int i = 0; i < dices.size(); i++){
            updown = dices.get(i).charAt(2)-'0';
            leftright = dices.get(i).charAt(3)-'0';

            out.print(ansi().cursorDown(updown).cursorRight((leftright*3)));

            printDice(dices.get(i).charAt(0),dices.get(i).charAt(1));

            out.print(ansi().reset());
            out.print(ansi().restoreCursorPosition());
        }
        out.print(ansi().restoreCursorPosition());
        out.print(ansi().cursorUp(1));
        out.print(ansi().saveCursorPosition());
        out.print(ansi().restoreCursorPosition());
        out.print(ansi().cursorDown(7));
    }

    private void printBackground(Character c){
        Color color = WHITE;
        switch (c) {
            case 'W':
                color = WHITE;
                break;
            case 'B':
                color = BLUE;
                break;
            case 'Y':
                color = YELLOW;
                break;
            case 'R':
                color = RED;
                break;
            case 'G':
                color = GREEN;
                break;
            case 'P':
                color = MAGENTA;
                break;
            default:
        }
        out.print(ansi().bg(color).fg(color).a(ZERO).reset());

    }

    private void printDice(Character colour, Character number){
        try{
            PrintStream outStream = new PrintStream(System.out, true, "UTF-8");
            Character num = whatNumber(number);
            Color color = WHITE;

            if(colour == 'W'){
                outStream.print(ansi().fg(WHITE).a(" " + num +" ").reset());
            }
            else{
                switch (colour){
                    case 'B':
                        color = BLUE;
                        break;
                    case 'Y':
                        color = YELLOW;
                        break;
                    case 'R':
                        color = RED;
                        break;
                    case 'G':
                        color = GREEN;
                        break;
                    case 'P':
                        color = MAGENTA;
                        break;
                    default:
                }
                outStream.print(ansi().bg(Color.WHITE).fg(color).a(" " + num +" ").reset());
            }
        } catch(UnsupportedEncodingException e){
            System.out.println("Caught exception: " + e.getMessage());
        }
    }

    private Character whatNumber(Character number){
        int intValue = 0;
        switch (number){
            case '1':
                intValue = Integer.parseInt(ONE, 16);
                break;
            case '2':
                intValue = Integer.parseInt(TWO, 16);
                break;
            case '3':
                intValue = Integer.parseInt(THREE, 16);
                break;
            case '4':
                intValue = Integer.parseInt(FOUR, 16);
                break;
            case '5':
                intValue = Integer.parseInt(FIVE, 16);
                break;
            case '6':
                intValue = Integer.parseInt(SIX, 16);
                break;
            default:
        }
        return (char)intValue;
    }

    public void printWaitingRoom(String timer, List<String> players, String output){
        out.print(ansi().saveCursorPosition());

        out.print(ansi().eraseScreen(Erase.ALL));

        this.printtimer(timer,output);

        out.print(ansi().cursorRight(output.length()+timer.length()+10));

        this.printplayersConnected(players);

        out.print(ansi().restoreCursorPosition());

    }

    public void printChooseCardRoom(String patternCards, String timer) {

        Deparser dep = new Deparser();

        List<String> numplusrestr;

        List<String> deparsed = dep.divideBySemicolon(patternCards);

        out.print(ansi().saveCursorPosition());

        out.print(ansi().eraseScreen(Erase.ALL));

        out.print(ansi().a(SETTEDTO).fg(BLUE).a(timer).reset().restoreCursorPosition());

        out.print(ansi().saveCursorPosition());

        out.print(ansi().cursorDown(3));


        for (String s: deparsed) {
            numplusrestr = dep.divideBySpace(s);

            this.printchoosePatternCard(numplusrestr.get(0),numplusrestr.get(1),dep.divideByComma(numplusrestr.get(2)));
        }

        //print all 4 windows and the timer (il timer deve essere fisso altrimenti il giocatore non può scrivere)
    }

    public void printMatch(String setup, String timer, List<String> players, String turnState) {

        Deparser deparser = new Deparser();

        String currentPlayer = turnState.substring(TURNOF.length()-1,turnState.length());

        deparser.setMyplayer(currentPlayer);

        out.print(ansi().eraseScreen(Erase.ALL));

        out.print(ansi().saveCursorPosition());

        this.printMatchOtherCards(setup,players,deparser);

        out.print(ansi().cursorRight(90));
        this.printplayersConnected(players);

        out.print(ansi().cursorRight(15));
        this.printActivePlayers(setup,players,deparser);

        out.print(ansi().restoreCursorPosition());
        out.print(ansi().cursorDown(8));
        out.print(ansi().saveCursorPosition());

        this.printCards(setup,deparser);

        out.print(ansi().restoreCursorPosition());
        out.print(ansi().cursorRight(70));

        this.printRoundtrack(setup,deparser);

        out.print(ansi().restoreCursorPosition());
        out.print(ansi().cursorDown(13));
        out.print(ansi().saveCursorPosition());

        this.printDescriptionCards(setup,deparser);

        out.print(ansi().restoreCursorPosition());
        out.print(ansi().cursorDown(15));
        out.print(ansi().saveCursorPosition());

        this.printMyPatternCard(setup,currentPlayer,deparser);

        out.print(ansi().restoreCursorPosition().cursorLeft(3).saveCursorPosition());
        out.print(ansi().cursorRight(25));

        this.printPrivateCard(setup,deparser);

        out.print(ansi().restoreCursorPosition());
        out.print(ansi().cursorRight(60));

        this.printDraftPool(setup,deparser);

        out.print(ansi().restoreCursorPosition());
        out.print(ansi().cursorRight(100));

        this.printTurnOf(turnState, deparser);

        out.print(ansi().restoreCursorPosition());
        out.print(ansi().cursorRight(140));

        this.printTimerMatch(timer);

        out.print(ansi().restoreCursorPosition());
        out.print(ansi().cursorDown(8));

        out.print(ansi().fg(YELLOW).a(WAITING).reset());

        out.print(ansi().cursorDown(2));

        //print all match layout (statico)
    }

    public void printScore(String score) {
        //print players score
    }

    private void printActivePlayers(String setup, List<String> players, Deparser deparser){

        String tmp = deparser.findString(setup,"state");

        List<String> tempz = deparser.divideByComma(tmp);

        List<String> active;

        Color color;

        for (String s: tempz) {

            active = deparser.divideBySpace(s);

            if(active.get(1).charAt(0)=='A')
                color = GREEN;
            else
                color = RED;


            out.print(ansi().fg(color).a(active.get(1)).reset().cursorLeft(active.get(1).length()).cursorDown(1));

        }

    }

    private void printMatchOtherCards(String setup, List<String> players, Deparser deparser){

        String parsed = deparser.otherCards(setup,players);

        List<String> tmp = deparser.divideBySemicolon(parsed);
        List<String> newString;

        for (int i = 0; i < players.size()-1; i++){
            newString = deparser.divideBySpace(tmp.get(i));
            if(!deparser.getMyPlayer().equals(newString.get(0))) {
                out.print(ansi().a(newString.get(0))); //Print player name

                out.print(ansi().cursorDown(1).cursorLeft(newString.get(0).length()));

                this.printFavoreTokens(setup, newString.get(0), deparser);

                out.print(ansi().cursorDown(1));

                this.printPatternCard(deparser.divideByComma(newString.get(1)));

                if(newString.size() == 3)
                    this.printPlacedDices(deparser.divideByComma(newString.get(2)));

                out.print(ansi().restoreCursorPosition());

                out.print(ansi().cursorRight(((i % 2) + 1) * (15 + 7)));
            }
        }
        out.print(ansi().restoreCursorPosition());
    }

    private void printFavoreTokens(String setup, String name, Deparser deparser) {

        try {
            PrintStream outStream = new PrintStream(System.out, true, "UTF-8");


            int tmp = setup.indexOf("favors " + name);
            int num = setup.charAt(tmp + ("favors " + name).length() + 1) - '0';

            char circle = (char)Integer.parseInt(CIRCLE, 16);

            for(int i = 0; i < num; i++){
                outStream.print(ansi().fg(CYAN).a(circle + " ").reset());
            }
            outStream.print(ansi().cursorLeft(num*2));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    private void printCards(String setup, Deparser deparser){

        String toolCards = deparser.findString(setup,"toolcards");
        String objectiveCards = deparser.findString(setup,"publiccards");


        List<String> tmp = deparser.divideByBackslash(toolCards);
        List<String> tempz = deparser.divideBySlash(tmp.get(0));

        out.print(ansi().a(TOOLCARDS + ("    ") + COSTO));

        out.print(ansi().cursorDown(1).cursorLeft((TOOLCARDS + ("    ") + COSTO).length()));

        for(int i = 0; i < tmp.size(); i++){
            tempz = deparser.divideBySlash(tmp.get(i));
            out.print(ansi().a(tempz.get(0)));

            out.print(ansi().cursorRight((TOOLCARDS + "    ").length()-tempz.get(0).length()));

            out.print(ansi().a(tempz.get(1)));

            out.print(ansi().cursorDown(1));

            out.print(ansi().cursorLeft((TOOLCARDS+"    ").length()+tempz.get(0).length()));

        }

        out.print(ansi().restoreCursorPosition());

        //here I finished printing toolcards, starting objective cards

        out.print(ansi().cursorRight((TOOLCARDS + "    " + COSTO).length() + 9));

        out.print(ansi().a(OBJECTIVE));

        out.print(ansi().cursorLeft((OBJECTIVE).length()).cursorDown(1));

        tmp = deparser.divideByBackslash(objectiveCards);

        for(int i = 0; i < tmp.size(); i++){
            tempz = deparser.divideBySlash(tmp.get(i));
            out.print(ansi().a(tempz.get(0)));
            out.print(ansi().cursorLeft(tempz.get(0).length()).cursorDown(1));
        }

        out.print(ansi().restoreCursorPosition());

    }

    private void printRoundtrack(String setup, Deparser deparser){

        List<String> tmp = deparser.divideByComma(deparser.findString(setup,"roundtrack"));

        out.print(ansi().a("Traccia dei Round"));
        out.print(ansi().cursorLeft("Traccia dei Round".length()).cursorDown(1));
        out.print(ansi().a("Round numero:"));
        for(int i = 1; i < 11; i++){
            out.print(ansi().a(" " + i + " "));
        }

        out.print(ansi().cursorLeft(31).cursorDown(1));

        for (String dice: tmp){

            out.print(ansi().cursorRight((dice.charAt(2)-'0')*3).cursorDown((dice.charAt(3)-'0')));


            this.printDice(dice.charAt(0),dice.charAt(1));


            out.print(ansi().cursorLeft(((dice.charAt(2)-'0')+1)*3).cursorUp(((dice.charAt(3)-'0'))));

        }

        out.print(ansi().restoreCursorPosition());

    }

    private void printDescriptionCards(String setup, Deparser deparser){

        String tmp = deparser.findString(setup,"toolcards");

        List<String> tempz = deparser.divideByBackslash(tmp);
        List<String> last;

        out.print(ansi().a(TOOLCARDS + "   " + DESCRIZIONE));
        out.print(ansi().cursorLeft(TOOLCARDS.length() + "   ".length() + DESCRIZIONE.length()).cursorDown(1));

        for (int i = 0; i < tempz.size(); i++){
            last = deparser.divideBySlash(tempz.get(i));
            out.print(ansi().a(last.get(0)));
            out.print(ansi().cursorRight((TOOLCARDS.length() + "   ".length() - last.get(0).length())));

            out.print(ansi().a(last.get(2)));

            out.print(ansi().cursorLeft((TOOLCARDS.length() + "   ".length() + DESCRIZIONE.length() - last.get(0).length())+last.get(2).length()));

            out.print(ansi().cursorDown(1));
        }

        out.print(ansi().cursorDown(2));

        tmp = deparser.findString(setup,"publiccards");
        tempz = deparser.divideByBackslash(tmp);


        out.print(ansi().a(OBJECTIVE + "   " + DESCRIZIONE));
        out.print(ansi().cursorLeft(OBJECTIVE.length() + "   ".length() + DESCRIZIONE.length()).cursorDown(1));

        for (int i = 0; i < tempz.size(); i++){
            last = deparser.divideBySlash(tempz.get(i));
            out.print(ansi().a(last.get(0)));
            out.print(ansi().cursorRight((OBJECTIVE.length() + "   ".length() - last.get(0).length())));

            out.print(ansi().a(last.get(1)));

            out.print(ansi().cursorLeft((OBJECTIVE.length() + "   ".length() + DESCRIZIONE.length() - last.get(0).length())+last.get(1).length()));

            out.print(ansi().cursorDown(1));
        }
    }

    private void printMyPatternCard(String setup, String player, Deparser deparser){

        String myCard = deparser.getMyCard(setup,player);

        List<String> newString = deparser.divideBySpace(myCard);

        out.print(ansi().a("   "+newString.get(0)));

        out.print(ansi().cursorDown(1).cursorLeft(newString.get(0).length()));

        this.printFavoreTokens(setup,newString.get(0),deparser);

        out.print(ansi().cursorDown(1).cursorLeft(3));

        this.printCoordinates();
        this.printPatternCard(deparser.divideByComma(newString.get(1)));

        if(newString.size() == 3)
            this.printPlacedDices(deparser.divideByComma(newString.get(2)));

        out.print(ansi().restoreCursorPosition());

    }

    private void printPrivateCard(String setup, Deparser deparser){

        String tmp = deparser.findString(setup,"privatecard");

        List<String> tempz = deparser.divideByComma(tmp);

        out.print(ansi().a("Carta obiettivo privata").cursorDown(1));

        out.print(ansi().cursorLeft("Carta obiettivo privata".length()).a("Numero  Colore Dadi"));

        out.print(ansi().cursorLeft("Numero  Colore Dadi".length()).cursorDown(1));

        Color color = WHITE;

        switch (tempz.get(1).charAt(0)){
            case 'b':
                color = BLUE;
                break;
            case 'g':
                color = YELLOW;
                break;
            case 'r':
                color = RED;
                break;
            case 'v':
                if(tempz.get(1).charAt(1)=='i')
                    color = MAGENTA;
                else
                    color = GREEN;
                break;
            default:
        }

        out.print(ansi().a(tempz.get(0) + "       ").fg(color).a(tempz.get(1).substring(0,1).toUpperCase() + tempz.get(1).substring(1)).reset());

    }

    private void printDraftPool(String setup, Deparser deparser){

        String tmp = deparser.findString(setup,"draftpool");

        List<String> tempz = deparser.divideByComma(tmp);

        out.print(ansi().a("Riserva"));

        out.print(ansi().cursorDown(1).cursorLeft("Riserva".length()));

        for (String s: tempz) {
            this.printDice(s.charAt(0),s.charAt(1));
        }

    }

    private void printTurnOf(String turnState, Deparser deparser){

        out.print(ansi().a(TURNOF + deparser.getMyPlayer()));

    }

    private void printTimerMatch(String timer){

        out.print(ansi().a("Timer: "+timer));

    }

    private class Deparser {

        String myplayer;

        private String getMyPlayer(){
            return myplayer;
        }

        private void setMyplayer(String myplayer) {
            this.myplayer = myplayer;
        }

        private String otherCards(String setup, List<String> players){
            StringBuilder builder = new StringBuilder();

            String tmp = setup.substring(setup.indexOf(RESTRICTIONS),setup.length());

            List<String> tempz = this.divideBySemicolon(tmp);


            for(int i = 0; i < (players.size()*2); i = i+2){
                tempz.set(i,tempz.get(i).replace(RESTRICTIONS + " ",""));
                tempz.set(i+1,tempz.get(i+1).replace("dices ",""));

                if(!tempz.get(i).substring(0,tempz.get(i).indexOf(',')).equals(myplayer)){
                    builder.append(tempz.get(i).substring(0,tempz.get(i).indexOf(',')));
                    builder.append(" ");
                    builder.append(tempz.get(i).replace(";","").replace(tempz.get(i).substring(0,tempz.get(i).indexOf(',')+1),""));
                    builder.append(" ");
                    builder.append(tempz.get(i+1).replace(";","").replace(
                            tempz.get(i).substring(0,tempz.get(i).indexOf(',')),""));
                    builder.append(";");
                }

            }

            return builder.toString();
        }

        private String getMyCard(String setup, String player){

            StringBuilder builder = new StringBuilder();

            String tmp = setup.substring(setup.indexOf((RESTRICTIONS + " " + player)));

            int secondIndex = tmp.indexOf(';', tmp.indexOf(';')+1);

            tmp = tmp.substring(0, secondIndex); //should find the pattern card and dices of my player

            List<String> tempz = this.divideBySemicolon(tmp);

            tempz.set(0,tempz.get(0).replace(RESTRICTIONS + " ",""));
            tempz.set(1,tempz.get(1).replace("dices ",""));

            builder.append(tempz.get(0).substring(0,tempz.get(0).indexOf(',')));
            builder.append(" ");
            builder.append(tempz.get(0).replace(";","").replace(tempz.get(0).substring(0,tempz.get(0).indexOf(',')+1),""));
            builder.append(" ");
            builder.append(tempz.get(1).replace(";","").replace(
                    tempz.get(0).substring(0,tempz.get(0).indexOf(',')+1),""));
            builder.append(";");


            return builder.toString();
        }



        //returns the substring you're looking for without the keyword toFind
        private String findString(String setup, String toFind){
            List<String> divided = divideBySemicolon(setup);

            int i;
            for(i = 0; i < divided.size() && !divideBySpace(divided.get(i)).get(0).equals(toFind);i++);

            return divided.get(i).substring(toFind.length()+1,divided.get(i).length());
        }

        private List<String> divideBySemicolon(String setup) {return Arrays.asList(setup.split(";")); }

        private List<String> divideByComma(String subMessage) {
            return Arrays.asList(subMessage.split(","));
        }

        private List<String> divideBySpace(String chosen){return Arrays.asList(chosen.split(" ")); }

        private List<String> divideBySlash(String setup) {return Arrays.asList(setup.split("/")); }

        private List<String> divideByBackslash(String setup) {return Arrays.asList(setup.split("\\\\")); }

    }


}
