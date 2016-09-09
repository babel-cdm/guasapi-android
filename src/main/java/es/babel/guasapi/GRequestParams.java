package es.babel.guasapi;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by BABEL Sistemas de Información.
 */

class GRequestParams extends GInputParams {

    public GRequestParams(Guasapi wsapi) {
        super(wsapi);
    }

    public GRequestParams(GInputParams gInputParams) {
        this.id = gInputParams.id;
        this.mediaType = gInputParams.mediaType;
        this.type = gInputParams.type;
        this.timeOut = gInputParams.timeOut;
        this.url = gInputParams.url;
        this.gUrlParams = gInputParams.gUrlParams;
        this.header = gInputParams.header;
        this.body = gInputParams.body;
        this.callback = gInputParams.callback;
    }

    public String getId() {
        return id;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public GConstants.Type getType() {
        return type;
    }

    public Long getTimeOut() {
        return timeOut;
    }

    public String getUrl() {
        return url;
    }

    public RequestBody getBody() {
        if (body == null) {
            return null;
        } else {
            return (RequestBody.create(this.mediaType, this.body));
        }
    }

    public Map<String, String> getGUrlParams() {
        if (gUrlParams != null) {
            return gUrlParams.getUrlParams();
        } else {
            return null;
        }
    }

    public Map<String, String> getHeader() {
        if (header != null) {
            return header.getHeader();
        } else {
            return null;
        }
    }

    public GCallback getCallback() {
        return callback;
    }
}
