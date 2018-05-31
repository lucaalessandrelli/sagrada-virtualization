package it.polimi.ingsw.network.server;

import it.polimi.ingsw.Match;
import it.polimi.ingsw.WaitingRoom;
import it.polimi.ingsw.controller.ClientHandler;
import it.polimi.ingsw.controller.Manager;
import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.network.ClientInterface;
import it.polimi.ingsw.network.ServerInterface;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.*;

public class Server implements ServerInterface {
    private Manager manager;


    public Server() throws RemoteException {
    }
    public void setManager(Manager m){
        manager = m;
    }




    //Getter methods
    /*public List<Match> getMatchList() {
        return this.matchList;
    }

    public WaitingRoom getLobby() {
        return lobby;
    }*/


    @Override
    public synchronized boolean login(String name,ClientInterface client){
        try {
            out.println(name + " is trying to connect " + client.getTypeConnection());
            if (manager.checkIfPlayerIsLogged(name)) {
                out.println("Player " + name + " is already logged");
                return false;
            } else {
                if (manager.checkIfPlayerIsPlaying(name)) {
                    //riconnetti il giocatore alla partita
                } else {
                    manager.addPlayerInQueue(client);
                    out.println("Player " + name + " is connected");
                    return true;
                }
            }
        } catch (RemoteException e) {
            return false;
        }
        return false;
    }

    @Override
    public synchronized String command(String cmd) {
        if(cmd.equals("exit")){
            return "service Disconnected form server";
        }else{
           return "service Invalid command";
        }
    }


    @Override
    public synchronized void disconnect(String name, ClientInterface client){
        manager.remove(name);
        out.println(name+ " is disconnected");
    }

}
