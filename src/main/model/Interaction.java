package model;

import org.json.JSONObject;
import persistence.Writable;

public class Interaction implements Writable {
    // CLASS LEVEL COMMENT: Interactions that keeps track of what the player has done
    private String name;

    String prompt;

    String description;
    private int obID;

    private int itemLocation;


    // EFFECTS: Creates an interaction that can be added to the player's log
    public Interaction(String prompt, String description, String name, int obID) {
        this.prompt = prompt;
        this.description = description;
        this.name = name;
        this.obID = obID;
    }

    // EFFECTS: Gets interactions name
    public String getName() {
        return name;
    }

    //EFFECTS: Returns the obID of a GUI event
    public int getObID() {
        return obID;
    }

    //EFFECTS: Returns the prompt of a GUI event
    public String getPrompt() {
        return prompt;
    }

    //EFFECTS: Returns the description of the GUI event
    public String getDescription() {
        return description;
    }

    // EFFECTS: Converts interaction to JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("prompt", prompt);
        json.put("description", description);
        json.put("name", name);
        json.put("obID", obID);
        return json;
    }
}
