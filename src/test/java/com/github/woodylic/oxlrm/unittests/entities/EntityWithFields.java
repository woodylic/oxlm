package com.github.woodylic.oxlrm.unittests.entities;

import com.github.woodylic.oxlrm.annotations.Label;

public class EntityWithFields {

    private String name;

    @Label(name="passwd")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
