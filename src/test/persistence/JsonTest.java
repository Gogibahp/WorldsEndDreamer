package persistence;

import model.Interaction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    public void checkInteraction(String name, Interaction interaction, String description,
                                 String prompt, int obID) {
        assertEquals(name, interaction.getName());
        assertEquals(description, interaction.getDescription());
        assertEquals(prompt, interaction.getPrompt());
        assertEquals(obID, interaction.getObID());
    }
}
