package ui.event;

import model.Interaction;
import model.InventoryItem;
import ui.GameManager;

import javax.swing.*;


public class Event1 {
    // The first event class that handles any events that trigger in the bed or hubroom

    GameManager gm;

    //EFFECTS: Creates a new event1 object that is known by the game manager
    public Event1(GameManager gm) {
        this.gm = gm;
    }

    //MODIFIES: this
    //EFFECTS: Shatters the mirror in the bed room
    public void punch() {
        gm.getGui().setAreaText("You punch the mirror with the power of 1 very large ant.");
        gm.getGui().setLabelIcon("Broken mirror.png", 0);
        gm.getGui().setUniversalMenu("Observe",
                "Your hand bleeds. You feel the pain of an ant who has hurt their hand very badly",
                0);
        gm.getGui().getPopMenu(0).removeAll();
        gm.getGui().getPopMenu(0).add(gm.getGui().getMenuItem(0));
        gm.getGui().getPopMenu(0).add(gm.getGui().getMenuItem(1));
        gm.getGui().getPopMenu(0).add(gm.getGui().getMenuItem(2));
        gm.getGuiPlayer().addInteraction(new Interaction("Observe",
                "Your hand bleeds. You feel the pain of an ant who has hurt their hand very badly",
                "Broken mirror.png", 0));

    }

    //REQUIRES: valid obIDs
    //MODIFIES: this
    //EFFECTS: Once a savefile is loaded it activates all the changes recorded in the GUIplayer class
    public void setLoadedChanges(String prompt, String description, String name, int obID) {
        gm.getGui().setLabelIcon(name, obID);
        gm.getGui().setUniversalMenu(prompt, description, obID);
        gm.getGui().getPopMenu(obID).removeAll();
        gm.getGui().getPopMenu(obID).add(gm.getGui().getMenuItem(0));
        gm.getGui().getPopMenu(obID).add(gm.getGui().getMenuItem(1));
        gm.getGui().getPopMenu(obID).add(gm.getGui().getMenuItem(2));
    }

    //MODIFIES: this
    //EFFECTS: Transports the player into the hubroom
    public void sleep() {
        gm.getGui().setAreaText("You snooze with the power of 1 sleepy ant");
        gm.getSceneChanger().changeScene1();
    }

    //MODIFIES: this
    //EFFECTS: Should force the game to quit (when I program it in that is)
    public void leave() {
        gm.getSceneChanger().gameOver();
    }

    //MODIFIES: this
    //EFFECTS: Saves the game!
    public void save() {
        gm.getGui().savePlayer();
        gm.getGui().setAreaText("Saved!");
    }

    //MODIFIES: this
    //EFFECTS: Mirror image for extra angst.
    public void observe() {
        gm.getGui().setAreaText("The reflection looks more accurate now.");
    }

    //MODIFIES: this
    //EFFECTS: Takes the player from the hub to the bedroom
    public void awake() {
        gm.getGui().setAreaText("You awake from your slumber");
        gm.getSceneChanger().changeScene2();
    }

    //MODIFIES: this
    //EFFECS: Removes an object that has a valid ID once the TAKE command is used on it
    public boolean takeItem(int obID) {
        try {
            if (gm.getGuiPlayer().getObtainables().get(obID) != null) {
                gm.getGui().removeLabel(obID);
                return true;
            }
        } catch (Exception e) {
            gm.getGui().setAreaText("You cannot take that.");
            return false;
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: Adds an item to the player's inventory and displays it in the toolbar.
    public void addInventory(int obID) {
        String sep = System.getProperty("file.separator");

        JLabel newInventory = new JLabel();

        ImageIcon inventoryIcon = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + gm.getGuiPlayer().getInventoryName(obID));

        newInventory.setIcon(inventoryIcon);

        gm.getGui().getInventoryPanel().add(newInventory);
        gm.getGuiPlayer().addToInventory(new InventoryItem(obID, gm.getGuiPlayer().getInventoryName(obID),
                gm.getGuiPlayer().getInventoryLocation(obID)));
    }

}
