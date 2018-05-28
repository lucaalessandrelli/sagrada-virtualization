package it.polimi.ingsw.view;

public class WindowFalsa {
    private String[][] window;

    public WindowFalsa(String[][] window) {
        this.window = window;
    }

    public void printWindow() {
        for(int i = 0; i < 5;i++) {
            for(int j = 0; j < 4; j++) {
                System.out.println(window[i][j]);
            }
            System.out.println("\n");
        }
    }

    public String getStr(int i ,int j) {
        return window[i][j];
    }
}
