package me.evankim.cs4270final.model.missions;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import me.evankim.cs4270final.model.Mission;

/**
 * Mission 02: Map Reveal
 * Score: 10 points per topsoil section cleared.
 */
public class Mission02MapReveal extends Mission {
    private final IntegerProperty topsoilSectionsCleared; // 10 points each

    /**
     * Creates a new Mission 02 with default values (0 sections cleared).
     */
    public Mission02MapReveal() {
        this.topsoilSectionsCleared = new SimpleIntegerProperty(0);
        this.score = new ReadOnlyIntegerWrapper(0);
        this.score.bind(topsoilSectionsCleared.multiply(10));
    }

    /**
     * Gets the number of topsoil sections cleared.
     *
     * @return the number of sections cleared (0+)
     */
    public int getTopsoilSectionsCleared() {
        return topsoilSectionsCleared.get();
    }

    /**
     * Gets the topsoil sections cleared as a property for JavaFX bindings.
     *
     * @return the sections property
     */
    public IntegerProperty topsoilSectionsClearedProperty() {
        return topsoilSectionsCleared;
    }

    /**
     * Sets the number of topsoil sections cleared.
     *
     * @param topsoilSectionsCleared the number of sections cleared (clamped to 0+)
     */
    public void setTopsoilSectionsCleared(int topsoilSectionsCleared) {
        this.topsoilSectionsCleared.set(Math.max(0, topsoilSectionsCleared));
    }

    /**
     * Gets the mission number.
     *
     * @return 2
     */
    @Override
    public int getMissionNumber() {
        return 2;
    }

    /**
     * Gets the mission name.
     *
     * @return "Map Reveal"
     */
    @Override
    public String getMissionName() {
        return "Map Reveal";
    }
}
