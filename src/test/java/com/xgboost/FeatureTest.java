package com.xgboost;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class FeatureTest {
    @Test
    void testFeature() throws JsonProcessingException, URISyntaxException, FileNotFoundException {
        URL resource = this.getClass().getClassLoader().getResource("models/feature.json");
        File featureFile = Paths.get(resource.toURI()).toFile();
        JsonNode jsonNode = new JsonParser().unmarshal(featureFile);
        assert (!jsonNode.isArray());
        Feature feature = new Feature();
        feature.setFromJson(jsonNode);
        assertEquals(feature.getFeature("position_1"), 1.0, 0.0001F);

        feature.setFeature("new_feature", 99.19F);
        assertEquals(feature.getFeature("new_feature"), 99.19F, 0.0001F);

        assertTrue(Float.isNaN(feature.getFeature("bm25_brand")));
        AssertionError exception = assertThrows(AssertionError.class, () -> {
            feature.getFeature("non-existing-feature");
        });
    }
}
