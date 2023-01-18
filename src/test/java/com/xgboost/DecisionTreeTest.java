package com.xgboost;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecisionTreeTest {
    @Test
    void testScore() throws JsonProcessingException, URISyntaxException, FileNotFoundException {

        ModelParser parser = new ModelParser();

        URL resourceModel = this.getClass().getClassLoader().getResource("models/sample_xgboost_model.json");
        File modelFile = Paths.get(resourceModel.toURI()).toFile();
        DecisionTree myTree = parser.getDecisionTreeFrom(modelFile);

        Feature feature = new Feature();
        URL resourceFeature = this.getClass().getClassLoader().getResource("models/feature.json");
        File featureFile = Paths.get(resourceFeature.toURI()).toFile();
        feature.setFromJson(new JsonParser().unmarshal(featureFile));

        float score = myTree.score(feature);
        System.out.println("score is: " + score);
        assertEquals(-0.7427578F, score, 0.001F);
    }

}