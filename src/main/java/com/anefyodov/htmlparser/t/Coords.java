package com.anefyodov.htmlparser.t;

import java.util.Objects;

public class Coords {
    private final int j;
    private final int i;

    public Coords(int i, int j) {
        this.i = i;
        this.j= j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.siliconvd.user.api.t.Coords coords = (com.siliconvd.user.api.t.Coords) o;
        return j == coords.j &&
                i == coords.i;
    }

    @Override
    public int hashCode() {
        return Objects.hash(j, i);
    }

    public int getI() {
        return i;
    }


    public int getJ() {
        return j;

    }

    @Override
    public String toString() {
        return "Coords{" +
                "j=" + j +
                ", i=" + i +
                '}';
    }
}
