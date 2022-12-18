package ui.event;

import ui.GameManager;


import java.awt.event.ActionEvent;

public class ActionHandler1 extends ActionHandler {
// CLASS FOR THE FIRST ACTION HANDLER. TAKES COMMAND FROM THE GUI AND THEN USES EVENTS FROM EV1

    //EFFECTS: Connects this action handler to the game manager
    public ActionHandler1(GameManager gm) {
        super(gm);
    }


    //MODIFIES: this
    //EFFECTS: Switch statement that chooses which event action should occcur depending on a specific menu option.
    @Override
    public void actionPerformed(ActionEvent e) {
        String selection = e.getActionCommand();
        switch (selection) {
            case "Punch":
                gm.getEv1().punch();
                break;
            case "Sleep":
                gm.getEv1().sleep();
                break;
            case "Leave":
                gm.getEv1().leave();
                break;
            case "Observe":
                gm.getEv1().observe();
                break;
            case "Awake":
                gm.getEv1().awake();
                break;
            case "Lick":
                gm.getGui().setAreaText("Tastes like sour apples");
                break;
        }
    }

}
