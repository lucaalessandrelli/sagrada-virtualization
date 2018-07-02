package it.polimi.ingsw.view.gui.utilitywindows;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Static class to define a new javafx window screen that displays alert messages.
 */
public class AlertWindow {

    /**
     * Private constructor preventing instantiation of AlertWindow objects.
     */
    private AlertWindow() {

    }

    /**
     * Method used to display the alert Window.
     * @param title String containing the title of the alter window.
     * @param message String containing the message that will be displayed in the alert window.
     */
    public static void display(String title, String message) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(400);
        stage.setMinHeight(150);

        Label label = new Label(message);
        VBox layout = new VBox(10);
        layout.getChildren().add(label);
        layout.setAlignment(Pos.CENTER);

        layout.setStyle("-fx-background-color: #2D3647");
        label.setTextFill(Color.valueOf("#f8e7e7"));

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
