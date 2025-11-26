package me.evankim.cs4270final.model.missions;

import me.evankim.cs4270final.model.Mission;

public class Mission11AnglerArtifacts implements Mission {
    private boolean artifactsRaisedAboveGroundLayer; // 20 points
    private boolean craneFlagAtLeastPartlyLowered; // 10 points bonus

    public Mission11AnglerArtifacts() {
        this.artifactsRaisedAboveGroundLayer = false;
        this.craneFlagAtLeastPartlyLowered = false;
    }

    public boolean isArtifactsRaisedAboveGroundLayer() {
        return artifactsRaisedAboveGroundLayer;
    }

    public void setArtifactsRaisedAboveGroundLayer(boolean artifactsRaisedAboveGroundLayer) {
        this.artifactsRaisedAboveGroundLayer = artifactsRaisedAboveGroundLayer;
    }

    public boolean isCraneFlagAtLeastPartlyLowered() {
        return craneFlagAtLeastPartlyLowered;
    }

    public void setCraneFlagAtLeastPartlyLowered(boolean craneFlagAtLeastPartlyLowered) {
        this.craneFlagAtLeastPartlyLowered = craneFlagAtLeastPartlyLowered;
    }

    @Override
    public int calculateScore() {
        int score = 0;
        if (artifactsRaisedAboveGroundLayer) {
            score += 20;
            if (craneFlagAtLeastPartlyLowered) {
                score += 10; // Bonus only applies if main task completed
            }
        }
        return score;
    }

    @Override
    public int getMissionNumber() {
        return 11;
    }

    @Override
    public String getMissionName() {
        return "Angler Artifacts";
    }
}
