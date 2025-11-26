package me.evankim.cs4270final.model.missions;

import me.evankim.cs4270final.model.Mission;

public class Mission09WhatsOnSale implements Mission {
    private boolean roofCompletelyRaised; // 20 points
    private boolean marketWaresRaised; // 10 points

    public Mission09WhatsOnSale() {
        this.roofCompletelyRaised = false;
        this.marketWaresRaised = false;
    }

    public boolean isRoofCompletelyRaised() {
        return roofCompletelyRaised;
    }

    public void setRoofCompletelyRaised(boolean roofCompletelyRaised) {
        this.roofCompletelyRaised = roofCompletelyRaised;
    }

    public boolean isMarketWaresRaised() {
        return marketWaresRaised;
    }

    public void setMarketWaresRaised(boolean marketWaresRaised) {
        this.marketWaresRaised = marketWaresRaised;
    }

    @Override
    public int calculateScore() {
        int score = 0;
        if (roofCompletelyRaised) {
            score += 20;
        }
        if (marketWaresRaised) {
            score += 10;
        }
        return score;
    }

    @Override
    public int getMissionNumber() {
        return 9;
    }

    @Override
    public String getMissionName() {
        return "What's On Sale?";
    }
}
