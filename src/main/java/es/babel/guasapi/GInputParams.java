package es.babel.guasapi;

import okhttp3.MediaType;

/**
 * Created by BABEL Sistemas de Información.
 */

public class GInputParams {

    protected Guasapi guasapi;

    protected String id = null;
    protected MediaType mediaType = null;
    protected GConstants.Type type = null;
    protected Long timeOut = GConstants.DEFAULT_SECONDS_TIMEOUT;
    protected String url = null;
    protected GUrlParams gUrlParams = null;
    protected GHeader header = null;
    protected String body = null;
    protected GCallback callback;

    public GInputParams() {
    }

    public GInputParams(Guasapi guasapi) {
        this.guasapi = guasapi;
    }

    public void doCall() {
        guasapi.setParams(new GRequestParams(this));
        guasapi.doCall();
    }

    public GInputParams setId(String id) {
        this.id = id;
        return this;
    }
    public GInputParams setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
        return this;
    }
    public GInputParams setType(GConstants.Type type) {
        this.type = type;
        return this;
    }
    public GInputParams setTimeOut(Long timeOut) {
        this.timeOut = timeOut;
        return this;
    }
    public GInputParams setUrl(String url) {
        this.url = url;
        return this;
    }
    public GInputParams setBody(String body) {
        this.body = body;
        return this;
    }
    public GInputParams setgUrlParams(GUrlParams gUrlParams) {
        this.gUrlParams = gUrlParams;
        return this;
    }
    public GInputParams setHeader(GHeader header) {
        this.header = header;
        return this;
    }
    public GInputParams setCallback(GCallback callback) {
        this.callback = callback;
        return this;
    }

}
