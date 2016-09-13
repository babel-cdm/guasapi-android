package es.babel.guasapi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BABEL Sistemas de Información.
 */

public class GMultipartForm {

    List<GFormDataItem> list;
    GConstants.GMultipartBody multipartType = null;

    public GMultipartForm() {
        this.list = new ArrayList<>();
    }

    public GMultipartForm add(String key, String value, boolean includedBody) {
        this.list.add(new GFormDataItem(key, value, includedBody));
        return this;
    }

    public GMultipartForm setType(GConstants.GMultipartBody multipartType) {
        this.multipartType = multipartType;
        return this;
    }

    List<GFormDataItem> getData() {
        return list;
    }

    GConstants.GMultipartBody getType() {
        return multipartType;
    }
}
