package com.xgboost;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ModelParserTest {
    @Test
    void parseModel() throws JsonProcessingException {
        ModelParser parser = new ModelParser();
        String modelPath = "models/sample_xgboost_model.json";
        JsonNode arrayNode = parser.unmarshal(modelPath);
        DecisionTree myTree = parser.getDecisionTree(arrayNode);

        assertEquals(myTree.trees.size(), 9);

        DecisionTree.Node firstTree = myTree.trees.get(0);
        assertEquals(firstTree.isLeaf(), true);
        assertEquals(firstTree.eval(null), -0.131447807, Math.ulp(0.01F));

        DecisionTree.Node secondTree = myTree.trees.get(1);
        assertEquals(secondTree.isLeaf(), false);
    }

}

