package ui;

import eventlog.Event;
import eventlog.EventLog;
import model.GuiPlayer;
import model.Interaction;
import model.InventoryItem;


import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class SceneChanger {
    // Class responsible for changing the different scenes of the game. Both inside and outside the game!
    GameManager gm;

    GuiPlayer gp;


    //EFFECTS: Connects the scene manager to the game manager and the player.
    public SceneChanger(GameManager gm) {
        this.gm = gm;
        this.gp = gm.guiPlayer;
    }

    //MODIFIES: this
    //EFFECTS: Transitions out of the title screen into the first background screen (bedroom)
    public void newGame() {
        gm.gui.setAreaText("It's your room.");
        gm.gui.getBgPanel(1).setVisible(false);
        gm.gui.getBgPanel(2).setVisible(true);
        gm.guiPlayer.setCurrentLocation(2);

    }

    //MODIFIES: this
    //EFFECTS: Transitions out of the bedroom into the hub
    public void changeScene1() {
        gm.gui.setAreaText("In here, the possibilities are endless.");
        gm.gui.getBgPanel(2).setVisible(false);
        gm.gui.getBgPanel(3).setVisible(true);
        gm.guiPlayer.setCurrentLocation(3);
    }

    //MODIFIES: this
    //EFFECTS: Transitions out of the hub into the bedroom
    public void changeScene2() {
        gm.gui.getBgPanel(2).setVisible(true);
        gm.gui.getBgPanel(3).setVisible(false);
        gm.guiPlayer.setCurrentLocation(2);
    }

    //MODIFIES: this
    //EFFECTS: Loads the JSON save data into the GUIplayer class and leaves the player where they last saved.
    public void loadCurrentLocation() {
        try {
            gm.gui.getJsonReader().read();
            int currentLocation = gm.guiPlayer.getCurrentLocation();
            // Sets all BG panels to invisible
            for (int i = 1; i < gm.gui.getAllBgPanel().length; i++) {
                if (!(gm.gui.getAllBgPanel()[i] == null)) {
                    gm.gui.getAllBgPanel()[i].setVisible(false);
                }
            }
            setLoadedChanges();
            gm.gui.getBgPanel(currentLocation).setVisible(true);

        } catch (IOException e) {
            System.out.println("Unable to read from file: ");
        } catch (NullPointerException e) {
            newGame();
        }
    }

    public void gameOver() {
        for (int i = 1; i < gm.gui.getAllBgPanel().length; i++) {
            if (!(gm.gui.getAllBgPanel()[i] == null)) {
                gm.gui.getAllBgPanel()[i].setVisible(false);
            }
        }
        gm.gui.getBgPanel(9).setVisible(true);
        gm.getGui().setAreaText("You opened the door. In front of you is a brand new world.");
        JButton gameOverButton = new JButton("END");
        gameOverButton.setBounds(180, 120, 120, 30);
        gm.getGui().getBgPanel(9).add(gameOverButton);
        endGame(gameOverButton);

    }

    public void endGame(JButton jb) {
        jb.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    for (Event event : EventLog.getInstance()) {
                        System.out.println(event.toString());
                        System.out.println("\n");
                    }
                    System.exit(0);
                }
            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }
        });
    }

    //MODIFIES: this
    //EFFECTS: adds all the changes to the game state according to the players history
    public void setLoadedChanges() {

        int comparisonInt = gm.guiPlayer.getInventory().size();
        for (Interaction i : gm.guiPlayer.getInteractables()) {
            gm.ev1.setLoadedChanges(i.getPrompt(), i.getDescription(), i.getName(), i.getObID());
        }

        for (int i = 0; i < comparisonInt; i++) {
            InventoryItem inventoryItem = gm.guiPlayer.getInventory().get(i);
//            gm.gui.removeLabel(inventoryItem.getObID());
            gm.ev1.takeItem(inventoryItem.getObID());
        }

        for (InventoryItem i : gm.guiPlayer.getInventory()) {
            String sep = System.getProperty("file.separator");
            JLabel newInventory = new JLabel();
            ImageIcon inventoryIcon = new ImageIcon(System.getProperty("user.dir") + sep
                    + "images" + sep + gm.guiPlayer.getInventoryName(i.getObID()));
            newInventory.setIcon(inventoryIcon);
            gm.gui.getInventoryPanel().add(newInventory);
        }
    }
}




