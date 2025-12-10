package com.qtrip.utils;

import com.qtrip.constants.FrameworkConstants;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

public final class ExcelUtils {

    @DataProvider(name = "getData")
    public static Object[] getData(Method m) throws IOException {
        String testMethodName = m.getName();
        String sheetName = testMethodName;

        // --- MAPPING LOGIC START ---
        // Maps your new Method Names to your existing Excel Sheet Names
        if (testMethodName.equals("verifyRegistrationAndLogin")) {
            sheetName = "TestCase01";
        } else if (testMethodName.equals("verifySearchAndFilter")) {
            sheetName = "TestCase02";
        } else if (testMethodName.equals("verifyBookingFlow")) {
            sheetName = "TestCase03";
        }// Inside the if/else block in ExcelUtils.java
        else if (testMethodName.equals("verifyReliabilityFlow")) {
            sheetName = "TestCase04";
        }

        // --- MAPPING LOGIC END ---

        System.out.println("Looking for Excel Sheet: " + sheetName); // Debug log

        try (FileInputStream fs = new FileInputStream(FrameworkConstants.EXCEL_PATH);
             XSSFWorkbook workbook = new XSSFWorkbook(fs)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);

            // If sheet is missing, throw error instead of silent skip
            if (sheet == null) {
                throw new RuntimeException("CRITICAL ERROR: Sheet '" + sheetName + "' not found in Excel file!");
            }

            int rowNum = sheet.getLastRowNum();
            int colNum = sheet.getRow(0).getLastCellNum();
            List<Map<String, String>> dataList = new ArrayList<>();

            for (int i = 1; i <= rowNum; i++) {
                Map<String, String> map = new HashMap<>();
                for (int j = 0; j < colNum; j++) {
                    String key = sheet.getRow(0).getCell(j).getStringCellValue();
                    String value = sheet.getRow(i).getCell(j).toString();
                    map.put(key, value);
                }
                dataList.add(map);
            }
            return dataList.toArray();
        }
    }
}