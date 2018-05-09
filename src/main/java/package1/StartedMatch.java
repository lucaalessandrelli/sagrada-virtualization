package package1;

import java.util.ArrayList;
import java.util.List;

//create a Table object and send it to the Match class.)
public class StartedMatch implements State {

    public StartedMatch() {

    }

    public void addPlayer(List<Player> playerList, Player player, Match match) {
        //can't add a player, match already started
    }

    public void removePlayer(List<Player> playerList, Player player, Match match) {
        //can't remove a player, match already started
    }

    public void createRounds(List<Player> playerList, Match match,List<Round> roundList) {
        int roundNumber = 1;

        for(;roundNumber < 11; roundNumber++) {
            match.addRoundList(new Round(match, playerList, roundNumber));
        }
    }
}
