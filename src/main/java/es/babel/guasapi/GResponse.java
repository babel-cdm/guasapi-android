package es.babel.guasapi;

/**
 * Created by BABEL Sistemas de Información.
 */

public class GResponse {

    String id;
    int code;
    String result;
    String originUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    public boolean successResponse() {
        return (code >= 200 && code < 300);
    }
}
