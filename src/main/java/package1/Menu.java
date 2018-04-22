package package1;

public class Menu {
    private int matchType;
    private int connectionType;
    private int uiType;

    public Menu () {

    }

    public void setMatchType(int tipoPartita) {
        this.matchType = tipoPartita;
    }

    public int getMatchType() {
        return this.matchType;
    }

    public void setConnectionType(int connectionType) {
        this.connectionType = connectionType;
    }

    public int getConnectionType() {
        return this.connectionType;
    }

    public void setUIType(int uiType) {
        this.uiType = uiType;
    }

    public int getUIType() {
        return this.uiType;
    }
}
