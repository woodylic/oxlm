package com.github.woodylic.oxlrm.core;

import org.apache.poi.ss.usermodel.Workbook;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class TableMapper {

    private Workbook workbook;
    private ExcelReader excelReader;

    public TableMapper(Workbook workbook){
        this.workbook = workbook;
        this.excelReader = new ExcelReader(workbook);
    }

    public <T> List<T> getTableData(Class entityType) {
        throw new NotImplementedException();
    }
}
