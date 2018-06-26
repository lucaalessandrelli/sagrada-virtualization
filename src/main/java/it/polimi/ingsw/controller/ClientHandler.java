package it.polimi.ingsw.controller;

import java.util.HashMap;

import static java.lang.System.*;

class ClientHandler {
    private HashMap<String,Integer> allPlayers;


    ClientHandler(){
        allPlayers = new HashMap<>();
    }

    synchronized boolean isAPlayerIn(String name){
        return allPlayers.containsKey(name);
    }

    synchronized void addPlayer(String name, int idMatch){
        allPlayers.put(name,idMatch);
        out.println(allPlayers.size() + " players playing");
    }

    synchronized void removePlayer(String name){
        allPlayers.remove(name);
        out.println(allPlayers.size() + " players playing");
    }

    int getGame(String name) {
        return allPlayers.get(name);
    }
}
