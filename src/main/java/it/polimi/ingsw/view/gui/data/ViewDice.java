package it.polimi.ingsw.view.gui.data;

import javafx.scene.image.ImageView;

public class ViewDice {
    private static final int COLOR_INDEX = 0;
    private static final int NUMBER_INDEX = 1;
    private ImageView image;
    private char color;
    private char number;

    public ViewDice(ImageView image, String info) {
        this.image = image;
        this.color = info.charAt(COLOR_INDEX);
        this.number = info.charAt(NUMBER_INDEX);
    }

    public char getDiceColor() {
        return this.color;
    }

    public char getDiceNumber() {
        return this.number;
    }

    public ImageView getDiceImage() {
        return image;
    }
}
