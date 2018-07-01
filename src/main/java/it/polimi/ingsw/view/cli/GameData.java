package it.polimi.ingsw.view.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameData {
    private String player;
    private List<String> draftPool;
    private List<List<String>> roundTrack;
    private String[][] restrictions;

    GameData(String name){
        player=name;
        roundTrack= new ArrayList<>();
        for(int i = 0; i<10;i++){
            roundTrack.add(i,new ArrayList<>());
        }
        restrictions = new String[3][4];
    }

    void setRoundTrack(String roundTrack) {
        String[] dices = roundTrack.split(",");
        if (roundTrack.length()>0) {
            for (String dice : dices) {
                int x = Integer.parseInt(String.valueOf(dice.charAt(2)));
                int y = Integer.parseInt(String.valueOf(dice.charAt(3)));
                String tmp = String.valueOf(dice.charAt(1));
                tmp = tmp+"," + (dice.charAt(0));
                this.roundTrack.get(x).add(y, tmp);
            }
        }
    }
    void setDraftPool(String draftPool) {
        List<String> splitted = Arrays.asList(draftPool.split(","));
        this.draftPool = new ArrayList<>();
        for(int i = 0; i< splitted.size(); i++){
            String tmp;
            tmp = String.valueOf(splitted.get(i).charAt(1));
            tmp= tmp +","+(splitted.get(i).charAt(0));
            this.draftPool.add(tmp);
        }

    }

    void setRestrictions(String restrictions) {
        List<String> dices = Arrays.asList(restrictions.split(","));
        if(dices.size()>1 && dices.get(0).equals(player)) {
            dices.remove(0);
            for (String dice : dices) {
                int x = Integer.parseInt(String.valueOf(dice.charAt(2)));
                int y = Integer.parseInt(String.valueOf(dice.charAt(3)));
                String tmp = String.valueOf(dice.charAt(1));
                tmp = tmp + "," + (dice.charAt(0));
                this.restrictions[x][y] = tmp;
            }
        }
    }

    public String getDraft(int x) {
        return draftPool.get(x);
    }

    public String getWindow(int x, int y) {
        return restrictions[x][y];
    }

    public String getRound(int x, int y) {
        return roundTrack.get(x).get(y);
    }
}
