package me.evankim.cs4270final.omr;

/**
 * Represents the result of detecting a filled bubble in a scoresheet field.
 * Contains the field ID and the detected value.
 */
public class DetectionResult {
    private final String fieldId;
    private final String detectedValue;

    /**
     * Creates a new detection result.
     *
     * @param fieldId the field identifier (e.g., "M01_SOIL")
     * @param detectedValue the detected value (e.g., "2")
     */
    public DetectionResult(String fieldId, String detectedValue) {
        this.fieldId = fieldId;
        this.detectedValue = detectedValue;
    }

    /**
     * Gets the field identifier.
     *
     * @return the field ID
     */
    public String getFieldId() {
        return fieldId;
    }

    /**
     * Gets the detected value.
     *
     * @return the detected value, or null if no value detected
     */
    public String getDetectedValue() {
        return detectedValue;
    }

    /**
     * Checks if a value was detected.
     *
     * @return true if a value was detected, false otherwise
     */
    public boolean hasValue() {
        return detectedValue != null;
    }
}
