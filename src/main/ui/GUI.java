package ui;

import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

public class GUI {
    // GUI for the game. Very large and holds a lot of data.
    private static final String JSON_STORE = "./data/player.json";
    GameManager gm;
    private JFrame window;
    private JTextArea areaText;
    protected JPanel[] bgPanel = new JPanel[10];

    protected JLabel[] bgLabel = new JLabel[10];

    protected JLabel[] objectLabel = new JLabel[100];

    protected JPanel inventoryPanel = new JPanel();
    protected JMenuItem[] universalMenu = new JMenuItem[4];

    protected JPopupMenu[] popMenu = new JPopupMenu[100];


    private JsonWriter jsonWriter;

    private JsonReader jsonReader;

    private String openingText;

    //EFFECTS: Creates a new GUI that consumes the initial GameManager.
    //Establishes the persistence and creates the main window and title screen.
    //Afterwords the player can load a save or play a new game.
    public GUI(GameManager gm) {
        this.gm = gm;

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE, this.gm);

        createMainField();
        createTitleScreen();
        createInventoryLayout();

        window.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: Creates the new window that every other GUI element will be layed on top of.
    public void createMainField() {
        window = new JFrame(openingText);
        window.setSize(600, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);

        areaText = new JTextArea();
        areaText.setBounds(50, 408, 480, 220);
        areaText.setBackground(Color.black);
        areaText.setForeground(Color.white);
        areaText.setEditable(false);
        areaText.setLineWrap(true);
        areaText.setWrapStyleWord(true);
        areaText.setFont(new Font("Times new roman", Font.PLAIN, 26));
        window.add(areaText);
    }

    //MODIFIES: this
    //EFFECTS: Creates the layout for inventory items that the player can add throughout the game.
    public void createInventoryLayout() {

        inventoryPanel.setBounds(0, 0, 650, 50);
        inventoryPanel.setBackground(Color.black);
        inventoryPanel.setLayout(new GridLayout(1, 10));
        window.add(inventoryPanel);

    }

    //REQUIRES: An existing bgNum and an existing bgFileName
    //MODIFIES: this
    //EFFECTS: Creates a background image that labels can be added onto
    public void createBackground(int bgNum, String bgFileName) {
        String sep = System.getProperty("file.separator");

        bgPanel[bgNum] = new JPanel();
        bgPanel[bgNum].setBounds(0, 0, 600, 450);
        bgPanel[bgNum].setBackground(Color.black);
        bgPanel[bgNum].setLayout(null);
        window.add(bgPanel[bgNum]);

        bgLabel[bgNum] = new JLabel();
        bgLabel[bgNum].setBounds(0, 0, 600, 450);

        ImageIcon bgIcon = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + bgFileName);
        bgLabel[bgNum].setIcon(bgIcon);

