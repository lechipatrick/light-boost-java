package com.xgboost;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    public static class Branch implements Node {
        private int nodeId;
        private int depth;
        private String feature;
        private float threshold;
        private int leftNodeId;
        private int rightNodeId;
        private int missingNodeId;
        private Node left;
        private Node right;

        public Branch(int nodeId, int depth, String feature, float threshold, int leftNodeId, int rightNodeId, int missingNodeId, Node left, Node right){
            this.nodeId = nodeId;
            this.depth = depth;
            this.feature = feature;
            this.threshold = threshold;
            this.leftNodeId = leftNodeId;
            this.rightNodeId = rightNodeId;
            this.missingNodeId = missingNodeId;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf() {return false;}
        public float eval(float[] featureValues) {return 0F;}
    }

    public static class Leaf implements Node {
        private int nodeId;
        private float leafValue;

        public Leaf(int nodeId, float leafValue) {
            this.nodeId = nodeId;
            this.leafValue = leafValue;
        }

        public boolean isLeaf() {return true;}
        public float eval(float[] featureValues) {return leafValue;}

    }
}
