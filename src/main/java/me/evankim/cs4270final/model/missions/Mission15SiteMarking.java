package me.evankim.cs4270final.model.missions;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import me.evankim.cs4270final.model.Mission;

/**
 * Mission 15: Site Marking
 * Score: 10 points per site with a flag.
 */
public class Mission15SiteMarking extends Mission {
    private final IntegerProperty sitesWithFlags; // 10 points each

    /**
     * Creates a new Mission 15 with default values (0 sites with flags).
     */
    public Mission15SiteMarking() {
        this.sitesWithFlags = new SimpleIntegerProperty(0);
        this.score = new ReadOnlyIntegerWrapper(0);
        this.score.bind(sitesWithFlags.multiply(10));
    }

    /**
     * Gets the number of sites with flags.
     *
     * @return the number of sites with flags (0+)
     */
    public int getSitesWithFlags() {
        return sitesWithFlags.get();
    }

    /**
     * Gets the sites with flags as a property for JavaFX bindings.
     *
     * @return the sites with flags property
     */
    public IntegerProperty sitesWithFlagsProperty() {
        return sitesWithFlags;
    }

    /**
     * Sets the number of sites with flags.
     *
     * @param sitesWithFlags the number of sites with flags (clamped to 0+)
     */
    public void setSitesWithFlags(int sitesWithFlags) {
        this.sitesWithFlags.set(Math.max(0, sitesWithFlags));
    }

    /**
     * Gets the mission number.
     *
     * @return 15
     */
    @Override
    public int getMissionNumber() {
        return 15;
    }

    /**
     * Gets the mission name.
     *
     * @return "Site Marking"
     */
    @Override
    public String getMissionName() {
        return "Site Marking";
    }
}
