package it.polimi.ingsw.network.client;

import java.util.ArrayList;
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
    private static final String RETRY = "retry ";
    private static final String MYCARD = "mycard ";




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

    public String compose(String cmd) {
        if(cmd.equals("login")) {
            return LOGIN + " <User>" + client.getName() + "<User>";
        }
        return cmd;
    }

    public String sanitize(String command) {
        for (String s : words) {
            if (command.contains(s)) {
                return "error";
            }
        }

        String res;
        if(command.startsWith(DRAFT)) {
            res=command.replace(DRAFT,"");
            return MOVE+client.getNumOfMatch()+" "+client.getName()+" "+"D;"+res;   //tra x e y bisogna vedere qual'è a 0
        }
        else if (command.startsWith(WINDOW)) {
            res=command.replace(WINDOW,"");
            return MOVE+client.getNumOfMatch()+" "+client.getName()+" "+"D;"+res;
        }
        else if (command.startsWith(ROUNDTRACK) ){
            res=command.replace(ROUNDTRACK,"");
            return MOVE+client.getNumOfMatch()+" "+client.getName()+" "+"D;"+res;
        }
        else if (command.startsWith(PLACE) ){
            res=command.replace(PLACE,"");
            return MOVE+client.getNumOfMatch()+" "+client.getName()+" "+"P;"+res;
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

        return "error";

    }
}
