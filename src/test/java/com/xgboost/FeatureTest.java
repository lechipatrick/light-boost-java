package com.xgboost;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FeatureTest {
    @Test
    void testFeature() throws JsonProcessingException {
        JsonNode jsonNode = new JsonParser().unmarshal("models/feature.json");
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
