package com.leitianyu.blue.connector;

import com.leitianyu.blue.Config;
import com.leitianyu.blue.engine.HttpServletRequestImpl;
import com.leitianyu.blue.engine.HttpServletResponseImpl;
import com.leitianyu.blue.engine.ServletContextImpl;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author leitianyu
 * @date 2024/1/13
 */
public class HttpConnector implements HttpHandler, AutoCloseable {


    final Logger logger = LoggerFactory.getLogger(getClass());

    final Config config;
    final ClassLoader classLoader;
    final ServletContextImpl servletContext;
    final HttpServer httpServer;
    final Duration stopDelay = Duration.ofSeconds(5);

    public HttpConnector(Config config, String webRoot, Executor executor, ClassLoader classLoader, List<Class<?>> autoScannedClasses) throws IOException {
        logger.info("starting jerrymouse http server at {}:{}...", config.server.host, config.server.port);
        this.config = config;
        this.classLoader = classLoader;

        // init servlet context:
        Thread.currentThread().setContextClassLoader(this.classLoader);
        ServletContextImpl ctx = new ServletContextImpl(classLoader, config, webRoot);
        ctx.initialize(autoScannedClasses);
        this.servletContext = ctx;
        Thread.currentThread().setContextClassLoader(null);

        // start http server:
        this.httpServer = HttpServer.create(new InetSocketAddress(config.server.host, config.server.port), config.server.backlog);
        this.httpServer.createContext("/",this);
        this.httpServer.setExecutor(executor);
        this.httpServer.start();
        logger.info("jerrymouse http server started at {}:{}...", config.server.host, config.server.port);
    }

    @Override
    public void close() {
        this.servletContext.destroy();
        this.httpServer.stop((int) this.stopDelay.getSeconds());
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        HttpExchangeAdapter adapter = new HttpExchangeAdapter(exchange);
        HttpServletResponseImpl response = new HttpServletResponseImpl(this.config, adapter);
        HttpServletRequestImpl request = new HttpServletRequestImpl(this.config, this.servletContext, adapter, response);
        // process:
        try {
            Thread.currentThread().setContextClassLoader(this.classLoader);
            this.servletContext.process(request, response);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            Thread.currentThread().setContextClassLoader(null);
            response.cleanup();
        }
    }


}
