package it.polimi.ingsw.controller;

public class InputAnalyzer {
    private Manager manager;

    public  InputAnalyzer(){manager=null;}
    InputAnalyzer(Manager manager) {
        this.manager = manager;
    }

    /**
     * This analyze the command from the client and verify the type and if it is well formed. After that it will be passed
     * to the manager
     * @param in command from users
     */
    void analyse(String in){
        try {
            if (in.startsWith("move ")) {
                String res = in.replace("move ", "");
                String[] token = res.split(" ");
                int num = Integer.parseInt(token[0]);
                String name = token[1];
                String move = token[2];
                manager.move(num, name, move);
            } else if (in.startsWith("chooseCard ")) {
                String res = in.replace("chooseCard ", "");
                String[] token = res.split(" ");
                int num = Integer.parseInt(token[0]);
                String name = token[1];
                String window = token[2];
                manager.setPlayerWindow(num, name, window);
            } else if (in.startsWith("disconnect ")) {
                String res = in.replace("disconnect ", "");
                String[] token = res.split(" ");
                int num = Integer.parseInt(token[0]);
                String name = token[1];
                manager.disconnectPlayer(num, name);
            } else if (in.startsWith("playAgain ")) {
                String res = in.replace("playAgain ", "");
                String[] token = res.split(" ");
                String name = token[0];
                manager.revenge(name);
            }
        }catch (RuntimeException e){
            System.out.println("Bad Command");
        }
    }

    public  String getData(String in){
        String tmp = in.replace("<User>","");
                tmp=tmp.replace("login","");
                tmp=tmp.trim();
        return tmp;
    }

}
