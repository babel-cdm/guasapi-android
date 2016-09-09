package es.babel.guasapi;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by BABEL Sistemas de Información.
 */

public class Guasapi implements Callback {

    private GInputParams inputParams;
    private GRequestParams params;

    public Guasapi() {
        inputParams = new GInputParams(this);
    }

    public GInputParams builder() {
        return this.inputParams;
    }

    void setParams(GRequestParams params) {
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

        if (params.getUrl() == null) return GConstants.GErrorMessages.URL_NOT_FOUND;
        if (params.getType() == null) return GConstants.GErrorMessages.TYPE_NOT_FOUND;
        if (params.getMediaType() == null) return GConstants.GErrorMessages.MEDIA_TYPE_NOT_FOUND;
        if (params.getSimpleBody() != null && params.getSimpleFromBody() != null)
            return GConstants.GErrorMessages.BODY_AND_FORM_BODY_CONFLICT;

        return null;
    }

    private OkHttpClient buildClient() {
        OkHttpClient.Builder builderClient = new OkHttpClient.Builder()
                .readTimeout(params.getTimeOut(), TimeUnit.SECONDS)
                .connectTimeout(params.getTimeOut(), TimeUnit.SECONDS)
                .writeTimeout(params.getTimeOut(), TimeUnit.SECONDS);

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
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                params.getCallback().onError(buildWSResponseFailParams(error));
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final GResponse res = buildWSResponse(response);
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
