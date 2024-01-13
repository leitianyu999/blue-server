package com.leitianyu.blue.engine.support;

import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;

/**
 * 线程安全的map
 * 属性集合
 *
 * @author leitianyu
 * @date 2024/1/13
 */
public class Attributes extends LazyMap<Object> {

    public Attributes(boolean concurrent) {
        super(concurrent);
    }

    public Attributes() {
        this(false);
    }

    public Object getAttribute(String name) {
        Objects.requireNonNull(name, "name is null.");
        return super.get(name);
    }

    public Enumeration<String> getAttributeNames() {
        return super.keyEnumeration();
    }

    public Object setAttribute(String name, Object value) {
        Objects.requireNonNull(name, "name is null.");
        return super.put(name, value);
    }

    public Object removeAttribute(String name) {
        Objects.requireNonNull(name, "name is null.");
        return super.remove(name);
    }

    public Map<String, Object> getAttributes() {
        return super.map();
    }

}
