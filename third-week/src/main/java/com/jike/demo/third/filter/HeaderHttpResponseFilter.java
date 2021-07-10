package com.jike.demo.third.filter;

import io.netty.handler.codec.http.FullHttpResponse;

public class HeaderHttpResponseFilter {

    public void filter(FullHttpResponse response) {
        response.headers().set("yidaStatus", true);
    }
}
