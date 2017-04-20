package com.github.woodylic.oxlrm;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.InputStream;
import java.util.List;

public class OXlMapper {

    private String xlFilePath;
    private InputStream inputStream;

    public OXlMapper(String xlFilePath) {
        throw new NotImplementedException();
    }

    public <T> T getKVData(Class type) {
        throw new NotImplementedException();
    }

    public <T> List<T> getTableData(Class type) {
        throw new NotImplementedException();
    }

    public void close() {

    }
}
