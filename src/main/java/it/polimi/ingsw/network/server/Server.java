package it.polimi.ingsw.network.server;

import it.polimi.ingsw.Match;
import it.polimi.ingsw.WaitingRoom;
import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.network.ClientInterface;
import it.polimi.ingsw.network.ServerInterface;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server extends UnicastRemoteObject implements ServerInterface, Serializable {
    private ArrayList<ClientInterface> clients;
    private WaitingRoom lobby;
    private ArrayList<Match> matchList;
    private ClientHandler allPlayers;
    private int numOfMatch;
    private long timerWaitingRoom;


    public Server() throws RemoteException {
        this.matchList = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.numOfMatch = 0;
        this.allPlayers = new ClientHandler();
        this.timerWaitingRoom = 5000;
        this.lobby = new WaitingRoom(timerWaitingRoom);
    }

    //Modifier methods

    public void connectPlayer(Player player) {
        lobby.addPlayer(player);
    }

    public void disconnectPlayer(Player player) {
        lobby.removePlayer(player);
    }

    public void createMatch(WaitingRoom lobby) {
        matchList.add(new Match(lobby.getPlayerList(), this, numOfMatch));
        /*START MATCH*/
        matchList.get(numOfMatch).start();
        numOfMatch++;
        lobby.restore();
    }


    //Getter methods
    public ArrayList<Match> getMatchList() {
        return this.matchList;
    }

    public WaitingRoom getLobby() {
        return lobby;
    }


    @Override
    public synchronized boolean login(String name,ClientInterface client){
        try {
            System.out.println(name + " is connecting " + client.getTypeConnection());
        if(allPlayers.isAPlayerIn(name)){
            System.out.println("Player " + name + " is already logged");
            return false;
        }else{
            allPlayers.addPlayer(name,numOfMatch);
            System.out.println("Player " + name + " is connected");
            clients.add(client);
            return true;
        }
        } catch (RemoteException e) {
            return false;
        }
    }

    @Override
    public String command(String cmd) {
        if(cmd.equals("exit")){
            return "ok";
        }else{
           return "Invalid command";
        }
    }


    @Override
    public void disconnect(String name, ClientInterface client){
        clients.remove(client);
        allPlayers.removePlayer(name);
        System.out.println(name+ " is disconnected");
    }

}
