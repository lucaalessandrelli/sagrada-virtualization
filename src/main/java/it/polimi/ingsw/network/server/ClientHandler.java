package it.polimi.ingsw.network.server;

import java.util.HashMap;

public class ClientHandler {
    HashMap<String,Integer> allPlayers;


    protected ClientHandler(){
        allPlayers = new HashMap<>();
    }

    protected synchronized boolean isAPlayerIn(String name){
        return allPlayers.containsKey(name);
    }

    protected synchronized void addPlayer(String name,int idMatch){
        allPlayers.put(name,idMatch);
        System.out.println(allPlayers.size() + " players logged");
    }

    protected synchronized void removePlayer(String name){
        allPlayers.remove(name);
        System.out.println(allPlayers.size() + " players logged");
    }
}
