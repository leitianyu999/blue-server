package com.leitianyu.blue.connector;

import com.sun.net.httpserver.Headers;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;

/**
 * Request
 *
 * @author leitianyu
 * @date 2024/1/13
 */
public interface HttpExchangeRequest {

    String getRequestMethod();

    URI getRequestURI();

    Headers getRequestHeaders();

    InetSocketAddress getRemoteAddress();

    InetSocketAddress getLocalAddress();

    byte[] getRequestBody() throws IOException;

}
