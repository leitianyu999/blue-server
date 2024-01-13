package com.leitianyu.blue.engine.support;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义包装Map
 *
 * @author leitianyu
 * @date 2024/1/13
 */
public class LazyMap<V> {

    private Map<String, V> map = null;
    // 是否需要线程安全类
    private final boolean concurrent;

    public LazyMap(boolean concurrent) {
        this.concurrent = concurrent;
    }

    protected V get(String name) {
        if (this.map == null) {
            return null;
        }
        return this.map.get(name);
    }

    protected Set<String> keySet() {
        if (this.map == null) {
            return new HashSet<>();
        }
        return this.map.keySet();
    }

    protected Enumeration<String> keyEnumeration() {
        if (this.map == null) {
            return Collections.emptyEnumeration();
        }
        return Collections.enumeration(this.map.keySet());
    }

    protected boolean containsKey(String name) {
        if (this.map == null) {
            return false;
        }
        return this.map.containsKey(name);
    }

    protected V put(String name, V value) {
        if (this.map == null) {
            this.map = concurrent ? new ConcurrentHashMap<>() : new HashMap<>();
        }
        return this.map.put(name, value);
    }

    protected V remove(String name) {
        if (this.map != null) {
            return this.map.remove(name);
        }
        return null;
    }

    protected Map<String, V> map() {
        if (this.map == null) {
            return new HashMap<>();
        }
        return Collections.unmodifiableMap(this.map);
    }

}
