package com.xgboost;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.*;

public class Feature {
    private HashMap<String, Float> features;

    public Feature() {
        features = new HashMap<>();
    }

    public void setFromJson(JsonNode jsonNode) {
        Iterator<String> iterator = jsonNode.fieldNames();
        iterator.forEachRemaining(e -> setFeature(e, getFloat(jsonNode.get(e))));
    }

    private float getFloat(JsonNode jsonNode) {
        if (jsonNode.isNull()) {
            return Float.NaN;
        }
        return jsonNode.floatValue();
    }

    public void setFeature(String featureName, Float featureValue) {
        features.put(featureName, featureValue);
    }

    public Float getFeature(String featureName) {
        assert features.containsKey(featureName);
        return features.get(featureName);
    }
}
