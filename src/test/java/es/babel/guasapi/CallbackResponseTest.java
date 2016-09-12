package es.babel.guasapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

/**
 * Created by BABEL Sistemas de Información.
 */
@RunWith(MockitoJUnitRunner.class)
public class CallbackResponseTest {

    @Mock
    Guasapi guasapi;

    @Mock
    GCallback callback;

    @Captor
    ArgumentCaptor<GResponse> callbackCaptor;

    @Test
    public void checkParams_successResponse() throws Exception {
        mockCallbackSuccess();
        guasapi.doCall();
        verify(callback).onSuccess(callbackCaptor.capture());
        assertTrue(callbackCaptor.getValue().successResponse());
    }

    @Test
    public void checkParams_errorResponse() throws Exception {
        mockCallbackError();
        guasapi.doCall();
        verify(callback).onSuccess(callbackCaptor.capture());
        assertFalse(callbackCaptor.getValue().successResponse());
    }

    private void mockCallbackSuccess() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                callback.onSuccess(GConstantTest.responseOK);
                return null;
            }
        }).when(guasapi).doCall();
    }

    private void mockCallbackError() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                callback.onSuccess(GConstantTest.responseErrorGeneric);
                return null;
            }
        }).when(guasapi).doCall();
    }
}