package com.leitianyu.blue.utils;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author leitianyu
 * @date 2024/1/13
 */
public class AnnoUtils {

    public static String getServletName(Class<? extends Servlet> clazz) {
        WebServlet w = clazz.getAnnotation(WebServlet.class);
        if (w != null && !w.name().isEmpty()) {
            return w.name();
        }
        return defaultNameByClass(clazz);
    }

    public static String getFilterName(Class<? extends Filter> clazz) {
        WebFilter w = clazz.getAnnotation(WebFilter.class);
        if (w != null && !w.filterName().isEmpty()) {
            return w.filterName();
        }
        return defaultNameByClass(clazz);
    }

    public static Map<String, String> getServletInitParams(Class<? extends Servlet> clazz) {
        WebServlet w = clazz.getAnnotation(WebServlet.class);
        if (w == null) {
            return new HashMap<>();
        }
        return initParamsToMap(w.initParams());
    }

    public static Map<String, String> getFilterInitParams(Class<? extends Filter> clazz) {
        WebFilter w = clazz.getAnnotation(WebFilter.class);
        if (w == null) {
            return new HashMap<>();
        }
        return initParamsToMap(w.initParams());
    }

    public static String[] getServletUrlPatterns(Class<? extends Servlet> clazz) {
        WebServlet w = clazz.getAnnotation(WebServlet.class);
        if (w == null) {
            return new String[0];
        }
        return arraysToSet(w.value(), w.urlPatterns()).toArray(new String[0]);
    }

    public static String[] getFilterUrlPatterns(Class<? extends Filter> clazz) {
        WebFilter w = clazz.getAnnotation(WebFilter.class);
        if (w == null) {
            return new String[0];
        }
        return arraysToSet(w.value(), w.urlPatterns()).toArray(new String[0]);
    }

    public static EnumSet<DispatcherType> getFilterDispatcherTypes(Class<? extends Filter> clazz) {
        WebFilter w = clazz.getAnnotation(WebFilter.class);
        if (w == null) {
            return EnumSet.of(DispatcherType.REQUEST);
        }
        return EnumSet.copyOf(Arrays.asList(w.dispatcherTypes()));
    }

    private static String defaultNameByClass(Class<?> clazz) {
        String name = clazz.getSimpleName();
        name = Character.toLowerCase(name.charAt(0)) + name.substring(1);
        return name;
    }

    private static Map<String, String> initParamsToMap(WebInitParam[] params) {
        return Arrays.stream(params).collect(Collectors.toMap(p -> p.name(), p -> p.value()));
    }

    private static Set<String> arraysToSet(String[] arr1) {
        Set<String> set = new LinkedHashSet<>();
        for (String s : arr1) {
            set.add(s);
        }
        return set;
    }

    private static Set<String> arraysToSet(String[] arr1, String[] arr2) {
        Set<String> set = arraysToSet(arr1);
        set.addAll(arraysToSet(arr2));
        return set;
    }



}
