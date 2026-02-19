package com.qtrip.tests.ui;

import com.qtrip.base.BaseTest;
import com.qtrip.pages.*;
import com.qtrip.utils.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * End-to-end test for complete booking flow.
 * Covers: Registration → Login → Search → Book → Verify → Cancel
 */
public class BookingFlowTest extends BaseTest {

    @Test(dataProvider = "getData", dataProviderClass = ExcelUtils.class,
          groups = {"Regression", "Booking"},
          description = "Verify complete booking flow from registration to cancellation")
    public void verifyBookingFlow(Map<String, String> data) {
        HomePage home = new HomePage();
        RegisterPage register = home.navigateToRegister();
        String email = register.registerNewUser(data.get("NewUserName"), data.get("Password"), true);

        LoginPage login = new LoginPage();
        login.performLogin(email, data.get("Password"));

        home.searchCity(data.get("SearchCity"));

        AdventurePage adventure = new AdventurePage();
        adventure.selectAdventure(data.get("AdventureName"));

        AdventureDetailsPage details = new AdventureDetailsPage();
        details.bookAdventure(data.get("GuestName"), data.get("Date"), data.get("count"));
        Assert.assertTrue(details.isBookingSuccessful(), "Booking should be successful");

        HistoryPage history = details.navigateToHistory();
        String txnId = history.getTransactionId();
        Assert.assertNotNull(txnId, "Transaction ID should not be null");

        history.cancelReservation();
        home.logOutUser();
    }
}