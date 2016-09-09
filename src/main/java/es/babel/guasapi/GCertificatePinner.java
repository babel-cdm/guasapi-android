package es.babel.guasapi;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by BABEL Sistemas de Información.
 */

public class GCertificatePinner {
    Map<String, String> map;

    public GCertificatePinner() {
        this.map = new HashMap<>();
    }

    public GCertificatePinner add(String key, String value) {
        this.map.put(key, value);
        return this;
    }

    public Map<String, String> getList() {
        return map;
    }
}
