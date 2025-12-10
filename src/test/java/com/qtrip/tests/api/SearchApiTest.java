package com.qtrip.tests.api;

import com.qtrip.base.BaseApiTest;
import com.qtrip.utils.ApiUtils;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import java.util.List;

public class SearchApiTest extends BaseApiTest {

    @Test(groups = {"API_Flow"})
    public void testSearchCityAndSchema() {
        // 1. Get Cities
        Response response = ApiUtils.get("/api/v1/cities?q=bengaluru", null);
        Assert.assertEquals(response.getStatusCode(), 200);

        // 2. Validate List Size
        List<String> cities = response.jsonPath().getList("id");
        Assert.assertEquals(cities.size(), 1, "Expected 1 city for 'bengaluru'");

        // 3. JSON Schema Validation
        // Ensure you have tc02schema.json inside src/test/resources/schemas/
        File schema = ApiUtils.getSchemaFile("tc02schema.json");
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(schema));
    }
}