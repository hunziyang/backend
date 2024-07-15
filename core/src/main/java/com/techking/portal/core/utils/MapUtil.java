package com.techking.portal.core.utils;

import java.util.HashMap;
import java.util.Map;

public class MapUtil {

    public static Map<String, String> getErrMap(String message) {
        return new HashMap<String, String>() {{
            put("err", message);
        }};
    }
}
