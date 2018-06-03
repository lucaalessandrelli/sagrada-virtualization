package it.polimi.ingsw.controller;

import java.util.Arrays;
import java.util.List;

public class Deparser {

    StringBuilder builder = new StringBuilder();


    public List<String> DivideinStrings(String input){
        List<String> strings;
        strings = Arrays.asList(input.substring(input.indexOf(" ") + 1,input.length()).split(";"));
        return strings;
    }

    //input è ogni stringa restituita dal metodo sopra

    public List<String> Deparse(String input){
        List<String> topass;

        topass = Arrays.asList((input.substring(input.indexOf(" ") + 1,input.length())).split(","));

        return topass;
    }
        }
