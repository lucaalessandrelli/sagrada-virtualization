package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.network.ClientInterface;
import it.polimi.ingsw.network.client.Client;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ClientContainer {
    private List<ClientInterface> clients;
    private Manager manager;


    public ClientContainer(Manager manager){
        clients = new ArrayList<>();
        this.manager= manager;
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(5);
        exec.scheduleWithFixedDelay(() -> {
            if(sizeContainer()>0) {
                for (ClientInterface c : clients) {
                    try {
                        c.ping();
                    } catch (RemoteException e) {
                        clients.remove(c);
                        if (clients.size()<2){
                           // manager.notEnoughPlayer();
                        }
                        //notify disconnection
                    }
                }
            }
        },0,2,TimeUnit.SECONDS);

    }
    public void addClient(ClientInterface c){
        clients.add(c);
    }
    public int sizeContainer(){
        return clients.size();
    }

    public List<Player> getPlayerList(){
        List<Player> p = new ArrayList<>();
        for (ClientInterface s : clients){
            try {
                p.add(new Player(s.getName()));
            } catch (RemoteException e) {
                clients.remove(s);
                if (clients.size()<2) {
                  //  manager.notEnoughPlayer();
                }
            }
        }
        return p;
    }
    public boolean findClient(String name) {
        for (ClientInterface cli : clients){
            try {
                if(cli.getName().equals(name)){
                    return true;
                }
            } catch (RemoteException e) {
                return false;
            }
        }
        return false;
    }

    public boolean remove(String name) {
        for (ClientInterface cli : clients){
            try {
                if(cli.getName().equals(name)){
                    clients.remove(cli);
                    return true;
                }
            } catch (RemoteException e) {
                return false;
            }
        }
        return false;
    }

    public void notifyIdMatch(int numOfMatch) {

    }
}
