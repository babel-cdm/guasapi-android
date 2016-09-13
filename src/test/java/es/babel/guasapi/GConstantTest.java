package es.babel.guasapi;

/**
 * Created by BABEL Sistemas de Información.
 */

public class GConstantTest {
     static String ID = "1234567";
     static String URL = "http://desarrollo.babel.es/api/ejemplo";

     static GResponse responseOK = new GResponse();
     static GResponse responseErrorGeneric = new GResponse();
     static GResponse responseUrlNotFound = new GResponse();
     static GResponse responseTypeNotFound = new GResponse();
     static GResponse responseMediaTypeNotFound = new GResponse();
     static GResponse responseBodyConflict = new GResponse();
     static GResponse responseSecurityConflict = new GResponse();
     static GResponse responseMultipartDataIncomplete = new GResponse();

     static GFormBody formBody = new GFormBody();

    static {
        responseOK.setCode(200);
        responseOK.setResult("Response OK");
        responseOK.setOriginUrl(URL);
        responseOK.setId(ID);

        responseErrorGeneric.setCode(400);
        responseErrorGeneric.setResult("generic error");
        responseErrorGeneric.setOriginUrl(URL);
        responseErrorGeneric.setId(ID);

        responseUrlNotFound.setCode(400);
        responseUrlNotFound.setResult("URL_NOT_FOUND");
        responseUrlNotFound.setId(ID);

        responseTypeNotFound.setCode(400);
        responseTypeNotFound.setResult("TYPE_NOT_FOUND");
        responseTypeNotFound.setId(ID);
        responseTypeNotFound.setOriginUrl(URL);

        responseMediaTypeNotFound.setCode(400);
        responseMediaTypeNotFound.setResult("MEDIA_TYPE_NOT_FOUND");
        responseMediaTypeNotFound.setId(ID);
        responseMediaTypeNotFound.setOriginUrl(URL);

        responseBodyConflict.setCode(400);
        responseBodyConflict.setResult("BODY_AND_FORM_BODY_CONFLICT");
        responseBodyConflict.setId(ID);
        responseBodyConflict.setOriginUrl(URL);

        responseSecurityConflict.setCode(400);
        responseSecurityConflict.setResult("SECURITY_CONFIG_CONFLICT");
        responseSecurityConflict.setId(ID);
        responseSecurityConflict.setOriginUrl(URL);

        responseMultipartDataIncomplete.setCode(400);
        responseMultipartDataIncomplete.setResult("MULTIPART_DATA_INCOMPLETE");
        responseMultipartDataIncomplete.setId(ID);
        responseMultipartDataIncomplete.setOriginUrl(URL);

        formBody.add("key1", "value1");
    }
}
