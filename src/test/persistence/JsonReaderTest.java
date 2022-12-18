package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.GuiPlayer;
import model.Interaction;

import ui.GameManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    private static final String JSON_STORE = "./data/player.json";
    GameManager gm;

    GuiPlayer gp;

    JsonReader jsonReader;

    JsonWriter jsonWriter;

    @BeforeEach
    void runBefore() {
        gm = new GameManager();
        gp = gm.getGuiPlayer();
        jsonReader = new JsonReader(JSON_STORE, gm);
        jsonWriter = new JsonWriter("./data/testWriter1Player.json");
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json", new GameManager());
        try {
             reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyPlayer() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPlayer.json", gm);
        try {
            reader.read();
            assertEquals(0, gp.getCurrentLocation());
            assertEquals(0, gp.getInventory().size());
            assertEquals(2,gp.getObtainables().size());
            assertEquals(0, gp.getInteractables().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReader1Player() {
        JsonReader reader = new JsonReader("./data/testReader1Player.json",gm);
        try {
            reader.read();
            Set<Interaction> interactions = gp.getInteractables();
            List<Interaction> testList = new ArrayList<>();
            for (Interaction i: interactions) {
                testList.add(i);
            }

            assertEquals(1, gp.getInteractables().size());
            checkInteraction("Broken mirror.png", testList.get(0),
                    "Your hand bleeds. You feel the pain of an ant who has hurt their hand very badly",
                    "Observe", 0);
            assertEquals(2, gp.getCurrentLocation());
            assertEquals(2, gp.getInventory().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }



}
