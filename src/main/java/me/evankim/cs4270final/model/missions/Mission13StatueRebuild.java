package me.evankim.cs4270final.model.missions;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import me.evankim.cs4270final.model.Mission;

/**
 * Mission 13: Statue Rebuild
 * Score: 30 points if statue is completely raised.
 */
public class Mission13StatueRebuild extends Mission {
    private final BooleanProperty statueCompletelyRaised; // 30 points

    /**
     * Creates a new Mission 13 with default values (statue not raised).
     */
    public Mission13StatueRebuild() {
        this.statueCompletelyRaised = new SimpleBooleanProperty(false);
        this.score = new ReadOnlyIntegerWrapper(0);
        this.score.bind(
                statueCompletelyRaised.map(b -> b ? 30 : 0)
        );
    }

    /**
     * Checks if the statue is completely raised.
     *
     * @return true if statue is completely raised, false otherwise
     */
    public boolean isStatueCompletelyRaised() {
        return statueCompletelyRaised.get();
    }

    /**
     * Gets the statue status as a property for JavaFX bindings.
     *
     * @return the statue property
     */
    public BooleanProperty statueCompletelyRaisedProperty() {
        return statueCompletelyRaised;
    }

    /**
     * Sets whether the statue is completely raised.
     *
     * @param statueCompletelyRaised true if statue is raised, false otherwise
     */
    public void setStatueCompletelyRaised(boolean statueCompletelyRaised) {
        this.statueCompletelyRaised.set(statueCompletelyRaised);
    }

    /**
     * Gets the mission number.
     *
     * @return 13
     */
    @Override
    public int getMissionNumber() {
        return 13;
    }

    /**
     * Gets the mission name.
     *
     * @return "Statue Rebuild"
     */
    @Override
    public String getMissionName() {
        return "Statue Rebuild";
    }
}
