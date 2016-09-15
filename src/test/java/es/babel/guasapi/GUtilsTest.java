package es.babel.guasapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import okhttp3.MediaType;
import okhttp3.MultipartBody;

import static org.junit.Assert.assertEquals;

/**
 * Created by BABEL Sistemas de Información.
 */

@RunWith(MockitoJUnitRunner.class)
public class GUtilsTest {

    @Test
    public void check_buildMediaType() throws Exception {
        assertEquals(GUtils.getMediaType(GConstants.GMediaType.JSON), MediaType.parse("application/json; charset=utf-8"));
        assertEquals(GUtils.getMediaType(GConstants.GMediaType.PNG), MediaType.parse("image/png"));
        assertEquals(GUtils.getMediaType(GConstants.GMediaType.XML), MediaType.parse("text/xml; charset=utf-8"));
    }

    @Test
    public void check_buildMultipartBodyType() throws Exception {
        assertEquals(GUtils.getMultipartBody(GConstants.GMultipartBodyType.ALTERNATIVE), MultipartBody.ALTERNATIVE);
        assertEquals(GUtils.getMultipartBody(GConstants.GMultipartBodyType.DIGEST), MultipartBody.DIGEST);
        assertEquals(GUtils.getMultipartBody(GConstants.GMultipartBodyType.FORM), MultipartBody.FORM);
        assertEquals(GUtils.getMultipartBody(GConstants.GMultipartBodyType.MIXED), MultipartBody.MIXED);
        assertEquals(GUtils.getMultipartBody(GConstants.GMultipartBodyType.PARALLEL), MultipartBody.PARALLEL);
    }
}
