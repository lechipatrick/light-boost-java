package com.xgboost;

import com.fasterxml.jackson.core.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ModelParserTest {
    @Test
    void testParser() throws JsonProcessingException {
        ModelParser parser = new ModelParser();
        String modelPath = "models/sample_xgboost_model.json";
        DecisionTree myTree = parser.getDecisionTreeFrom(modelPath);
        assertEquals(myTree.trees.length, 9);

        Node secondTree = myTree.trees[1];
        assertFalse(secondTree.isLeaf());
        Branch myBranch = (Branch) secondTree;
        assertEquals(myBranch.threshold(), 8.74501896, Math.ulp(0.1F));
    }

}

