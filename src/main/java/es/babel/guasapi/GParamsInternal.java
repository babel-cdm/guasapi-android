package es.babel.guasapi;

import java.io.File;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by BABEL Sistemas de Información.
 */

class GParamsInternal extends GParams {

    public GParamsInternal(Guasapi wsapi) {
        super(wsapi);
    }

    public GParamsInternal(GParams gParams) {
        this.id = gParams.id;
        this.mediaType = gParams.mediaType;
        this.type = gParams.type;
        this.timeOut = gParams.timeOut;
        this.url = gParams.url;
        this.gUrlParams = gParams.gUrlParams;
        this.header = gParams.header;
        this.body = gParams.body;
        this.callback = gParams.callback;
        this.formBody = gParams.formBody;
        this.securityParams = new GParamsInternalSecurity(gParams.securityInputParams);
        this.debug = gParams.debug;
        this.multipartForm = gParams.multipartForm;
        this.file = gParams.file;
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

        RequestBody requestBody = null;
        if (this.body != null) {
            requestBody = RequestBody.create(this.mediaType, this.body);
        } else if (this.file != null) {
            requestBody = RequestBody.create(this.mediaType, this.file);
        } else if (this.formBody != null) {
            FormBody.Builder formBodyBuilder = new FormBody.Builder();
            for (Map.Entry<String, String> entry : this.formBody.getFormBody().entrySet()) {
                formBodyBuilder.add(entry.getKey(), entry.getValue());
            }

            requestBody = formBodyBuilder.build();
        }

        if (this.multipartForm != null) {
            return getRequestBodyMultipartForm(requestBody);
        }

        return requestBody;
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

    public GParamsInternalSecurity getSecurityParams() {
        return securityParams;
    }

    public boolean getDebug() {
        return debug;
    }

    public GMultipartForm getMultipartForm() {
        return multipartForm;
    }

    public File getFile() {
        return file;
    }

    ////////////////////////////////
    // Private functions

    private RequestBody getRequestBodyMultipartForm(RequestBody body) {
        MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder()
                .setType(GUtils.getMultipartBody(multipartForm.getType()));

        for (GFormDataItem item : multipartForm.getData()) {
            if (item.isIncludedBody()) {
                multipartBodyBuilder.addFormDataPart(item.getKey(), item.getValue(), body);
            } else {
                multipartBodyBuilder.addFormDataPart(item.getKey(), item.getValue());
            }
        }

        return multipartBodyBuilder.build();
    }
}
