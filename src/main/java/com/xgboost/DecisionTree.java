package com.xgboost;

import java.util.ArrayList;

public class DecisionTree {
    public ArrayList<Node> trees;

    public DecisionTree(ArrayList<Node> trees) {
        this.trees = trees;
    }

    public interface Node {
        boolean isLeaf();

        float eval(float[] featureValues);
    }

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

    public record Leaf (int nodeId, float leafValue) implements Node {

        public boolean isLeaf() {
            return true;
        }

        public float eval(float[] featureValues) {
            return leafValue;
        }

    }
}
