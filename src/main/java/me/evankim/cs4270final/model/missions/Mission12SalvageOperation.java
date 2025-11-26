package me.evankim.cs4270final.model.missions;

import me.evankim.cs4270final.model.Mission;

public class Mission12SalvageOperation implements Mission {
    private boolean sandCompletelyCleared; // 20 points
    private boolean shipCompletelyRaised; // 10 points

    public Mission12SalvageOperation() {
        this.sandCompletelyCleared = false;
        this.shipCompletelyRaised = false;
    }

    public boolean isSandCompletelyCleared() {
        return sandCompletelyCleared;
    }

    public void setSandCompletelyCleared(boolean sandCompletelyCleared) {
        this.sandCompletelyCleared = sandCompletelyCleared;
    }

    public boolean isShipCompletelyRaised() {
        return shipCompletelyRaised;
    }

    public void setShipCompletelyRaised(boolean shipCompletelyRaised) {
        this.shipCompletelyRaised = shipCompletelyRaised;
    }

    @Override
    public int calculateScore() {
        int score = 0;
        if (sandCompletelyCleared) {
            score += 20;
        }
        if (shipCompletelyRaised) {
            score += 10;
        }
        return score;
    }

    @Override
    public int getMissionNumber() {
        return 12;
    }

    @Override
    public String getMissionName() {
        return "Salvage Operation";
    }
}
