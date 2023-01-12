package com.xgboost;

public record Branch(int nodeId, int depth, String featureName, float threshold, int leftNodeId, int rightNodeId,
                     int missingNodeId, Node left,
                     Node right) implements Node {

    public boolean isLeaf() {
        return false;
    }

    public float eval(Feature feature) {
        Node node = this;
        if (!this.isLeaf()) {
            assert node instanceof Branch;
            Branch branch = (Branch) this;
            if (feature.getFeature(featureName) <= threshold) {
                return branch.left.eval(feature);
            } else if (feature.getFeature(featureName) > threshold) {
                return branch.right.eval(feature);
            } else {
                // reaching here implies that feature value is Float.NaN
                if (missingNodeId == leftNodeId) {
                    return branch.left.eval(feature);
                } else {
                    return branch.right.eval(feature);
                }
            }
        }
        else {
            assert node instanceof Leaf;
            Leaf leaf = (Leaf) node;
            return leaf.eval(feature);
        }
    }
}
