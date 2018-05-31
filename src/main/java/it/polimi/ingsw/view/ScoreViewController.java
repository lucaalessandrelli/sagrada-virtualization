package it.polimi.ingsw.view;

import com.jfoenix.controls.JFXListView;
import it.polimi.ingsw.network.client.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ScoreViewController implements Initializable, ViewInterface {
    private Client client;
    private Stage stage;
    private ObservableList<String> list = FXCollections.observableArrayList();

    @FXML
    private JFXListView<String> scoreList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadScores();
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void loadScores() {
        String us1 = "Luca";
        String us2 = "Vincenzo";
        String us3 = "Gionny";
        list.addAll(us1,us2,us3);
        scoreList.setItems(list);
    }

    /*public void changeScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/matchGui.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Match");
        stage.setResizable(true);
        stage.show();
    }*/

    @Override
    public void sendDraftPools(String draftPools) {

    }

    @Override
    public void sendRestrictions(String restrictions) {

    }

    @Override
    public void sendWindowPatternCards(String windowPatternCards) {

    }

    @Override
    public void sendRoundTrack(String roundTrack) {

    }
}
