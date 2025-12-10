package com.qtrip.tests.api;

import com.qtrip.base.BaseApiTest;
import com.qtrip.utils.ApiUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AuthApiTest extends BaseApiTest {

    public static String token;
    public static String userId;
    public static String email;
    public static String password = "password123";

    @Test(priority = 1, groups = {"API_Flow"})
    public void testRegistration() {
        email = "test" + UUID.randomUUID() + "@test.com";

        Map<String, String> payload = new HashMap<>();
        payload.put("email", email);
        payload.put("password", password);
        payload.put("confirmpassword", password);

        Response response = ApiUtils.post("/api/v1/register", payload);
        Assert.assertEquals(response.getStatusCode(), 201, "Registration Failed");
    }

    @Test(priority = 2, groups = {"API_Flow"}, dependsOnMethods = "testRegistration")
    public void testLogin() {
        Map<String, String> payload = new HashMap<>();
        payload.put("email", email);
        payload.put("password", password);

        Response response = ApiUtils.post("/api/v1/login", payload);
        Assert.assertEquals(response.getStatusCode(), 201, "Login Failed");

        JsonPath json = response.jsonPath();
        Assert.assertTrue(json.getBoolean("success"));

        token = json.getString("data.token");
        userId = json.getString("data.id");

        Assert.assertNotNull(token, "Token is null");
    }
}