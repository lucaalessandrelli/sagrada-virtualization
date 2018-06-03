package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.network.ClientInterface;
import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.view.virtualview.VirtualView;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ClientsContainer {
    private List<ClientBox> clients;
    private boolean matchStarted;
    private Manager manager;
    ScheduledExecutorService exec;


    public ClientsContainer(Manager manager){
        clients = new ArrayList<>();
        this.manager= manager;
        matchStarted = false;
        exec = Executors.newScheduledThreadPool(1);
        exec.scheduleWithFixedDelay(() -> {
            for (ClientBox c : clients) {
                try {
                    c.ping();
                } catch (RemoteException e) {
                    System.out.println(c.getName() + " is disconnected");
                    if(matchStarted) {
                        manager.setPlayerInactive(c.getName());
                    }
                    clients.remove(c);
                    notifyPlayers();
                    if (clients.size() < 2) {
                        if (matchStarted) {
                            notifyWinner(clients.get(0).getName());
                            manager.notifyEnd(clients.get(0).getName());

                        }else{
                            manager.notEnoughPlayer();
                        }
                    }
                }
            }
        },0,1,TimeUnit.SECONDS);

    }

    private void notifyWinner(String name) {
    }

    synchronized public void addClient(ClientInterface c){
        try {
            clients.add(new ClientBox(c,c.getName(),c.getTypeConnection()));
            notifyPlayers();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    synchronized public int sizeContainer(){
        return clients.size();
    }

    synchronized public List<Player>  getPlayerList(){
        List<Player> p = new ArrayList<>();
        Iterator<ClientBox> i = clients.iterator();
        while (i.hasNext()){
            ClientBox c = i.next();
                Player player = new Player(c.getName());
                player.addObserver(new VirtualView(c,player));
                p.add(player);
        }
        return p;
    }
    synchronized public boolean findClient(String name) {
        for (ClientBox cli : clients){
                if(cli.getName().equals(name)){
                    return true;
                }
        }
        return false;
    }

    synchronized public boolean remove(String name) {
        for (ClientBox cli : clients){
                if(cli.getName().equals(name)){
                    clients.remove(cli);
                    notifyPlayers();
                    return true;
                }
        }
        return false;
    }

    synchronized void notifyPlayers(){
        StringBuilder str = new StringBuilder();
            for (ClientBox c : clients){
                 str.append(" " + c.getName());
            }
            String playersIn = str.toString();
            playersIn = "service" + playersIn;
            String finalPlayersIn = playersIn;
            for(ClientBox c : clients){
                try {
                    c.updatePlayers(finalPlayersIn);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
    }

    public void setMatchStarted(boolean matchStarted) {
        this.matchStarted = matchStarted;
    }

    synchronized public void notifyIdMatch(int numOfMatch) {
        for (ClientBox c : clients){
            try {
                c.setNumMatch(numOfMatch);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }

    public void notifyTimer(long tempTime) {
        for (ClientBox c : clients){
            try {
                c.setTimer(tempTime);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void reconnect(ClientBox cb) {
        clients.add(cb);
    }
}
