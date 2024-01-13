package com.leitianyu.blue.engine.mapping;

import javax.servlet.Servlet;

/**
 * @author leitianyu
 * @date 2024/1/13
 */
public class ServletMapping extends AbstractMapping{

    public final Servlet servlet;

    public ServletMapping(String urlPattern, Servlet servlet) {
        super(urlPattern);
        this.servlet = servlet;
    }

}
