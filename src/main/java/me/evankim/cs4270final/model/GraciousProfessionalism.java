package me.evankim.cs4270final.model;

public class GraciousProfessionalism {
    public enum Level {
        DEVELOPING(2),
        ACCOMPLISHED(3),
        EXCEEDS(4);

        private final int points;

        Level(int points) {
            this.points = points;
        }

        public int getPoints() {
            return points;
        }
    }

    private Level level;

    public GraciousProfessionalism() {
        this.level = Level.ACCOMPLISHED; // Default to middle level
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public int calculateScore() {
        return level.getPoints();
    }
}
