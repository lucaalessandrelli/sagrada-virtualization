package package1;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.util.ArrayList;

public class ScoreTrack {
    //initialized at 4 because is the maximum capacity we'll ever use for this ArrayList
    private ArrayList<ScoreMarker> markers = new ArrayList<ScoreMarker>(4);


    //It's a simply solution, I've to think better about how to do this
    public ScoreTrack(ScoreMarker one){
        markers.add(one);
    }

    public ScoreTrack(ScoreMarker one, ScoreMarker two){
        markers.add(one);
        markers.add(two);
    }

    public ScoreTrack(ScoreMarker one, ScoreMarker two, ScoreMarker three) {
        markers.add(one);
        markers.add(two);
        markers.add(three);
    }

    public ScoreTrack(ScoreMarker one, ScoreMarker two, ScoreMarker three, ScoreMarker four){
        markers.add(one);
        markers.add(two);
        markers.add(three);
        markers.add(four);
    }

    public ArrayList<String> getPlayers() {
        ArrayList<String> players = new ArrayList<String>(4);
        for (ScoreMarker x: markers) {
            //players.add(x.getPlayer().getName());
        }
        return players;
    }
}
