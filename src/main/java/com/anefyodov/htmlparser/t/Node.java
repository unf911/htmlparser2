package com.anefyodov.htmlparser.t;

import java.util.Objects;

public class Node {
    private final int value;
    private final int i;
    private final int j;

    public Node(int value, int i, int j) {
        this.i=i;
        this.j=j;
        this.value=value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", i=" + i +
                ", j=" + j +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return value == node.value &&
                i == node.i &&
                j == node.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, i, j);
    }
}
