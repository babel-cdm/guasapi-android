package es.babel.guasapi;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by BABEL Sistemas de Información.
 */

public class GFormBody {

    Map<String, String> map;

    public GFormBody() {
        this.map = new HashMap<>();
    }

    public GFormBody add(String key, String value) {
        this.map.put(key, value);
        return this;
    }

    Map<String, String> getFormBody() {
        return map;
    }
}
