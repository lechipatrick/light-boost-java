package com.xgboost;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

public class ModelParser {

    public DecisionTree getDecisionTreeFrom(JsonNode arrayNode) {
        assert arrayNode.isArray();

        ArrayList<Node> trees = new ArrayList<>();
        for (JsonNode jsonNode : arrayNode) {
            trees.add(getNodeFrom(jsonNode));
        }
        return new DecisionTree(trees);
    }

    private boolean isBranch(JsonNode jsonNode) {
        JsonNode value = jsonNode.findValue("children");
        return value != null;
    }

    public Node getNodeFrom(JsonNode jsonNode) {
        if (isBranch(jsonNode)) {
            return new Branch(
                    jsonNode.get("nodeid").intValue(),
                    jsonNode.get("depth").intValue(),
                    jsonNode.get("split").toString(),
                    jsonNode.get("split_condition").floatValue(),
                    jsonNode.get("yes").intValue(),
                    jsonNode.get("no").intValue(),
                    jsonNode.get("missing").intValue(),
                    getNodeFrom(jsonNode.get("children").get(0)),
                    getNodeFrom(jsonNode.get("children").get(1))
            );
        }
        else {
            return new Leaf(jsonNode.get("nodeid").intValue(), jsonNode.get("leaf").floatValue());
        }
    }

    public JsonNode unmarshal(String modelFilePath) throws JsonProcessingException {
        String modelString = readModel(modelFilePath);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(modelString);
    }

    private String readModel(String model) {
        InputStream inputStream = getFileAsIOStream(model);
        return getString(inputStream);
    }

    private InputStream getFileAsIOStream(String fileName) {
        InputStream ioStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (ioStream == null) {
            throw new IllegalArgumentException(fileName + " is not found");
        }
        return ioStream;
    }

    private String getString(InputStream inputStream) {
        String text = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        return text;
    }
}
