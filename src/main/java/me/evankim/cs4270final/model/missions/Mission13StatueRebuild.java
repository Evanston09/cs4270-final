package me.evankim.cs4270final.model.missions;

import me.evankim.cs4270final.model.Mission;

public class Mission13StatueRebuild implements Mission {
    private boolean statueCompletelyRaised; // 30 points

    public Mission13StatueRebuild() {
        this.statueCompletelyRaised = false;
    }

    public boolean isStatueCompletelyRaised() {
        return statueCompletelyRaised;
    }

    public void setStatueCompletelyRaised(boolean statueCompletelyRaised) {
        this.statueCompletelyRaised = statueCompletelyRaised;
    }

    @Override
    public int calculateScore() {
        return statueCompletelyRaised ? 30 : 0;
    }

    @Override
    public int getMissionNumber() {
        return 13;
    }

    @Override
    public String getMissionName() {
        return "Statue Rebuild";
    }
}
