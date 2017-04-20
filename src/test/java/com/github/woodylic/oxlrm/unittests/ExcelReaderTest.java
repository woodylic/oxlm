package com.github.woodylic.oxlrm.unittests;


import com.github.woodylic.oxlrm.core.ExcelReader;
import com.google.common.io.Resources;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class ExcelReaderTest {

    @Test
    public void testGetRangeData() throws IOException {

        String filePath = Resources.getResource("sample.xlsx").getFile();
        Workbook workbook = openWorkbook(filePath);

        String[][] array = new ExcelReader(workbook).getRangeData("KV_RequestInfo");
        assertEquals(3, array.length);
        assertEquals(2, array[0].length);
    }

    private Workbook openWorkbook(String xlFilePath) throws IOException {

        String extension = xlFilePath.substring(xlFilePath.lastIndexOf("."));
        InputStream inputStream = new FileInputStream(xlFilePath);

        if (extension.equals(".xls"))
            return new HSSFWorkbook(inputStream);
        else
            return new XSSFWorkbook(inputStream);
    }

}
