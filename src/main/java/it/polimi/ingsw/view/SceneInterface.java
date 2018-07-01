package it.polimi.ingsw.view;

import javafx.scene.control.Alert;

public interface SceneInterface {

    default void handleAlert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText(null);
        alert.setContentText(alertMessage);

        alert.showAndWait();
    }

    default void handleClientConnected(String messageConnection) {

    }

    default void handleConnectedPlayers(String playerlist) {

    }

    default void handleTimer(String timer) {

    }

    default void handleMatchId(String idMatch) {

    }

    default void handleTurnMessage(String turnMessage) {

    }

    default void updateBoard(String setup) {}

    default void setPatternCards(String patternCards) {

    }

    default void handleGameState(String gameState) {

    }

    default void handleScore(String score) {

    }
}
