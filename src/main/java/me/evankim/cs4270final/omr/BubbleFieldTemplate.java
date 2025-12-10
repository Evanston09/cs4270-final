package me.evankim.cs4270final.omr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BubbleFieldTemplate {

    public static final int WARPED_WIDTH = 1700;
    public static final int WARPED_HEIGHT = 2200;

    private final int pageNumber;
    private final Map<String, List<Bubble>> fields;

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

    private void initializePage1() {
        List<Bubble> ei = new ArrayList<>();
        ei.add(new Bubble(0.8545, 0.155, "Y"));
        ei.add(new Bubble(0.87745, 0.155, "N"));
        fields.put("EI", ei);

        List<Bubble> m01Soil = new ArrayList<>();
        m01Soil.add(new Bubble(0.82, 0.215, "0"));
        m01Soil.add(new Bubble(0.84, 0.215, "1"));
        m01Soil.add(new Bubble(0.86, 0.215, "2"));
        m01Soil.add(new Bubble(0.88, 0.215, "3"));
        fields.put("M01_SOIL", m01Soil);

        List<Bubble> m01Brush = new ArrayList<>();
        m01Brush.add(new Bubble(0.8545, 0.23, "Y"));
        m01Brush.add(new Bubble(0.87745, 0.23, "N"));
        fields.put("M01_BRUSH", m01Brush);

        List<Bubble> m02 = new ArrayList<>();
        m02.add(new Bubble(0.82, 0.285, "0"));
        m02.add(new Bubble(0.84, 0.285, "1"));
        m02.add(new Bubble(0.86, 0.285, "2"));
        m02.add(new Bubble(0.88, 0.285, "3"));
        fields.put("M02", m02);

        List<Bubble> m03Your = new ArrayList<>();
        m03Your.add(new Bubble(0.8545, 0.34, "Y"));
        m03Your.add(new Bubble(0.87745, 0.34, "N"));
        fields.put("M03_YOUR", m03Your);

        List<Bubble> m03Opp = new ArrayList<>();
        m03Opp.add(new Bubble(0.8545, 0.36, "Y"));
        m03Opp.add(new Bubble(0.87745, 0.36, "N"));
        fields.put("M03_OPP", m03Opp);

        List<Bubble> m04Art = new ArrayList<>();
        m04Art.add(new Bubble(0.8545, 0.425, "Y"));
        m04Art.add(new Bubble(0.87745, 0.425, "N"));
        fields.put("M04_ART", m04Art);

        List<Bubble> m04Sup = new ArrayList<>();
        m04Sup.add(new Bubble(0.8545, 0.445, "Y"));
        m04Sup.add(new Bubble(0.87745, 0.445, "N"));
        fields.put("M04_SUP", m04Sup);

        List<Bubble> m05 = new ArrayList<>();
        m05.add(new Bubble(0.8545, 0.5, "Y"));
        m05.add(new Bubble(0.87745, 0.5, "N"));
        fields.put("M05", m05);

        List<Bubble> m06 = new ArrayList<>();
        m06.add(new Bubble(0.84, 0.555, "0"));
        m06.add(new Bubble(0.86, 0.555, "1"));
        m06.add(new Bubble(0.88, 0.555, "2"));
        fields.put("M06", m06);

        List<Bubble> m07 = new ArrayList<>();
        m07.add(new Bubble(0.8545, 0.625, "Y"));
        m07.add(new Bubble(0.87745, 0.625, "N"));
        fields.put("M07", m07);

        List<Bubble> m08 = new ArrayList<>();
        m08.add(new Bubble(0.78, 0.68, "0"));
        m08.add(new Bubble(0.8, 0.68, "1"));
        m08.add(new Bubble(0.82, 0.68, "2"));
        m08.add(new Bubble(0.84, 0.68, "3"));
        m08.add(new Bubble(0.86, 0.68, "4"));
        m08.add(new Bubble(0.88, 0.68, "5"));
        fields.put("M08", m08);

        List<Bubble> m09Roof = new ArrayList<>();
        m09Roof.add(new Bubble(0.8545, 0.735, "Y"));
        m09Roof.add(new Bubble(0.87745, 0.735, "N"));
        fields.put("M09_ROOF", m09Roof);

        List<Bubble> m09Wares = new ArrayList<>();
        m09Wares.add(new Bubble(0.8545, 0.755, "Y"));
        m09Wares.add(new Bubble(0.87745, 0.755, "N"));
        fields.put("M09_WARES", m09Wares);
    }

    private void initializePage2() {
        List<Bubble> m10Scale = new ArrayList<>();
        m10Scale.add(new Bubble(0.8545, 0.155, "Y"));
        m10Scale.add(new Bubble(0.87745, 0.155, "N"));
        fields.put("M10_SCALE", m10Scale);

        List<Bubble> m10Pan = new ArrayList<>();
        m10Pan.add(new Bubble(0.8545, 0.175, "Y"));
        m10Pan.add(new Bubble(0.87745, 0.175, "N"));
        fields.put("M10_PAN", m10Pan);

        List<Bubble> m11Art = new ArrayList<>();
        m11Art.add(new Bubble(0.8545, 0.23, "Y"));
        m11Art.add(new Bubble(0.87745, 0.23, "N"));
        fields.put("M11_ART", m11Art);

        List<Bubble> m11Flag = new ArrayList<>();
        m11Flag.add(new Bubble(0.8545, 0.25, "Y"));
        m11Flag.add(new Bubble(0.87745, 0.25, "N"));
        fields.put("M11_FLAG", m11Flag);

        List<Bubble> m12Sand = new ArrayList<>();
        m12Sand.add(new Bubble(0.8545, 0.305, "Y"));
        m12Sand.add(new Bubble(0.87745, 0.305, "N"));
        fields.put("M12_SAND", m12Sand);

        List<Bubble> m12Ship = new ArrayList<>();
        m12Ship.add(new Bubble(0.8545, 0.325, "Y"));
        m12Ship.add(new Bubble(0.87745, 0.325, "N"));
        fields.put("M12_SHIP", m12Ship);

        List<Bubble> m13 = new ArrayList<>();
        m13.add(new Bubble(0.8545, 0.39, "Y"));
        m13.add(new Bubble(0.87745, 0.39, "N"));
        fields.put("M13", m13);

        List<Bubble> m14 = new ArrayList<>();
        m14.add(new Bubble(0.74, 0.44475, "0"));
        m14.add(new Bubble(0.76, 0.44475, "1"));
        m14.add(new Bubble(0.78, 0.44475, "2"));
        m14.add(new Bubble(0.80, 0.44475, "3"));
        m14.add(new Bubble(0.82, 0.44475, "4"));
        m14.add(new Bubble(0.84, 0.44475, "5"));
        m14.add(new Bubble(0.86, 0.44475, "6"));
        m14.add(new Bubble(0.88, 0.44475, "7"));
        fields.put("M14", m14);

        List<Bubble> m15 = new ArrayList<>();
        m15.add(new Bubble(0.802, 0.5125, "0"));
        m15.add(new Bubble(0.822, 0.5125, "1"));
        m15.add(new Bubble(0.842, 0.5125, "2"));
        m15.add(new Bubble(0.862, 0.5125, "3"));
        m15.add(new Bubble(0.882, 0.5125, "4"));
        fields.put("M15", m15);


        List<Bubble> precision = new ArrayList<>();
        precision.add(new Bubble(0.075, 0.7525, "0"));
        precision.add(new Bubble(0.1175, 0.7525, "1"));
        precision.add(new Bubble(0.1600, 0.7525, "2"));
        precision.add(new Bubble(0.2025, 0.7525, "3"));
        precision.add(new Bubble(0.2450, 0.7525, "4"));
        precision.add(new Bubble(0.2875, 0.7525, "5"));
        precision.add(new Bubble(0.3300, 0.7525, "6"));
        fields.put("PRECISION", precision);

        List<Bubble> gp = new ArrayList<>();
        gp.add(new Bubble(0.13, 0.8214, "2"));
        gp.add(new Bubble(0.21, 0.8214, "3"));
        gp.add(new Bubble(0.29, 0.8214, "4"));
        fields.put("GP", gp);
    }


    public Map<String, List<Bubble>> getFields() {
        return fields;
    }
}
