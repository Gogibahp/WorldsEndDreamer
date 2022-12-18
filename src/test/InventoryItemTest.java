import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.InventoryItem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class InventoryItemTest {
    InventoryItem testItem;

    @BeforeEach
    void runBefore() {
        testItem = new InventoryItem(0,"filler", 0);
    }

    @Test
    void constructorTest() {
        assertEquals(0, testItem.getObID());
        assertEquals(0, testItem.getLocation());
        assertEquals("filler", testItem.getKey());
    }

}
