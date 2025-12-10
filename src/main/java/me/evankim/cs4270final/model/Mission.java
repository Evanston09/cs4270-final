package me.evankim.cs4270final.model;

public abstract class Mission extends ScoringComponent {
    /**
     * Get the mission number (1-15).
     * @return the mission number
     */
    public abstract int getMissionNumber();

    /**
     * Get the mission name.
     * @return the mission name
     */
    public abstract String getMissionName();
}
