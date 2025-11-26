package me.evankim.cs4270final.model;

public class EquipmentInspection {
    private boolean passed;

    public EquipmentInspection() {
        this.passed = false;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public int calculateScore() {
        return passed ? 20 : 0;
    }
}
