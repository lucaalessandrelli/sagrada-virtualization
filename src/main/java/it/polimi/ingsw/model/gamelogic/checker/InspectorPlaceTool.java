package it.polimi.ingsw.model.gamelogic.checker;

import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.gametools.ToolCard;
import it.polimi.ingsw.model.gamedata.gametools.WindowPatternCard;

import java.util.ArrayList;

/**
 * The class is used to verify dynamically if, chosen a tool card, the placing respect the specific rules of that tool card.
 * In this case, the parameter "pos" represent the position where the player wants to move the dice.
 */
public class InspectorPlaceTool implements InspectorTool {
    private WindowPatternCard window;
    private int index;

    public InspectorPlaceTool(WindowPatternCard window){
        this.window=window;

    }

    /**
     * This method is called every time the player wants to place the dice using a tool card.
     * It creates an object "RuleEngineP" which implements every checker methods for the placing. The method gets from the tool card the name of
     * the rights methods to invoke on RuleEngine, and it will do it in the right sequence.
     * @param dice Dice to place.
     * @param pos Where move the dice.
     * @param tool Tool card chosen.
     * @return True if the place is right using the tool card
     */
    public boolean check(Dice dice, Pos pos, ToolCard tool){
        RuleEngine ruleEngine = new RuleEngineP(dice,pos,window);
        ArrayList<ArrayList<String>> nameMethods = tool.getNamePMethods();
        ArrayList<String> currMethods = nameMethods.get(index);
        return doMethods(currMethods,ruleEngine);
    }

    /**
     * The class contains every methods used to check the particular placing using the chosen tool card.
     */

    private class RuleEngineP extends InspectorPlace implements RuleEngine{
        Dice dice;
        Pos pos;
        WindowPatternCard window;
        private RuleEngineP(Dice dice, Pos pos, WindowPatternCard window){
            this.dice=dice;
            this.pos=pos;
            this.window=window;

        }

        /**
         * This method is used from tool card n°9.
         * @return True if placing respect rules of tool card.
         */
        protected boolean checkFrame(){
            return (checkPos() && checkColour() && checkNumber() && particularFrame());
        }

        /**
         * This method check if the placing is far from other dice.
         * @return True if conditions are respected.
         */
        private boolean particularFrame() {
            int x = pos.getX();
            int y = pos.getY();
            for (int i = -1; i<=1; i++){
                for(int j =1 ; j>= -1; j--){
                    if(x+i>=MINCOL && x+i<=MAXCOL && y+j>=MINROW && y+j<=MAXROW && window.getCell(new Pos(x,y)).isOccupied()){
                        return false;

                    }
                }
            }
            return true;
        }

        /**
         * This method is called to check the normal placing rules.
         * @return True if placing is right.
         */
        protected boolean checkAll(){
            return super.check(dice,pos,window);
        }

        /**
         * Check if pos could be out of window's frame and if the position is occupied.
         * @return True if conditions are verified.
         */
        boolean checkPos() {
            return super.checkPos(pos,window);
        }

        /**
         * Check colour restriction on the chosen pos.
         * @return True if the Cell doesn't have colour restriction or it is verified.
         */
        boolean checkColour() {
            return super.checkColour(window, pos, dice);
        }

        /**
         * Check number restriction on the chosen pos.
         * @return True if the Cell doesn't have number restriction or it is verified.
         */
        boolean checkNumber() {
            return super.checkNumber(window, pos, dice);
        }

        /**
         * Check if the chosen position check the colour and number restriction on the column nearby.
         * @return True if conditions are verified.
         */
        protected boolean checkCol() {
            return super.checkCol(pos, window, dice);
        }

        /**
         * Check if the chosen position check the colour and number restriction on the rows nearby.
         * @return True if conditions are verified.
         */
        protected boolean checkRow() {
            return super.checkRow(pos, window, dice);
        }
    }
}
