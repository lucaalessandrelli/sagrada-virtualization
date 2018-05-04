package package1;

import java.util.List;

public class StartedMatch implements State {

    public StartedMatch() {

    }

    public void addPlayer(List<Player> playerList, Player player, Match match) {
        //can't add a player, match already started
    }

    public void removePlayer(List<Player> playerList, Player player, Match match) {
        //can't remove a player, match already started
    }

    public void somethingForRounds(List<Player> playerList, Round[] roundArray) {
        //decide later what to do
    }
}
