package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Interaction;
import model.GuiPlayer;


import org.json.*;
import model.InventoryItem;
import ui.GameManager;

public class JsonReader {
    // CLASS LEVEL COMMENT: Reads JSON files into in game files
    private String source;

    GameManager gm;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source, GameManager gm) {
        this.source = source;
        this.gm = gm;
    }

    // EFFECTS: reads player from file and returns it;
    // throws IOException if an error occurs reading data from file
    public void read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        addInteractionLog(gm.getGuiPlayer(), jsonObject);
        addInventory(gm.getGuiPlayer(), jsonObject);
        int currentLocation = jsonObject.getInt("currentLocation");
        gm.getGuiPlayer().setCurrentLocation(currentLocation);
    }


    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


    // MODIFIES: p
    // EFFECTS: parses interactionlog from JSON object and adds them to the player file
    private void addInteractionLog(GuiPlayer p, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("interactionLog");
        for (Object json : jsonArray) {
            JSONObject nextInteraction = (JSONObject) json;
            addInteraction(p, nextInteraction);
        }
    }

    // MODIFIES: p
    // EFFECTS: parses interaction from JSON object and adds it to interaction log
    private void addInteraction(GuiPlayer p, JSONObject jsonObject) {
        String prompt = jsonObject.getString("prompt");
        String description = jsonObject.getString("description");
        String name = jsonObject.getString("name");
        int obID = jsonObject.getInt("obID");

        Interaction interaction = new Interaction(prompt, description, name, obID);

        p.addInteraction(interaction);
    }

    // MODIFIES: p
    // EFFECTS: parses inventory from JSON object and adds it to the player file
    private void addInventory(GuiPlayer p, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("inventory");
        for (Object json : jsonArray) {
            JSONObject nextInteraction = (JSONObject) json;
            addInventoryItem(p, nextInteraction);
        }
    }

    // MODIFIES: p
    // EFFECTS: parses individual inventory from JSON object and adds it to the inventory log
    public void addInventoryItem(GuiPlayer p, JSONObject jsonObject) {
        int obID = jsonObject.getInt("inventoryID");
        int location = jsonObject.getInt("location");
        String key = jsonObject.getString("key");

        InventoryItem it = new InventoryItem(obID, key, location);
        p.addToInventory(it);
    }
}
