package es.babel.guasapi;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by BABEL Sistemas de Información.
 */

public class GUrlParams {
    Map<String, String> map;

    public GUrlParams() {
        this.map = new HashMap<>();
    }

    public GUrlParams add(String key, String value) {
        this.map.put(key, value);
        return this;
    }

    public Map<String, String> getUrlParams() {
        return map;
    }
}
