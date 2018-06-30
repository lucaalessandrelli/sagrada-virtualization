package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.network.client.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GuiView extends Application {
    private static Client client;
    private static GuiHandler guiHandler;
    private LoginViewController controller;

    public GuiView() {
        /*no need to create an instance of this class*/
    }

    public static void setClient(Client c) {
        client = c;
    }

    public static void setGuiHandler(GuiHandler handler) {
        guiHandler = handler;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/loginGui.fxml"));
        Parent root = fxmlLoader.load();

        // Get the Controller from the FXMLLoader
        controller = fxmlLoader.getController();
        // Set data in the controller
        controller.setClient(client);
        controller.setGuiHandler(guiHandler);
        controller.setStage(primaryStage);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Sagrada");

        primaryStage.setOnCloseRequest(event -> {
            //If the numOfMatch in the client isn't the default one
            /*if(client.getNumOfMatch() >= 0) {
                client.sendCommand("disconnect " + client.getNumOfMatch() + " " + client.getName());
            }
            System.exit(0);*/
        });

        primaryStage.show();
    }

    public static void launching() {
        launch();
    }
}
