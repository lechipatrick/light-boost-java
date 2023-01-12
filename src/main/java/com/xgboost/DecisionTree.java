package com.xgboost;


public class DecisionTree {
    public Node[] trees;


    public DecisionTree(Node[] trees) {
        this.trees = trees;
    }

    public float score(Feature feature) {
        float sum = 0F;
        for (int i = 0; i < trees.length; i++) {
            Node node = trees[i];
            sum += node.eval(feature);
        }
        return sum;
    }

}
