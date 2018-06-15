package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.model.gamedata.gametools.WindowPatternCard;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import org.fusesource.jansi.AnsiRenderer;

import javax.management.timer.Timer;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class Printer {
    //private static final String RESET = "\\e[0m";
    //private static final String GREEN = "\\e[0;32m";
    //private static final String RED = "\\e[0;31m";
    private static final String ONE = "2680";
    private static final String TWO = "2681";
    private static final String THREE = "2682";
    private static final String FOUR = "2683";
    private static final String FIVE = "2684";
    private static final String SIX = "2685";
    private static final String BLOCK = "⚀";

    private Scanner in = new Scanner(System.in);

    public Printer(){
        //AnsiConsole.systemInstall();
    }


    public void welcome() {
        out.print(ansi().a("Welcome to "));
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
        out.println("    ");
    }

    public String getName() {
        out.println("Please, insert username:");
        return in.nextLine();
    }

    public int getConnection() {
        out.println("Insert what kind of connection you want to use: \n1)SOCKET \n2)RMI");
        return in.nextInt();
    }

    public void print(String messageConnection) {
        out.println(ansi().fg(GREEN).a(messageConnection).reset()); //add a colour maybe
    }


    public void printError(String alert) {
        out.println(ansi().fg(RED).a(alert).reset());
    }

    public void connectedPlayers(String players){
        List<String> connectedplayers = Arrays.asList(players.split(" "));
        out.print(ansi().a("Players connected :"));
        for(int i = 0; i < connectedplayers.size(); i++){
            out.print("  " + ansi().a(connectedplayers.get(i)));
        }
        out.println();
    }

    public void matchnumber(String match){
        out.println(ansi().fg(MAGENTA).a("You are connected to the match number ").reset().a(match).reset());
    }



    public void timer(String timer){
        out.println(ansi().a("The timer is setted to: " + timer +"s"));
    }

    public void startTimer(String timer){
        int time = Integer.parseInt(timer);
        for(;time>=0;time--){
            out.print(ansi().cursorLeft("The match will start in..".length() +5).a("The match will start in.." + time + "  "));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void resetTimer(String timer){
        out.println(ansi().fg(RED).a("Not enough player to start the match, the timer will be resetted to" + timer));
    }

    public void choosePatternCard(String windows){
        out.println(ansi().eraseScreen());
        out.print(ansi().eraseLine());
        List<String> space = Arrays.asList(windows.split(";"));
        for(int k = 0; k < space.size(); k++){
            List <String> list = Arrays.asList(space.get(k).split(" "));
            out.print("Id card number: " + list.get(0)); //prints the id number of the card
            out.print("    Difficulty: " + list.get(1)); //prints the difficulty of the card
            List<String> restr = Arrays.asList(list.get(2).split(","));
            out.println();
            out.println();
            this.printPatternCard(restr);
            out.println();
        }
    }

    public String getCommand() {
        return in.nextLine();
    }

    public void printPatternCard(List<String> restr){
        for(int i = 0; i < restr.size(); i++){
            if((restr.get(i).charAt(1)-'0')!=0){
                out.print(ansi().bg(WHITE).fg(BLACK).a(" " + restr.get(i).charAt(1) + " ").reset());
            }
            else
                printBackground(restr.get(i).charAt(0));
            out.print(ansi().reset());
            if((restr.get(i).charAt(3)-'0')==4)
                out.println();
        }
    }

    public void printPlacedDices(List<String> dices){
        int updown;
        int leftright;
        out.print(ansi().cursorLeft(50));
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
        out.print(ansi().cursorDown(6));
    }

    private void printBackground(Character c){
        if(c == 'W')
            out.print(ansi().bg(WHITE).fg(WHITE).a(" 0 ").reset());
        if(c == 'B')
            out.print(ansi().bg(BLUE).fg(BLUE).a(" 0 ").reset());
        if(c == 'Y')
            out.print(ansi().bg(YELLOW).fg(YELLOW).a(" 0 ").reset());
        if(c == 'R')
            out.print(ansi().bg(RED).fg(RED).a(" 0 ").reset());
        if(c == 'G')
            out.print(ansi().bg(GREEN).fg(GREEN).a(" 0 ").reset());
        if(c == 'P')
            out.print(ansi().bg(MAGENTA).fg(MAGENTA).a(" 0 ").reset());
    }

    private void printDice(Character colour, Character number){
        try{
            PrintStream outStream = new PrintStream(System.out, true, "UTF-8");
            Character num = whatNumber(number);
            if(colour == 'W')
                outStream.print(ansi().fg(WHITE).a(" " + num +" ").reset());
            if(colour == 'B')
                outStream.print(ansi().bg(Color.WHITE).fg(BLUE).a(" " + num +" ").reset());
            if(colour == 'Y')
                outStream.print(ansi().bg(Color.WHITE).fg(YELLOW).a(" " + num +" ").reset());
            if(colour == 'R')
                outStream.print(ansi().bg(Color.WHITE).fg(RED).a(" " + num +" ").reset());
            if(colour == 'G')
                outStream.print(ansi().bg(Color.WHITE).fg(GREEN).a(" " + num +" ").reset());
            if(colour == 'P')
                outStream.print(ansi().bg(Color.WHITE).fg(MAGENTA).a(" " + num +" ").reset());
        } catch(UnsupportedEncodingException e){
            System.out.println("Caught exception: " + e.getMessage());
        }
    }

    private Character whatNumber(Character number){
        int intValue = 0;
        if(number == '1'){
            intValue = Integer.parseInt(ONE, 16);
        }
        if(number == '2'){
            intValue = Integer.parseInt(TWO, 16);
        }
        if(number == '3'){
            intValue = Integer.parseInt(THREE, 16);
        }
        if(number == '4'){
            intValue = Integer.parseInt(FOUR, 16);
        }
        if(number == '5'){
            intValue = Integer.parseInt(FIVE, 16);
        }
        if(number == '6'){
            intValue = Integer.parseInt(SIX, 16);
        }
        return (char)intValue;
    }
}
