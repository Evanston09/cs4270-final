package me.evankim.cs4270final.model.missions;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import me.evankim.cs4270final.model.Mission;

/**
 * Mission 06: Forge
 * Score: 10 points per ore block not touching the forge.
 */
public class Mission06Forge extends Mission {
    private final IntegerProperty oreBlocksNotTouchingForge; // 10 points each

    /**
     * Creates a new Mission 06 with default values (0 ore blocks).
     */
    public Mission06Forge() {
        this.oreBlocksNotTouchingForge = new SimpleIntegerProperty(0);
        this.score = new ReadOnlyIntegerWrapper(0);
        this.score.bind(oreBlocksNotTouchingForge.multiply(10));
    }

    /**
     * Gets the number of ore blocks not touching the forge.
     *
     * @return the number of ore blocks (0+)
     */
    public int getOreBlocksNotTouchingForge() {
        return oreBlocksNotTouchingForge.get();
    }

    /**
     * Gets the ore blocks as a property for JavaFX bindings.
     *
     * @return the ore blocks property
     */
    public IntegerProperty oreBlocksNotTouchingForgeProperty() {
        return oreBlocksNotTouchingForge;
    }

    /**
     * Sets the number of ore blocks not touching the forge.
     *
     * @param oreBlocksNotTouchingForge the number of ore blocks (clamped to 0+)
     */
    public void setOreBlocksNotTouchingForge(int oreBlocksNotTouchingForge) {
        this.oreBlocksNotTouchingForge.set(Math.max(0, oreBlocksNotTouchingForge));
    }

    /**
     * Gets the mission number.
     *
     * @return 6
     */
    @Override
    public int getMissionNumber() {
        return 6;
    }

    /**
     * Gets the mission name.
     *
     * @return "Forge"
     */
    @Override
    public String getMissionName() {
        return "Forge";
    }
}
