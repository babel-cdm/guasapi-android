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
public class InternalErrorTest {

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
                .setId(GConstantTest.ID)
                .setType(GConstants.Type.GET)
                .setMediaType(GConstants.GMediaType.JSON)
                .setCallback(callback)
                .doCall();

        verify(callback, times(1)).onError(callbackCaptor.capture());
        checkResponseParams(GConstantTest.responseUrlNotFound);
    }

    @Test
    public void checkParams_errorTypeNotFound() throws Exception {

        new Guasapi().builder()
                .setId(GConstantTest.ID)
                .setUrl(GConstantTest.URL)
                .setMediaType(GConstants.GMediaType.JSON)
                .setCallback(callback)
                .doCall();

        verify(callback, times(1)).onError(callbackCaptor.capture());
        checkResponseParams(GConstantTest.responseTypeNotFound);
    }

    @Test
    public void checkParams_errorMediaTypeNotFound() throws Exception {

        new Guasapi().builder()
                .setId(GConstantTest.ID)
                .setUrl(GConstantTest.URL)
                .setType(GConstants.Type.GET)
                .setCallback(callback)
                .doCall();

        verify(callback, times(1)).onError(callbackCaptor.capture());
        checkResponseParams(GConstantTest.responseMediaTypeNotFound);
    }

    @Test
    public void checkParams_errorBodyConflict() throws Exception {

        new Guasapi().builder()
                .setId(GConstantTest.ID)
                .setUrl(GConstantTest.URL)
                .setType(GConstants.Type.POST)
                .setMediaType(GConstants.GMediaType.JSON)
                .setBody(anyString())
                .setFormBody(GConstantTest.formBody)
                .setCallback(callback)
                .doCall();

        verify(callback, times(1)).onError(callbackCaptor.capture());
        checkResponseParams(GConstantTest.responseBodyConflict);
    }

    @Test
    public void checkParams_errorSecurityConflict() throws Exception {

        new Guasapi().builder()
                .setId(GConstantTest.ID)
                .setUrl(GConstantTest.URL)
                .setType(GConstants.Type.POST)
                .setMediaType(GConstants.GMediaType.JSON)
                .setBody(anyString())
                .setSecurity(new GParamsSecurity()
                        .setTrustAllCert(true)
                        .setCertificates(new GCertificatePinner()
                                .add(GConstantTest.URL, "sha1/123456"))
                )
                .setCallback(callback)
                .doCall();

        verify(callback, times(1)).onError(callbackCaptor.capture());
        checkResponseParams(GConstantTest.responseSecurityConflict);
    }

    private void checkResponseParams(GResponse response) {
        assertEquals(response.getId(), callbackCaptor.getValue().getId());
        assertEquals(response.getCode(), callbackCaptor.getValue().getCode());
        assertEquals(response.getResult(), callbackCaptor.getValue().getResult());
        assertEquals(response.getOriginUrl(), callbackCaptor.getValue().getOriginUrl());
    }
}