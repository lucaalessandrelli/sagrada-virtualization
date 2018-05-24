package it.polimi.ingsw.network.server;

import java.util.HashMap;

public class ClientHandler {
    HashMap<String,Integer> allPlayers;


    ClientHandler(){
        allPlayers = new HashMap<>();
    }

    synchronized boolean isAPlayerIn(String name){
        return allPlayers.containsKey(name);
    }

    synchronized void addPlayer(String name, int idMatch){
        allPlayers.put(name,idMatch);
        System.out.println(allPlayers.size() + " players logged");
    }

    synchronized void removePlayer(String name){
        allPlayers.remove(name);
        System.out.println(allPlayers.size() + " players logged");
    }
}
