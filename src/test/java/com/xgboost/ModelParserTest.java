package com.xgboost;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ModelParserTest {
    @Test
    void parseModel() throws JsonProcessingException {
        ModelParser parser = new ModelParser();
        String modelPath = "models/sample_xgboost_model.json";
        JsonNode arrayNode = parser.unmarshal(modelPath);
        DecisionTree myTree = parser.getDecisionTreeFrom(arrayNode);

        assertEquals(myTree.trees.length, 9);

        Node firstTree = myTree.trees[0];
        assertTrue(firstTree.isLeaf());
        assertEquals(firstTree.eval(null), -0.131447807, Math.ulp(0.1F));

        Node secondTree = myTree.trees[1];
        assertFalse(secondTree.isLeaf());
        Branch myBranch = (Branch) secondTree;
        assertEquals(myBranch.threshold(), 8.74501896, Math.ulp(0.1F));
    }

}

