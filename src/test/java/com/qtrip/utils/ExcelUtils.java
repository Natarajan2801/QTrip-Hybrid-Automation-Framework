package com.qtrip.utils;

import com.qtrip.config.EnvironmentManager;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Excel-based data provider utility for data-driven tests.
 *
 * @author Natarajan M
 */
public final class ExcelUtils {

    @DataProvider(name = "getData")
    public static Object[] getData(Method m) throws IOException {
        String testMethodName = m.getName();
        String sheetName = testMethodName;

        // Map method names to Excel sheet names
        switch (testMethodName) {
            case "verifyRegistrationAndLogin":
                sheetName = "TestCase01";
                break;
            case "verifySearchAndFilter":
                sheetName = "TestCase02";
                break;
            case "verifyBookingFlow":
                sheetName = "TestCase03";
                break;
            case "verifyReliabilityFlow":
                sheetName = "TestCase04";
                break;
        }

        String excelPath = EnvironmentManager.get("excel.path", "src/test/resources/DatasetsforQTrip.xlsx");

        try (FileInputStream fs = new FileInputStream(excelPath);
             XSSFWorkbook workbook = new XSSFWorkbook(fs)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException("Sheet '" + sheetName + "' not found in Excel file!");
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