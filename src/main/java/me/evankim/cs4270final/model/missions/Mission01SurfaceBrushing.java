package me.evankim.cs4270final.model.missions;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import me.evankim.cs4270final.model.Mission;

/**
 * Mission 01: Surface Brushing
 * Score: 10 points per soil deposit cleared + 10 bonus if brush not touching dig site.
 */
public class Mission01SurfaceBrushing extends Mission {
    private final IntegerProperty soilDepositsCleared;
    private final BooleanProperty brushNotTouchingDigSite;

    /**
     * Creates a new Mission 01 with default values (0 deposits, brush touching site).
     */
    public Mission01SurfaceBrushing() {
        this.soilDepositsCleared = new SimpleIntegerProperty(0);
        this.brushNotTouchingDigSite = new SimpleBooleanProperty(false);
        this.score = new ReadOnlyIntegerWrapper(0);
        this.score.bind(
                Bindings.createIntegerBinding(() -> {
                    int score = getSoilDepositsCleared() * 10;
                    if (isBrushNotTouchingDigSite()) {
                        score += 10;
                    }
                    return score;
                }, soilDepositsCleared, brushNotTouchingDigSite)
        );
    }

    /**
     * Gets the number of soil deposits cleared.
     *
     * @return the number of deposits cleared (0+)
     */
    public int getSoilDepositsCleared() {
        return soilDepositsCleared.get();
    }

    /**
     * Gets the soil deposits cleared as a property for JavaFX bindings.
     *
     * @return the soil deposits property
     */
    public IntegerProperty soilDepositsClearedProperty() {
        return soilDepositsCleared;
    }

    /**
     * Sets the number of soil deposits cleared.
     *
     * @param soilDepositsCleared the number of deposits cleared (clamped to 0+)
     */
    public void setSoilDepositsCleared(int soilDepositsCleared) {
        this.soilDepositsCleared.set(Math.max(0, soilDepositsCleared));
    }

    /**
     * Checks if the brush is not touching the dig site.
     *
     * @return true if brush is not touching, false otherwise
     */
    public boolean isBrushNotTouchingDigSite() {
        return brushNotTouchingDigSite.get();
    }

    /**
     * Gets the brush status as a property for JavaFX bindings.
     *
     * @return the brush status property
     */
    public BooleanProperty brushNotTouchingDigSiteProperty() {
        return brushNotTouchingDigSite;
    }

    /**
     * Sets whether the brush is not touching the dig site.
     *
     * @param brushNotTouchingDigSite true if brush not touching, false otherwise
     */
    public void setBrushNotTouchingDigSite(boolean brushNotTouchingDigSite) {
        this.brushNotTouchingDigSite.set(brushNotTouchingDigSite);
    }

    /**
     * Gets the mission number.
     *
     * @return 1
     */
    @Override
    public int getMissionNumber() {
        return 1;
    }

    /**
     * Gets the mission name.
     *
     * @return "Surface Brushing"
     */
    @Override
    public String getMissionName() {
        return "Surface Brushing";
    }
}
