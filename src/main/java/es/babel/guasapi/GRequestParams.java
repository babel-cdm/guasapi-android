package es.babel.guasapi;

import java.util.Map;

import okhttp3.FormBody;
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
        this.formBody = gInputParams.formBody;
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

    public String getSimpleBody() {
        return body;
    }

    public GFormBody getSimpleFromBody() {
        return formBody;
    }

    public RequestBody getBody() {
        if (body == null) {
            return null;
        } else {
            if (this.formBody != null) {
                FormBody.Builder formBodyBuilder = new FormBody.Builder();
                for (Map.Entry<String, String> entry : this.formBody.getFormBody().entrySet()) {
                    formBodyBuilder.add(entry.getKey(), entry.getValue());
                }

                return formBodyBuilder.build();
            }

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
