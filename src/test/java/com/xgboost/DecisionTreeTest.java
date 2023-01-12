package com.xgboost;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecisionTreeTest {
    @Test
    void testScore() throws JsonProcessingException {

        ModelParser parser = new ModelParser();
        String modelPath = "models/sample_xgboost_model.json";
        DecisionTree myTree = parser.getDecisionTreeFrom(modelPath);

        Feature feature = new Feature();
        feature.setFromJson(new JsonParser().unmarshal("models/feature.json"));

        float score = myTree.score(feature);
        System.out.println("score is: " + score);
        assertEquals(-0.7427578F, score, 0.001F);
    }

}