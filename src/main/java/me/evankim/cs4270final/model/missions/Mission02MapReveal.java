package me.evankim.cs4270final.model.missions;

import me.evankim.cs4270final.model.Mission;

public class Mission02MapReveal implements Mission {
    private int topsoilSectionsCleared; // 10 points each

    public Mission02MapReveal() {
        this.topsoilSectionsCleared = 0;
    }

    public int getTopsoilSectionsCleared() {
        return topsoilSectionsCleared;
    }

    public void setTopsoilSectionsCleared(int topsoilSectionsCleared) {
        this.topsoilSectionsCleared = Math.max(0, topsoilSectionsCleared);
    }

    @Override
    public int calculateScore() {
        return topsoilSectionsCleared * 10;
    }

    @Override
    public int getMissionNumber() {
        return 2;
    }

    @Override
    public String getMissionName() {
        return "Map Reveal";
    }
}
