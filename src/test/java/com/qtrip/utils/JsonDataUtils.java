package com.qtrip.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * JSON-based data provider utility for test data.
 * Alternative to Excel for complex nested test data.
 *
 * @author Natarajan M
 */
public final class JsonDataUtils {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String JSON_DATA_PATH = "src/test/resources/testdata/";

    private JsonDataUtils() {}

    /**
     * DataProvider that reads from JSON file named after test method.
     */
    @DataProvider(name = "jsonData")
    public static Object[][] getJsonData(Method method) {
        String fileName = method.getName() + ".json";
        return readJsonFile(JSON_DATA_PATH + fileName);
    }

    /**
     * Read JSON file and convert to DataProvider format.
     */
    public static Object[][] readJsonFile(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new RuntimeException("JSON file not found: " + filePath);
            }

            JsonNode rootNode = mapper.readTree(file);

            if (rootNode.isArray()) {
                List<Map<String, Object>> dataList = new ArrayList<>();
                for (JsonNode node : rootNode) {
                    dataList.add(mapper.convertValue(node, Map.class));
                }
                return convertToDataProvider(dataList);
            } else {
                Map<String, Object> data = mapper.convertValue(rootNode, Map.class);
                return new Object[][]{{data}};
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file: " + filePath, e);
        }
    }

    /**
     * Read specific test data from JSON file.
     */
    public static Map<String, Object> getTestData(String fileName, String testCaseId) {
        try {
            File file = new File(JSON_DATA_PATH + fileName);
            JsonNode rootNode = mapper.readTree(file);

            if (rootNode.isArray()) {
                for (JsonNode node : rootNode) {
                    if (node.has("testCaseId") && node.get("testCaseId").asText().equals(testCaseId)) {
                        return mapper.convertValue(node, Map.class);
                    }
                }
            }
            throw new RuntimeException("Test case not found: " + testCaseId);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file: " + fileName, e);
        }
    }

    /**
     * Convert list of maps to DataProvider format.
     */
    private static Object[][] convertToDataProvider(List<Map<String, Object>> dataList) {
        Object[][] result = new Object[dataList.size()][1];
        for (int i = 0; i < dataList.size(); i++) {
            result[i][0] = dataList.get(i);
        }
        return result;
    }

    /**
     * Parse JSON string to Map.
     */
    public static Map<String, Object> parseJson(String jsonString) {
        try {
            return mapper.readValue(jsonString, Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON string", e);
        }
    }

    /**
     * Convert object to JSON string.
     */
    public static String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert object to JSON", e);
        }
    }

    /**
     * Convert object to pretty JSON string.
     */
    public static String toPrettyJson(Object object) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert object to JSON", e);
        }
    }
}

