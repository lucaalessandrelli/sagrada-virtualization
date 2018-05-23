package it.polimi.ingsw.network.client;

import java.io.Serializable;

public class InputComposer implements Serializable {
    private Client client;
    public InputComposer(Client client) {
        this.client = client;
    }

    public String compose(String cmd) {
        if(cmd.equals("login")){
            return "login " + " <User>" + client.getName() + "<User>";
        }else if(cmd.equals("disconnect")){
            return "//disconnect"+ " <User>" + client.getName() + "<User>";
        }else if(cmd.equals("exit")){
            return "//exit";
        }
        return cmd;
    }
}
