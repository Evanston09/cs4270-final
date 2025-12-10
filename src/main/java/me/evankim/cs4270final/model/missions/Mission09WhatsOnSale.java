package me.evankim.cs4270final.model.missions;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import me.evankim.cs4270final.model.Mission;

/**
 * Mission 09: What's On Sale?
 * Score: 20 points if roof is completely raised + 10 points if market wares are raised.
 */
public class Mission09WhatsOnSale extends Mission {
    private final BooleanProperty roofCompletelyRaised; // 20 points
    private final BooleanProperty marketWaresRaised; // 10 points

    /**
     * Creates a new Mission 09 with default values (roof and wares not raised).
     */
    public Mission09WhatsOnSale() {
        this.roofCompletelyRaised = new SimpleBooleanProperty(false);
        this.marketWaresRaised = new SimpleBooleanProperty(false);
        this.score = new ReadOnlyIntegerWrapper(0);
        this.score.bind(
                Bindings.createIntegerBinding(() -> {
                    int score = 0;
                    if (isRoofCompletelyRaised()) {
                        score += 20;
                    }
                    if (isMarketWaresRaised()) {
                        score += 10;
                    }
                    return score;
                }, roofCompletelyRaised, marketWaresRaised)
        );
    }

    /**
     * Checks if the roof is completely raised.
     *
     * @return true if roof is completely raised, false otherwise
     */
    public boolean isRoofCompletelyRaised() {
        return roofCompletelyRaised.get();
    }

    /**
     * Gets the roof status as a property for JavaFX bindings.
     *
     * @return the roof property
     */
    public BooleanProperty roofCompletelyRaisedProperty() {
        return roofCompletelyRaised;
    }

    /**
     * Sets whether the roof is completely raised.
     *
     * @param roofCompletelyRaised true if roof is raised, false otherwise
     */
    public void setRoofCompletelyRaised(boolean roofCompletelyRaised) {
        this.roofCompletelyRaised.set(roofCompletelyRaised);
    }

    /**
     * Checks if the market wares are raised.
     *
     * @return true if market wares are raised, false otherwise
     */
    public boolean isMarketWaresRaised() {
        return marketWaresRaised.get();
    }

    /**
     * Gets the market wares status as a property for JavaFX bindings.
     *
     * @return the market wares property
     */
    public BooleanProperty marketWaresRaisedProperty() {
        return marketWaresRaised;
    }

    /**
     * Sets whether the market wares are raised.
     *
     * @param marketWaresRaised true if wares are raised, false otherwise
     */
    public void setMarketWaresRaised(boolean marketWaresRaised) {
        this.marketWaresRaised.set(marketWaresRaised);
    }

    /**
     * Gets the mission number.
     *
     * @return 9
     */
    @Override
    public int getMissionNumber() {
        return 9;
    }

    /**
     * Gets the mission name.
     *
     * @return "What's On Sale?"
     */
    @Override
    public String getMissionName() {
        return "What's On Sale?";
    }
}
