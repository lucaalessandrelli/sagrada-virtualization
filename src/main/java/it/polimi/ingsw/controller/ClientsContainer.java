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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ClientsContainer {
    private List<ClientBox> clients;
    private boolean matchStarted;
    private Manager manager;
    private ScheduledExecutorService exec;


    public ClientsContainer(Manager manager){
        clients = new ArrayList<>();
        this.manager= manager;
        matchStarted = false;
        exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleWithFixedDelay(() -> {
            for (ClientBox c : clients) {
                try {
                    c.ping();
                } catch (IOException e) {
                    System.out.println(c.getName() + " is disconnected");
                    remove(c.getName());

                }
            }
        },0,1,TimeUnit.SECONDS);

    }


    synchronized public void addClient(ClientInterface c, long tempTime){
        try {
            ClientBox cli =new ClientBox(c,c.getName(),c.getTypeConnection());
            cli.setTimer(tempTime);
            clients.add(cli);
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
                 str.append(" " + c.getName());
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

    public void sendWinner(String message) {
        for(ClientBox c : clients){
            try {
                c.update(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
