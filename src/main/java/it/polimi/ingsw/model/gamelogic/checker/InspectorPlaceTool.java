package it.polimi.ingsw.model.gamelogic.checker;

import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.gametools.ToolCard;
import it.polimi.ingsw.model.gamedata.gametools.WindowPatternCard;

import java.util.ArrayList;

public class InspectorPlaceTool extends InspectorTool {
    private final static int MINCOL=0;
    private final static int MAXCOL=4;
    private final static int MINROW=0;
    private final static int MAXROW=3;
    RuleEngine ruleEngine;
    ToolCard tool;

    public InspectorPlaceTool(Dice dice, Pos pos, WindowPatternCard window,ToolCard t){
        tool = t;
        ruleEngine = new RuleEngineP(dice,pos,window);
    }
    //check(Dice,Pos,toolcard); new Rule engine();
    public boolean check(){
        ArrayList<String> nameMethods = tool.getNamePMethods();

        return doMethods(nameMethods,ruleEngine);
    }

    private class RuleEngineP extends InspectorPlace implements RuleEngine{
        Dice dice;
        Pos pos;
        WindowPatternCard window;
        private RuleEngineP(Dice dice, Pos pos, WindowPatternCard window){
            this.dice=dice;
            this.pos=pos;
            this.window=window;

        }

        protected boolean checkFrame(){
            if(checkPos(pos,window)) {
                if (checkColour(window, pos, dice)) {
                    if (checkNumber(window, pos, dice)) {
                        if(particularFrame(window,pos)){
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        private boolean particularFrame(WindowPatternCard window, Pos pos) {
            int x = pos.getX();
            int y = pos.getY();
            for (int i = -1; i<=1; i++){
                for(int j =1 ; j>= -1; j--){
                    if(x+i>=MINCOL && x+i<=MAXCOL && y+j>=MINROW && y+j<=MAXROW){
                        if(window.getCell(new Pos(x,y)).isOccupied()){
                            return false;
                        }
                    }
                }
            }
            return true;
        }

        protected boolean checkAll(){
            return super.check(dice,pos,window);
        }

        @Override
        protected boolean checkPos(Pos pos,WindowPatternCard window) {
            return super.checkPos(pos,window);
        }

        @Override
        protected boolean checkColour(WindowPatternCard window, Pos pos, Dice dice) {
            return super.checkColour(window, pos, dice);
        }

        @Override
        protected boolean checkNumber(WindowPatternCard window, Pos pos, Dice dice) {
            return super.checkNumber(window, pos, dice);
        }

        @Override
        protected boolean checkCol(Pos pos, WindowPatternCard window, Dice dice) {
            return super.checkCol(pos, window, dice);
        }

        @Override
        protected boolean checkRow(Pos pos, WindowPatternCard window, Dice dice) {
            return super.checkRow(pos, window, dice);
        }
    }
}
