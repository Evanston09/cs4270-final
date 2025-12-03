package me.evankim.cs4270final.omr;

public class DetectionResult {
    private final String fieldId;
    private final String detectedValue;

    public DetectionResult(String fieldId, String detectedValue) {
        this.fieldId = fieldId;
        this.detectedValue = detectedValue;
    }

    public String getFieldId() {
        return fieldId;
    }

    public String getDetectedValue() {
        return detectedValue;
    }

    public boolean hasValue() {
        return detectedValue != null;
    }
}
