package it.polimi.ingsw.model.gameLogic;

import it.polimi.ingsw.model.gameData.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class PlayersContainer {
    private List<Player> players;

    public PlayersContainer(List<Player> p){
        players = p;
    }

    public boolean checkActivity(){
        List<Player> tmp = players.stream().filter(p -> p.isActive()).collect(Collectors.toCollection(ArrayList::new));
        if(tmp.size()>1){
            return true;
        }else{
            return false;
        }
    }

    public Iterator<Player> getIterator(){
        return new IterPlayer();
    }

    private class IterPlayer implements Iterator{
        private int i;
        boolean firstTurn;

        private IterPlayer() {
            i = -1;
            firstTurn = true;
        }

        @Override
        public boolean hasNext() {
            if(firstTurn) {
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
            if (i == players.size()-1 && firstTurn == true) {
                firstTurn = false;
                i++;
            }
            if (this.hasNext()) {
                if (firstTurn) {
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

