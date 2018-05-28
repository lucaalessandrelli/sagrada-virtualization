package it.polimi.ingsw.model.gamelogic;

import it.polimi.ingsw.model.gamedata.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class PlayersContainer {
    private List<Player> players;
    private boolean firstBracket;

    public PlayersContainer(List<Player> p){
        players = p;
        firstBracket=true;
    }

    public boolean checkActivity(){
        List<Player> tmp = players.stream().filter(Player::isActive).collect(Collectors.toCollection(ArrayList::new));
        return tmp.size() > 1;
    }

    boolean isFirstBracket(){
        return firstBracket;
    }

    private void setFirstBracket(boolean v){
        firstBracket =v;
    }



    public IterPlayer getIterator(){
        return new IterPlayer();
    }

    public void notifyChanges() {
        players.forEach(p -> p.notifyPlayer());
    }

    public void notifyTurn(String username) {
        players.forEach(p->p.notifyTurn(username));
    }

    public Player getLastPlayer() {
        for (Player p : players){
            if(p.isActive()){
                return p;
            }
        }
        return null;
    }

    private class IterPlayer implements Iterator{
        private int i;

        private IterPlayer() {
            i = -1;
        }

        @Override
        public boolean hasNext() {
            if(isFirstBracket()) {
                if (i <= players.size()) {
                    return true;
                } else {
                    return false;
                }
            }else{
                if(i!=0){
                    return true;
                }else{
                    return false;
                }
            }
        }

        @Override
        public Player next() {
            if (i == players.size()-1 && isFirstBracket()) {
                setFirstBracket(false);
                i++;
            }
            if (this.hasNext()) {
                if (isFirstBracket()) {
                    i++;
                    return players.get(i);
                } else {
                    i--;
                    return players.get(i);
                }
            }else {
                return null;
            }
        }
    }
}

