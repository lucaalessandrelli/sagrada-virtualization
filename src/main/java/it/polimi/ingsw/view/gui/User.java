package it.polimi.ingsw.view.gui;

public class User {
    private String username;
    private String status = "xxx";
    private String score = "xxx";

    public User(String username) {
        this.username = username;
    }

    public User(String username, String status) {
        this.username = username;
        this.status = status;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getUsername() {
        return this.username;
    }

    public String getStatus() {
        return this.status;
    }

    public String getScore() {
        return this.score;
    }
}
