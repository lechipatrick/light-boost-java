package com.xgboost;


public interface Node {
    boolean isLeaf();

    float eval(Feature feature);
}
