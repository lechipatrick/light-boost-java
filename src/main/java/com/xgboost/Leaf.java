package com.xgboost;

public record Leaf(int nodeId, float leafValue) implements Node {

    public boolean isLeaf() {
        return true;
    }

    public float eval(Feature feature) {
        return leafValue;
    }

}
