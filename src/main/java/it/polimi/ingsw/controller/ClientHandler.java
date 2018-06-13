package it.polimi.ingsw.controller;

import java.util.HashMap;

import static java.lang.System.*;

public class ClientHandler {
    HashMap<String,Integer> allPlayers;


    ClientHandler(){
        allPlayers = new HashMap<>();
    }

    public synchronized boolean isAPlayerIn(String name){
        return allPlayers.containsKey(name);
    }

    public synchronized void addPlayer(String name, int idMatch){
        allPlayers.put(name,idMatch);
        out.println(allPlayers.size() + " players playing");
    }

    public synchronized void removePlayer(String name){
        allPlayers.remove(name);
        out.println(allPlayers.size() + " players playing");
    }

    public int getGame(String name) {
        return allPlayers.get(name);
    }
}
