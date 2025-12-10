package me.evankim.cs4270final.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Represents the gracious professionalism scoring component.
 * Teams are rated at one of three levels: Developing, Accomplished, or Exceeds.
 * Scoring: Developing = 2 pts, Accomplished = 3 pts, Exceeds = 4 pts
 */
public class GraciousProfessionalism extends ScoringComponent {
    /**
     * Enum representing the three graciousness levels with their point values.
     */
    public enum Level {
        /**
         * Developing level: 2 points
         */
        DEVELOPING(2),
        /**
         * Accomplished level: 3 points
         */
        ACCOMPLISHED(3),
        /**
         * Exceeds level: 4 points
         */
        EXCEEDS(4);

        private final int points;

        Level(int points) {
            this.points = points;
        }

        /**
         * Gets the points for this level.
         *
         * @return the points value
         */
        public int getPoints() {
            return points;
        }
    }

    private final ObjectProperty<Level> level;

    /**
     * Creates a GraciousProfessionalism component with default level of ACCOMPLISHED.
     */
    public GraciousProfessionalism() {
        this.level = new SimpleObjectProperty<>(Level.ACCOMPLISHED); // Default to middle level
        this.score = new ReadOnlyIntegerWrapper(0);
        this.score.bind(level.map(l -> l.getPoints()));
    }

    /**
     * Gets the current graciousness level.
     *
     * @return the current level
     */
    public Level getLevel() {
        return level.get();
    }

    /**
     * Gets the level as a property for JavaFX bindings.
     *
     * @return the level property
     */
    public ObjectProperty<Level> levelProperty() {
        return level;
    }

    /**
     * Sets the graciousness level.
     *
     * @param level the new level
     */
    public void setLevel(Level level) {
        this.level.set(level);
    }
}
