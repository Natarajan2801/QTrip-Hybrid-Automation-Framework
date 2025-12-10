package com.qtrip.tests.ui;

import com.qtrip.base.BaseTest;
import com.qtrip.pages.AdventurePage;
import com.qtrip.pages.HomePage;
import com.qtrip.utils.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class SearchTest extends BaseTest {

    @Test(dataProvider = "getData", dataProviderClass = ExcelUtils.class, groups = {"Search and Filter flow"})
    public void verifySearchAndFilter(Map<String, String> data) throws InterruptedException {
        // 1. Initialize Pages
        HomePage home = new HomePage();
        AdventurePage adventure = new AdventurePage();

        // 2. Search for City
        home.searchCity(data.get("CityName"));

        // 3. Apply Filters
        adventure.selectDurationFilter(data.get("DurationFilter"));
        adventure.selectCategoryFilter(data.get("Category_Filter"));

        // 4. Validate Filtered Count (Convert Excel String to Int)
        int expectedFiltered = Integer.parseInt(data.get("ExpectedFilteredResults").replace(".0", ""));
        int actualFiltered = adventure.getResultCount();

        Assert.assertEquals(actualFiltered, expectedFiltered,
                "Failed: Filtered Result Count mismatch for City: " + data.get("CityName"));

        // 5. Clear Filters
        adventure.clearFilters();

        // 6. Validate Unfiltered Count
        int expectedUnfiltered = Integer.parseInt(data.get("ExpectedUnFilteredResults").replace(".0", ""));
        int actualUnfiltered = adventure.getResultCount();

        Assert.assertEquals(actualUnfiltered, expectedUnfiltered,
                "Failed: Unfiltered Result Count mismatch for City: " + data.get("CityName"));
    }
}