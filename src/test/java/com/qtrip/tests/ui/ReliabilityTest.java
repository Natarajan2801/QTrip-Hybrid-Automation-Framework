package com.qtrip.tests.ui;

import com.qtrip.base.BaseTest;
import com.qtrip.pages.*;
import com.qtrip.utils.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class ReliabilityTest extends BaseTest {

    @Test(dataProvider = "getData", dataProviderClass = ExcelUtils.class, groups = {"Reliability Flow"})
    public void verifyReliabilityFlow(Map<String, String> data) throws InterruptedException {
        // 1. Login
        HomePage home = new HomePage();
        RegisterPage register = home.navigateToRegister();
        String email = register.registerNewUser(data.get("NewUserName"), data.get("Password"), true);
        LoginPage login = new LoginPage();
        login.performLogin(email, data.get("Password"));

        Assert.assertTrue(home.isUserLoggedIn(), "User failed to login");

        // 2. Perform 3 separate bookings from the datasets
        performBooking(home, data.get("dataset1"));
        performBooking(home, data.get("dataset2"));
        performBooking(home, data.get("dataset3"));

        // 3. Go to History and Verify Transaction Count
        HistoryPage history = home.navigateToHistory(); // You might need to add this method to HomePage
        int currentTransactions = history.getTransactionCount();

        // Assert we have at least 3 transactions
        Assert.assertEquals(currentTransactions, 3, "Mismatch in transaction count!");
        home.logOutUser();
    }

    // Helper method to parse the "City;Adventure;Guest;Date;Count" string
    private void performBooking(HomePage home, String dataset) throws InterruptedException {
        String[] details = dataset.split(";");
        String city = details[0];
        String adventureName = details[1];
        String guestName = details[2];
        String date = details[3];
        String count = details[4];

        home.searchCity(city);

        AdventurePage adventurePage = new AdventurePage();
        adventurePage.selectAdventure(adventureName);

        AdventureDetailsPage detailsPage = new AdventureDetailsPage();
        detailsPage.bookAdventure(guestName, date, count);

        Assert.assertTrue(detailsPage.isBookingSuccessful(), "Booking failed for " + adventureName);

        // Navigate back to Home for the next iteration
        home.navigateToHome();
    }
}