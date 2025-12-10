package me.evankim.cs4270final.model.missions;

import me.evankim.cs4270final.model.Mission;

public class Mission04CarefulRecovery extends Mission {
    private boolean preciousArtifactNotTouchingMine; // 30 points
    private boolean bothSupportStructuresStanding; // 10 points

    public Mission04CarefulRecovery() {
        this.preciousArtifactNotTouchingMine = false;
        this.bothSupportStructuresStanding = false;
    }

    public boolean isPreciousArtifactNotTouchingMine() {
        return preciousArtifactNotTouchingMine;
    }

    public void setPreciousArtifactNotTouchingMine(boolean preciousArtifactNotTouchingMine) {
        this.preciousArtifactNotTouchingMine = preciousArtifactNotTouchingMine;
    }

    public boolean isBothSupportStructuresStanding() {
        return bothSupportStructuresStanding;
    }

    public void setBothSupportStructuresStanding(boolean bothSupportStructuresStanding) {
        this.bothSupportStructuresStanding = bothSupportStructuresStanding;
    }

    @Override
    public int calculateScore() {
        int score = 0;
        if (preciousArtifactNotTouchingMine) {
            score += 30;
        }
        if (bothSupportStructuresStanding) {
            score += 10;
        }
        return score;
    }

    @Override
    public int getMissionNumber() {
        return 4;
    }

    @Override
    public String getMissionName() {
        return "Careful Recovery";
    }
}
