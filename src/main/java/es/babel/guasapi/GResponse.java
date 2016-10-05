package es.babel.guasapi;

import okhttp3.ResponseBody;
import okio.BufferedSource;

/**
 * Created by BABEL Sistemas de Información.
 */

public class GResponse {

    String id;
    int code;
    String result;
    String originUrl;
    ResponseBody responseBody;

    public ResponseBody getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    public String getId() {
        return id;
    }

    void setId(String id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    void setResult(String result) {
        this.result = result;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    public boolean successResponse() {
        return (code >= 200 && code < 300);
    }
}
