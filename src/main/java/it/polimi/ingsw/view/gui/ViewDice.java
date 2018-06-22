package it.polimi.ingsw.view.gui;

import javafx.scene.image.ImageView;

public class ViewDice {
    private static final int colorIndex = 0;
    private static final int numberIndex = 1;
    private ImageView image;
    private char color;
    private char number;

    public ViewDice(ImageView image, String info) {
        this.image = image;
        this.color = info.charAt(colorIndex);
        this.number = info.charAt(numberIndex);
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
