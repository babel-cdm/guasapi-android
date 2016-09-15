package es.babel.guasapi;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by BABEL Sistemas de Información.
 */

@RunWith(MockitoJUnitRunner.class)
public class GDoCall {

    MockWebServer server;

    @Before
    public void setup() throws IOException {
        server = new MockWebServer();
    }

    @Test
    public void test_doCallResponseOK() throws Exception {

        server.enqueue(new MockResponse().setResponseCode(200).setBody(""));
        server.start();

        final CountDownLatch signal = new CountDownLatch(1);

        new Guasapi().builder()
                .setDebug(true)
                .setId("1234567")
                .setUrl(server.getUrl("/").toString())
                .setType(GConstants.Type.POST)
                .setBody("")
                .setMediaType(GConstants.GMediaType.JSON)
                .setHeader(GConstantTest.gHeader)
                .setSecurity(GConstantTest.gParamSecurityTrustAllCert)
                .setCache(GConstantTest.gCache)
                .setCallback(new GCallback() {
                    @Override
                    public void onError(GResponse response) {
                        assertFalse(response.successResponse());
                        signal.countDown();// notify the count down latch
                    }

                    @Override
                    public void onSuccess(GResponse response) {
                        assertTrue(response.successResponse());
                        signal.countDown();// notify the count down latch
                    }
                })
                .setResponseInBackground(true)
                .doCall();

        signal.await();
    }

    @Test
    public void test_doCallResponseError() throws Exception {

        server.enqueue(new MockResponse().setResponseCode(400).setBody(""));
        server.start();

        final CountDownLatch signal = new CountDownLatch(1);

        new Guasapi().builder()
                .setId("1234567")
                .setUrl(server.getUrl("/").toString())
                .setType(GConstants.Type.PUT)
                .setUrlParams(GConstantTest.gUrlParams)
                .setBody("")
                .setMediaType(GConstants.GMediaType.JSON)
                .setHeader(GConstantTest.gHeader)
                .setSecurity(GConstantTest.gParamSecurityWhitCerts)
                .setCache(GConstantTest.gCache)
                .setCallback(new GCallback() {
                    @Override
                    public void onError(GResponse response) {
                        assertFalse(response.successResponse());
                        signal.countDown();// notify the count down latch
                    }

                    @Override
                    public void onSuccess(GResponse response) {
                        assertTrue(response.successResponse());
                        signal.countDown();// notify the count down latch
                    }
                })
                .setResponseInBackground(true)
                .doCall();

        signal.await();
    }

    @After
    public void finish() throws Exception {
        server.shutdown();
    }
}