        bgPanel[bgNum].add(bgLabel[bgNum]);

    }

    //REQUIRES: A whole lot of things. Essentially coordinates for the object, and a non empty image file
    //MODIFIES: this
    //EFFECTS: Creates objects to be layed on top of a background.
    public void createObject(int obID, int bgNum, int objx, int objY, int objWidth, int objHeight, String objFile,
                             String prompt, String description) {

        popMenu[obID] = new JPopupMenu();

        setUniversalMenu(prompt, description, obID);

        popMenu[obID].add(universalMenu[0]);
        popMenu[obID].add(universalMenu[1]);
        popMenu[obID].add(universalMenu[2]);
        popMenu[obID].add(universalMenu[3]);

        objectLabel[obID] = new JLabel();
        objectLabel[obID].setBounds(objx, objY, objWidth, objHeight);

        setLabelIcon(objFile, obID);

        addML(objectLabel[obID], popMenu[obID]);

        bgPanel[bgNum].add(objectLabel[obID]);
        bgPanel[bgNum].add(bgLabel[bgNum]);

    }

    //MODIFIES: this
    //EFFECTS: Creates the title screen that the player sees upon booting up the game.
    // has TWO buttons. One that starts a new game, and one that loads up a JSON save file.
    public void createTitleScreen() {
        createBackground(1, "Title screen.png");
        JButton startButton = new JButton("Start Game");
        startButton.setBounds(50, 200, 120, 30);
        bgPanel[1].add(startButton);
        startGame(startButton);

        JButton loadButton = new JButton("Load Game");
        loadButton.setBounds(50, 250, 120, 30);
        bgPanel[1].add(loadButton);
        loadGame(loadButton);
    }

    //MODIFIES: this
    //EFFECTS: Pre-creates ALL screens that the player can traverse through. Be weary of layer hierarchy in Jframe.
    public void generateScreen() {
        //SCENE 1
        createBackground(2, "wed bedroom.png");
        createObject(5, 2, 436, 70, 33, 108, "legendary sword.png",
                "Lick", "The legendary sword of Arcadia. Almost as powerful as a large ant.");
        createObject(4, 2, 263, 215, 87, 68, "abomination.png", "Lick",
                "An abomination... looking at it makes you nauseous.");
        createObject(0, 2, 284, 68, 146, 120, "mirror.png", "Punch",
                "A plain mirror. You cant recognize the reflection.");
        createObject(1, 2, 158, 255, 247, 89, null, "Sleep",
                "A comfortable bed you never want to leave");
        createObject(2, 2, 113, 92, 89, 155, null, "Leave",
                "A door that casts a heavy aura. Opening it will destroy the world.");


        //SCENE 2
        createBackground(3, "Hub.png");
        createObject(3, 3, 193, 313, 247, 66, null, "Awake",
                "A simple bed. Will you wake up?");

        // GAMEOVER
        createBackground(9,null);

    }

    //REQUIRES: non-empty JpopupMenu
    //Modifies: this
    //EFFECTS: adds a mouse listener to an object which lets the player interact with the object.
    // Reveals a pop up screen when an object is right clicked
    public void addML(JLabel objectlabel, JPopupMenu popMenu) {
        JLabel ol = objectlabel;
        objectlabel.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    popMenu.show(ol, e.getX(), e.getY());
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

    //REQUIRES: non-null Jbutton
    //MODIFIES: this
    //EFFECTS: Creates the prompt that starts up the game once the startbutton is clicked
    public void startGame(JButton jb) {
        jb.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    generateScreen();
                    gm.sceneChanger.newGame();
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

    //REQUIRES: non-null Jbutton
    //MODIFIES: this
    //EFFECTS: Loads up a saved JSON file once the "load game" button is pressed
    public void loadGame(JButton jb) {
        jb.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    generateScreen();
                    gm.sceneChanger.loadCurrentLocation();

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

    //REQUIRES: non-null JMenuItem
    //MODIFIES: this
    //EFFECTS: Universal command that every object accesses. Shows the object's description
    public void showDescription(JMenuItem jm, String description) {
        jm.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    setAreaText(description);
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

    //REQUIRES: non-null JMenuItem, and an INT corresponding to a take-able item
    //MODIFIES: this
    //EFFECTS: Removes an item from the screen and adds it to the player's inventory
    public void takeItem(JMenuItem jm, int obID) {
        jm.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (gm.ev1.takeItem(obID)) {
                        gm.ev1.addInventory(obID);
                    }
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
    //EFFECTS: Puts text into the main textbox
    public void setAreaText(String text) {
        areaText.setText(text);
    }


    //REQUIRES: valid labelName, and an obID given to an instantiated object
    //MODIFIES: this
    //EFFECTS: Changes the image of an object on the screen.
    public void setLabelIcon(String labelName, int obID) {

        String sep = System.getProperty("file.separator");

        ImageIcon objectIcon = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + labelName);

        objectLabel[obID].setIcon(objectIcon);
    }

    //REQUIRES: Valid obID
    //MODIFIES: this
    //EFFECTS: Creates the universal prompt that all object have.
    public void setUniversalMenu(String prompt, String description, int obID) {
        universalMenu[0] = new JMenuItem(prompt);
        universalMenu[0].addActionListener(gm.actionHandler1);
        universalMenu[0].setActionCommand(prompt);

        universalMenu[1] = new JMenuItem("View");
        showDescription(universalMenu[1], description);

        universalMenu[2] = new JMenuItem("Take");
        takeItem(universalMenu[2], obID);


        universalMenu[3] = new JMenuItem("Save");
        universalMenu[3].addActionListener(gm.universalAH);
        universalMenu[3].setActionCommand("Save");

    }

    //MODIFIES: this
    //EFFECTS: Saves player data to a JSON file.
    public void savePlayer() {
        try {
            jsonWriter.open();
            jsonWriter.write(gm.guiPlayer);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            setAreaText("Unable to write to file: " + JSON_STORE);
        }
    }

    //EFFECTS: Returns a specific bgPanel
    public JPanel getBgPanel(int i) {
        return bgPanel[i];
    }

    //EFFECTS: Returns all bgPanels.
    public JPanel[] getAllBgPanel() {
        return bgPanel;
    }


    //EFFECTS: Returns a specific popup menu.
    public JPopupMenu getPopMenu(int i) {
        return popMenu[i];
    }

    //EFFECTS: Returns a specific universal menu
    public JMenuItem getMenuItem(int i) {
        return universalMenu[i];
    }

    //EFFECTS: Returns the JSON reader
    public JsonReader getJsonReader() {
        return jsonReader;
    }

    public JLabel getObjectLabel(int i) {
        return objectLabel[i];
    }

    //EFFECTS: Removes ANY label from the window
    public void removeLabel(int obID) {

        bgPanel[gm.guiPlayer.getInventoryLocation(obID)].remove(objectLabel[obID]);
    }

    //EFFECTS: Gets a specific inventory panel to add or remove inventory items from.
    public JPanel getInventoryPanel() {
        return inventoryPanel;
    }


}
