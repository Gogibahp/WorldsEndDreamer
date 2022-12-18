package model;

import eventlog.Event;
import eventlog.EventLog;
import org.json.JSONArray;
import org.json.JSONObject;
import ui.GameManager;

import java.util.*;

public class GuiPlayer {
    //A class for the player that keeps track of a history of different meaningful interactions the player has done
    private int currentLocation;

    private Set<Interaction> interactionLog;

    private Map<Integer, String> obtainables;

    private GameManager gm;

    private List<InventoryItem> inventory;


    private Map<Integer, Integer> idToLocation;


    //EFFECTS: Creates a new GUI player connected to the game manager.
    public GuiPlayer(GameManager gameManager) {
        this.gm = gameManager;
        this.interactionLog = new HashSet<>();
        this.inventory = new ArrayList<>();
        instantiateLocation();
        instantiateObtainables();
    }


    //MODIFIES: this
    //EFFECTS: Creates the list of items the player is able to take from the overworld and add to their inventory
    public void instantiateObtainables() {
        obtainables = new HashMap<>();
        obtainables.put(4, "Neco arc.png");
        obtainables.put(5, "Sword icon.png");
    }

    //MODIFIES: this
    //EFFECTS:  Gives keys to the location of takeable items
    public void instantiateLocation() {
        this.idToLocation = new HashMap<>();
        idToLocation.put(4, 2);
        idToLocation.put(5, 2);
    }


    //MODIFIES: this
    //EFFECTS: Sets the current location of the player to track where to remove items
    //Also tracks where the player last was when they saved
    public void setCurrentLocation(int i) {
        this.currentLocation = i;
        EventLog.getInstance().logEvent(new Event("Player has changed rooms."));
    }

    //EFFECTS: Returns where the player last went/saved
    public int getCurrentLocation() {
        return currentLocation;
    }

    //MODIFIES: this
    //EFFECTS: adds an interation to the history of interactions
    public void addInteraction(Interaction interaction) {
        interactionLog.add(interaction);
        EventLog.getInstance().logEvent(new Event("Player has impacted the environment."));
    }


    //EFFECTS: Returns the list of the player's inventory
    public List<InventoryItem> getInventory() {
        return inventory;
    }

    //EFFECTS: Returns the list of the player's interactions
    public Set<Interaction> getInteractables() {
        return interactionLog;
    }

    //EFFECTS: Returns the name of an object thats able to be added to the inventory
    public String getInventoryName(int obID) {
        return obtainables.get(obID);
    }

    //EFFECTS: Returns an inventory items location
    public int getInventoryLocation(int obID) {
        return idToLocation.get(obID);
    }

    //EFFECTS: Adds an inventory item to the inventory
    public void addToInventory(InventoryItem i) {
        EventLog.getInstance().logEvent(new Event("Player has added an item to inventory!"));
        inventory.add(i);
    }

    //EFFECTSL Returns the list of all objects that are able to be added to the inventory
    public Map<Integer, String> getObtainables() {
        return obtainables;
    }

    public Map<Integer, Integer> getLocations() {
        return idToLocation;
    }


    // EFFECTS: Turns the player's fields into JSON readable format
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("currentLocation", currentLocation);
        json.put("interactionLog", interactionLogtoJson());
        json.put("inventory", inventoryToJson());
        return json;
    }

    // EFFECTS: Turns the player's entire inventory into JSON readable format
    public JSONArray inventoryToJson() {
        JSONArray jsonArray = new JSONArray();
        for (InventoryItem i : inventory) {
            jsonArray.put(i.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: Turns interaction log into JSON readable format
    public JSONArray interactionLogtoJson() {
        JSONArray jsonArray = new JSONArray();

        for (Interaction i : interactionLog) {
            jsonArray.put(i.toJson());
        }
        return jsonArray;
    }




}
