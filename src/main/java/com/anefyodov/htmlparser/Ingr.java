package com.anefyodov.htmlparser;

public class Ingr {
    private final String section;
    private final String nutrient;
    private final String unit;
    private final String oz;
    private final String value;

    public Ingr(String section, String nutrient, String unit, String oz, String value) {
        this.section = section;
        this.nutrient = nutrient;
        this.unit = unit;
        this.oz = oz;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Ingr{" +
                "section='" + section + '\'' +
                ", nutrient='" + nutrient + '\'' +
                ", unit='" + unit + '\'' +
                ", oz='" + oz + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
