package it.polimi.ingsw.network.client;

import java.rmi.RemoteException;

public class AnswerAnalyzer {
    Client client;

    public AnswerAnalyzer(Client client) {
        this.client = client;
    }

    public synchronized void analyze(String answer) throws RemoteException {
        if(answer.equals("service Disconnected form server")){
            answer = answer.replace("service ", "");
            client.setConnected(false);
            client.setServiceMessage(answer);
        }else if(answer.startsWith("play")){
            answer =answer.replace("play ","");
            client.setNumMatch(Integer.parseInt(answer));
        }else if(answer.startsWith("moveWindow")){
            String window= answer.replace("moveWindow ","");
            client.updateWindows(window);
        }else if(answer.startsWith("moveDraft")){
            String draft =answer.replace("moveDraft ","");
            client.updateDraftPool(draft);
        }else if(answer.startsWith("service")) {
            answer = answer.replace("service ", "");
            client.setServiceMessage(answer);
        }
    }
}
