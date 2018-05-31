package it.polimi.ingsw.view;

import it.polimi.ingsw.network.client.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GuiView extends Application {
    private static Client client;
    private LoginViewController controller;

    public GuiView() {
    }

    public static void setClient(Client c) {
        client = c;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/loginGui.fxml"));
        Parent root = fxmlLoader.load();

        // Get the Controller from the FXMLLoader
        controller = fxmlLoader.getController();
        // Set data in the controller
        controller.setClient(client);
        controller.setStage(primaryStage);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Sagrada");

        primaryStage.show();
    }

    public static void launching() {
        launch();
    }
}