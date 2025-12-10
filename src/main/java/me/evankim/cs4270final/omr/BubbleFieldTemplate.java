package me.evankim.cs4270final.omr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Defines the bubble field template for scoresheet pages.
 * Maps bubble locations to field IDs for each of the two scoresheet pages.
 * Uses relative positioning (0.0 to 1.0) that scales with the warped image size.
 */
public class BubbleFieldTemplate {

    /**
     * Width of the warped scoresheet page in pixels.
     */
    public static final int WARPED_WIDTH = 1700;

    /**
     * Height of the warped scoresheet page in pixels.
     */
    public static final int WARPED_HEIGHT = 2200;

    private final int pageNumber;
    private final Map<String, List<Bubble>> fields;

    /**
     * Creates a new BubbleFieldTemplate for the specified page.
     *
     * @param pageNumber the page number (1 or 2)
     */
    public BubbleFieldTemplate(int pageNumber) {
        this.pageNumber = pageNumber;
        this.fields = new HashMap<>();
        initializeTemplate();
    }

    private void initializeTemplate() {
        if (pageNumber == 1) {
            initializePage1();
        } else if (pageNumber == 2) {
            initializePage2();
        }
    }

    private BubbleBuilder bubble() {
        return new BubbleBuilder();
    }

    private static class BubbleBuilder {
        private double y;
        private List<Bubble> bubbles = new ArrayList<>();

        public BubbleBuilder at(double y) {
            this.y = y;
            return this;
        }

        public BubbleBuilder yesNo(double x) {
            bubbles.add(new Bubble(x, y, "Y"));
            bubbles.add(new Bubble(x + 0.02295, y, "N"));
            return this;
        }

        public BubbleBuilder numbers(double startX, double spacing, int count) {
            for (int i = 0; i < count; i++) {
                bubbles.add(new Bubble(startX + i * spacing, y, String.valueOf(i)));
            }
            return this;
        }

        public BubbleBuilder numbersOffset(double startX, double spacing, int start, int count) {
            for (int i = 0; i < count; i++) {
                bubbles.add(new Bubble(startX + i * spacing, y, String.valueOf(start + i)));
            }
            return this;
        }

        public List<Bubble> build() {
            return bubbles;
        }
    }

    private void initializePage1() {
        fields.put("EI", bubble().at(0.155).yesNo(0.8545).build());
        fields.put("M01_SOIL", bubble().at(0.215).numbers(0.82, 0.02, 4).build());
        fields.put("M01_BRUSH", bubble().at(0.23).yesNo(0.8545).build());
        fields.put("M02", bubble().at(0.285).numbers(0.82, 0.02, 4).build());
        fields.put("M03_YOUR", bubble().at(0.34).yesNo(0.8545).build());
        fields.put("M03_OPP", bubble().at(0.36).yesNo(0.8545).build());
        fields.put("M04_ART", bubble().at(0.425).yesNo(0.8545).build());
        fields.put("M04_SUP", bubble().at(0.445).yesNo(0.8545).build());
        fields.put("M05", bubble().at(0.5).yesNo(0.8545).build());
        fields.put("M06", bubble().at(0.555).numbers(0.84, 0.02, 3).build());
        fields.put("M07", bubble().at(0.625).yesNo(0.8545).build());
        fields.put("M08", bubble().at(0.68).numbers(0.78, 0.02, 6).build());
        fields.put("M09_ROOF", bubble().at(0.735).yesNo(0.8545).build());
        fields.put("M09_WARES", bubble().at(0.755).yesNo(0.8545).build());
    }

    private void initializePage2() {
        fields.put("M10_SCALE", bubble().at(0.155).yesNo(0.8545).build());
        fields.put("M10_PAN", bubble().at(0.175).yesNo(0.8545).build());
        fields.put("M11_ART", bubble().at(0.23).yesNo(0.8545).build());
        fields.put("M11_FLAG", bubble().at(0.25).yesNo(0.8545).build());
        fields.put("M12_SAND", bubble().at(0.305).yesNo(0.8545).build());
        fields.put("M12_SHIP", bubble().at(0.325).yesNo(0.8545).build());
        fields.put("M13", bubble().at(0.39).yesNo(0.8545).build());
        fields.put("M14", bubble().at(0.44475).numbers(0.74, 0.02, 8).build());
        fields.put("M15", bubble().at(0.5125).numbers(0.802, 0.02, 5).build());
        fields.put("PRECISION", bubble().at(0.7525).numbers(0.075, 0.0425, 7).build());
        fields.put("GP", bubble().at(0.8214).numbersOffset(0.13, 0.08, 2, 3).build());
    }


    /**
     * Gets all field definitions for this page.
     *
     * @return map of field IDs to lists of bubble options
     */
    public Map<String, List<Bubble>> getFields() {
        return fields;
    }
}
