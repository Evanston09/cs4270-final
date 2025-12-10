package me.evankim.cs4270final.model.missions;

import me.evankim.cs4270final.model.Mission;

public class Mission03MineshaftExplorer extends Mission {
    private boolean yourMinecartOnOpposingField; // 30 points
    private boolean opposingMinecartOnYourField; // 10 points bonus

    public Mission03MineshaftExplorer() {
        this.yourMinecartOnOpposingField = false;
        this.opposingMinecartOnYourField = false;
    }

    public boolean isYourMinecartOnOpposingField() {
        return yourMinecartOnOpposingField;
    }

    public void setYourMinecartOnOpposingField(boolean yourMinecartOnOpposingField) {
        this.yourMinecartOnOpposingField = yourMinecartOnOpposingField;
    }

    public boolean isOpposingMinecartOnYourField() {
        return opposingMinecartOnYourField;
    }

    public void setOpposingMinecartOnYourField(boolean opposingMinecartOnYourField) {
        this.opposingMinecartOnYourField = opposingMinecartOnYourField;
    }

    @Override
    public int calculateScore() {
        int score = 0;
        if (yourMinecartOnOpposingField) {
            score += 30;
            if (opposingMinecartOnYourField) {
                score += 10; // Bonus only applies if main task completed
            }
        }
        return score;
    }

    @Override
    public int getMissionNumber() {
        return 3;
    }

    @Override
    public String getMissionName() {
        return "Mineshaft Explorer";
    }
}
