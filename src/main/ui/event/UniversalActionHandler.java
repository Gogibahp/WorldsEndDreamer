package ui.event;

import ui.GameManager;

import java.awt.event.ActionEvent;


public class UniversalActionHandler extends ActionHandler {
// A class for universal actions.

    //EFFECTS: Creates an action handler for universal events
    public UniversalActionHandler(GameManager gm) {
        super(gm);
    }

    //MODIFIES: this
    //EFFECTS: Allows the player to save their game.
    @Override
    public void actionPerformed(ActionEvent e) {
        String selection = e.getActionCommand();
        switch (selection) {
            case "Save":
                gm.getEv1().save();
                break;
            case "View":
                break;
            case "Take":
                break;
        }
    }


}
