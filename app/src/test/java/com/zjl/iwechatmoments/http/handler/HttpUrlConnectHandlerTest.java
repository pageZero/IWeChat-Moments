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
        String userUrl = "http://thoughtworks-ios.herokuapp.com/user/jsmith";
        String userResponse = handler.get(userUrl);
        assertTrue(userResponse != null);
        assertTrue(userResponse.startsWith("{"));
        assertTrue(userResponse.endsWith("}"));
        //testing get tweets list
        String tweetUrl = "http://thoughtworks-ios.herokuapp.com/user/jsmith/tweets";
        String tweetResponse = handler.get(tweetUrl);
        assertTrue(tweetResponse != null);
        assertTrue(tweetResponse.startsWith("["));
        assertTrue(tweetResponse.endsWith("]"));
    }

    @Test
    public void post() throws Exception {
    }

}