package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.network.ClientInterface;
import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.view.virtualview.VirtualView;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ClientsContainer {
    private Vector<ClientInterface> clients;
    private Vector<String> nameClients;
    private Manager manager;
    ScheduledExecutorService exec;


    public ClientsContainer(Manager manager){
        clients = new Vector<>();
        nameClients = new Vector<>();
        this.manager= manager;
        exec = Executors.newScheduledThreadPool(1);
        exec.scheduleWithFixedDelay(() -> {

                    for (ClientInterface c : clients) {
                        try {
                                c.ping();
                        } catch (RemoteException e) {
                            System.out.println(nameClients.get(clients.indexOf(c)) + " is disconnected");
                            nameClients.remove(clients.indexOf(c));
                            clients.remove(c);
                            if (clients.size() < 2) {
                                // manager.notEnoughPlayer();
                            }
                            //notify disconnection
                        }
                    }



        },0,1,TimeUnit.SECONDS);

    }
    synchronized public void addClient(ClientInterface c){
        try {
            nameClients.add(c.getName());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        clients.add(c);
    }
    synchronized public int sizeContainer(){
        return clients.size();
    }

    synchronized public List<Player>  getPlayerList(){
        List<Player> p = new ArrayList<>();
        for (ClientInterface s : clients){
            try {
                Player player = new Player(s.getName());
                player.addObserver(new VirtualView(s,player));
                p.add(player);
            } catch (RemoteException e) {
                clients.remove(s);
                if (clients.size()<2) {
                  //  manager.notEnoughPlayer();
                }
            }
        }
        return p;
    }
    synchronized public boolean findClient(String name) {
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

    synchronized public boolean remove(String name) {
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

   synchronized public void notifyIdMatch(int numOfMatch) {

    }
}
