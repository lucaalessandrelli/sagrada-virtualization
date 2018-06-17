package it.polimi.ingsw.network.server;

import it.polimi.ingsw.controller.Manager;
import it.polimi.ingsw.network.ClientInterface;
import it.polimi.ingsw.network.ServerInterface;

import java.rmi.RemoteException;

import static java.lang.System.out;

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
                    out.println("Reconnecting "+ name + " to the match");
                    manager.reconnectPlayer(client);
                    return true;
                } else {
                    client.updateMessage("Connected, Welcome!");
                    manager.addPlayerInQueue(client);
                    out.println("Player " + name + " is connected");
                    return true;
                }
            }
        } catch (RemoteException e) {
            return false;
        }
    }

    @Override
    public synchronized void command(String cmd) {
        manager.analyze(cmd);
    }


    @Override
    public synchronized void disconnect(String name, ClientInterface client){
        manager.remove(name);
        out.println(name+ " is disconnected");
    }

}
