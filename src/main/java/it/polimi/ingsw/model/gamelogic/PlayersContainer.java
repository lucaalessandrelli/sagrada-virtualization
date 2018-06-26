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

    public IterPlayer getIterator(){
        return new IterPlayer();
    }

    void notifyChanges() {
        players.forEach(Player::notifyPlayer);
    }

    void notifyTurn(String username, long timeSleep) {
        players.forEach(p->p.notifyTurn(username,timeSleep));
    }

    private class IterPlayer implements Iterator{
        private int i;

        private IterPlayer() {
            i = -1;
        }

        @Override
        public boolean hasNext() {
            if(isFirstBracket()) {
                return i <= players.size();
            }else{
                return i != 0;
            }
        }

        @Override
        public Player next() {
            if (i == players.size()-1 && isFirstBracket()) {
                setFirstBracket();
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

        private void setFirstBracket(){
            firstBracket = false;
        }
    }
}

