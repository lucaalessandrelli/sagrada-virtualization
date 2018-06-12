package it.polimi.ingsw.view.cli;

import java.util.Scanner;

import static java.lang.System.out;

public class Printer {
    private static final String RESET = "\\e[0m";
    private static final String GREEN = "\\e[0;32m";
    private static final String RED = "\\e[0;31m";
    private Scanner in = new Scanner(System.in);

    public void welcome() {
        //write a welcome message;
    }

    public String getName() {
        out.print("Insert username:");
        return in.nextLine();
    }

    public int getConnection() {
        out.println("Insert kind connection: \n1)SOCKET \n2)RMI");
        return in.nextInt();
    }

    public void print(String messageConnection) {
        out.println(GREEN+messageConnection+RESET); //add a colour maybe
    }


    public void printError(String alert) {
        out.println(RED+alert+RESET);
    }

    public String getCommand() {
        return in.nextLine();
    }
}
