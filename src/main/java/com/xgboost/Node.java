package com.xgboost;

public interface Node {
    boolean isLeaf();

    float eval(float[] featureValues);
}
