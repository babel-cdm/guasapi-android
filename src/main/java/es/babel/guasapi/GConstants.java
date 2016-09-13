package es.babel.guasapi;

import okhttp3.MediaType;
import okhttp3.MultipartBody;

/**
 * Created by BABEL Sistemas de Información.
 */

public class GConstants {

    static final String TAG_LIBRARY = "Guasapi";

    static class GErrorMessages {
        static final String URL_NOT_FOUND = "URL_NOT_FOUND";
        static final String TYPE_NOT_FOUND = "TYPE_NOT_FOUND";
        static final String MEDIA_TYPE_NOT_FOUND = "MEDIA_TYPE_NOT_FOUND";
        static final String BODY_AND_FORM_BODY_CONFLICT = "BODY_AND_FORM_BODY_CONFLICT";
        static final String SECURITY_CONFIG_CONFLICT = "SECURITY_CONFIG_CONFLICT";
        static final String MULTIPART_DATA_INCOMPLETE = "MULTIPART_DATA_INCOMPLETE";
    }

    static final long DEFAULT_SECONDS_TIMEOUT = 30;

    public enum Type {GET, POST, PUT, DELETE}

    public enum GMediaType { JSON, XML, PNG }

    public static class GSSLContext {
        public static final String SSL = "SSL";
        public static final String TLS = "TLS";
        public static final String TLSv1 = "TLSv1.1";
        public static final String TLSv2 = "TLSv1.2";
    }

    public enum GMultipartBodyType { ALTERNATIVE, DIGEST, FORM, MIXED, PARALLEL }

}
