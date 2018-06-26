package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.view.SceneInterface;

public class ChooseWindowState implements SceneInterface {

    private String tmpTimer;
    private CliHandler cliHandler;
    private Printer printer;
    private String timer;
    private String cards;

    public ChooseWindowState(Printer printer, CliHandler cliHandler, String timer) {
        this.timer=timer;
        this.printer=printer;
        this.cliHandler=cliHandler;
        this.tmpTimer=timer;
    }

    @Override
    public void handleMatchId(String idMatch) {
        cliHandler.setIdMatch(Integer.valueOf(idMatch));
    }

    @Override
    public void setPatternCards(String patternCards) {
        cards=patternCards;
        printer.printChooseCardRoom(patternCards,timer);
    }

    @Override
    public void updateBoard(String setup) {
        cliHandler.setState(new MatchState(printer,cliHandler,setup));
    }
}
