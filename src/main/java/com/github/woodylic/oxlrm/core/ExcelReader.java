package com.github.woodylic.oxlrm.core;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;

import java.util.Locale;

public class ExcelReader {

    private Workbook workbook;

    public ExcelReader(Workbook workbook){
        this.workbook = workbook;
    }

    public String[][] getRangeData(String rangeName) {

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

}
