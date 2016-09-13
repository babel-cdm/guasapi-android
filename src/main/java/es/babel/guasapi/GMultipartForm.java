package es.babel.guasapi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BABEL Sistemas de Información.
 */

public class GMultipartForm {

    List<GFormDataItem> list;
    GConstants.GMultipartBodyType multipartType = null;

    public GMultipartForm() {
        this.list = new ArrayList<>();
    }

    public GMultipartForm add(String key, String value, boolean includedBody) {
        this.list.add(new GFormDataItem(key, value, includedBody));
        return this;
    }

    public GMultipartForm setType(GConstants.GMultipartBodyType multipartType) {
        this.multipartType = multipartType;
        return this;
    }

    List<GFormDataItem> getData() {
        return list;
    }

    GConstants.GMultipartBodyType getType() {
        return multipartType;
    }
}
