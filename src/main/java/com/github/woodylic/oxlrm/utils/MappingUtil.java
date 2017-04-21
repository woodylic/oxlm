package com.github.woodylic.oxlrm.utils;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Map;

public class MappingUtil {

    private MappingUtil(){}

    public static <T> T getKVData(Class entityClass, Map<String, String> fieldAndLabelMap, String[][] sourceData) {
        throw new NotImplementedException();
    }

    public static <T> List<T> getTableData(Class entityClass, Map<String, String> fieldAndLabelMap, String[][] sourceData) {
        throw new NotImplementedException();
    }
}
