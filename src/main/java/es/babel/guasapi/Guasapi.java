package es.babel.guasapi;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by BABEL Sistemas de Información.
 */

public class Guasapi implements Callback {

    private GParams inputParams;
    private GParamsInternal params;

    public Guasapi() {
        inputParams = new GParams(this);
    }

    public GParams builder() {
        if (this.inputParams == null) {
            inputParams = new GParams(this);
        }
        return this.inputParams;
    }

    void setParams(GParamsInternal params) {
        this.params = params;
    }

    void doCall() {
        String checkParams = checkParams();
        if (checkParams == null) {
            buildClient().newCall(buildRequest()).enqueue(this);
        } else {
            params.getCallback().onError(buildWSResponseFailParams(checkParams));
        }
    }

    ////////////////////////////////////
    // Private functions

    private String checkParams() {

        if (params.getUrl() == null) {
            return GConstants.GErrorMessages.URL_NOT_FOUND;
        }

        if (params.getType() == null) {
            return GConstants.GErrorMessages.TYPE_NOT_FOUND;
        }

        if (params.getMediaType() == null) {
            return GConstants.GErrorMessages.MEDIA_TYPE_NOT_FOUND;
        }

        if (params.getFile() != null && (params.getSimpleBody() != null || params.getSimpleFromBody() != null)) {
            return GConstants.GErrorMessages.BODY_AND_FORM_BODY_CONFLICT;
        }

        if (params.getSimpleBody() != null && (params.getFile() != null || params.getSimpleFromBody() != null)) {
            return GConstants.GErrorMessages.BODY_AND_FORM_BODY_CONFLICT;
        }

        if (params.getSimpleFromBody() != null && (params.getSimpleBody() != null || params.getFile() != null)) {
            return GConstants.GErrorMessages.BODY_AND_FORM_BODY_CONFLICT;
        }

        if (params.getSecurityParams() != null && params.getSecurityParams().trustAllCerts() &&
                params.getSecurityParams().getCertificatePinner() != null &&
                params.getSecurityParams().getCertificatePinner().getList().size() > 0) {
            return GConstants.GErrorMessages.SECURITY_CONFIG_CONFLICT;
        }

        if (params.getMultipartForm() != null && params.getMultipartForm().getType() == null) {
            return GConstants.GErrorMessages.MULTIPART_DATA_INCOMPLETE;
        }

        if (params.getCache() != null && params.getCache().getDirectory() == null) {
            return GConstants.GErrorMessages.CACHE_CONFIG_NOT_FOUND;
        }

        return null;
    }

    OkHttpClient buildClient() {
        OkHttpClient.Builder builderClient = new OkHttpClient.Builder()
                .readTimeout(params.getTimeOut(), TimeUnit.SECONDS)
                .connectTimeout(params.getTimeOut(), TimeUnit.SECONDS)
                .writeTimeout(params.getTimeOut(), TimeUnit.SECONDS);

        if (params.getDebug()) {
            builderClient.addInterceptor(new GInterceptor());
        }

        if (params.getCache() != null) {
            Cache cache = new Cache(params.getCache().getDirectory(), params.getCache().getSize());
            builderClient.cache(cache);
        }

        // Security config
        if (params.getSecurityParams() != null) {
            if (params.getSecurityParams().trustAllCerts()) {
                GSSLSocketFactory gsslSocketFactory = new GSSLSocketFactory(params.getSecurityParams());
                builderClient.sslSocketFactory(gsslSocketFactory.getSSLSocketFactory());
                builderClient.hostnameVerifier(gsslSocketFactory.getVerifierTrusAllCerts());
            }

            if (params.getSecurityParams().getCertificatePinner() != null &&
                    params.getSecurityParams().getCertificatePinner().getList() != null) {

                CertificatePinner.Builder certificatePinnerBuilder = new CertificatePinner.Builder();
                for (Map.Entry<String, String> entry : params.getSecurityParams().getCertificatePinner().getList().entrySet()) {
                    certificatePinnerBuilder.add(entry.getKey(), entry.getValue());
                }

                builderClient.certificatePinner(certificatePinnerBuilder.build());
            }
        }

        return builderClient.build();
    }

    private Request buildRequest() {
        Request.Builder request = new Request.Builder()
                .url(buildUrl());

        switch (params.getType()) {
            case POST:
                request.post(params.getBody());
                break;
            case PUT:
                request.put(params.getBody());
                break;
            case DELETE:
                request.delete(params.getBody());
                break;
            default:
                break;
        }

        if (params.getHeader() != null) {
            for (Map.Entry<String, String> entry : params.getHeader().entrySet()) {
                request.header(entry.getKey(), entry.getValue());
            }
        }

        return request.build();
    }

    private String buildUrl() {
        String url = params.getUrl();

        if (params.getGUrlParams() != null) {
            Iterator it = params.getGUrlParams().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry e = (Map.Entry) it.next();
                if (url.contains("?")) {
                    url += "&" + e.getKey() + "=" + e.getValue();
                } else {
                    url += "?" + e.getKey() + "=" + e.getValue();
                }
            }
        }

        return url;
    }

    private GResponse buildWSResponse(Response response) {
        try {
            GResponse gResponse = new GResponse();
            gResponse.setId(params.getId());
            gResponse.setOriginUrl(params.getUrl());
            gResponse.setCode(response.code());
            gResponse.setResult(response.body().string());
            return gResponse;
        } catch (IOException e) {
            return null;
        }

    }

    private GResponse buildWSResponseFailParams(String error) {
        GResponse gResponse = new GResponse();
        gResponse.setId(params.getId());
        gResponse.setOriginUrl(params.getUrl());
        gResponse.setCode(400);
        gResponse.setResult(error);

        return gResponse;
    }


    @Override
    public void onFailure(Call call, IOException e) {
        final String error = e.getMessage().toString();

        if (params.getResponseInBackground()) {
            params.getCallback().onError(buildWSResponseFailParams(error));
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    params.getCallback().onError(buildWSResponseFailParams(error));
                }
            });
        }
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final GResponse res = buildWSResponse(response);
        if (params.getResponseInBackground()) {
            if (res != null && res.successResponse()) {
                params.getCallback().onSuccess(res);
            } else {
                params.getCallback().onError(res);
            }
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    if (res != null && res.successResponse()) {
                        params.getCallback().onSuccess(res);
                    } else {
                        params.getCallback().onError(res);
                    }
                }
            });
        }
    }
}
