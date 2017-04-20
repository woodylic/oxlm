package com.github.woodylic.oxlrm.core;

import com.github.woodylic.oxlrm.utils.ReflectionUtil;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public class KVMapper {

    private Workbook workbook;
    private ExcelReader excelReader;

    public KVMapper(Workbook workbook){
        this.workbook = workbook;
        this.excelReader = new ExcelReader(workbook);
    }

    public <T> T getKVData(Class entityClass) throws IllegalAccessException, InstantiationException {
        //获得entityType的Range annotation或类名，作为数据源的rangeName。
        String rangeName = ReflectionUtil.getRangeName(entityClass);

        //根据rangeName获得数据数组。
        excelReader.getRangeData(rangeName);

        //遍历获得entityType所有field的Field annotation或字段名，作为column header。
        List<String> headers = ReflectionUtil.getLabelsNames(entityClass);

        //把数组赋值给entityType result。
        Object result = entityClass.newInstance();
    }
}
