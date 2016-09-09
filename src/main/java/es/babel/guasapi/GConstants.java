package es.babel.guasapi;

import okhttp3.MediaType;

/**
 * Created by BABEL Sistemas de Información.
 */

public class GConstants {

    static class GErrorMessages {
        static final String URL_NOT_FOUND = "URL_NOT_FOUND";
        static final String TYPE_NOT_FOUND = "TYPE_NOT_FOUND";
        static final String MEDIA_TYPE_NOT_FOUND = "MEDIA_TYPE_NOT_FOUND";
        static final String BODY_AND_FORM_BODY_CONFLICT = "BODY_AND_FORM_BODY_CONFLICT";
    }

    public static final long DEFAULT_SECONDS_TIMEOUT = 30;

    public enum Type {GET, POST, PUT, DELETE}

    public static class GMediaType {
        public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        public static final MediaType XML = MediaType.parse("text/xml; charset=utf-8");
        public static final MediaType PNG = MediaType.parse("image/png");
    }
}
