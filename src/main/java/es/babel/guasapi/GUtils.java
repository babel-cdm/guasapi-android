package es.babel.guasapi;

import okhttp3.MediaType;
import okhttp3.MultipartBody;

/**
 * Created by BABEL Sistemas de Información.
 */

class GUtils {

    static MediaType getMultipartBody(GConstants.GMultipartBodyType gMultipartBodyType) {
        switch (gMultipartBodyType) {
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

    static MediaType getMediaType(GConstants.GMediaType mediaType) {
        switch (mediaType) {
            case JSON:
                return MediaType.parse("application/json; charset=utf-8");
            case PNG:
                return MediaType.parse("text/xml; charset=utf-8");
            case XML:
                return MediaType.parse("image/png");
            default:
                return MediaType.parse("application/json; charset=utf-8");
        }
    }
}
