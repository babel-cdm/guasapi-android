package es.babel.guasapi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GuasapiInternalErrorTest {

    private static String ID = "1234567";
    private static String URL = "http://desarrollo.babel.es/api/ejemplo";
    private static GResponse responseUrlNotFound = new GResponse();
    private static GResponse responseTypeNotFound = new GResponse();
    private static GResponse responseMediaTypeNotFound = new GResponse();
    private static GResponse responseBodyConflict = new GResponse();
    private static GResponse responseSecurityConflict = new GResponse();

    private static GFormBody formBody = new GFormBody();

    static {
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

        formBody.add("key1", "value1");
    }

    @Mock
    Guasapi guasapi;

    @Mock
    GCallback callback;

    @Captor
    ArgumentCaptor<GResponse> callbackCaptor;

    @Before
    public void setup() {
    }

    @Test
    public void checkParams_errorUrlNotFound() throws Exception {

        new Guasapi().builder()
                .setId(ID)
                .setType(GConstants.Type.GET)
                .setMediaType(GConstants.GMediaType.JSON)
                .setCallback(callback)
                .doCall();

        verify(callback, times(1)).onError(callbackCaptor.capture());
        checkResponseParams(responseUrlNotFound);
    }

    @Test
    public void checkParams_errorTypeNotFound() throws Exception {

        new Guasapi().builder()
                .setId(ID)
                .setUrl(URL)
                .setMediaType(GConstants.GMediaType.JSON)
                .setCallback(callback)
                .doCall();

        verify(callback, times(1)).onError(callbackCaptor.capture());
        checkResponseParams(responseTypeNotFound);
    }

    @Test
    public void checkParams_errorMediaTypeNotFound() throws Exception {

        new Guasapi().builder()
                .setId(ID)
                .setUrl(URL)
                .setType(GConstants.Type.GET)
                .setCallback(callback)
                .doCall();

        verify(callback, times(1)).onError(callbackCaptor.capture());
        checkResponseParams(responseMediaTypeNotFound);
    }

    @Test
    public void checkParams_errorBodyConflict() throws Exception {

        new Guasapi().builder()
                .setId(ID)
                .setUrl(URL)
                .setType(GConstants.Type.POST)
                .setMediaType(GConstants.GMediaType.JSON)
                .setBody(anyString())
                .setFormBody(formBody)
                .setCallback(callback)
                .doCall();

        verify(callback, times(1)).onError(callbackCaptor.capture());
        checkResponseParams(responseBodyConflict);
    }

    @Test
    public void checkParams_errorSecurityConflict() throws Exception {

        new Guasapi().builder()
                .setId(ID)
                .setUrl(URL)
                .setType(GConstants.Type.POST)
                .setMediaType(GConstants.GMediaType.JSON)
                .setBody(anyString())
                .setSecurity(new GParamsSecurity()
                        .setTrustAllCert(true)
                        .setCertificates(new GCertificatePinner()
                                .add(URL, "sha1/123456"))
                )
                .setCallback(callback)
                .doCall();

        verify(callback, times(1)).onError(callbackCaptor.capture());
        checkResponseParams(responseSecurityConflict);
    }

    private void checkResponseParams(GResponse response) {
        assertEquals(response.getId(), callbackCaptor.getValue().getId());
        assertEquals(response.getCode(), callbackCaptor.getValue().getCode());
        assertEquals(response.getResult(), callbackCaptor.getValue().getResult());
        assertEquals(response.getOriginUrl(), callbackCaptor.getValue().getOriginUrl());
    }
}