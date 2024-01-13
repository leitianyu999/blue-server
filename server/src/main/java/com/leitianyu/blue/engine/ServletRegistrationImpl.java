package com.leitianyu.blue.engine;

import com.leitianyu.blue.engine.support.InitParameters;

import javax.servlet.*;
import java.util.*;

/**
 * Servlet注册器
 *
 * @author leitianyu
 * @date 2024/1/13
 */
public class ServletRegistrationImpl implements ServletRegistration.Dynamic {


    final ServletContext servletContext;
    final String name;
    final Servlet servlet;
    final List<String> urlPatterns = new ArrayList<>(4);
    final InitParameters initParameters = new InitParameters();
    // 是否实例化完成
    boolean initialized = false;

    public ServletRegistrationImpl(ServletContext servletContext, String name, Servlet servlet) {
        this.servletContext = servletContext;
        this.name = name;
        this.servlet = servlet;
    }

    public ServletConfig getServletConfig() {
        return new ServletConfig() {
            @Override
            public String getServletName() {
                return ServletRegistrationImpl.this.name;
            }

            @Override
            public ServletContext getServletContext() {
                return ServletRegistrationImpl.this.servletContext;
            }

            @Override
            public String getInitParameter(String name) {
                return ServletRegistrationImpl.this.initParameters.getInitParameter(name);
            }

            @Override
            public Enumeration<String> getInitParameterNames() {
                return ServletRegistrationImpl.this.initParameters.getInitParameterNames();
            }
        };
    }


    @Override
    public String getName() {
        return this.name;
    }


    @Override
    public String getClassName() {
        return servlet.getClass().getName();
    }

    // proxy to InitParameters:

    @Override
    public boolean setInitParameter(String name, String value) {
        checkNotInitialized("setInitParameter");
        return this.initParameters.setInitParameter(name, value);
    }

    @Override
    public String getInitParameter(String name) {
        return this.initParameters.getInitParameter(name);
    }

    @Override
    public Set<String> setInitParameters(Map<String, String> initParameters) {
        checkNotInitialized("setInitParameter");
        return this.initParameters.setInitParameters(initParameters);
    }

    @Override
    public Map<String, String> getInitParameters() {
        return this.initParameters.getInitParameters();
    }

    @Override
    public Set<String> addMapping(String... urlPatterns) {
        checkNotInitialized("addMapping");
        if (urlPatterns == null || urlPatterns.length == 0) {
            throw new IllegalArgumentException("Missing urlPatterns.");
        }
        for (String urlPattern : urlPatterns) {
            this.urlPatterns.add(urlPattern);
        }
        return new HashSet<>();
    }

    @Override
    public Collection<String> getMappings() {
        return this.urlPatterns;
    }

    @Override
    public String getRunAsRole() {
        return null;
    }

    @Override
    public void setAsyncSupported(boolean isAsyncSupported) {
        checkNotInitialized("setAsyncSupported");
        if (isAsyncSupported) {
            throw new UnsupportedOperationException("Async is not supported.");
        }
    }

    @Override
    public void setLoadOnStartup(int loadOnStartup) {
        checkNotInitialized("setLoadOnStartup");
        // do nothing
    }

    @Override
    public Set<String> setServletSecurity(ServletSecurityElement constraint) {
        checkNotInitialized("setServletSecurity");
        throw new UnsupportedOperationException("Servlet security is not supported.");
    }

    @Override
    public void setMultipartConfig(MultipartConfigElement multipartConfig) {
        checkNotInitialized("setMultipartConfig");
        throw new UnsupportedOperationException("Multipart config is not supported.");
    }

    @Override
    public void setRunAsRole(String roleName) {
        checkNotInitialized("setRunAsRole");
        if (roleName != null) {
            throw new UnsupportedOperationException("Role is not supported.");
        }
    }

    private void checkNotInitialized(String name) {
        if (this.initialized) {
            throw new IllegalStateException("Cannot call " + name + " after initialization.");
        }
    }


}
