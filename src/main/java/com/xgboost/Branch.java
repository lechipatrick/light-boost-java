package com.xgboost;

public record Branch(int nodeId, int depth, String feature, float threshold, int leftNodeId, int rightNodeId,
                     int missingNodeId, Node left,
                     Node right) implements Node {

    public boolean isLeaf() {
        return false;
    }

    public float eval(float[] featureValues) {
        return 0F;
    }
}
