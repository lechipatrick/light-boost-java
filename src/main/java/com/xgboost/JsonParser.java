package com.xgboost;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.stream.Collectors;

public class JsonParser {
    public JsonNode unmarshal(File file) throws JsonProcessingException, FileNotFoundException {
        String text = getString(file);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(text);
    }

    public String getString(File file) throws FileNotFoundException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String text = bufferedReader.lines().collect(Collectors.joining("\n"));
        return text;
    }
}
