package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.network.ClientInterface;
import it.polimi.ingsw.view.virtualview.VirtualView;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.System.*;

public class ClientsContainer {
    private List<ClientBox> clients;
    private boolean matchStarted;
    private Manager manager;
    private ScheduledExecutorService exec;


    ClientsContainer(Manager manager){
        clients = new ArrayList<>();
        this.manager= manager;
        matchStarted = false;
        exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleWithFixedDelay(() -> {
            for (ClientBox c : clients) {
                try {
                    c.ping();
                } catch (IOException e) {
                    out.println(c.getName() + " is disconnected");
                    remove(c.getName());

                }
            }
        },0,500,TimeUnit.MILLISECONDS);

    }


    public synchronized void addClient(ClientInterface c, long tempTime){
        try {
            ClientBox cli =new ClientBox(c,c.getName());
            cli.setTimer(tempTime);
            clients.add(cli);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public synchronized int sizeContainer(){
        return clients.size();
    }

    public synchronized List<Player>  getPlayerList(){
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
    synchronized boolean findClient(String name) {
        for (ClientBox cli : clients){
                if(cli.getName().equals(name)){
                    return true;
                }
        }
        return false;
    }

    public synchronized boolean remove(String name) {
        for (ClientBox cli : clients){
                if(cli.getName().equals(name)){
                    clients.remove(cli);
                    if(matchStarted) {
                        manager.setPlayerActivity(cli.getName(),false);
                    }
                    notifyPlayers();
                    return true;
                }
        }
        return false;
    }

    public synchronized void notifyPlayers(){
        StringBuilder str = new StringBuilder();
            for (ClientBox c : clients){
                 str.append(" ").append(c.getName());
            }
            String playersIn = str.toString();
            for(ClientBox c : clients){
                try {
                    c.updatePlayers(playersIn);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
    }

    void setMatchStarted() {
        this.matchStarted = true;
    }

    synchronized void notifyIdMatch(int numOfMatch) {
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

    void reconnect(ClientBox cb) {
        clients.add(cb);
        notifyPlayers();
    }

    public void sendWinner(String message) {
        for(ClientBox c : clients){
            try {
                c.update(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    ClientInterface getClientBox(String name) {
        for (ClientBox c : clients){
            if (c.getName().equals(name)){
                clients.remove(c);
                return c.getInterface();
            }
        }
        return null;
    }
}
