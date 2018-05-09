package package1;

import java.util.List;

public interface State {

    //add a player to the waiting rooom
    public void addPlayer(List<Player> playerList, Player player, Match match);
    public void removePlayer(List<Player> playerList, Player player, Match match);
    public void createRounds(List<Player> playerList, Match match, List<Round> roundList);
}
