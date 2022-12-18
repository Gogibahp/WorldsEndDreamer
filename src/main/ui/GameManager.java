package ui;

import eventlog.EventLog;
import ui.event.ActionHandler1;
import ui.event.Event1;
import ui.event.UniversalActionHandler;
import model.GuiPlayer;


public class GameManager {
// the most important class. Keeps the game reffering to itself and reduces null exceptions.
    ActionHandler1 actionHandler1 = new ActionHandler1(this);

    UniversalActionHandler universalAH = new UniversalActionHandler(this);

    protected GuiPlayer guiPlayer;

    protected SceneChanger sceneChanger = new SceneChanger(this);

    protected Event1 ev1 = new Event1(this);

    protected GUI gui;

    protected EventLog el;



    //EFFECTS: Main method baybii!!!!
    public static void main(String[] args) {
        new GameManager();
    }

    //EFFECTS: Instantiates a new GameManager.
    public GameManager() {
        this.guiPlayer = new GuiPlayer(this);
        gui = new GUI(this);

    }

    public GUI getGui() {
        return gui;
    }

    public GuiPlayer getGuiPlayer() {
        return guiPlayer;
    }

    public Event1 getEv1() {
        return ev1;
    }

    public SceneChanger getSceneChanger() {
        return sceneChanger;
    }

}
