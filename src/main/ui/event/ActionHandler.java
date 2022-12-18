package ui.event;

import ui.GameManager;

import java.awt.event.ActionListener;

public abstract class ActionHandler implements ActionListener {
    //CLASS THAT ALL ACTIONHANDLERS EXTEND FROM. Has the convenience of always being connected to the game manager
    private static final String sep = System.getProperty("file.separator");
    GameManager gm;

    //EFFECTS: Connects all actionhandlers to the game manager.
    public ActionHandler(GameManager gm) {
        this.gm = gm;
    }
}
