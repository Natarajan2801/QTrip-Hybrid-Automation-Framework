package com.qtrip.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.File;
import java.util.Map;

public final class ApiUtils {
    private ApiUtils() {}

    /**
     * Get base request specification with common headers and logging
     */
    private static RequestSpecification getRequestSpec() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .log().all();
    }

    /**
     * GET request with optional authentication token
     */
    public static Response get(String endpoint, String token) {
        RequestSpecification spec = getRequestSpec();
        if (token != null && !token.isEmpty()) {
            spec.header("Authorization", "Bearer " + token);
        }
        return spec.when()
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    /**
     * GET request with query parameters
     */
    public static Response getWithParams(String endpoint, Map<String, String> params, String token) {
        RequestSpecification spec = getRequestSpec();
        if (token != null && !token.isEmpty()) {
            spec.header("Authorization", "Bearer " + token);
        }
        if (params != null && !params.isEmpty()) {
            spec.queryParams(params);
        }
        return spec.when()
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    /**
     * POST request with JSON body
     */
    public static Response post(String endpoint, Object body) {
        return getRequestSpec()
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    /**
     * POST request with token authentication
     */
    public static Response postWithToken(String endpoint, Object body, String token) {
        RequestSpecification spec = getRequestSpec();
        if (token != null && !token.isEmpty()) {
            spec.header("Authorization", "Bearer " + token);
        }
        return spec.body(body)
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    /**
     * PUT request with JSON body
     */
    public static Response put(String endpoint, Object body, String token) {
        RequestSpecification spec = getRequestSpec();
        if (token != null && !token.isEmpty()) {
            spec.header("Authorization", "Bearer " + token);
        }
        return spec.body(body)
                .when()
                .put(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    /**
     * DELETE request with optional token
     */
    public static Response delete(String endpoint, String token) {
        RequestSpecification spec = getRequestSpec();
        if (token != null && !token.isEmpty()) {
            spec.header("Authorization", "Bearer " + token);
        }
        return spec.when()
                .delete(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    /**
     * Get schema file for JSON schema validation
     */
    public static File getSchemaFile(String fileName) {
        return new File("src/test/resources/schemas/" + fileName);
    }

    /**
     * Extract value from response JSON using JSONPath
     */
    public static String getValueFromResponse(Response response, String jsonPath) {
        return response.jsonPath().getString(jsonPath);
    }

    /**
     * Extract object from response JSON
     */
    public static Object getObjectFromResponse(Response response, String jsonPath) {
        return response.jsonPath().get(jsonPath);
    }
}