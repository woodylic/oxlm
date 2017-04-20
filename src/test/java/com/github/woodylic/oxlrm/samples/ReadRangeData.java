package com.github.woodylic.oxlrm.samples;

import com.google.common.io.Resources;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.Locale;

public class ReadRangeData {
    public static void main(String[] args) {
        try {
            // 读取excel文件
            String filePath = Resources.getResource("sample.xlsx").getFile();
            Workbook workbook = openWorkbook(filePath);

            // 从Key-Value结构的Range读取数据
            readKVData(workbook, "KV_RequestInfo");

            // 从Table结构的Range读取数据
            readTableData(workbook, "Tbl_RequestParams");
        } catch (Exception e) {
            System.out.print("Error: " + e.getMessage());
        }
    }

    private static void readKVData(Workbook workbook, String rangeName) {

        String[][] array = readRangeIntoArray(workbook, rangeName);

        System.out.println("key\t\tvalue");
        for (int i = 0; i < array.length; i++) {
            System.out.println(String.format("%s\t\t%s", array[i][0], array[i][1]));
        }
    }

    private static void readTableData(Workbook workbook, String rangeName) {

        String[][] array = readRangeIntoArray(workbook, rangeName);

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private static String[][] readRangeIntoArray(Workbook workbook, String rangeName) {

        // 获取Range包含的所有Cell。
        Name kvRange = workbook.getName(rangeName);
        AreaReference aref = new AreaReference(kvRange.getRefersToFormula());
        CellReference[] crefs = aref.getAllReferencedCells();

        // 获取Range所在的。
        Sheet sheet = workbook.getSheet(kvRange.getSheetName());

        // 通过firstCell和lastCell计算Table的行列数。
        CellReference firstCellRef = aref.getFirstCell();
        CellReference lastCellRef = aref.getLastCell();
        int startRow = firstCellRef.getRow(), endRow = lastCellRef.getRow();
        int startCol = firstCellRef.getCol(), endCol = lastCellRef.getCol();
        int rows = endRow - startRow + 1;
        int cols = endCol - startCol + 1;

        String[][] result = new String[rows][cols];
        DataFormatter formatter = new DataFormatter(Locale.CHINA);

        // 按行、列遍历Table并读取数据。
        for (int i = 0; i < rows; i++) {
            Row row = sheet.getRow(startRow + i);
            for (int j = 0; j < cols; j++) {
                Cell cell = row.getCell(startCol + j);
                result[i][j] = formatter.formatCellValue(cell);
            }
        }

        return result;
    }

    private static Workbook openWorkbook(String filePath) throws IOException, InvalidParameterException {

        String extension = filePath.substring(filePath.lastIndexOf("."));
        InputStream inputStream = new FileInputStream(filePath);

        if (".xls".equals(extension)) {
            return new HSSFWorkbook(inputStream);
        }

        if (".xlsx".equals(extension)) {
            return new XSSFWorkbook(inputStream);
        }

        throw new InvalidParameterException(String.format("filePath %s is invalid.", filePath));
    }
}
