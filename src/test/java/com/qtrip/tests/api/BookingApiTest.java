package com.qtrip.tests.api;

import com.qtrip.base.BaseApiTest;
import com.qtrip.utils.ApiUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

public class BookingApiTest extends BaseApiTest {

    // Assuming we use the token/userId from AuthApiTest.
    // In a real framework, you might want to re-authenticate or use a ThreadLocal DataContext.
    // For now, we will re-login or make this test depend on AuthApiTest execution order in XML.

    @Test(priority = 3, groups = {"API_Flow"}, dependsOnGroups = "API_Flow")
    public void testNewBooking() {
        // Prepare Payload
        Map<String, Object> payload = new HashMap<>();
        payload.put("userId", AuthApiTest.userId); // Getting static data from AuthTest
        payload.put("name", "Test User");
        payload.put("date", "2025-12-12");
        payload.put("person", "2");
        payload.put("adventureId", "2447910730"); // Ensure this ID exists or fetch dynamically

        // Perform Booking
        Response response = ApiUtils.post("/api/v1/reservations/new", payload);

        // Validate
        Assert.assertTrue(response.jsonPath().getBoolean("success"), "Booking Failed");
    }

    @Test(priority = 4, groups = {"API_Flow"}, dependsOnMethods = "testNewBooking")
    public void testGetReservations() {
        Response response = ApiUtils.get("/api/v1/reservations?id=" + AuthApiTest.userId, AuthApiTest.token);

        Assert.assertEquals(response.getStatusCode(), 200);
        int size = response.jsonPath().getList("$").size();
        Assert.assertTrue(size > 0, "No reservations found for user");
    }

    @Test(priority = 5, groups = {"API_Flow"})
    public void testNegativeBooking() {
        // Sending Invalid Token
        Response response = ApiUtils.get("/api/v1/reservations?id=" + AuthApiTest.userId, "INVALID_TOKEN");
        Assert.assertEquals(response.getStatusCode(), 401, "Negative Test Failed: Expected 401");
    }
}