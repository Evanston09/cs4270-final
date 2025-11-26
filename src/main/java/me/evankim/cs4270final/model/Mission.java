package me.evankim.cs4270final.model;

public interface Mission {
    /**
     * Calculate the score for this mission
     * @return the total points earned for this mission
     */
    int calculateScore();

    /**
     * Get the mission number (1-15)
     * @return the mission number
     */
    int getMissionNumber();

    /**
     * Get the mission name
     * @return the mission name
     */
    String getMissionName();
}
