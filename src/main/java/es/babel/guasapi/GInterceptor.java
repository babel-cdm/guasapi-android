package es.babel.guasapi;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static es.babel.guasapi.GConstants.TAG_LIBRARY;

/**
 * Created by BABEL Sistemas de Información.
 */

class GInterceptor implements Interceptor {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        Log.i(TAG_LIBRARY, String.format("Sending request %s%n%s%n%s%n%s",
                request.url(), request.method(), chain.connection(), request.headers()));

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        Log.i(TAG_LIBRARY, String.format("Received response for %s in %.1fms%n%s%n%sMessage: %s",
                response.request().url(), (t2 - t1) / 1e6d, response.protocol().toString(), response.headers(), response.message()));

        return response;
    }
}