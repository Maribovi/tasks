import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к файлу values.json:");
        String valuesFilePath = scanner.nextLine();
        System.out.println("Введите путь к файлу tests.json:");
        String testsFilePath = scanner.nextLine();
        System.out.println("Введите путь к формируемому файлу report.json:");
        String reportFilePath = scanner.nextLine();
        scanner.close();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode valuesNode = mapper.readTree(new File(valuesFilePath));
            Map<Integer, String> valuesMap = new HashMap<>();

            for (JsonNode valueNode : valuesNode.get("values")) {
                int id = valueNode.get("id").asInt();
                String value = valueNode.get("value").asText();
                valuesMap.put(id, value);
            }

            JsonNode testsNode = mapper.readTree(new File(testsFilePath));

            JsonNode reportNode = fillValues(testsNode, valuesMap);

            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(reportFilePath), reportNode);
            System.out.println("Report generated successfully at " + reportFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JsonNode fillValues(JsonNode testsNode, Map<Integer, String> valuesMap) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode reportNode = mapper.createObjectNode();
        reportNode.set("tests", mapper.createArrayNode());

        for (JsonNode testNode : testsNode.get("tests")) {
            ObjectNode testReportNode = (ObjectNode) testNode.deepCopy();
            int id = testReportNode.get("id").asInt();

            if (valuesMap.containsKey(id)) {
                testReportNode.put("value", valuesMap.get(id));
            }

            if (testNode.has("values")) {
                testReportNode.set("values", fillNestedValues(testNode.get("values"), valuesMap));
            }

            reportNode.withArray("tests").add(testReportNode);
        }

        return reportNode;
    }

    private static JsonNode fillNestedValues(JsonNode valuesNode, Map<Integer, String> valuesMap) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode nestedValuesNode = mapper.createObjectNode();
        nestedValuesNode.set("values", mapper.createArrayNode());

        for (JsonNode valueNode : valuesNode) {
            ObjectNode valueReportNode = (ObjectNode) valueNode.deepCopy();
            int id = valueReportNode.get("id").asInt();

            if (valuesMap.containsKey(id)) {
                valueReportNode.put("value", valuesMap.get(id));
            }
            
            if (valueNode.has("values")) {
                valueReportNode.set("values", fillNestedValues(valueNode.get("values"), valuesMap));
            }

            nestedValuesNode.withArray("values").add(valueReportNode);
        }

        return nestedValuesNode.get("values");
    }
}
