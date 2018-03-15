package com.zjl.iwechatmoments.http.handler;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zjl on 18-3-15.
 */
public class HttpUrlConnectHandlerTest {
    @Test
    public void get() throws Exception {
        HttpUrlConnectHandler handler = new HttpUrlConnectHandler();
        String url = "http://thoughtworks-ios.herokuapp.com/user/jsmith";
        String response = handler.get(url);
        assertTrue(response != null);
    }

    @Test
    public void post() throws Exception {
    }

}