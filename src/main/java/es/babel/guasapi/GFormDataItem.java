package es.babel.guasapi;

/**
 * Created by BABEL Sistemas de Información.
 */

class GFormDataItem {
    String key;
    String value;
    boolean includedBody;

    GFormDataItem(String key, String value, boolean includedBody) {
        this.key = key;
        this.value = value;
        this.includedBody = includedBody;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public boolean isIncludedBody() {
        return includedBody;
    }
}