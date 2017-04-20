package com.github.woodylic.oxlrm.unittests.entities;

import com.github.woodylic.oxlrm.annotations.Range;

@Range(name="Tbl_RequestParams")
public class RequestParam {

    private Integer group;
    private String paramType;
    private String dataType;
    private String name;
    private String value;

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
