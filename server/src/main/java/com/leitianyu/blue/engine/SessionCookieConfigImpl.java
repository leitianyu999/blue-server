package com.leitianyu.blue.engine;

import com.leitianyu.blue.Config;
import com.leitianyu.blue.engine.support.Attributes;

import javax.servlet.SessionCookieConfig;

/**
 * @author leitianyu
 * @date 2024/1/13
 */
public class SessionCookieConfigImpl implements SessionCookieConfig {


    final Config config;

    int maxAge;
    boolean httpOnly = true;
    boolean secure = false;
    String domain;
    String path;


    public SessionCookieConfigImpl(Config config) {
        this.config = config;
        this.maxAge = config.server.webApp.sessionTimeout * 60;
    }


    @Override
    public void setName(String name) {
        this.config.server.webApp.sessionCookieName = name;
    }

    @Override
    public String getName() {
        return this.config.server.webApp.sessionCookieName;
    }

    @Override
    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String getDomain() {
        return this.domain;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public void setComment(String comment) {
        // ignore
    }

    @Override
    public String getComment() {
        return null;
    }

    @Override
    public void setHttpOnly(boolean httpOnly) {
        this.httpOnly = httpOnly;
    }

    @Override
    public boolean isHttpOnly() {
        return this.httpOnly;
    }

    @Override
    public void setSecure(boolean secure) {
        this.secure = secure;
    }

    @Override
    public boolean isSecure() {
        return this.secure;
    }

    @Override
    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    @Override
    public int getMaxAge() {
        return this.maxAge;
    }


}
