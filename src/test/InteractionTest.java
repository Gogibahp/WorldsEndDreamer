import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Interaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class InteractionTest {

    Interaction testInteraction;

    @BeforeEach
    void runBefore() {
        testInteraction = new Interaction("filler", "filler", "filler", 0);
    }

    @Test
    void constructorTest() {
        assertEquals("filler", testInteraction.getName());
        assertEquals("filler", testInteraction.getDescription());
        assertEquals("filler", testInteraction.getPrompt());
        assertEquals(0, testInteraction.getObID());
    }
}
