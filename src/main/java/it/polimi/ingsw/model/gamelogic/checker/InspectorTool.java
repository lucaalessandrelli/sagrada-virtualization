package it.polimi.ingsw.model.gamelogic.checker;

import java.lang.reflect.Method;
import java.util.ArrayList;


public class InspectorTool {

    protected boolean doMethods(ArrayList<String> nameMethods,RuleEngine ruleEngine){
        for(String name : nameMethods){
            try {
                Method method = ruleEngine.getClass().getMethod(name);
                if(!(boolean)method.invoke(ruleEngine)){
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }












}
