package model;

import org.json.JSONObject;
import persistence.Writable;

public class InventoryItem implements Writable {
    // Inventory item that is added to a player that corresponds to a GUI object
    private int inventoryID;

    private String key;

    private int location;

    //EFFECTS: Creates a inventory item with a corresponding image name and objectID number
    public InventoryItem(int obID, String key, int location) {
        this.inventoryID = obID;
        this.key = key;
        this.location = location;
    }

    //EFFECTS: Converts an inventory item into a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("inventoryID", inventoryID);
        json.put("location", this.location);
        json.put("key", key);
        return json;
    }

    //EFFECTS: returns the inventory object ID
    public int getObID() {
        return inventoryID;
    }

    //EFFECTS: returns the inventory location
    public int getLocation() {
        return location;
    }

    //EFFECTS: Returns the inventory key name

    public String getKey() {
        return key;

    }
}
