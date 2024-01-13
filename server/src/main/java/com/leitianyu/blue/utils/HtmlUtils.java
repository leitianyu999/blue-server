package com.leitianyu.blue.utils;

/**
 * @author leitianyu
 * @date 2024/1/13
 */
public class HtmlUtils {

    public static String encodeHtml(String s) {
        return s.replace("<", "&lt;").replace(">", "&gt;").replace("&", "&amp;").replace("\"", "&quot;");
    }

}
