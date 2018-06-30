package it.polimi.ingsw.network.client;

import it.polimi.ingsw.view.cli.GameData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputComposer {
    private static final String MOVE = "move ";
    private static final String CHOOSECARD = "chooseCard ";
    private static final String PLAYAGAIN = "playAgain ";
    private static final String DISCONNECT = "disconnect ";
    private static final String LOGIN = "login ";

    private static final String DRAFT = "draft ";
    private static final String WINDOW = "window ";
    private static final String ROUNDTRACK = "roundtrack ";
    private static final String PLACE = "place ";
    private static final String TOOLCARD = "toolcard ";
    private static final String RETRY = "retry";
    private static final String MYCARD = "mycard ";

    private static final String ALERT = "alert Formato mossa errato!";
    private static final String ERROR = "error";

    private static final int MAXRIG = 4 ;
    private static final int MAXCOL = 5;


    private Client client;
    private List<String> words;
    public InputComposer(Client client) {
        this.client = client;
        words = new ArrayList<>();
        words.add(MOVE);
        words.add(CHOOSECARD);
        words.add(PLAYAGAIN);
        words.add(DISCONNECT);
        words.add(LOGIN);

    }

    String compose(String cmd) {
        if(cmd.equals("login")) {
            return LOGIN + " <User>" + client.getName() + "<User>";
        }
        return cmd;
    }

    public String sanitize(String command, GameData gameData) {
        for (String s : words) {
            if (command.contains(s)) {
                return ERROR;
            }
        }

        String res;
        if(command.startsWith(DRAFT)) {
            res=command.replace(DRAFT,"");
            return getDraftData(res,gameData);
        }
        else if (command.startsWith(WINDOW)) {
            res=command.replace(WINDOW,"");
            return getWindowData(res,gameData);
        }
        else if (command.startsWith(ROUNDTRACK) ){
            res=command.replace(ROUNDTRACK,"");
            return getRoundData(res,gameData);
        }
        else if (command.startsWith(PLACE) ){
            res=command.replace(PLACE,"");
            return getPos(res);
        }
        else if (command.startsWith(TOOLCARD) ){
            res=command.replace(TOOLCARD,"");
            return MOVE+client.getNumOfMatch()+" "+client.getName()+" "+"T;"+res;
        }
        else if (command.startsWith(RETRY)) {
            return PLAYAGAIN + client.getName();
        }
        else if (command.startsWith(MYCARD) ){
            res=command.replace(MYCARD,"");
            return CHOOSECARD+client.getNumOfMatch()+" "+client.getName()+" "+res;
        }

        return ERROR;

    }

    private String getPos(String res) {
        try{
            String[] pos = res.split(",");
            int x = Integer.parseInt(pos[0]);
            int y = Integer.parseInt(pos[1]);
            if(x>=0 && x< MAXRIG && y>=0 && y<MAXCOL){
                return MOVE+client.getNumOfMatch()+" "+client.getName()+" "+"P;"+res;
            }else {
                return ERROR;
            }
        }catch (RuntimeException e ){
            client.setServiceMessage(ALERT);
            return ERROR;
        }
    }

    private String getRoundData(String res, GameData gameData) {
        try {
            String[] pos = (res.split(","));
            int x = Integer.parseInt(pos[0]);
            int y = Integer.parseInt(pos[1]);
            return MOVE+client.getNumOfMatch()+" "+client.getName()+" "+"D;"+gameData.getRound(x-1,y)+","+ Arrays.toString(pos);
        }catch (RuntimeException e){
            client.setServiceMessage(ALERT);
            return ERROR;
        }
    }

    private String getWindowData(String res, GameData gameData) {
        try {
            String[] pos = (res.split(","));
            int x = Integer.parseInt(pos[0]);
            int y = Integer.parseInt(pos[1]);
            return MOVE+client.getNumOfMatch()+" "+client.getName()+" "+"D;"+gameData.getWindow(x,y)+","+ Arrays.toString(pos);
        }catch (RuntimeException e){
            client.setServiceMessage(ALERT);
            return ERROR;
        }
    }

    private String getDraftData(String res, GameData gameData) {
        try {
            int x = Integer.parseInt(res);
            return MOVE+client.getNumOfMatch()+" "+client.getName()+" "+"D;"+gameData.getDraft(x)+","+x+"0";
        }catch (RuntimeException e){
            client.setServiceMessage(ALERT);
            return ERROR;
        }

    }
}
