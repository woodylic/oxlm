package com.github.woodylic.oxlrm.unittests.core;


import com.github.woodylic.oxlrm.OXlRMapper;
import com.github.woodylic.oxlrm.core.ExcelReader;
import com.google.common.io.Resources;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class ExcelReaderTest {

    @Test
    public void testGetRangeData() throws IOException, InvalidFormatException {

        String xlFilePath = Resources.getResource("sample.xlsx").getFile();
        File file = new File(xlFilePath);
        OPCPackage opcPackage = OPCPackage.open(file);
        Workbook workbook = new XSSFWorkbook(opcPackage);

        try {
            String[][] array = new ExcelReader(workbook).getRangeData("KV_RequestInfo");
            assertEquals(3, array.length);
            assertEquals(2, array[0].length);
        } finally {
            if(opcPackage != null)
                opcPackage.close();
        }

    }

    private Workbook openWorkbook(String xlFilePath) throws IOException {

        String extension = xlFilePath.substring(xlFilePath.lastIndexOf("."));
        InputStream inputStream = new FileInputStream(xlFilePath);

        return new XSSFWorkbook(inputStream);
    }

}
