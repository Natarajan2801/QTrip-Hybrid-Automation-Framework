package com.qtrip.base;

import com.qtrip.config.ConfigLoader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class BaseApiTest {
    @BeforeSuite(alwaysRun = true)
    public void setupApi() {
        // Use the URL from config.properties, but strip the frontend part if needed
        // Or hardcode the backend URL if it's different (common in QTrip)
        RestAssured.baseURI = "https://content-qtripdynamic-qa-backend.azurewebsites.net";
        RestAssured.useRelaxedHTTPSValidation();
    }
}