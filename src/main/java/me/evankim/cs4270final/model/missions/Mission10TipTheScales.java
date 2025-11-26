package me.evankim.cs4270final.model.missions;

import me.evankim.cs4270final.model.Mission;

public class Mission10TipTheScales implements Mission {
    private boolean scaleTippedAndTouchingMat; // 20 points
    private boolean scalePanCompletelyRemoved; // 10 points

    public Mission10TipTheScales() {
        this.scaleTippedAndTouchingMat = false;
        this.scalePanCompletelyRemoved = false;
    }

    public boolean isScaleTippedAndTouchingMat() {
        return scaleTippedAndTouchingMat;
    }

    public void setScaleTippedAndTouchingMat(boolean scaleTippedAndTouchingMat) {
        this.scaleTippedAndTouchingMat = scaleTippedAndTouchingMat;
    }

    public boolean isScalePanCompletelyRemoved() {
        return scalePanCompletelyRemoved;
    }

    public void setScalePanCompletelyRemoved(boolean scalePanCompletelyRemoved) {
        this.scalePanCompletelyRemoved = scalePanCompletelyRemoved;
    }

    @Override
    public int calculateScore() {
        int score = 0;
        if (scaleTippedAndTouchingMat) {
            score += 20;
        }
        if (scalePanCompletelyRemoved) {
            score += 10;
        }
        return score;
    }

    @Override
    public int getMissionNumber() {
        return 10;
    }

    @Override
    public String getMissionName() {
        return "Tip the Scales";
    }
}
