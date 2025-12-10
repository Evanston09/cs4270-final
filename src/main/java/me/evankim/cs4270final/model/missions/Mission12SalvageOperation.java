package me.evankim.cs4270final.model.missions;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import me.evankim.cs4270final.model.Mission;

/**
 * Mission 12: Salvage Operation
 * Score: 20 points if sand is completely cleared + 10 points if ship is completely raised.
 */
public class Mission12SalvageOperation extends Mission {
    private final BooleanProperty sandCompletelyCleared; // 20 points
    private final BooleanProperty shipCompletelyRaised; // 10 points

    /**
     * Creates a new Mission 12 with default values (sand not cleared, ship not raised).
     */
    public Mission12SalvageOperation() {
        this.sandCompletelyCleared = new SimpleBooleanProperty(false);
        this.shipCompletelyRaised = new SimpleBooleanProperty(false);
        this.score = new ReadOnlyIntegerWrapper(0);
        this.score.bind(
                Bindings.createIntegerBinding(() -> {
                    int score = 0;
                    if (isSandCompletelyCleared()) {
                        score += 20;
                    }
                    if (isShipCompletelyRaised()) {
                        score += 10;
                    }
                    return score;
                }, sandCompletelyCleared, shipCompletelyRaised)
        );
    }

    /**
     * Checks if the sand is completely cleared.
     *
     * @return true if sand is completely cleared, false otherwise
     */
    public boolean isSandCompletelyCleared() {
        return sandCompletelyCleared.get();
    }

    /**
     * Gets the sand status as a property for JavaFX bindings.
     *
     * @return the sand property
     */
    public BooleanProperty sandCompletelyClearedProperty() {
        return sandCompletelyCleared;
    }

    /**
     * Sets whether the sand is completely cleared.
     *
     * @param sandCompletelyCleared true if sand is cleared, false otherwise
     */
    public void setSandCompletelyCleared(boolean sandCompletelyCleared) {
        this.sandCompletelyCleared.set(sandCompletelyCleared);
    }

    /**
     * Checks if the ship is completely raised.
     *
     * @return true if ship is completely raised, false otherwise
     */
    public boolean isShipCompletelyRaised() {
        return shipCompletelyRaised.get();
    }

    /**
     * Gets the ship status as a property for JavaFX bindings.
     *
     * @return the ship property
     */
    public BooleanProperty shipCompletelyRaisedProperty() {
        return shipCompletelyRaised;
    }

    /**
     * Sets whether the ship is completely raised.
     *
     * @param shipCompletelyRaised true if ship is raised, false otherwise
     */
    public void setShipCompletelyRaised(boolean shipCompletelyRaised) {
        this.shipCompletelyRaised.set(shipCompletelyRaised);
    }

    /**
     * Gets the mission number.
     *
     * @return 12
     */
    @Override
    public int getMissionNumber() {
        return 12;
    }

    /**
     * Gets the mission name.
     *
     * @return "Salvage Operation"
     */
    @Override
    public String getMissionName() {
        return "Salvage Operation";
    }
}
