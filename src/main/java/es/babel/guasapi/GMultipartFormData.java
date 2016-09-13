package es.babel.guasapi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BABEL Sistemas de Información.
 */

public class GMultipartFormData {

    List<GFormDataItem> list;

    public GMultipartFormData() {
        this.list = new ArrayList<>();
    }

    public GMultipartFormData add(String key, String value, boolean includedBody) {
        this.list.add(new GFormDataItem(key, value, includedBody));
        return this;
    }

    public List<GFormDataItem> getData() {
        return list;
    }
}
