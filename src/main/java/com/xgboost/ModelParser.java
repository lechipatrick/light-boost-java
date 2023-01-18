package com.xgboost;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import java.io.File;
import java.io.FileNotFoundException;

public class ModelParser extends JsonParser {

    public DecisionTree getDecisionTreeFrom(File modelFile) throws JsonProcessingException, FileNotFoundException {
        JsonNode arrayNode = unmarshal(modelFile);
        return getDecisionTreeFrom(arrayNode);
    }


    private DecisionTree getDecisionTreeFrom(JsonNode arrayNode) {
        assert arrayNode.isArray();

        Node[] trees = new Node[arrayNode.size()];
        int i = 0;
        for (JsonNode jsonNode : arrayNode) {
            trees[i] = getNodeFrom(jsonNode);
            i++;
        }
        return new DecisionTree(trees);
    }

    private boolean isBranch(JsonNode jsonNode) {
        JsonNode value = jsonNode.findValue("children");
        return value != null;
    }

    private Node getNodeFrom(JsonNode jsonNode) {
        if (isBranch(jsonNode)) {
            return new Branch(
                    jsonNode.get("nodeid").intValue(),
                    jsonNode.get("depth").intValue(),
                    jsonNode.get("split").textValue(),
                    jsonNode.get("split_condition").floatValue(),
                    jsonNode.get("yes").intValue(),
                    jsonNode.get("no").intValue(),
                    jsonNode.get("missing").intValue(),
                    getNodeFrom(jsonNode.get("children").get(0)),
                    getNodeFrom(jsonNode.get("children").get(1))
            );
        } else {
            return new Leaf(jsonNode.get("nodeid").intValue(), jsonNode.get("leaf").floatValue());
        }
    }

}
