package com.github.woodylic.oxlrm;

import com.github.woodylic.oxlrm.core.ExcelReader;
import com.github.woodylic.oxlrm.core.KVMapper;
import com.github.woodylic.oxlrm.core.TableMapper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.security.InvalidParameterException;
import java.util.List;

public class OXlRMapper {

    private OPCPackage opcPackage;
    private Workbook workbook;

    private KVMapper kvMapper;
    private TableMapper tableMapper;

    private OXlRMapper(OPCPackage opcPackage, Workbook workbook) {
        this.opcPackage = opcPackage;
        this.workbook = workbook;

        kvMapper = new KVMapper(workbook);
        tableMapper = new TableMapper(workbook);
    }

    public static OXlRMapper open(String xlFilePath) throws IOException, InvalidFormatException {
        String extension = xlFilePath.substring(xlFilePath.lastIndexOf("."));
        if(!extension.equals(".xlsx"))
            throw new InvalidParameterException("Only .xlsx file are supported.");

        File file = new File(xlFilePath);
        OPCPackage opcPackage = OPCPackage.open(file);

        return new OXlRMapper(opcPackage, new XSSFWorkbook(opcPackage));
    }

    public <T> T getKVData(Class entityType) {
        return null;
        //return kvMapper.getKVData(entityType);
    }

    public <T> List<T> getTableData(Class entityType) {
        return tableMapper.getTableData(entityType);
    }

    public void close() throws Exception {
        if(opcPackage != null)
            opcPackage.close();
    }
}
