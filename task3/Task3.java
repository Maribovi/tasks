package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Task3 {
    public static void main(String[] args) {

        if (args.length != 3) {
            System.out.println("Необходимо 3 аргумента");
            return;
        }

        String values = args[0];
        String tests = args[1];
        String report = args[2];

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode valuesNode = objectMapper.readTree(new File(values));

            Map<Integer, String> valuesMap = new HashMap<>();
            for (JsonNode valueNode : valuesNode) {
                int id = valueNode.get("id").asInt();
                String value = valueNode.get("value").asText();
                valuesMap.put(id, value);
            }

            JsonNode testsNode = objectMapper.readTree(new File(tests));

            ObjectNode reportNode = objectMapper.createObjectNode();
            fillReport(testsNode, reportNode, valuesMap);

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(report), reportNode);
            System.out.println("Данные успешно объединены и сохранены в " + report);

        } catch (IOException e) {
            System.out.println("Ошибка при обработке файлов: " + e.getMessage());
        }
    }

    private static void fillReport(JsonNode testsNode, ObjectNode reportNode, Map<Integer, String> valuesMap) {
        ArrayNode reportTestsArray = reportNode.putArray("tests");

        for (JsonNode testNode : testsNode.get("tests")) {
            ObjectNode reportTestNode = reportTestsArray.addObject();
            reportTestNode.put("id", testNode.get("id").asInt());
            reportTestNode.put("title", testNode.get("title").asText());

            int testId = testNode.get("id").asInt();
            if (valuesMap.containsKey(testId)) {
                reportTestNode.put("value", valuesMap.get(testId));
            }

            if (testNode.has("subtests")) {
                fillReport(testNode, reportTestNode, valuesMap);
            }
        }
    }
}
