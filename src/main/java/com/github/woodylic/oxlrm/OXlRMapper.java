package com.github.woodylic.oxlrm;

import com.github.woodylic.oxlrm.core.KVMapper;
import com.github.woodylic.oxlrm.core.TableMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.security.InvalidParameterException;
import java.util.List;

public class OXlRMapper {

    private InputStream inputStream;
    private Workbook workbook;

    private OXlRMapper(InputStream inputStream, Workbook workbook) {
        this.inputStream = inputStream;
        this.workbook = workbook;
    }

    public static OXlRMapper open(String xlFilePath) throws IOException {

        String extension = xlFilePath.substring(xlFilePath.lastIndexOf("."));
        if(!extension.equals(".xls") && !extension.equals(".xlsx"))
            throw new InvalidParameterException("Only .xls and .xlsx file are supported.");

        InputStream inputStream = new FileInputStream(xlFilePath);

        if (extension.equals(".xls"))
            return new OXlRMapper(inputStream, new HSSFWorkbook(inputStream));
        else
            return new OXlRMapper(inputStream, new XSSFWorkbook(inputStream));
    }

    public <T> T getKVData(Class type) {
        return new KVMapper().getKVData(type);
    }

    public <T> List<T> leData(Class type) {
        return new TableMapper().getTableData(type);
    }

    public void close() throws Exception {

        if(inputStream == null)
            return;

        try{
            inputStream.close();
        } catch (Exception e) {
            inputStream = null;
            throw e;
        }
    }
}
