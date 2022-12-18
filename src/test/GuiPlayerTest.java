import eventlog.EventLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.GuiPlayer;
import model.Interaction;

import ui.GameManager;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class GuiPlayerTest {

    GameManager gm;

    GuiPlayer gp;

    @BeforeEach
    void runBefore() {
        gm = new GameManager();
        gp = gm.getGuiPlayer();
    }

    @Test
    void constructorTest() {
        assertEquals(2, gp.getObtainables().size());
        assertEquals(0, gp.getInteractables().size());
        assertEquals(0, gp.getInventory().size());
        assertEquals(2, gp.getLocations().size());
    }

    @Test
    void getterSetterTest() {
        assertEquals(0, gp.getCurrentLocation());
        gp.setCurrentLocation(2);
        assertEquals(2, gp.getCurrentLocation());

        assertEquals(0, gp.getInventory().size());


        Interaction testInteraction = new Interaction("filler", "filler", "filler", 0);
        gp.addInteraction(testInteraction);
        assertEquals(1, gp.getInteractables().size());
        Set<Interaction> testList = new HashSet<>();


        assertEquals("Neco arc.png", gp.getInventoryName(4));
        assertEquals(2, gp.getInventoryLocation(4));

    }



}



