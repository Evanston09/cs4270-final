package me.evankim.cs4270final.model.missions;

import me.evankim.cs4270final.model.Mission;

public class Mission01SurfaceBrushing extends Mission {
    private int soilDepositsCleared;
    private boolean brushNotTouchingDigSite;

    public Mission01SurfaceBrushing() {
        this.soilDepositsCleared = 0;
        this.brushNotTouchingDigSite = false;
    }

    public int getSoilDepositsCleared() {
        return soilDepositsCleared;
    }

    public void setSoilDepositsCleared(int soilDepositsCleared) {
        this.soilDepositsCleared = Math.max(0, soilDepositsCleared);
    }

    public boolean isBrushNotTouchingDigSite() {
        return brushNotTouchingDigSite;
    }

    public void setBrushNotTouchingDigSite(boolean brushNotTouchingDigSite) {
        this.brushNotTouchingDigSite = brushNotTouchingDigSite;
    }

    @Override
    public int calculateScore() {
        int score = soilDepositsCleared * 10;
        if (brushNotTouchingDigSite) {
            score += 10;
        }
        return score;
    }

    @Override
    public int getMissionNumber() {
        return 1;
    }

    @Override
    public String getMissionName() {
        return "Surface Brushing";
    }
}
