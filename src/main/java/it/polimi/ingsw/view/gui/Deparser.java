package it.polimi.ingsw.view.gui;

import java.util.Arrays;
import java.util.List;

public class Deparser {


    public List<String> divideInStrings(String setup) {
        return Arrays.asList(setup.split(";"));
    }

    public List<String> deparse(String subMessage) {
        return Arrays.asList(subMessage.split(","));
    }

    public List<String> divideFromNumber(String chosen){return Arrays.asList(chosen.split(" ")); }
}
