package com.qtrip.base;

import com.qtrip.config.EnvironmentManager;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

/**
 * Base class for API tests.
 * Configures REST Assured with base URI from environment config.
 *
 * @author Natarajan M
 */
public class BaseApiTest {

    @BeforeSuite(alwaysRun = true)
    public void setupApi() {
        String apiBaseUrl = EnvironmentManager.get("api.base.url",
            "https://content-qtripdynamic-qa-backend.azurewebsites.net");
        RestAssured.baseURI = apiBaseUrl;
        RestAssured.useRelaxedHTTPSValidation();
    }
}