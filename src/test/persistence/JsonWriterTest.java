package persistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Interaction;
import model.GuiPlayer;
import ui.GameManager;


public class JsonWriterTest extends JsonTest {

    GameManager gm;
    GuiPlayer gp;

    @BeforeEach
    void runBefore() {
        gm = new GameManager();
        gp = gm.getGuiPlayer();
    }

    @Test
    void testWriterInvalidFile() {
        try {
            GuiPlayer gp = new GuiPlayer(new GameManager());
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPlayer() {
        try {
            JsonWriter writer = new JsonWriter("./data/testEmptyPlayer.json");
            writer.open();
            writer.write(gp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testEmptyPlayer.json", gm);
            reader.read();
            assertEquals(0, gp.getCurrentLocation());
            assertEquals(0, gp.getInteractables().size());
            assertEquals(0, gp.getInventory().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriter1Player() {
        try {
            gp.addInteraction(new Interaction("Observe",
                    "Your hand bleeds. You feel the pain of an ant who has hurt their hand very badly",
                    "Broken mirror.png", 0));

            JsonWriter writer = new JsonWriter("./data/testWriter1Player.json");
            writer.open();
            writer.write(gp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriter1Player.json", gm);
            reader.read();
            List<Interaction> testList = new ArrayList<>();
            for (Interaction i : gp.getInteractables()) {
                testList.add(i);
            }
            assertEquals(2, gp.getInteractables().size());
            assertEquals(0, testList.get(0).getObID());
            assertEquals("Broken mirror.png", testList.get(0).getName());


            checkInteraction("Broken mirror.png", testList.get(0),
                    "Your hand bleeds. You feel the pain of an ant who has hurt their hand very badly",
                    "Observe", 0);
            ;

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }




}
