package com.zjl.iwechatmoments.http.loader;

import com.zjl.iwechatmoments.http.handler.HttpLoadHandler;
import com.zjl.iwechatmoments.http.handler.HttpUrlConnectHandler;
import com.zjl.iwechatmoments.http.i.IHttpListener;

import java.util.Map;

/**
 * Created by zjl on 18-3-15.
 */

public class HttpLoader {
    //default load handler
    private HttpLoadHandler loadHandler = new HttpUrlConnectHandler();
    private ThreadManager threadManager;

    public HttpLoader() {
        threadManager = ThreadManager.getInstance();
    }

    public void setLoadHandler(HttpLoadHandler handler) {
        loadHandler = handler;
    }

    public String doGet(String url) {
        return loadHandler.get(url);
    }

    public String doPost(String url, Map<String, String> params) {
        return loadHandler.post(url, params);
    }

    public void doAsyncGet(final String url, final IHttpListener listener) {
        Runnable getTask = new Runnable() {
            @Override
            public void run() {
                String response = loadHandler.get(url);
                if (response != null) {
                    listener.onSuccess(response);
                } else {
                    listener.onError("no response");
                }
            }
        };

    }

    public void doAsyncPost(final String url, final Map<String, String> params, final IHttpListener listener) {
        Runnable postTask = new Runnable() {
            @Override
            public void run() {
                String response = loadHandler.post(url,params);
                if (response != null) {
                    listener.onSuccess(response);
                } else {
                    listener.onError("no response");
                }
            }
        };
        execute(postTask);
    }

    private void execute(Runnable getTask) {
        try {
            threadManager.execute(getTask);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
