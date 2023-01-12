package com.xgboost;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class JsonParser {
    public JsonNode unmarshal(String modelFilePath) throws JsonProcessingException {
        InputStream inputStream = getFileAsIOStream(modelFilePath);
        String modelString = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(modelString);
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
}
