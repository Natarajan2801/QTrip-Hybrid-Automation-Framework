package com.qtrip.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.File;

public final class ApiUtils {
    private ApiUtils() {}

    // Generic Request Specification
    private static RequestSpecification getRequestSpec() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .log().all(); // Logs request details to console
    }

    public static Response post(String endpoint, Object body) {
        return getRequestSpec()
                .body(body)
                .when().post(endpoint)
                .then().log().all().extract().response();
    }

    public static Response get(String endpoint, String token) {
        RequestSpecification spec = getRequestSpec();
        if (token != null) {
            spec.header("Authorization", "Bearer " + token);
        }
        return spec.when().get(endpoint)
                .then().log().all().extract().response();
    }

    // For Schema Validation (File based)
    public static File getSchemaFile(String fileName) {
        return new File("src/test/resources/schemas/" + fileName);
    }
}