package es.babel.guasapi;

import okhttp3.MediaType;
import okhttp3.MultipartBody;

/**
 * Created by BABEL Sistemas de Información.
 */

class GUtils {

    static MediaType getMultipartBody(GConstants.GMultipartBody gMultipartBody) {
        switch (gMultipartBody) {
            case ALTERNATIVE:
                return MultipartBody.ALTERNATIVE;
            case DIGEST:
                return MultipartBody.DIGEST;
            case FORM:
                return MultipartBody.FORM;
            case MIXED:
                return MultipartBody.MIXED;
            case PARALLEL:
                return MultipartBody.PARALLEL;
            default:
                return null;
        }
    }
}
