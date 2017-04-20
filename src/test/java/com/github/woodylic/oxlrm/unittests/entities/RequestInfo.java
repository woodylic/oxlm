package com.github.woodylic.oxlrm.unittests.entities;

import com.github.woodylic.oxlrm.annotations.Range;

@Range(name="KV_RequestInfo")
public class RequestInfo {

    private String url;
    private String method;
    private String requestBody;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }
}
