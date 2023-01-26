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

        URL resourceModel = this.getClass().getClassLoader().getResource("models/sample_model.json");
        assert resourceModel != null;
        File modelFile = Paths.get(resourceModel.toURI()).toFile();
        DecisionTree myTree = parser.getDecisionTreeFrom(modelFile);

        Feature feature = new Feature();
        URL resourceFeature = this.getClass().getClassLoader().getResource("models/sample_feature_1.json");
        assert resourceFeature != null;
        File featureFile = Paths.get(resourceFeature.toURI()).toFile();
        feature.setFromJson(new JsonParser().unmarshal(featureFile));

        float score = myTree.score(feature);
        System.out.println("score is: " + score);
        assertEquals(12F, score, 0.001F);

        resourceFeature = this.getClass().getClassLoader().getResource("models/sample_feature_2.json");
        assert resourceFeature != null;
        featureFile = Paths.get(resourceFeature.toURI()).toFile();
        feature.setFromJson(new JsonParser().unmarshal(featureFile));
        score = myTree.score(feature);
        System.out.println("score is: " + score);
        assertEquals(8F, score, 0.001F);
    }

}