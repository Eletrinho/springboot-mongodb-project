package com.eletrinho.springmongo.resources.util;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class URL {
    public static String decodeParam(String text) {
        return URLDecoder.decode(text, StandardCharsets.UTF_8);
    }
}
