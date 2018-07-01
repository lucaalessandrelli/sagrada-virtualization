package it.polimi.ingsw.view.gui.data;

public class User {
    private String username;
    private String status = "";
    private String score = "";

    public User(String username) {
        this.username = username;
    }

    public User(String username, String status) {
        this.username = username;
        this.status = status;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getUsername() {
        return this.username;
    }

    public String getScore() {
        return this.score;
    }
}
