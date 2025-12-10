package com.qtrip.pages;

import com.qtrip.base.BasePage;
import com.qtrip.enums.WaitStrategy;
import org.openqa.selenium.By;

public class HistoryPage extends BasePage {
    private final By btnCancel = By.className("cancel-button");
    private final By txtTransactionId = By.xpath("//th[@scope='row']");
    private final By transactionRows = By.xpath("//tbody/tr");

    public String getTransactionId() {
        return getText(txtTransactionId, WaitStrategy.VISIBLE);
    }

    public void cancelReservation() {
        click(btnCancel, WaitStrategy.CLICKABLE, "Cancel Button");
    }

    public int getTransactionCount() {
        // Wait for rows to be visible if any exist
        try {
            return getSize(transactionRows, WaitStrategy.VISIBLE);
        } catch (Exception e) {
            return 0;
        }
    }
}