package me.evankim.cs4270final.model.missions;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import me.evankim.cs4270final.model.Mission;

/**
 * Mission 08: Silo
 * Score: 10 points per preserved piece outside the silo.
 */
public class Mission08Silo extends Mission {
    private final IntegerProperty preservedPiecesOutsideSilo; // 10 points each

    /**
     * Creates a new Mission 08 with default values (0 preserved pieces).
     */
    public Mission08Silo() {
        this.preservedPiecesOutsideSilo = new SimpleIntegerProperty(0);
        this.score = new ReadOnlyIntegerWrapper(0);
        this.score.bind(preservedPiecesOutsideSilo.multiply(10));
    }

    /**
     * Gets the number of preserved pieces outside the silo.
     *
     * @return the number of preserved pieces (0+)
     */
    public int getPreservedPiecesOutsideSilo() {
        return preservedPiecesOutsideSilo.get();
    }

    /**
     * Gets the preserved pieces as a property for JavaFX bindings.
     *
     * @return the preserved pieces property
     */
    public IntegerProperty preservedPiecesOutsideSiloProperty() {
        return preservedPiecesOutsideSilo;
    }

    /**
     * Sets the number of preserved pieces outside the silo.
     *
     * @param preservedPiecesOutsideSilo the number of preserved pieces (clamped to 0+)
     */
    public void setPreservedPiecesOutsideSilo(int preservedPiecesOutsideSilo) {
        this.preservedPiecesOutsideSilo.set(Math.max(0, preservedPiecesOutsideSilo));
    }

    /**
     * Gets the mission number.
     *
     * @return 8
     */
    @Override
    public int getMissionNumber() {
        return 8;
    }

    /**
     * Gets the mission name.
     *
     * @return "Silo"
     */
    @Override
    public String getMissionName() {
        return "Silo";
    }
}
